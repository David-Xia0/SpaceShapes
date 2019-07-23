package spaceshapes;

import java.awt.Color;
public class MultiplyShape extends Shape {
	
	public MultiplyShape(){
		super();
	}
	
	/**
	 * Creates a MultipleShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed and direction for horizontal axis.
	 * @param deltaY speed and direction for vertical axis.
	 */
	public MultiplyShape(int x, int y, int deltaX, int deltaY) {
		super(x,y,deltaX,deltaY);
	}
	
	/**
	 * Creates a MultipleShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed (pixels per move call) and direction for horizontal 
	 *        axis.
	 * @param deltaY speed (pixels per move call) and direction for vertical 
	 *        axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 */
	public MultiplyShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x,y,deltaX,deltaY,width,height);
	}
	
	/**
	 * Paints this OvalShape object using the supplied Painter object.
	 */
	public void doPaint(Painter painter) {
		painter.drawOval(_x,_y,_width,_height);
		if (_dynamic!=0) {
			int deltaX = (int)(1.9*Math.random()*-_deltaX);
			int deltaY=(int)(1.9*Math.random()*_deltaY);
			Color color = new Color((int)(255*Math.random()),(int)(255*Math.random()),(int)(255*Math.random()));
			AnimationViewer.ani.addShape( new DynamicRectangleShape(_x,_y,deltaX ,deltaY,(int)(1.9*Math.random()*_width),(int)(1.9*Math.random()*_height),color));
		}
		_dynamic = 0;
	}
}
