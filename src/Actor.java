import java.awt.Image;
import java.awt.Color;

/**
 * This abstract class represents all objects that are contained in the Game.
 * It uses a Point object to represent its location, specifically the top left corner of its bounding box.
 * 
 * @author Jonathan Huang
 */
public abstract class Actor {
	private Image img;
	private Point p; // center coordinate

	/**
	 * Constructor for Actor class
	 * @param x center x-coordinate
	 * @param y center y-coordinate
	 */
	public Actor(double x, double y) {
		p = new Point(x, y);
	}
	
	/**
	 * Constructor for Actor class
	 * @param np center Point
	 */
	public Actor(Point np) {
		p = np;
	}

	/**
	 * Getter method for Image
	 * @return image associated with Actor
	 */
	public Image getImage() {
		return img;
	}

	/**
	 * Setter method for Image
	 * @param i image
	 */
	public void setImage(Image i) {
		img = i;
	}

	/**
	 * Getter method for Point
	 * @return point associated with Actor
	 */
	public Point getPoint() {
		return p;
	}

	/**
	 * Setter method for Point
	 * @param np point 
	 */
	public void setPoint(Point np) {
		p = np;
	}

	/**
	 * Getter method for X
	 * @return center x coordinate 
	 */
	public double getX() {
		return p.getX();
	}

	/**
	 * Setter method for X
	 * @param nx center x coordinate
	 */
	public void setX(double nx) {
		p.setX(nx);
	}

	/**
	 * Getter method for Y
	 * @return center y coordinate
	 */
	public double getY() {
		return p.getY();
	}

	/**
	 * Setter method for Y
	 * @param ny center y coordinate
	 */
	public void setY(double ny) {
		p.setY(ny);
	}
	
	public abstract Rectangle getHitbox();
	
	public abstract Color getColor();
}
