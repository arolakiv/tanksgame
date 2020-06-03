import java.awt.Color;
import java.awt.MouseInfo;
import javax.swing.ImageIcon;

/**
 * This class represents the Tank that can be controlled by the player.
 * WASD controls the Tank's movement, Mouseclicks fire a projectile, and Spacebar lays a mine.
 * @author Jonathan Huang
 */

public class PlayerTank extends Tank {
	private static double linVel = 2;
	private static double rotVel = 2; 
	private static int projMax = 5;
	private static int mineMax = 1;
	private int movement; // 4-bits to determine WASD
	
	public PlayerTank(GamePanel gp) {
		this(300, 300, gp);
	}
	
	
	/**
	 * Constructor for PlayerTank
	 * @param xpos center x coordinate
	 * @param ypos center y coordinate
	 * @param gp associated GamePanel
	 */
	public PlayerTank(int xpos, int ypos, GamePanel gp) {
		super(xpos, ypos, ProjectileType.Normal, projMax, mineMax, gp);
		super.setImage((new ImageIcon("img/playertank.png")).getImage());
	}
	
	/**
	 * Moves the tank upwards.
	 */
	public void moveForward() {
		Point ori = getPoint();
		Point np = new Point(getX() + linVel * Math.cos(Math.toRadians(getDirTank())),
				getY() + linVel * Math.sin(Math.toRadians(getDirTank())));
		
		setPoint(np);
		
		if(!isPossible()) setPoint(ori);
	}
	
	/**
	 * Moves the tank downwards.
	 */
	public void moveBackward() {
		Point ori = getPoint();
		Point np = new Point(getX() - linVel * Math.cos(Math.toRadians(getDirTank())),
				getY() - linVel * Math.sin(Math.toRadians(getDirTank())));
		
		setPoint(np);
		
		if(!isPossible()) setPoint(ori);
	}
	
	/**
	 * Moves the tank rightwards.
	 */
	public void turnLeft() {
		setDirTank((getDirTank() - rotVel + 360) % 360);
		
		if(!isPossible()) setDirTank((getDirTank() + rotVel + 360) % 360);
	}
	
	/**
	 * Moves the tank leftwards.
	 */
	public void turnRight() {
		setDirTank((getDirTank() + rotVel + 360) % 360);
		
		if(!isPossible()) setDirTank((getDirTank() - rotVel + 360) % 360);
	}
	
	/**
	 * Checks the current position of the mouse, and changes the tank's direction to face the mouse.
	 * @param x relative screen x coordinate of JPanel
	 * @param y relative screen y coordinate of JPanel
	 */
	public void checkMouse(double x, double y) {
		double mouseX = MouseInfo.getPointerInfo().getLocation().getX() - x;
		double mouseY = MouseInfo.getPointerInfo().getLocation().getY() - y;
		setDirBarrel(mouseX, mouseY);
	}
	
	/**
	 * Describes how the PlayerTank dies.
	 */
	public void die() {
		getGP().gameOver();
	}
	
	/**
	 * Must be defined, but empty since firing is determined by user.
	 */
	public void act() {}
	
	/**
	 * Must be defined, but empty since movement is determined by user.
	 */
	public void move() {}
	
	public void setMovement(int mvmt) {
		movement = mvmt;
	}
	
	public int getMovement() {
		return movement;
	}
	
	public Color getColor() {
		return Color.BLUE;
	}
}
