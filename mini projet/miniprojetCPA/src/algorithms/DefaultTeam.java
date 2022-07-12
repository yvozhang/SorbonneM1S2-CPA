package algorithms;


import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

import javax.sound.sampled.LineListener;


public class DefaultTeam {
	
	// Version sans budget
	public Tree2D calculSteiner(ArrayList<Point> points, int edgeThreshold, ArrayList<Point> hitPoints) {
		
		//construire K, calculer le plus court chemin entre tous les points deux par deux en utilisant l'algorithme Floyd-Warshall
		int[][] paths = calculShortestPaths(points,edgeThreshold);
		
		//construire T0, un MSTree qui est constitue par les hitpoints
		ArrayList<Edge> hitPointsEdges = Prim.prim(hitPoints);
		
		//Obtenir tous les points qui sont dans les chemin entre tous les deux points de hitpoints
		HashSet<Point>tousPoints=new HashSet<Point>();
		
		for (Edge e: hitPointsEdges){
			int i = points.indexOf(e.p);
			int j = points.indexOf(e.q);
			ArrayList<Integer> pointsI2J = getPath(i, j, paths);
			for (Integer k: pointsI2J){
				tousPoints.add(points.get(k));
			}
		}
		ArrayList<Point> selectedPoints=new ArrayList<Point>(tousPoints);
		
		//construire un nouveau MSTree qui contient tous les hitpoints qui sont mise à jour
		ArrayList<Edge> prim = Prim.prim(selectedPoints);

		return Edge.edgesToTree(prim,prim.get(0).p);
	}
	
	
	
	
	//Sélectionner l'arête la plus longue associée aux points d'extrémité
	//The endPoint has only one edge
	public Edge getEdgeMax(HashMap<Point, ArrayList<Edge>> pointEdges){
		Iterator<Map.Entry<Point, ArrayList<Edge>>>iterator=pointEdges.entrySet().iterator();
		double maxLongeur=0.0;
		Edge maxEdge=null;
		while(iterator.hasNext()) {
			Map.Entry<Point, ArrayList<Edge>> entry=(Map.Entry<Point, ArrayList<Edge>>)iterator.next();
			ArrayList<Edge> list=entry.getValue();
			if(list.size()==1) {
				Edge e=list.get(0);
				double distance=e.distance();
				if (distance>maxLongeur) {
					 maxEdge=e;
					 maxLongeur=distance;
				}
			}	
		}
		return maxEdge;
	}
	

	
	public Tree2D calculSteinerBudget(ArrayList<Point> points, int edgeThreshold, ArrayList<Point> hitPoints) {
		
		 double buget=1664.0;
		    //calculer d'abord le graphe sans buget,même principle qu'avant
		  	int[][] paths=calculShortestPaths(points,edgeThreshold);
			ArrayList <Edge> hitPointsEdges = Prim.prim(hitPoints);
			HashSet<Point>tousPoints=new HashSet<Point>();
			
			for (Edge e: hitPointsEdges){
				int i = points.indexOf(e.p);
				int j = points.indexOf(e.q);
				ArrayList<Integer> pointsI2J = getPath(i, j, paths);
				for (Integer k: pointsI2J){
					tousPoints.add(points.get(k));
				}
			}
			ArrayList<Point> selectedPointSansBuget=new ArrayList<Point>(tousPoints);
		    ArrayList<Edge> GraphEdges = Prim.prim(selectedPointSansBuget);
		    
		    
		    HashMap<Point,  ArrayList<Edge>> pointEdges=new HashMap<Point,  ArrayList<Edge>>();
		     
		     //regouper les arêtes de la graphe afin  trouver facilement les points d'extrémité
		     //pour chaque point ,stoker tous les arêtes qu'il lié
		     for(Edge e:GraphEdges ) {
		    	
		    	 if(!pointEdges.containsKey(e.p)) {
		    		  ArrayList<Edge> edgesp=new  ArrayList<Edge>();
		    		 pointEdges.put(e.p, edgesp);
		    	 }
		    	 
		    	 if(!pointEdges.containsKey(e.q)) {
		    		  ArrayList<Edge> edgesq=new  ArrayList<Edge>();
		    		 pointEdges.put(e.q, edgesq);	
		    	 }
		    	 
		    	 pointEdges.get(e.p).add(e);
		    	 pointEdges.get(e.q).add(e);
		    	 
		     }
		     //calculer la distanceTotal de graphe actuel
		     double distanceTotal=0.0;
		     for(Edge e: GraphEdges) {
		    	 distanceTotal+=e.distance();
		     }
		     
		     // tanque que la distance total est plus grand que le budget,
		     //choisir  l'arête la plus longue associée aux points d'extrémitéet et enlevez-la
		     
		     while(distanceTotal>buget) { 
		    	Edge edgeMax=getEdgeMax(pointEdges);
		    	distanceTotal-=edgeMax.distance();
		    	GraphEdges.remove(edgeMax);
		    	pointEdges.get(edgeMax.p).remove(edgeMax);
		    	pointEdges.get(edgeMax.q).remove(edgeMax);
		    	
		     }
		     
		     return Edge.edgesToTree(GraphEdges, GraphEdges.get(0).p);
		     
	}
	
	
	//Floyd-Warshall
	public int[][] calculShortestPaths(ArrayList<Point> points, int edgeThreshold) {
		  
	    int[][] paths=new int[points.size()][points.size()];
	    double[][] distance=new double[points.size()][points.size()];
	
	    for (int i=0;i<paths.length;i++) for (int j=0;j<paths.length;j++) paths[i][j]=j;
	    //get graph
	    for (int i=0;i<paths.length;i++) {
	    	for (int j=0;j<paths.length;j++) {
	    		if(points.get(i).distance(points.get(j))<=edgeThreshold) {
	    			distance[i][j] = points.get(i).distance(points.get(j));
	    		}else {
	    			distance[i][j] = Double.MAX_VALUE;
	    		}
	    		
	    	}
	    }
	   
	    for(int k=0; k<paths.length;k++) {
	    	for(int i=0; i<paths.length;i++) {
	    		for(int j=0; j<paths.length;j++) {
	    			if(distance[i][k]+distance[k][j]<distance[i][j]&&distance[i][k]!=Double.MAX_VALUE&&distance[k][j]!=Double.MAX_VALUE) {
	    				distance[i][j]=distance[i][k]+distance[k][j];
	    				paths[i][j]=paths[i][k];
	    			}
	    		}
	    	}
	    }
	    return paths;
	}
	
	
	//return la liste des points passé pour aller de i à j
 	public  ArrayList<Integer> getPath(int i, int j, int[][] paths){
        ArrayList<Integer> path = new ArrayList<>();
        path.add(i);
        while(paths[i][j] != j){
        	path.add(paths[i][j]);
            i = paths[i][j];
        }
        path.add(j);
        return path;
    }
	
  
  
}






