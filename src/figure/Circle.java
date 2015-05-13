package figure;

import java.awt.Color;
import java.awt.Graphics;

import number.Complex;

public class Circle extends Figure{
	private Point center;
	private double r;
	private Complex a, b, c, d;
	private static final int CLICK_THRESHOLD = 5;
	
	public Circle(Point center, Point other){
		this.center = center;
		r = Math.sqrt(center.x().sub(other.x()).re() * center.x().sub(other.x()).re() +
					  center.y().sub(other.y()).re() * center.y().sub(other.y()).re());
		a = center.z().mult(other.z().sq());
		b = center.x().mult(other.z().sq()).mult(-2);
		c = center.y().mult(other.z().sq()).mult(-2);
		d = center.z().mult( other.x().sq().add(other.y().sq()) ).mult(-1)
			.add(other.z().mult( center.x().mult(other.x()).add(center.y().mult(other.y())) ).mult(2));
	}
	
	public Circle(Point center, double r){
		this.center = center;
		this.r = r;
		Point other = new Point(center.x().re() + r, center.y().re());
		a = center.z().mult(other.z().sq());
		b = center.x().mult(other.z().sq()).mult(-2);
		c = center.y().mult(other.z().sq()).mult(-2);
		d = center.z().mult( other.x().sq().add(other.y().sq()) ).mult(-1)
			.add(other.z().mult( center.x().mult(other.x()).add(center.y().mult(other.y())) ).mult(2));
	}
	
	public Circle(Point p1, Point p2, Point p3){
		double lA = Point.dist(p2, p3);
		double lB = Point.dist(p1, p3);
		double lC = Point.dist(p1, p2);
		double coefA = lA * lA * (lB * lB + lC * lC - lA * lA);
		double coefB = lB * lB * (lA * lA + lC * lC - lB * lB);
		double coefC = lC * lC * (lA * lA + lB * lB - lC * lC);
		double denom = coefA + coefB + coefC;
		this.center = new Point((p1.x().mult(coefA).add(p2.x().mult(coefB)).add(p3.x().mult(coefC))).div(denom),
								(p1.y().mult(coefA).add(p2.y().mult(coefB)).add(p3.y().mult(coefC))).div(denom));
		this.r = Point.dist(center, p1);
		
		a = center.z().mult(p1.z().sq());
		b = center.x().mult(p1.z().sq()).mult(-2);
		c = center.y().mult(p1.z().sq()).mult(-2);
		d = center.z().mult(p1.x().sq().add(p1.y().sq())).mult(-1)
			.add(p1.z().mult(center.x().mult(p1.x()).add(center.y().mult(p1.y()))).mult(2));
	}
	
	public void recalc(Point center, Point other){
		center.setPosition(center.x().re(), center.y().re());
		r = Math.sqrt(center.x().sub(other.x()).re() * center.x().sub(other.x()).re() +
				  center.y().sub(other.y()).re() * center.y().sub(other.y()).re());
		a = center.z().mult(other.z().sq());
		b = center.x().mult(other.z().sq()).mult(-2);
		c = center.y().mult(other.z().sq()).mult(-2);
		d = center.z().mult(other.x().sq().add(other.y().sq())).mult(-1)
			.add(other.z().mult(center.x().mult(other.x()).add(center.y().mult(other.y()))).mult(2));
	}
	
	public void recalc(Point center){
		this.center = center;
		Point other = new Point(center.x().re() + r, center.y().re());
		a = center.z().mult(other.z().sq());
		b = center.x().mult(other.z().sq()).mult(-2);
		c = center.y().mult(other.z().sq()).mult(-2);
		d = center.z().mult( other.x().sq().add(other.y().sq()) ).mult(-1)
			.add(other.z().mult( center.x().mult(other.x()).add(center.y().mult(other.y())) ).mult(2));
	}
	
	public void recalc(Point p1, Point p2, Point p3){
		double lA = Point.dist(p2, p3);
		double lB = Point.dist(p1, p3);
		double lC = Point.dist(p1, p2);
		double coefA = lA * lA * (lB * lB + lC * lC - lA * lA);
		double coefB = lB * lB * (lA * lA + lC * lC - lB * lB);
		double coefC = lC * lC * (lA * lA + lB * lB - lC * lC);
		double denom = coefA + coefB + coefC;
		center.setPosition((p1.x().mult(coefA).add(p2.x().mult(coefB)).add(p3.x().mult(coefC))).div(denom),
										 (p1.y().mult(coefA).add(p2.y().mult(coefB)).add(p3.y().mult(coefC))).div(denom));
		this.r = Point.dist(center, p1);
		
		a = center.z().mult(p1.z().sq());
		b = center.x().mult(p1.z().sq()).mult(-2);
		c = center.y().mult(p1.z().sq()).mult(-2);
		d = center.z().mult(p1.x().sq().add(p1.y().sq())).mult(-1)
			.add(p1.z().mult(center.x().mult(p1.x()).add(center.y().mult(p1.y()))).mult(2));
	}
	
	@Override
	public void draw(Graphics g, int width, int height) {
		g.setColor(Color.white);
		g.drawOval((int) (center.x().re() - r),
				   (int) (center.y().re() - r), 
				   (int) (2 * r) , (int) (2 * r));
	}

	@Override
	public boolean isClicked(double mouseX, double mouseY) {
		if((center.x().re() - mouseX) * (center.x().re() - mouseX) + 
		   (center.y().re() - mouseY) * (center.y().re() - mouseY) < Math.abs(r * r - CLICK_THRESHOLD * CLICK_THRESHOLD)){
			return true;
		}
		return false;
	}
	
	public Complex a(){
		return a;
	}
	
	public Complex b(){
		return b;
	}
	
	public Complex c(){
		return c;
	}
	
	public Complex d(){
		return d;
	}
	
	public void setR(double r){
		this.r = r;
	}
}
