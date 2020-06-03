import java.awt.Color;

/**
 * This class describes the abstract Tank class.
 * @author Jonathan Huang
 */

public abstract class Tank extends Actor {
	/**
	 * WIDTH is the width of the tank.
	 */
	public static final double WIDTH = 50;
	/**
	 * HEIGHT is the height of the tank.
	 */
	public static final double HEIGHT = 50;
	/**
	 * BARREL_WIDTH is the width of the tank barrel.
	 */
	public static final double BARREL_WIDTH = 10;
	/**
	 * BARREL_HEIGHT is the height of the tank barrel.
	 */
	public static final double BARREL_HEIGHT = 50;
	/**
	 * INRADIUS is the radius of the inner circle on the tank.
	 */
	public static final double INRADIUS = 20;
	/**
	 * RADIUS is the radius of outer barrel.
	 */
	public static final double RADIUS = 50;
	public int projNum, mineNum;
	
	private double dirBarrel, dirTank; // 0 degrees is pointing upwards
	private int projMax, mineMax;
	private ProjectileType projType;
	private GamePanel gamePanel;
	/**
	 * Constructor for Tank
	 * @param curx center x coordinate
	 * @param cury center y coordinate
	 * @param ptype ProjectileType
	 * @param projM maximum number of projectiles
	 * @param mineM maximum number of mines
	 * @param v velocity
	 * @param gp associated GamePanel
	 */
	public Tank(double curx, double cury, ProjectileType ptype, int projM, int mineM, GamePanel gp) {
		super(curx, cury);
		projType = ptype;
		projMax = projM;
		mineMax = mineM;
		gamePanel = gp;
	}
	
	/**
	 * Updates the position and status of the tank.
	 * It is triggered every single time a frame is drawn.
	 */
	public void update() {
		act();
		move();
	}
	
	/**
	 * Describes how the tank fires.
	 */
	public abstract void act();
	
	/**
	 * Describes how the tank dies.
	 */
	public abstract void die();
	
	/**
	 * Describes how the tank moves.
	 */
	public abstract void move();
	
	/**
	 * Fires a new projectile of projType if the number of projectiles is less than the maximum quantity.
	 * @return true if a projectile was fired, false otherwise
	 */
	public boolean fire() {
		if(projNum < projMax) {
			Point barrel = new Point(getBarrelX(), getBarrelY());
			
			for(Obstacle o : GamePanel.obstacles) {
				if(o.isAlive() && barrel.inRectangle(o.getHitbox())) {
					return false;
				}
			}
			
			if(!barrel.inRectangle(new Rectangle(new Point(0, 0), 
					GamePanel.WIDTH, GamePanel.HEIGHT - GamePanel.TOP_BAR_HEIGHT))) return false;
			
			GamePanel.projectiles.add(new Projectile(projType, getBarrelX(), getBarrelY(), dirBarrel, this));
			projNum++;
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Creates a mine at the current location.
	 * @return true if a mine was created, false otherwise
	 */
	public boolean createMine() {
		if(mineNum < mineMax) {
			GamePanel.mines.add(new Mine(getX(), getY(), this));
			mineNum++;
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Determines whether the tank can move into a new location without intersecting other objects.
	 * @param newCenter is the center coordinate of the new location
	 * @return true if the tank can move, false otherwise
	 */
	public boolean isPossible() {
		
		Rectangle cur = getHitbox();
		
		for(Tank t : GamePanel.tanks) {
			if(t != this && cur.intersect(t.getHitbox())) return false;
		}
		
		for(Obstacle o : GamePanel.obstacles) {
			if(o.isAlive() && cur.intersect(o.getHitbox())) return false;
		}
		
		for(Point p : cur.getVertices()) {
			if(!p.inRectangle(new Rectangle(new Point(0, 0), GamePanel.WIDTH, 
				GamePanel.HEIGHT - GamePanel.TOP_BAR_HEIGHT))) return false;
		}
		
		return true;
	}
	
	/**
	 * Returns the barrel end x coordinate
	 * @return barrel end x coordinate
	 */
	public double getBarrelX() {
		return getX() + RADIUS*Math.cos(Math.toRadians(getDirBarrel()));
	}
	
	/**
	 * Returns the barrel end y coordinate
	 * @return barrel end y coordinate
	 */
	public double getBarrelY() {
		return getY() + RADIUS*Math.sin(Math.toRadians(getDirBarrel()));
	}
	
	/**
	 * Sets the direction of the barrel
	 * @param d in clockwise degrees (0 is right)
	 */
	public void setDirBarrel(double d) {
		dirBarrel = d;
	}
	
	/**
	 * Sets the direction of the barrel towards a point
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public void setDirBarrel(double x, double y) {
		double dir = Math.toDegrees(Math.atan2(x-getX(), getY()-y));
		dir += 180;
		setDirBarrel((270-dir >= 0) ? (dir + 90) : (dir - 270));
	}
	
	/**
	 * Getter method for the direction of the barrel
	 * @return dirBarrel
	 */
	public double getDirBarrel() {
		return dirBarrel;
	}
	
	/**
	 * Setter method for the direction of the tank 
	 * @param d in degrees (0 is right)
	 */
	public void setDirTank(double d) {
		dirTank = d;
	}
	
	/**
	 * Sets the direction of the tank towards a point
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public void setDirTank(double x, double y) {
		double dir = Math.toDegrees(Math.atan2(x-getX(), getY()-y));
		dir += 180;
		setDirTank((270-dir >= 0) ? (dir + 90) : (dir - 270));
	}
	
	/**
	 * Getter method for the direction of the tank
	 * @return dirTank
	 */
	public double getDirTank() {
		return dirTank;
	}
	
	/**
	 * Setter method for the projectileType
	 * @param t ProjectileType
	 */
	public void setProjType(ProjectileType t) {
		projType = t;
	}
	
	/**
	 * Setter method for the associated GamePanel
	 * @param gp GamePanel
	 */
	public void setGP(GamePanel gp) {
		gamePanel = gp;
	}
	
	/**
	 * Getter method for the associated GamePanel
	 * @return gamePanel
	 */
	public GamePanel getGP() {
		return gamePanel;
	}
	
	/**
	 * Getter method for the associated ProjectileType
	 * @return projType
	 */
	public ProjectileType getProjType() {
		return projType;
	}
	
	public Rectangle getHitbox() {		
		double minX = getX(), maxX = getX(), minY = getY(), maxY = getY();
		
		for(int i = 0; i < 4; i++) {
			double X = getX() + WIDTH  / Math.sqrt(2) * Math.cos(Math.toRadians(45 * (1 + 2 * i) + dirTank));
			double Y = getY() + HEIGHT / Math.sqrt(2) * Math.sin(Math.toRadians(45 * (1 + 2 * i) + dirTank));
			
			minX = Math.min(minX, X);
			maxX = Math.max(maxX, X);
			minY = Math.min(minY, Y);
			maxY = Math.max(maxY, Y);
		}
		
		return new Rectangle(new Point(minX, minY), maxX - minX, maxY - minY);
	}
	
	public abstract Color getColor();
}
