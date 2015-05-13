package evaluator;

import number.Complex;
import figure.Figure;
import figure.Line;
import figure.Point;

public class LineIntersectionEvaluator extends Evaluator{
	Line l1, l2;
	Point intersection;
	public LineIntersectionEvaluator(Line l1, Line l2) {
		this.l1 = l1;
		this.l2 = l2;
		intersection = new Point(0, 0);
		
	}
	
	private void calcIntersection(){
		double a1 = l1.a().re();
		double b1 = l1.b().re();
		double c1 = l1.c().re();
		double a2 = l2.a().re();
		double b2 = l2.b().re();
		double c2 = l2.c().re();
		double denom = (a1 * b2)-(a2 * b1);
		intersection.setPosition(((b1 * c2) - (b2 * c1)) / denom,
				 			     ((c1 * a2) - (c2 * a1)) / denom);
	}
	
	@Override
	public void evaluate() {
		calcIntersection();
	}
	
	@Override
	public Figure getFigure(){
		return intersection;
	}
}
