package evaluator;

import figure.Circle;
import figure.Figure;
import figure.Point;

public class CircleEvaluator extends Evaluator{
	private Point center, other;
	private Circle circle;
	public CircleEvaluator(Point center, Point other){
		this.center = center;
		this.other = other;
		circle = new Circle(center, other);
	}
	
	public void evaluate(){
		circle.recalc(center, other);
	}
	
	public Figure getFigure(){
		return circle;
	}
}
