package figure;

import java.awt.Graphics;

public abstract class Figure {
	abstract public void draw(Graphics g, int width, int height);
	abstract public boolean isClicked(double mouseX, double mouseY);
}
