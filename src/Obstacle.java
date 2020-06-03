import javax.swing.ImageIcon;
import java.awt.Color;

/**
 * This class represents an Obstacle.
 * Tanks cannot move through obstacles, and Projectiles reflect on the Obstacle's sides.
 * @author Jonathan Huang
 */
public class Obstacle extends Actor {
	public static final double WIDTH = 50, HEIGHT = 50;
	private Color color;
	boolean alive;
	
	/**
	 * Constructor for Obstacle
	 * @param x top left x coordinate
	 * @param y top left y coordinate
	 */
	public Obstacle(double x, double y) {
		super(x, y);		
		super.setImage((new ImageIcon("img/obstaclealive.png")).getImage());
		color = new Color(102, 51, 0);
		alive = true;
	}
	
	/**
	 * Describes how the Obstacle dies.
	 */
	public void die() {
		super.setImage((new ImageIcon("img/obstaclebroken.png")).getImage());
		color = Color.LIGHT_GRAY;
		alive = false;
	}
	
	/**
	 * Returns whether the obstacle has been broken or not
	 * @return alive (true if alive, false is broken)
	 */
	public boolean isAlive() {
		return alive;
	}
	
	public Rectangle getHitbox() {
		return new Rectangle(new Point(getX() - WIDTH/2, getY() - HEIGHT/2), WIDTH, HEIGHT);
	}
	
	public Color getColor() {
		return color;
	}
}
