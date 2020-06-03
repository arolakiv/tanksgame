import javax.swing.ImageIcon;
import java.awt.Color;

/**
 * This class represents the BrownTank.
 * The BrownTank is unable to move and shoots a normal projectile in a random direction.
 * @author Jonathan Huang
 */

public class BrownTank extends Tank {
	private static final int projMax = 1;
	private static final int mineMax = 0;
	private static final int MAX_TIME = 100;
	private int timeDir;
	
	/**
	 * Constructor for BrownTank
	 * @param x top left x coordinate
	 * @param y top left y coordinate
	 * @param gp associated GamePanel
	 */
	public BrownTank(double x, double y, GamePanel gp) {
		super(x, y, ProjectileType.Normal, projMax, mineMax, gp);
		super.setImage((new ImageIcon("img/browntank.png")).getImage());
		timeDir = MAX_TIME;
	}
	
	/**
	 * Describes how the BrownTank fires. 
	 * It fires randomly in a random direction on a regular time interval.
	 */
	public void act() {
		if(timeDir == 0) {
			setDirBarrel((int)(Math.random() * 360));
			fire();
			timeDir = MAX_TIME;
		}
		timeDir--;
	}
	
	/**
	 * Describes how the BrownTank moves.
	 * It does not move.
	 */
	public void move() {
		
	}
	
	/**
	 * Describes how the BrownTank dies.
	 */
	public void die() {
		GamePanel.tanks.remove(this);
	}
	
	public Color getColor() {
		return new Color(153, 102, 0);
	}
}
