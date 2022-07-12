package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

public class Prim {
	
	public static ArrayList<Edge> prim(ArrayList<Point> points) {

	  	int totalSize = points.size();
		double[][] poids = new double[totalSize][totalSize];
		int[] checkList = new int[totalSize];// la tableau pour enregiste la statu de chaque point, si il est testé ou pas
		ArrayList<Integer> checkedPoint = new ArrayList<Integer>(); //la liste pour stocker les points qui sont testé
		boolean allChecked = false; 
		Arrays.fill(checkList, 1); //mettre tous les valeur de checkList en 1 par default, ca veut dire il n'y a pas des points qui est testé 
		int index = 0;
		
		ArrayList<Edge> resultArrayList = new ArrayList<Edge>();
		
		//enregistre la distance entre tous les points deux par deux
		for(int i = 0; i < totalSize; i++) {
			for(int j = 0; j < totalSize; j++) {
				if(i == j) {
					poids[i][j] = 0;
				}else {
					double distance = points.get(i).distance(points.get(j));
					poids[i][j] = distance;
				}
			}
		}

		checkList[index] = 0;
		checkedPoint.add(index);
		
		//Choisissez un point au hasard et mettez-le dans la liste testé <checkedPoint>, 
		//trouvez toutes les arêtes qui lui sont connectées, mettez la plus petite arête dans la liste de résultats, puis mettez 
		//les points connectés à ce point dans la liste testé également, puis faites un boucle à travers les plus petites arêtes connectées 
		//aux points dans la liste testé jusqu'à ce que tous les points aient été détectés et arrêtez la recherche
		while(!allChecked) {
			double minPoids = Double.MAX_VALUE;
			Point beginPoint = null;
			Point finalPoint = null;
		
			for(int j = 0; j < checkedPoint.size(); j++) {
				for(int i = 0; i < totalSize; i++) {
					while(poids[checkedPoint.get(j)][i] < minPoids && poids[checkedPoint.get(j)][i] > 0 && checkList[i]!=0) {
						index = i;
						minPoids = poids[checkedPoint.get(j)][i];
						beginPoint = points.get(checkedPoint.get(j));
						finalPoint = points.get(index);
					}
				}
			}
			checkList[index] = 0;
			checkedPoint.add(index);
			Edge edge = new Edge(beginPoint, finalPoint);
			
			if(!Edge.contains(resultArrayList, edge.p, edge.q)){
          	  resultArrayList.add(edge);
            }			
			
			
			allChecked = checklist(checkList);
		}

		return resultArrayList;

	}
	
	//verifie si tous les points sont déjà testé
	public static boolean checklist(int[] a) {
	  for(int i =0; i < a.length; i++) {
			if(a[i]!=0) {
				return false;
			}
		}
	  return true;
	}
}
