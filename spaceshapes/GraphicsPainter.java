package spaceshapes;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Implementation of the Painter interface that delegates drawing to a
 * java.awt.Graphics object.
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
public class GraphicsPainter implements Painter {
	// Delegate object.
	private Graphics _g;
	/**
	 * Creates a GraphicsPainter object and sets its Graphics delegate.
	 */
	public GraphicsPainter(Graphics g) {
		this._g = g;
		_g.setColor(new Color(212, 212, 212));
	}

	/**
	 * @see spaceshapes.Painter.drawRect
	 */
	public void drawRect(int x, int y, int width, int height) {
		_g.drawRect(x, y, width, height);
	}

	/**
	 * @see spaceshapes.Painter.drawOval
	 */
	public void drawOval(int x, int y, int width, int height) {
		_g.drawOval(x, y, width, height);
	}

	/**
	 * @see spaeshapes.Painter.drawLine.
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		_g.drawLine(x1, y1, x2, y2);
	}
	
	/**
	 * 
	 *
	 */
	public void fillRect(int x1, int y1, int width, int height) {
		_g.fillRect(x1, y1, width, height);
	}
	
	/**
	 * gets current colour of graphics
	 */
	public Color getColor() {
		return _g.getColor();
	}
	
	/**
	 * sets color for graphics
	 */
	public void setColor(Color c) {
		_g.setColor(c);
	}
	
	/**
	 * translates the x and y axis accordingly
	 */
	public void translate(int x, int y){
		_g.translate(x,y);
	}
	
	/**
	 * 
	 */
	public void drawCenteredText(String text, int x, int y) {
		FontMetrics size = _g.getFontMetrics();
		int vertical = size.getAscent()-size.getDescent();
		_g.drawString(text, x-size.stringWidth(text)/2, y+vertical/2);
	}
	
	/**
	 * 
	 * @return
	 */
	public FontMetrics getFontMetrics() {
		return _g.getFontMetrics();
	}
	
	public void drawImage(Image img, int x, int y, int width, int height) {
		_g.drawImage(img,x,y,width,height,null);
	}
	
}
