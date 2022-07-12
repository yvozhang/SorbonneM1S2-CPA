package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/***************************************************************
 * TME 1: calcul de diamètre et de cercle couvrant minimum.    *
 *   - Trouver deux points les plus éloignés d'un ensemble de  *
 *     points donné en entrée.                                 *
 *   - Couvrir l'ensemble de poitns donné en entrée par un     *
 *     cercle de rayon minimum.                                *
 *                                                             *
 * class Circle:                                               *
 *   - Circle(Point c, int r) constructs a new circle          *
 *     centered at c with radius r.                            *
 *   - Point getCenter() returns the center point.             *
 *   - int getRadius() returns the circle radius.              *
 *                                                             *
 * class Line:                                                 *
 *   - Line(Point p, Point q) constructs a new line            *
 *     starting at p ending at q.                              *
 *   - Point getP() returns one of the two end points.         *
 *   - Point getQ() returns the other end point.               *
 ***************************************************************/
import graphe.Circle;

/**
 * 
 * @author Hongyu YAN, Liuyi CHEN, Yajie LIU, Zimeng ZHANG
 */
public class Welzl {

	public Circle calculCercleMin(ArrayList<Point> points) {
		ArrayList<Point> setR = new ArrayList<Point>();
		ArrayList<Point> copy = (ArrayList<Point>) points.clone();
		Circle c = auxWelzl(copy, setR);
		return c;
	}

	private double crossProduct(Point p, Point q, Point s, Point t) {
		return ((q.x - p.x) * (t.y - s.y) - (q.y - p.y) * (t.x - s.x));
	}

	/**
	 * dessiner une circle consiste de 2/3 points diff�rents de l'un et l'autre
	 * */
	public Circle trivial(ArrayList<Point> pointsR) {
		Circle circle = new Circle(new Point(0, 0), 0);
		//circle de deux points
		if (pointsR.size() == 2) {
			Point p1 = pointsR.get(0);
			Point p2 = pointsR.get(1);
			double center_x = (p1.getX() + p2.getX()) / 2;
			double center_y = (p1.getY() + p2.getY()) / 2;
			Point center = new Point((int) center_x, (int) center_y);
			circle = new Circle(center, (int) center.distance(p1));
		} else if (pointsR.size() == 3) {
			Point p1 = pointsR.get(0);
			Point p2 = pointsR.get(1);
			Point p3 = pointsR.get(2);
			if (crossProduct(p1, p2, p1, p3) != 0) {
				double a = p1.getX() - p2.getX();
				double b = p1.getY() - p2.getY();
				double c = p1.getX() - p3.getX();
				double d = p1.getY() - p3.getY();
				double e = ((p1.getX() * p1.getX() - p2.getX() * p2.getX())
						- (p2.getY() * p2.getY() - p1.getY() * p1.getY())) / 2;
				double f = ((p1.getX() * p1.getX() - p3.getX() * p3.getX())
						- (p3.getY() * p3.getY() - p1.getY() * p1.getY())) / 2;

				double x = (e * d - b * f) / (a * d - b * c);
				double y = (a * f - e * c) / (a * d - b * c);
				Point center = new Point((int) x, (int) y);
				circle = new Circle(center, (int) center.distance(p1));
			}
		}
		
		return circle;
	}

	public boolean inCircle(Circle circle, Point p) {
		return circle.getCenter().distance(p) <= circle.getRadius();
	}

	public Circle auxWelzl(ArrayList<Point> points, ArrayList<Point> pointsR) {
		ArrayList<Point> cp = new ArrayList<Point>(points);
		ArrayList<Point> cr = new ArrayList<Point>(pointsR);

		Circle circle = new Circle(new Point(0, 0), 0);
		if (cp.isEmpty() || cr.size() == 3) {
			circle = trivial(cr);
		} else {
			int index = (new Random()).nextInt(cp.size());
			Point p = cp.remove(index);
			circle = auxWelzl(cp, cr);
			if (!inCircle(circle, p)) {
				cr.add(p);
				circle = auxWelzl(cp, cr);
			}
		}
		return circle;
	}

}
