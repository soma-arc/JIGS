package launcher;

import javax.swing.JFrame;

import ui.Display;

public class Launcher {
	
	public static void main(String[] args) {
		new Launcher().start();
	}	
	
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 1000;
	
	public void start(){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		Display display = new Display();
		frame.add(display);
		frame.setVisible(true);
		display.requestFocus();
	}
}
