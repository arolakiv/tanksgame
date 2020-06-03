import javax.swing.ImageIcon;
import java.awt.Color;

/**
 * This class represents the TealTank.
 * The TealTank moves randomly, and fires a Fast projectile towards the PlayerTank's current position.
 * @author Jonathan Huang
 */
public class TealTank extends Tank {
	private static final int vel = 3;
	private static final int projMax = 1;
	private static final int mineMax = 0;
	private static final int MAX_TIME = 100;
	private int timeAct;
	
	/**
	 * Constructor for TealTank
	 * @param x top left coordinate
	 * @param y top left coordinate
	 * @param gp associated GamePanels
	 */
	public TealTank(double x, double y, GamePanel gp) {
		super(x, y, ProjectileType.Fast, projMax, mineMax, gp);
		super.setImage((new ImageIcon("img/tealtank.png")).getImage());
	}
	
	/**
	 * Describes how the TealTank fires.
	 * It fires towards the TealTank's current position with a Fast projectile.
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
	 * Describes how the TealTank dies.
	 */
	public void die() {
		GamePanel.tanks.remove(this);
	}
	
	/**
	 * Describes how the TealTank moves.
	 * It moves randomly on a regular time interval.
	 */
	public void move() {
		Point oldPoint = getPoint();
		Point newPoint = new Point(getX() + vel * Math.cos(Math.toDegrees(getDirTank())), getY() + vel * Math.sin(Math.toDegrees(getDirTank())));
		
		setPoint(newPoint);
		
		if(!isPossible()) setPoint(oldPoint);
	}
	
	public Color getColor() {
		return new Color(0, 128, 128);
	}
}
