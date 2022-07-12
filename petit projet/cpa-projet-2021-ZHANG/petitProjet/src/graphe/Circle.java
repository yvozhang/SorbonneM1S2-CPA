package graphe;

import java.awt.Color;
import java.awt.Point;

public class Circle {
	private Point center;
	private double radius;
	private Color c;

	protected Circle(Point center, double r, Color c) {
		this.center = center;
		this.radius = r;
		this.c = c;
	}

	public Circle(Point center, double r) {
		this.center = center;
		this.radius = r;
		this.c = Color.RED;
	}

	public Point getCenter() {
		return this.center;
	}

	public double getRadius() {
		return this.radius;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	protected Color getColor() {
		return this.c;
	}

	protected void setColor(Color c) {
		this.c = c;
	}
}