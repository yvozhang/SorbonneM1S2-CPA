package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Edge {
	
	protected Point p,q;
    protected Edge(Point p,Point q){ this.p=p; this.q=q; }
    protected double distance(){ return p.distance(q); }

	
	protected static boolean contains(ArrayList<Edge> edges,Point p,Point q){
	    for (Edge e:edges){
	      if (e.p.equals(p) && e.q.equals(q) ||
	          e.p.equals(q) && e.q.equals(p) ) return true;
	    }
	    return false;
	}
	protected static Tree2D edgesToTree(ArrayList<Edge> edges, Point root) {
	    ArrayList<Edge> remainder = new ArrayList<Edge>();
	    ArrayList<Point> subTreeRoots = new ArrayList<Point>();
	    Edge current;
		while (edges.size()!=0) {
			current = edges.remove(0);
			if (current.p.equals(root)) {
				subTreeRoots.add(current.q);
			} else {
				if (current.q.equals(root)) {
					subTreeRoots.add(current.p);
				} else {
					remainder.add(current);
				}
			}
		}
	    ArrayList<Tree2D> subTrees = new ArrayList<Tree2D>();
	    for (Point subTreeRoot: subTreeRoots) subTrees.add(edgesToTree((ArrayList<Edge>)remainder.clone(),subTreeRoot));

	    return new Tree2D(root, subTrees);
 	}
	

}
