package evaluator;

import figure.Circle;
import figure.Figure;
import figure.Point;

public class CircleWithThreePointsEvaluator extends Evaluator{
	private Point p1, p2, p3;
	private Circle circle;
	public CircleWithThreePointsEvaluator(Point p1, Point p2, Point p3){
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		circle = new Circle(p1, p2, p3);
	}
	
	public void evaluate(){
		circle.recalc(p1, p2, p3);
	}
	
	public Figure getFigure(){
		return circle;
	}
}
