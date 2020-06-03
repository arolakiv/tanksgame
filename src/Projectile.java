import java.awt.Color;

import javax.swing.ImageIcon;

/**
 * This class represents Projectiles fired by Tanks.
 * @author Jonathan Huang
 */
public class Projectile extends Actor {
	public static final int WIDTH = 10, HEIGHT = 10;
	
	private double dir; // in degrees
	private double vel;
	private int ricochetMax, ricochetCur; //cur < max;
	private ProjectileType projType;
	private Tank tank;
	private Color color;
	private boolean exist, test;
	
	/**
	 * Constructor for Projectile
	 * @param pt ProjectileType
	 * @param centerx center x coordinate
	 * @param centery center y coordinate
	 * @param d direction in clockwise degrees (0 is to the right)
	 * @param t associated Tank
	 */
	public Projectile(ProjectileType pt, double centerx, double centery, double d, Tank t) {
		super(centerx, centery);
		dir = d;
		projType = pt;
		tank = t;
		exist = true;
		test = false;
		
		switch (projType) {
			case Normal:
				vel = 4.0;
				ricochetMax = 1;
				color = Color.WHITE;
				break;
			case Fast:
				vel = 8.0;
				ricochetMax = 0;
				color = Color.RED;
				break;
			case SuperFast:
				vel = 12.0;
				ricochetMax = 2;
				color = new Color(125, 249, 255);
				break;
			case SuperFastTest:
				vel = 12.0;
				ricochetMax = 2;
				test = true;
				break;
		}
	}
	
	/**
	 * Describes how a projectile moves based on its direction and current position.
	 * Projectile will reflect across the boundaries of the windows and Obstacles.
	 */
	public void move() {
		Point oldPoint = new Point(getPoint());
		
		double x = getX() + vel * Math.cos(Math.toRadians(dir));
		double y = getY() + vel * Math.sin(Math.toRadians(dir));
		
		setX(x);
		setY(y);
		
		Rectangle newHitbox = getHitbox();
		Point newPoint = new Point(getPoint());
		
		if(exist && !test) {
			for(int i = 0; i < GamePanel.projectiles.size(); i++) {
				Projectile p = GamePanel.projectiles.get(i);
				
				if(p != this && newHitbox.intersect(p.getHitbox())) {
					GamePanel.projectiles.remove(p);
					p.getTank().projNum--;
					GamePanel.projectiles.remove(this);
					tank.projNum--;
					return;
				}
			}
			
			for(int i = 0; i < GamePanel.tanks.size(); i++) {
				Tank t = GamePanel.tanks.get(i);
				
				if(newHitbox.intersect(t.getHitbox())) {
					t.die();
					GamePanel.projectiles.remove(this);
					tank.projNum--;
					return;
				}
			}
			
			for(int i = 0; i < GamePanel.mines.size(); i++) {
				Mine m = GamePanel.mines.get(i);
				if(getPoint().inCircle(m.getX(), m.getY(), Mine.RADIUS)) {
					m.explode();
					GamePanel.projectiles.remove(this);
					tank.projNum--;
					
					return;
				}
			}
		}
		
		for(Obstacle o : GamePanel.obstacles) {
			if(!o.isAlive()) continue;
			
			double topLeftX = o.getX() - Obstacle.WIDTH/2, topLeftY = o.getY() - Obstacle.HEIGHT/2;
			
			if(newPoint.inRectangle(o.getHitbox()) && !oldPoint.inRectangle(o.getHitbox())) {
				// TOP
				if(Point.intersect(oldPoint, newPoint, 
						new Point(topLeftX, topLeftY), new Point(topLeftX + Obstacle.WIDTH, topLeftY))) {
					reflectX();
				}
				// RIGHT
				else if(Point.intersect(oldPoint, newPoint, 
						new Point(topLeftX + Obstacle.WIDTH, topLeftY), new Point(topLeftX + Obstacle.WIDTH, topLeftY + Obstacle.HEIGHT))) {
					reflectY();
				}
				// BOTTOM
				else if(Point.intersect(oldPoint, newPoint, 
						new Point(topLeftX, topLeftY + Obstacle.HEIGHT), new Point(topLeftX + Obstacle.WIDTH, topLeftY + Obstacle.HEIGHT))) {
					reflectX();
				}
				// LEFT
				else if(Point.intersect(oldPoint, newPoint, 
						new Point(topLeftX, topLeftY), new Point(topLeftX, topLeftY + Obstacle.HEIGHT))) {
					reflectY();
				}
			}
		}
		
		if(y <= 0 || y >= GamePanel.HEIGHT - GamePanel.TOP_BAR_HEIGHT) reflectX();
		if(x <= 0 || x >= GamePanel.WIDTH) reflectY();
	}
	
	/**
	 * Describes how the projectile reflects across horizontal walls.
	 */
	public void reflectX() { // horizontal obstacle
		dir = 360.0 - dir;
		if(ricochetCur == ricochetMax) {
			if(!test) {
				GamePanel.projectiles.remove(this);
				tank.projNum--;
			}
			exist = false;
		}
		ricochetCur++;
	}
	
	/**
	 * Describes how the projectile reflects across vertical walls.
	 */
	public void reflectY() { // vertical obstacle 
		dir = (540.0 - dir);
		if(dir > 360.0) dir -= 360.0;
		if(ricochetCur == ricochetMax) {
			if(!test) {
				GamePanel.projectiles.remove(this);
				tank.projNum--;
			}
			exist = false;
		}
		ricochetCur++;
	}
	
	/**
	 * Returns the number of current ricochets.
	 * @return ricochetCur
	 */
	public int getRicochetCur() {
		return ricochetCur;
	}
	
	/**
	 * Returns the maximum number of ricochets.
	 * @return ricochetMax
	 */
	public int getRicochetMax() {
		return ricochetMax;
	}
	
	/**
	 * Returns the tank associated with the projectile.
	 * @return tank
	 */
	public Tank getTank() {
		return tank;
	}
	
	public double getDir() {
		return dir;
	}
	
	public Rectangle getHitbox() {
		double minX = getX(), maxX = getX(), minY = getY(), maxY = getY();
		
		for(int i = 0; i < 4; i++) {
			double X = getX() + WIDTH  / Math.sqrt(2) * Math.cos(Math.toRadians(45 * (1 + 2 * i) + dir));
			double Y = getY() + HEIGHT / Math.sqrt(2) * Math.sin(Math.toRadians(45 * (1 + 2 * i) + dir));
			
			minX = Math.min(minX, X);
			maxX = Math.max(maxX, X);
			minY = Math.min(minY, Y);
			maxY = Math.max(maxY, Y);
		}
		
		return new Rectangle(new Point(minX, minY), maxX - minX, maxY - minY);
	}
	
	public Color getColor() {
		return color;
	}
	
	public boolean getExist() {
		return exist;
	}
}
