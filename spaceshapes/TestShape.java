package spaceshapes;

import java.awt.Color;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


/**
 * A class that implements test cases aimed at identifying bugs in the 
 * implementations of classes Shape and RectangleShape.
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
public class TestShape {
	// Fixture object that is used by the tests.
	private MockPainter _painter;

	/**
	 * This method is called automatically by the JUnit test-runner immediately
	 * before each @Test method is executed. setUp() recreates the fixture so 
	 * that there no side effects from running individual tests.
	 */
	@Before
	public void setUp() {
		_painter = new MockPainter();
	}

	/**
	 * Test to perform a simple (non-bouncing) movement, and to ensure that a
	 * Shape's position after the movement is correct.
	 */
	@Test
	public void testSimpleMove() {
		RectangleShape shape = new RectangleShape(100, 20, 12, 15);
		shape.paint(_painter);
		shape.move(500, 500);
		shape.paint(_painter);
		assertEquals("(rectangle 100,20,25,35)(rectangle 112,35,25,35)", 
				_painter.toString());
	}


	/**
	 * Test to perform a bounce movement off the right-most boundary and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffRight() {
		RectangleShape shape = new RectangleShape(100, 20, 12, 15);
		shape.paint(_painter);
		shape.move(135, 10000);
		shape.paint(_painter);
		shape.move(135, 10000);
		shape.paint(_painter);
		assertEquals("(rectangle 100,20,25,35)(rectangle 110,35,25,35)"
				+ "(rectangle 98,50,25,35)", _painter.toString());
	}

	/**
	 * Test to perform a bounce movement off the left-most boundary and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffLeft() {
		RectangleShape shape = new RectangleShape(10, 20, -12, 15);
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		assertEquals("(rectangle 10,20,25,35)(rectangle 0,35,25,35)"
				+ "(rectangle 12,50,25,35)", _painter.toString());
	}

	/**
	 * Test to perform a bounce movement off the bottom right corner and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffBottomAndRight() {
		RectangleShape shape = new RectangleShape(10, 90, -12, 15);
		shape.paint(_painter);
		shape.move(125, 135);
		shape.paint(_painter);
		shape.move(125, 135);
		shape.paint(_painter);
		assertEquals("(rectangle 10,90,25,35)(rectangle 0,100,25,35)"
				+ "(rectangle 12,85,25,35)", _painter.toString());
	}

	/**
	 * Test my oval drawing
	 */
	@Test
	public void testOval() {
		OvalShape shape = new OvalShape(20,90,1,1);
		shape.paint(_painter);
		assertEquals("(oval 20,90,25,35)",_painter.toString());
	}

	/**
	 * Test my small diamond shaped Hex
	 */
	@Test
	public void testSmallHex() {
		HexagonShape shape = new HexagonShape(30,20,1,1);

		shape.paint(_painter);
		shape.move(10000, 10000);
		assertEquals("(line 30,37,42,20)(line 42,20,55,37)(line 55,37,42,55)(line 42,55,30,37)",_painter.toString());
	}

	/**
	 * constucts a regular hex
	 */
	@Test
	public void testRegualrHex() {
		HexagonShape shape = new HexagonShape(30,20,1,1,60,60);
		shape.paint(_painter);
		shape.move(10000, 10000);
		assertEquals("(line 30,50,50,20)(line 50,20,70,20)(line 70,20,90,50)(line 90,50,70,80)(line 70,80,50,80)"
				+ "(line 50,80,30,50)",_painter.toString());
	}

	/**
	 * paints simple dynamic shape, both regular and small
	 */
	@Test
	public void testDynamicShape(){
		DynamicRectangleShape regular = new DynamicRectangleShape(70,70,10,0,60,60,Color.red);
		DynamicRectangleShape small = new DynamicRectangleShape(70,70,10,0,20,20,Color.red);
		regular.paint(_painter);
		small.paint(_painter);
		assertEquals("(rectangle 70,70,60,60)(rectangle 70,70,20,20)",_painter.toString());
		
	}
	/**
	 * test dynamic shape change to solid red after it hits right wall
	 */
	@Test
	public void testDynamicShapeRight() {
		DynamicRectangleShape shape = new DynamicRectangleShape(70,70,10,0,20,20,Color.red);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		assertEquals("(rectangle 70,70,20,20)(java.awt.Color[r=255,g=0,b=0])(rect 80,70,20,20)(java.awt.Color[r=255,g=255,b=255])",_painter.toString());
	}
	
	@Test
	public void testDynamicShapeLeft() {
		DynamicRectangleShape shape = new DynamicRectangleShape(10,50,-10,0,20,20,Color.red);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		assertEquals("(rectangle 10,50,20,20)(java.awt.Color[r=255,g=0,b=0])(rect 0,50,20,20)(java.awt.Color[r=255,g=255,b=255])",_painter.toString());
	}
	
	/**
	 * for top and bottom the shape should not change
	 */
	@Test
	public void testDynamicShapeTop() {
		DynamicRectangleShape shape = new DynamicRectangleShape(30,10,0,-10,20,20,Color.red);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		assertEquals("(rectangle 30,10,20,20)(rectangle 30,0,20,20)",_painter.toString());
	}
	
	@Test
	public void testDynamicShapeBottom() {
		DynamicRectangleShape shape = new DynamicRectangleShape(30,70,0,10,20,20,Color.red);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		assertEquals("(rectangle 30,70,20,20)(rectangle 30,80,20,20)",_painter.toString());
	}



	/**
	 * Test for bounces off corners, vertical walls / left and right should be superior
	 */
	@Test
	public void testDynamicShapeBounceOffTopLeft() {
		DynamicRectangleShape shape = new DynamicRectangleShape(20,10,-10,-10,50,50,Color.red);
		shape.paint(_painter);
		shape.move(500, 500);
		shape.paint(_painter);
		shape.move(500, 500);
		shape.paint(_painter);
		assertEquals("(rectangle 20,10,50,50)(rectangle 10,0,50,50)(java.awt.Color[r=255,g=0,b=0])"
				+ "(rect 0,10,50,50)(java.awt.Color[r=255,g=255,b=255])",_painter.toString());
	}

	@Test
	public void testDynamicShapeBounceOffTopRight() {
		DynamicRectangleShape shape = new DynamicRectangleShape(430,10, 10,-10,50,50,Color.red);
		shape.paint(_painter);
		shape.move(500, 500);
		shape.paint(_painter);
		shape.move(500, 500);
		shape.paint(_painter);
		
		assertEquals("(rectangle 430,10,50,50)(rectangle 440,0,50,50)(java.awt.Color[r=255,g=0,b=0])"
				+ "(rect 450,10,50,50)(java.awt.Color[r=255,g=255,b=255])",_painter.toString());
	}
	
	@Test
	public void testSmolDynamicShapeBounceOffBottomLeft() {
		DynamicRectangleShape shape = new DynamicRectangleShape(20,70,-10,10,20,20,Color.red);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		
		assertEquals("(rectangle 20,70,20,20)(rectangle 10,80,20,20)(java.awt.Color[r=255,g=0,b=0])"
				+ "(rect 0,70,20,20)(java.awt.Color[r=255,g=255,b=255])",_painter.toString());
	}
	
	@Test
	public void testSmolDynamicShapeBounceOffBottomRight() {
		DynamicRectangleShape shape = new DynamicRectangleShape(60,70,10,10,20,20,Color.red);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		assertEquals("(rectangle 60,70,20,20)(rectangle 70,80,20,20)(java.awt.Color[r=255,g=0,b=0])"
				+ "(rect 80,70,20,20)(java.awt.Color[r=255,g=255,b=255])",_painter.toString());
	}
	//note, in the actual AnimationViewer, the space the objects can travel in is not 500/500 (500/500 includes the window bar on top)
}
