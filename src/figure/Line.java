package figure;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import number.Complex;

public class Line extends Figure{
	private Point p1, p2;
	private Complex a, b, c;
	public Line(Point p1, Point p2){
		this.p1 = p1;
		this.p2 = p2;
		a = p2.y().sub(p1.y()).mult(-1);
		b = p2.x().sub(p1.x());
		c = a.mult(p1.x()).add(b.mult(p1.y())).mult(-1).div(p1.z());
	}
	
	@Override
	public void draw(Graphics g, int width, int height) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(4.0f));	
		g.setColor(Color.white);
		g2.drawLine((int) p1.x().re(),
				   (int) p1.y().re(), 
				   (int) p2.x().re(), 
				   (int) p2.y().re());
	}
	
	public void setPoints(Point p1, Point p2){
		this.p1 = p1;
		this.p2 = p2;
		a = p2.y().sub(p1.y()).mult(-1);
		b = p2.x().sub(p1.x());
		c = a.mult(p1.x()).add(b.mult(p1.y())).mult(-1).div(p1.z());
	}
	
	public Point p1(){
		return p1;
	}
	
	public Point p2(){
		return p2;
	}
	
	public Complex a(){
		return a;
	}
	
	public Complex b() {
		return b;
	}
	
	public Complex c(){
		return c;
	}
	
	public double calcX(double y){
		return -(b.re() * y + c.re())/a.re();
	}
	
	public double calcY(double x){
		return -(a.re() * x + c.re())/b.re();
	}

	@Override
	public boolean isClicked(double mouseX, double mouseY) {
		if(Math.abs(calcY(mouseX) - mouseY) < 5){
			return true;
		}
		return false;
	}
}
