package spaceshapes;

import java.awt.Color;


public class DynamicRectangleShape extends Shape {


	private Color _color;
	
	public DynamicRectangleShape() {
		super();
	}
	/**
	 * Creates a DynamicShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed and direction for horizontal axis.
	 * @param deltaY speed and direction for vertical axis.
	 */
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY,Color color) {
		super(x,y,deltaX,deltaY);
		_color=color;

	
	}
	
	
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY) {
		super(x,y,deltaX,deltaY);
	}

	/**
	 * Creates a DynamicShape instance with specified values for instance 
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
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height,Color color) {
		super(x,y,deltaX,deltaY,width,height);
		_color = color;
	}
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height,String text,Color color) {
		super(x,y,deltaX,deltaY,width,height,text);
		_color = color;
	}
	
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x,y,deltaX,deltaY,width,height);
	}


	/**
	 * Paints this DynamicShape object using the supplied Painter object.
	 */
	public void doPaint(Painter painter) {
		
		if(_dynamic==2) {//if , to check if a rect or hex should be created
			Color oldC = painter.getColor();//stores old color
			painter.setColor(_color);
			painter.fillRect(_x,_y,_width,_height);
			painter.setColor(oldC);
		}else {
			painter.drawRect(_x, _y, _width, _height);
		}
	}
}



