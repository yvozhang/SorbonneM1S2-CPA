package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import graphe.Circle;

public class Naif {
	
	public Circle calculCercleMin(ArrayList<Point> inputPoints) {
		@SuppressWarnings("unchecked")
		ArrayList<Point> points = (ArrayList<Point>) inputPoints.clone();
		if (points.size() < 1)
			return null;
		double x, y, cRadius;
		for (Point p : points) {
			for (Point q : points) {
				x = .5 * (p.x + q.x);
				y = .5 * (p.y + q.y);
				cRadius = 0.25 * ((p.x - q.x) * (p.x - q.x) + (p.y - q.y) * (p.y - q.y));
				boolean allHit = true;
				for (Point s : points)
					if ((s.x - x) * (s.x - x) + (s.y - y) * (s.y - y) > cRadius) {
						allHit = false;
						break;
					}
				if (allHit)
					return new Circle(new Point((int) x, (int) y), (int) Math.sqrt(cRadius));
			}
		}
		
		double resX = 0;
		double resY = 0;
		double resRadius = Double.MAX_VALUE;
		for (int i = 0; i < points.size(); i++) {
			for (int j = i + 1; j < points.size(); j++) {
				for (int k = j + 1; k < points.size(); k++) {
					Point p1 = points.get(i);
					Point p2 = points.get(j);
					Point p3 = points.get(k);
					
					// si les trois sont colineaires on passe
					if ((p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x) == 0)
						continue;
					// si p et q sont sur la meme ligne, ou p et r sont sur la meme ligne, on les
					// echange
					if ((p1.y == p2.y) || (p1.y == p3.y)) {
						if (p1.y == p2.y) {
							p1 = points.get(k); // ici on est certain que p n'est sur la meme ligne de ni q ni r
							p3 = points.get(i); // parce que les trois points sont non-colineaires
						} else {
							p1 = points.get(j); // ici on est certain que p n'est sur la meme ligne de ni q ni r
							p2 = points.get(i); // parce que les trois points sont non-colineaires
						}
					}
					
					double a = p1.getX() - p2.getX();
					double b = p1.getY() - p2.getY();
					double c = p1.getX() - p3.getX();
					double d = p1.getY() - p3.getY();
					double e = ((p1.getX() * p1.getX() - p2.getX() * p2.getX())
							- (p2.getY() * p2.getY() - p1.getY() * p1.getY())) / 2;
					double f = ((p1.getX() * p1.getX() - p3.getX() * p3.getX())
							- (p3.getY() * p3.getY() - p1.getY() * p1.getY())) / 2;

					x = (e * d - b * f) / (a * d - b * c);
					y = (a * f - e * c) / (a * d - b * c);
					Point center = new Point((int) x, (int) y);
					cRadius = center.distance(p1);
					
					if (cRadius >= resRadius)
						continue;
					boolean allHit = true;
					for (Point s : points)
						if ((s.x - x) * (s.x - x) + (s.y - y) * (s.y - y) > cRadius * cRadius) {
							allHit = false;
							break;
						}
					if (allHit) {
						resX = x;
						resY = y;
						resRadius = cRadius;
					}
				}
			}
		}
		
		Circle c = new Circle(new Point((int) resX, (int) resY), (int)resRadius);
		return c;
	}
	
}
	