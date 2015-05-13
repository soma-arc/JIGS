package figure;

import java.awt.Color;
import java.awt.Graphics;

import number.Complex;

public class Point extends Figure{
	private Complex x, y, z;
	private static final int POINT_RADIUS = 16;
	
	public Point(){
		x = new Complex(0);
		y = new Complex(0);
	}
	
	public Point(double x, double y){
		this.x = new Complex(x, 0);
		this.y = new Complex(y, 0);
		this.z = Complex.ONE;
	}
	
	public Point(Complex x, Complex y){
		this.x = x;
		this.y = y;
		this.z = Complex.ONE;
	}
	
	@Override
	public void draw(Graphics g, int width, int height){
		g.setColor(Color.red);
		g.fillOval((int) x.re() - POINT_RADIUS/2, (int) y.re() - POINT_RADIUS/2, POINT_RADIUS, POINT_RADIUS);
	}
	
	public void setPosition(double x, double y){
		this.x.setRe(x);
		this.y.setRe(y);
	}
	
	public void setPosition(Complex x, Complex y){
		this.x = x;
		this.y = y;
	}
	
	public boolean isClicked(double mouseX, double mouseY){
		if((mouseX - x.re()) * (mouseX - x.re()) + (mouseY - y.re()) * (mouseY - y.re()) < POINT_RADIUS * POINT_RADIUS ){
			return true;
		}else{
			return false;
		}
	}
	
	public Complex x(){
		return x;
	}
	
	public Complex y(){
		return y;
	}
	
	public Complex z(){
		return z;
	}
	
	public static double dist(Point p1, Point p2){
		return Math.sqrt((p1.x.re() - p2.x.re()) * (p1.x.re() - p2.x.re()) + 
						 (p1.y.re() - p2.y.re()) * (p1.y.re() - p2.y.re()));
	}
}
