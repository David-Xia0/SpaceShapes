package spaceshapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;



/**
 * Simple GUI program to show an animation of shapes in a confined space. Class AnimationViewer is
 * a special kind of GUI component (JPanel), and as such an instance of 
 * AnimationViewer can be added to a JFrame object. A JFrame object is a 
 * window that can be closed, minimised, and maximised. The state of an
 * AnimationViewer object comprises a list of Shapes and a Timer object. An
 * AnimationViewer instance subscribes to events that are published by a Timer.
 * In response to receiving an event from the Timer, the AnimationViewer iterates 
 * through a list of Shapes requesting that each Shape paints and moves itself.
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
@SuppressWarnings("serial")
public class AnimationViewer extends JPanel implements ActionListener {
	// Frequency in milliseconds for the Timer to generate events.
	private static final int DELAY = 20;

	// Collection of Shapes to animate.
	private List<Shape> _shapes;
	private List<Shape> _nextShapes;
	protected static AnimationViewer ani;

	private Timer _timer = new Timer(DELAY, this);

	/**
	 * Creates an AnimationViewer instance with a list of Shape objects and 
	 * starts the animation.
	 */
	public AnimationViewer() {
		this.setBackground(Color.BLACK);
		_shapes = new ArrayList<Shape>();
		_nextShapes = new ArrayList<Shape>();
		// Populate the list of Shapes.
		_shapes.add(new RectangleShape(100, 100, -1, -1,50,50));
		_shapes.get(0).addText("jhfhgj");
		_shapes.add(new DynamicRectangleShape(10,19,2,4,60,50,Color.blue));
		//_shapes.add(new OvalShape(20,20,3, 5));
		CarrierShape carr = new CarrierShape(150,150, 2 ,2 ,200 ,200);
		carr.addText("h");
		RectangleShape rec =new RectangleShape(150, 150, 2, 2, 20, 20);
		rec.addText("good day");
        carr.add(rec);
        _shapes.add(carr);
		
        CarrierShape shape = new CarrierShape(10,10,2,2,200,200);
		CarrierShape shape2 = new CarrierShape(10,10,2,2,100,100);
		CarrierShape shape3 = new CarrierShape(10,3,2,1,50,50);
		shape.add(shape2);
		shape2.add(shape3);
		shape2.add(new DynamicRectangleShape(10,19,2,4,60,50,Color.blue));
		shape2.add(new DynamicRectangleShape(19,29,2,4,60,50,Color.RED));
		_shapes.add(shape);
		
		// Start the animation.
		_timer.start();
	}

	/**
	 * Called by the Swing framework whenever this AnimationViewer object
	 * should be repainted. This can happen, for example, after an explicit 
	 * repaint() call or after the window that contains this AnimationViewer 
	 * object has been opened, exposed or moved.
	 * 
	 */
	public void paintComponent(Graphics g) {
		// Call inherited implementation to handle background painting.
		super.paintComponent(g);
		
		// Calculate bounds of animation screen area.
		int width = getSize().width;
		int height = getSize().height;
		
		// Create a GraphicsPainter that Shape objects will use for drawing.
		// The GraphicsPainter delegates painting to a basic Graphics object.
		Painter painter = new GraphicsPainter(g);
		
		// Progress the animation.
		_shapes.addAll(_nextShapes);
		_nextShapes.clear();
		for(Shape s : _shapes) {
			s.paint(painter);//I changed the function that should be called to paint shape, so text is painted
			s.move(width, height);
		}
	}

	/**
	 * Notifies this AnimationViewer object of an ActionEvent. ActionEvents are
	 * received by the Timer.
	 */
	public void actionPerformed(ActionEvent e) {
		// Request that the AnimationViewer repaints itself. The call to 
		// repaint() will cause the AnimationViewer's paintComponent() method 
		// to be called.
		repaint();
	}
	
	
	/**
	 * Main program method to create an AnimationViewer object and display this
	 * within a JFrame window.
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Animation viewer");
				ani = new AnimationViewer();
				frame.add(ani);
		
				// Set window properties.
			
				frame.setSize(800, 800);//500, 534 This size of the frame accounts for the window frame
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
	
	public void addShape(Shape shape) {
		_nextShapes.add(shape);
	}

}
