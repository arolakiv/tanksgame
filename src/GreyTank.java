import java.awt.Color;
import javax.swing.ImageIcon;

/**
 * This class represents the GreyTank.
 * The GreyTank moves randomly and shoots a projectile towards the PlayerTank's current position on a regular interval.
 * @author Jonathan Huang
 */

public class GreyTank extends Tank {
	private static final int vel = 3;
	private static final int projMax = 1;
	private static final int mineMax = 0;
	private static final int MAX_TIME = 100;
	private int timeAct;
	
	/**
	 * Constructor for GreyTank
	 * @param x top left x coordinate
	 * @param y top left y coordinate
	 * @param gp associated GamePanel
	 */
	public GreyTank(double x, double y, GamePanel gp) {
		super(x, y, ProjectileType.Normal, projMax, mineMax, gp);
		super.setImage((new ImageIcon("img/greytank.png")).getImage());
	}
	
	/**
	 * Describes how the GreyTank fires.
	 * It fires towards the PlayerTank's current position on a regular time interval.
	 */
	public void act() {
		if(timeAct == 0) {
			for(int i = 0; i < GamePanel.tanks.size(); i++) {
				if(GamePanel.tanks.get(i) instanceof PlayerTank) {
					setDirBarrel(GamePanel.tanks.get(i).getX(), GamePanel.tanks.get(i).getY());
					fire();
				}
			}
			setDirTank((int)(Math.random() * 360));
			timeAct = MAX_TIME;
		}
		timeAct--;
	}
	
	/**
	 * Describes how the GreyTank moves.
	 * It moves in a random direction on a regular interval.
	 */
	public void move() {
		Point oldPoint = getPoint();
		Point newPoint = new Point(getX() + vel * Math.cos(Math.toDegrees(getDirTank())), 
				getY() + vel * Math.sin(Math.toDegrees(getDirTank())));
		
		setPoint(newPoint);
		
		if(!isPossible()) setPoint(oldPoint);
	}
	
	/**
	 * Describes how the GreyTank dies.
	 */
	public void die() {
		GamePanel.tanks.remove(this);
	}
	
	public Color getColor() {
		return Color.DARK_GRAY;
	}
}
