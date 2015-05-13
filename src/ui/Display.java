package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import evaluator.CircleIntersectionEvaluator;
import evaluator.CircleEvaluator;
import evaluator.CircleWithThreePointsEvaluator;
import evaluator.Evaluator;
import evaluator.LineCircleIntersectionEvaluator;
import evaluator.LineEvaluator;
import evaluator.LineIntersectionEvaluator;
import figure.Circle;
import figure.Figure;
import figure.Line;
import figure.Point;

public class Display extends JPanel{
	private ArrayList<Figure> figures = new ArrayList<Figure>();
	private MouseModes mode = MouseModes.LINE;
	private ArrayList<Figure> makingFigures = new ArrayList<>();
	private Figure selectedFigure = null;
	private ArrayList<Evaluator> evaluators = new ArrayList<>();
	private ArrayList<Figure> selectedFigures = new ArrayList<>();

	public Display(){
		addMouseListener(new MouseAdapter());
		addMouseMotionListener(new MouseMotionAdapter());
		addKeyListener(new KeyPressedAdapter());
	}

	public void paintComponent(Graphics g){
		for(Evaluator evaluator : evaluators){
			evaluator.evaluate();
		}
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                			RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.translate(getWidth()/2, getHeight()/2);
		for(Figure figure : figures){
			figure.draw(g2, getWidth(), getHeight());
		}
		for(Figure makingFigure : makingFigures){
			makingFigure.draw(g2, getWidth(), getHeight());
		}
	}

	private class MouseAdapter extends java.awt.event.MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e){
			double mouseX = e.getX() - getWidth()/2;
			double mouseY = e.getY() - getHeight() /2;
			for(Figure figure : figures){
				if(figure.isClicked(mouseX, mouseY)){
					selectedFigures.add(figure);
					break;
				}
			}
			switch (mode) {
			case SELECTION:
				break;
			case MOVE:
				for(Figure figure : figures){
					if(figure.isClicked(mouseX, mouseY)){
						selectedFigure = figure;
						break;	
					}
				}
				break;
			case POINT:
				makingFigures.add(new Point(mouseX, mouseY));
				break;
			case LINE:
				if(selectedFigures.size() == 2 && selectedFigures.get(0) instanceof Point){
					LineEvaluator le = new LineEvaluator((Point) selectedFigures.get(0),(Point) selectedFigures.get(1));
					figures.add(le.getFigure());
					evaluators.add(le);
					selectedFigures.clear();
				}
				if(selectedFigures.size() == 2 && selectedFigures.get(0) instanceof Line){
					LineIntersectionEvaluator le = new LineIntersectionEvaluator((Line) selectedFigures.get(0),(Line) selectedFigures.get(1));
					figures.add(le.getFigure());
					evaluators.add(le);
					selectedFigures.clear();
				}
				break;
			case LINE_CIRCLE_INTERSECTION:
				if(selectedFigures.size() == 2 && selectedFigures.get(0) instanceof Line){
					LineCircleIntersectionEvaluator le = new LineCircleIntersectionEvaluator(
							(Line) selectedFigures.get(0),
							(Circle) selectedFigures.get(1));
					le.addFigure(figures);
					evaluators.add(le);
					selectedFigures.clear();
				}
				break;
			case CIRCLE:
				if(selectedFigures.size() == 2 && selectedFigures.get(0) instanceof Point){
					CircleEvaluator le = new CircleEvaluator((Point) selectedFigures.get(0),(Point) selectedFigures.get(1));
					figures.add(le.getFigure());
					evaluators.add(le);
					selectedFigures.clear();
				}
				break;
			case CIRCLE_WITH_THREE_POINTS:
				if(selectedFigures.size() == 3 && selectedFigures.get(0) instanceof Point){
					CircleWithThreePointsEvaluator le = new CircleWithThreePointsEvaluator((Point) selectedFigures.get(0),(Point) selectedFigures.get(1),(Point) selectedFigures.get(2));
					figures.add(le.getFigure());
					evaluators.add(le);
					selectedFigures.clear();
				}
				break;
			case CIRCLE_INTERSECTION:
				if(selectedFigures.size() == 2 && selectedFigures.get(0) instanceof Circle){
					CircleIntersectionEvaluator le = new CircleIntersectionEvaluator((Circle) selectedFigures.get(0),(Circle) selectedFigures.get(1));
					le.addFigures(figures);
					evaluators.add(le);
					selectedFigures.clear();
				}
			default:
				break;
			}
			
			if(selectedFigures.size() == 3){
				selectedFigures.clear();
			}
			repaint();
		}
		
		@Override
		public void mouseReleased(MouseEvent e){
			switch (mode) {
			case SELECTION:
				selectedFigure = null;
				break;
			case POINT:
				figures.addAll(makingFigures);
				break;
			case LINE:
				figures.addAll(makingFigures);
				break;
			case MOVE:
				selectedFigure = null;
			default:
				break;
			}
			makingFigures.clear();
		}
	}

	private class MouseMotionAdapter extends java.awt.event.MouseMotionAdapter{
		@Override
		public void mouseDragged(MouseEvent e){
			double mouseX = e.getX() - getWidth()/2;
			double mouseY = e.getY() - getHeight()/2;
			if(selectedFigure != null && selectedFigure instanceof Point){
				((Point) selectedFigure).setPosition(mouseX,  mouseY);
			}
			if(makingFigures.size() != 0 && makingFigures.get(0) instanceof Point)
				((Point) makingFigures.get(0)).setPosition(mouseX, mouseY);
			repaint();
		}
	}

	private class KeyPressedAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e){
			char key = e.getKeyChar();
			if(key == 's'){
				mode = MouseModes.SELECTION;
			}else if(key == 'l'){
				mode = MouseModes.LINE;
			}else if(key == 'p'){
				mode = MouseModes.POINT;
			}else if(key == 'm'){
				mode = MouseModes.MOVE;
			}else if(key == 'c'){
				mode = MouseModes.CIRCLE;
			}else if(key == 't'){
				mode = MouseModes.CIRCLE_WITH_THREE_POINTS;
			}else if(key == 'i'){
				mode = MouseModes.LINE_CIRCLE_INTERSECTION;
			}else if(key == 'q'){
				mode = MouseModes.CIRCLE_INTERSECTION;
			}
		}
	}
}
