package evaluator;

import figure.Figure;
import figure.Line;
import figure.Point;

public class LineEvaluator extends Evaluator{
	private Point p1, p2;
	private Line line;
	public LineEvaluator(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
		line = new Line(p1, p2);
	}

	@Override
	public void evaluate() {
		line.setPoints(p1, p2);
	}
	
	@Override
	public Figure getFigure(){
		return line;
	}
}
