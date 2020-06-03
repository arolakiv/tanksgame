import java.awt.Color;

import javax.swing.ImageIcon;

/**
 * This class represents the Mines.
 * Mines are objects that can be detonated with a projectile to destroy Obstacles and other Tanks.
 * @author Jonathan Huang
 */
public class Mine extends Actor {
	public static final double RADIUS = 10.0;
	public static final double EXPLOSION_RADIUS = 100.0;
	Tank tank;
	
	/**
	 * Constructor for Mine.
	 * @param centerx center x coordinate
	 * @param centery center y coordinate
	 * @param t associated Tank
	 */
	public Mine(double centerx, double centery, Tank t) {
		super(centerx, centery);
		super.setImage((new ImageIcon("img/mine.png")).getImage());
		tank = t;
	}
	
	/**
	 * Describes how the mine explodes.
	 * It checks all objects to see if the objects are within the explosion RADIUS.
	 */
	public void explode() {
		tank.mineNum--;
		
		for(int i = 0; i < GamePanel.tanks.size(); i++) {
			if(inExplosion(GamePanel.tanks.get(i).getX(), GamePanel.tanks.get(i).getY())) {
				GamePanel.tanks.get(i).die();
			}
		}
		
		for(int i = 0; i < GamePanel.obstacles.size(); i++) {
			if(inExplosion(GamePanel.obstacles.get(i).getX(), GamePanel.obstacles.get(i).getY())) {
				GamePanel.obstacles.get(i).die();
			}
		}
		
		int i = 0;
		while(i < GamePanel.mines.size()) {
			if(inExplosion(GamePanel.mines.get(i).getX(), GamePanel.mines.get(i).getY()) && this != GamePanel.mines.get(i)) {
				GamePanel.mines.remove(i);
			}
			i++;
		}
		
		GamePanel.mines.remove(this);
	}
	
	private boolean inExplosion(double x, double y) {
		return (x - getX()) * (x - getX()) + (y - getY()) * (y - getY()) <= EXPLOSION_RADIUS * EXPLOSION_RADIUS;
	}
	
	public Rectangle getHitbox() {
		// because visual hitbox is circle, make a smaller rectangle that roughly corresponds
		
		return new Rectangle(new Point(getX() - RADIUS/(2 * Math.sqrt(2)), getY() - RADIUS/(2 * Math.sqrt(2))), 
				RADIUS/Math.sqrt(2), RADIUS/Math.sqrt(2));
	}
	
	public Color getColor() {
		return Color.YELLOW;
	}
}
