package spaceshapes;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author DavidXiao
 *
 */
public class CarrierShape extends Shape {


	private List<Shape> _children = new ArrayList<Shape>();
	/**
	 * Default constructor that creates a CarrierShape instance whose instance
	 * variables are set to default values.
	 */
	public CarrierShape() {
		super();
	}

	public CarrierShape(int x, int y) {
		super(x,y);
	}

	/**
	 * Creates a CarrierShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed and direction for horizontal axis.
	 * @param deltaY speed and direction for vertical axis.
	 */
	public CarrierShape(int x, int y, int deltaX, int deltaY) {
		super(x,y,deltaX,deltaY);
	}

	/**
	 * Creates a CarrierShape instance with specified values for instance 
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
	public CarrierShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x,y,deltaX,deltaY,width,height);
	}
	
	public CarrierShape(int x, int y, int deltaX, int deltaY, int width, int height,String text) {
		super(x,y,deltaX,deltaY,width,height,text);
	}

	/**
	 *moves carrier object and also moves children
	 */
	public void move(int width, int height) {
		super.move(width,height);
		for(Shape shape:_children) {
			shape.move(_width,_height);
		}
	}

	/**
	 * Paints this CarrierShape object using the supplied Painter object.
	 */
	public void doPaint(Painter painter) {
		painter.drawRect(_x,_y,_width,_height);
		painter.translate(_x, _y);
		for(Shape shape:_children) {
			shape.paint(painter);
		}
		painter.translate(-_x, -_y);
	}


	/**
	 * adds a child as a shape
	 * @param shape
	 * @throws IllegalArgumentException
	 */
	void add(Shape shape) throws IllegalArgumentException{
		if((shape._width+shape._x)>this._width || (shape._height+shape._y)>_height) {
			//thrown if shape is too large for parent
			throw new IllegalArgumentException();
		}


		if(shape.parent()==null) {
			_children.add(shape);
			shape._parent = this;
		}else {
			//Thrown if shape already has a parent
			throw new IllegalArgumentException();
		}

	}

	/**
	 * removes shape as child
	 * @param shape
	 */
	void remove(Shape shape) {
		_children.remove(shape);
		shape._parent = null;
	}


	/**
	 * returns shape at desired index(out of all children shapes)
	 * @param index
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public Shape shapeAt(int index) throws IndexOutOfBoundsException{
		if(index<0 || index>_children.size()) {
			throw new IndexOutOfBoundsException();
		}
		return _children.get(index);
	}


	/**
	 * number of children shape
	 * @return
	 */
	public int shapeCount() {
		return _children.size();
	}


	/** index of a child shape
	 * 
	 * @param shape
	 * @return
	 */
	public int indexOf(Shape shape) {
		return _children.indexOf(shape);
	}

	/**
	 * find if a carrier contains this shape
	 * @param shape
	 * @return
	 */
	public boolean contains(Shape shape) {
		return _children.contains(shape);
	}
}
