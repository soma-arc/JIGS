package evaluator;

import java.util.Collection;

import number.Complex;
import figure.Circle;
import figure.Figure;
import figure.Line;
import figure.Point;

public class LineCircleIntersectionEvaluator extends Evaluator{
	private Point intersection1, intersection2;
	private Line line;
	private Circle circle;
	public LineCircleIntersectionEvaluator(Line line, Circle circle){
		this.line = line;
		this.circle = circle;
		intersection1 = new Point();
		intersection2 = new Point();
		calcIntersection();
	}

	private void calcIntersection(){
		Complex a1 = line.a();
		Complex b1 = line.b();
		Complex c1 = line.c();
		Complex a2 = circle.a();
		Complex b2 = circle.b();
		Complex c2 = circle.c();
		Complex d2 = circle.d();
		Complex d = c1.mult(a2).mult( c1.mult(a2).mult(-1).add(a1.mult(b2)).add(b1.mult(c2)) ).mult(4)
				    .add( b1.mult(b2).sub(a1.mult(c2)).sq() )
				    .sub( a1.sq().add(b1.sq()).mult(a2).mult(d2).mult(4) );
		Complex denom = a1.sq().add(b1.sq()).mult(a2).mult(2);

		Complex x1 = a1.mult(c1).mult(a2).mult(2).add(b1.sq().mult(b2)).sub(a1.mult(b1).mult(c2)).mult(-1)
				    .add(b1.mult(Complex.sqrt(d)))
				    .div(denom);
		Complex y1 = b1.mult(c1).mult(a2).mult(2).add(a1.sq().mult(c2)).sub(a1.mult(b1).mult(b2)).mult(-1)
			    	.sub(a1.mult(Complex.sqrt(d)))
			    	.div(denom);
		
		Complex x2 = a1.mult(c1).mult(a2).mult(2).add(b1.sq().mult(b2)).sub(a1.mult(b1).mult(c2)).mult(-1)
			    	.sub(b1.mult(Complex.sqrt(d)))
			    	.div(denom);
		Complex y2 = b1.mult(c1).mult(a2).mult(2).add(a1.sq().mult(c2)).sub(a1.mult(b1).mult(b2)).mult(-1)
					.add(a1.mult(Complex.sqrt(d)))
		    		.div(denom);
		intersection1.setPosition(x1, y1);
		intersection2.setPosition(x2, y2);
	}

	public void evaluate(){
		calcIntersection();
	}

	public Figure getFigure(){
		return intersection1;
	}

	public void addFigure(Collection<Figure> collection){
		collection.add(intersection1);
		collection.add(intersection2);
	}
}
