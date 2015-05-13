package evaluator;

import figure.Circle;
import figure.Figure;
import figure.Point;

public class CircleWithCenterEvaluator extends Evaluator{
	private Circle circle;
	private Point center;
	public CircleWithCenterEvaluator(Point center, double r){
		this.center = center;
		circle = new Circle(center, r);
	}
	
	public void evaluate(){
		circle.recalc(center);
	}
		
	public Figure getFigure(){
		return circle;
	}
}