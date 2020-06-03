import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.geom.Ellipse2D.Double;

import javax.swing.*;

import java.util.ArrayList;

/**
 * A GamePanel stores all the graphical elements and includes global variables storing all of the actors.
 * This GamePanel moves between different modes which represent different sections of the game.
 * @author Jonathan Huang
 */

public class GamePanel extends JPanel implements ActionListener {
	/**
	 * TIME_INTERVAL represents the number of milliseconds between visual updates.
	 * A value of 10 corresponds to a 100 fps game.
	 */
	public static final int TIME_INTERVAL = 10;
	/**
	 * FINAL_LEVEL represents the final level of the game.
	 */
	public static final int FINAL_LEVEL = 20;
	/**
	 * HEIGHT represents the height of the window.
	 */
	public static final int HEIGHT = 900;
	public static final int TOP_BAR_HEIGHT = 30; // the uncounted width at the top of the bar
	/**
	 * WIDTH represents the width of the window.
	 */
	public static final int WIDTH = 1600;
	/**
	 * LVLSEQTIME represents the number of milliseconds for the level screen.
	 */
	public static final int LVLSEQTIME = 200;
	/**
	 * projectiles represents the global ArrayList of Projectiles.
	 */
	public static ArrayList<Projectile> projectiles;
	/**
	 * obstacles represents the global ArrayList of Obstacles.
	 */
	public static ArrayList<Obstacle> obstacles;
	/**
	 * mines represents the global ArrayList of Mines.
	 */
	public static ArrayList<Mine> mines;
	/**
	 * tanks represents the global ArrayList of tanks.
	 */
	public static ArrayList<Tank> tanks;
	
	private Timer timer;
	private int mode; // 0 - title, 1 - lobby, 2 - game
	private int lvl; // 0 for non-game
	private int lvlSeq; // 200 ms to display lvl
	private ArrayList<KeyListener> listeners;
	private boolean start = false, end = false, pause = false;
	private JButton singlePlayer;
	private PlayerTank pt;
	private PlayerKeyListener pkl;
	private PlayerMouseListener pml;
	private GeneralKeyListener gkl;
	
	/**
	 * Constructor for GamePanel.
	 * Represents the initialization of all of the elements to start the game.
	 */
	public GamePanel() {
		tanks = new ArrayList<Tank>();
		projectiles = new ArrayList<Projectile>();
		obstacles = new ArrayList<Obstacle>();
		mines = new ArrayList<Mine>();
		
		listeners = new ArrayList<KeyListener>();
		
		mode = 0;
		lvl = 0;        
		setFocusable( true );
		singlePlayer = new JButton("Single Player");
		
		this.add(singlePlayer);
		this.setLayout(new GridLayout(0, 2));
		pt = new PlayerTank(300, 300, this);
		
		timer = new Timer(TIME_INTERVAL, this);
		timer.start();
	}
	
	/**
	 * An action is performed when the timer triggers an action, which causes the game to be redrawn.
	 */
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	/**
	 * Describes how the game is drawn, dependent on the mode.
	 * Mode 0 - Title
	 * Mode 1 - Multi player Lobby
	 * Mode 2 - Game
	 * Mode 3 - End Screen
	 */
	public void paint(Graphics g) {
		switch (mode) {
			case -1: // debug
				drawGame(g);
				break;
			case 0:
				drawTitle(g);
				break;
			case 1:
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(0, 0, WIDTH, HEIGHT);
				g.setColor(Color.BLACK);
				g.setFont(new Font("SansSerif", Font.PLAIN, 50));
				g.drawString("W/S to move forwards/backwards.", WIDTH/10, HEIGHT/2 - 150);
				g.drawString("A/D to turn left/right.", WIDTH/10, HEIGHT/2 - 50);
				g.drawString("Spacebar to place a mine, Left-click to shoot a projectile.", WIDTH/10, HEIGHT/2 + 50);
				g.drawString("Press \"R\" to start game.", WIDTH/10, HEIGHT/2 + 100);
				gkl = new GeneralKeyListener(this);
				addKeyListener(gkl);
				break;
			case 2:
				if(end) {
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(0, 0, WIDTH, HEIGHT);
					g.setColor(Color.BLACK);
					g.setFont(new Font("SansSerif", Font.PLAIN, 50));
					g.drawString("CONGRATULATIONS!!!", WIDTH/4, HEIGHT/2);
				}
				else if(tanks.size() == 1 && tanks.get(0) instanceof PlayerTank && !end && lvlSeq <= 0) {
					setLevel(lvl + 1);
				}
				else if(lvlSeq > 0) {
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(0, 0, WIDTH, HEIGHT);
					g.setColor(Color.BLACK);
					g.setFont(new Font("SansSerif", Font.PLAIN, 50));
					g.drawString("LEVEL " + lvl, WIDTH/4, HEIGHT/2);
					lvlSeq--;
				}
				else {
					if(lvlSeq == 0) {
						lvlSeq--;
					}
					drawGame(g);
				}
				break;
			case 3:
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(0, 0, WIDTH, HEIGHT);
				g.setColor(Color.BLACK);
				g.setFont(new Font("SansSerif", Font.PLAIN, 50));
				g.drawString(":( - Press \"R\" to restart.", WIDTH/4, HEIGHT/2);
				
				start = false;
				gkl = new GeneralKeyListener(this);
				addKeyListener(gkl);
				
				removeKeyListener(pkl);
				removeMouseListener(pml);
				
		}
	}
	
	/**
	 * Draws the title.
	 * @param g Graphics
	 */
	public void drawTitle(Graphics g) {
		if(singlePlayer.getModel().isPressed()) {
			removeAll();
			mode = 1;
		}
	}
	
	/**
	 * Starts the game.
	 */
	public void gameStart() {
		if(start) return;
		start = true;

		removeAll();
		
		tanks.clear();
		mines.clear();
		obstacles.clear();
		projectiles.clear();
		
		pkl = new PlayerKeyListener(pt);
		pml = new PlayerMouseListener(pt);
		
		addKeyListener(pkl);
		addMouseListener(pml);
		tanks.add(pt);
		
		mode = 2;
		setLevel(20);
	}
	
	/**
	 * Draws the game and all of its Actors.
	 * @param g Graphics
	 */
	public void drawGame(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform old = g2d.getTransform();
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRect(0, 0, WIDTH, HEIGHT);
	
		
		
		for(int i = 0; i < mines.size(); i++) {
			Mine m = mines.get(i);
			g2d.setColor(Color.YELLOW);
			g2d.fill(new Ellipse2D.Double(m.getX() - Mine.RADIUS, m.getY() - Mine.RADIUS, 2 * Mine.RADIUS, 2 * Mine.RADIUS));
		}
		
		for(int i = 0; i < projectiles.size(); i++) {
			Projectile p = projectiles.get(i);
			
			g2d.setColor(p.getColor());
			Rectangle2D.Double body = new Rectangle2D.Double(-Projectile.WIDTH/2, -Projectile.HEIGHT/2,
					Projectile.WIDTH, Projectile.HEIGHT);
			g2d.translate(p.getX(), p.getY());
			g2d.rotate(Math.toRadians(p.getDir()));
			g2d.fill(body);
			g2d.setTransform(old);
			
			p.move();
		}
		
		
		for(Tank t : tanks) {		
			if(t instanceof PlayerTank) {
				pt.checkMouse(getLocationOnScreen().getX(), getLocationOnScreen().getY());
				int k = pt.getMovement();
				
				if((k & 8) > 0) pt.moveForward();
				if((k & 4) > 0) pt.turnLeft();
				if((k & 2) > 0) pt.turnRight();
				if((k & 1) > 0) pt.moveBackward();
			}
			
			if(t instanceof WhiteTank) {
				continue;
			}
			
			t.update();
			
			// tank body, marking, treads
			Rectangle2D.Double treads = new Rectangle2D.Double(-Tank.WIDTH/2, -Tank.HEIGHT/2, Tank.WIDTH, Tank.HEIGHT);
			Rectangle2D.Double body = new Rectangle2D.Double(-Tank.WIDTH/2, -Tank.HEIGHT*5/12, Tank.WIDTH, Tank.HEIGHT*5/6);
			Rectangle2D.Double marking = new Rectangle2D.Double(Tank.WIDTH/4, -Tank.HEIGHT/4, Tank.WIDTH/4, Tank.HEIGHT/2);
			g2d.translate(t.getX(), t.getY());
			g2d.rotate(Math.toRadians(t.getDirTank()));
			g2d.setColor(new Color(202, 164, 114));
			g2d.fill(treads);
			g2d.setColor(t.getColor());
			g2d.fill(body);
			g2d.setColor(t.getColor().darker());
			g2d.fill(marking);
			g2d.setTransform(old);
			
			// tank barrel
			
			g2d.setColor(Color.GREEN);
			Rectangle2D.Double barrel = new Rectangle2D.Double(0, -0.5 * Tank.BARREL_WIDTH, Tank.BARREL_HEIGHT, Tank.BARREL_WIDTH);
	        g2d.translate(t.getX(), t.getY());
			g2d.rotate(Math.toRadians(t.getDirBarrel()));
			g2d.fill(barrel);
			g2d.setTransform(old);
			
			// tank center
			
			g2d.setColor(Color.BLACK);
			g2d.fill(new Ellipse2D.Double(t.getX() - 0.5 * Tank.INRADIUS, 
					t.getY() - 0.5 * Tank.INRADIUS, Tank.INRADIUS, Tank.INRADIUS));
			
			// tank tracks
		}
		
		for(Obstacle o : obstacles) {
			if(!o.isAlive()) continue;
			g2d.setColor(o.getColor());
			g2d.fill(new Rectangle2D.Double(o.getX() - Obstacle.WIDTH/2, o.getY() - Obstacle.HEIGHT/2, Obstacle.WIDTH, Obstacle.HEIGHT));
		}
	}
	
	/**
	 * Adds a tank to the global ArrayList tanks
	 * @param t Tank
	 */
	public void addTank(Tank t) {
		tanks.add(t);
	}
	
	/**
	 * Adds an obstacle to the global ArrayList obstacles
	 * @param o Obstacle
	 */
	public void addObstacle(Obstacle o) {
		obstacles.add(o);
	}
	
	/**
	 * Sets the level of the game to certain number
	 * @param l Level
	 */
	public void setLevel(int l) {
		if(l > FINAL_LEVEL) end = true;
		
		lvl = l;
		lvlSeq = LVLSEQTIME;
		
		for(int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i);
			if(!(t instanceof PlayerTank)) tanks.remove(t);
		}
		pt.projNum = 0;
		pt.setMovement(0);
		pt.mineNum = 0;
		
		while(obstacles.size() > 0) {
			obstacles.remove(obstacles.get(0));
		}
		
		while(mines.size() > 0) {
			mines.remove(mines.get(0));
		}
		
		while(projectiles.size() > 0) {
			projectiles.remove(projectiles.get(0));
		}
		
		tanks.get(0).setPoint(LevelInfo.getStarting(lvl));
		tanks.addAll(LevelInfo.getTanks(lvl));
		
		for(Tank t : tanks) {
			t.setGP(this);
		}
		
		obstacles.addAll(LevelInfo.getObstacles(lvl));
	}
	
	/**
	 * Ends the game.
	 */
	public void gameOver() {
		mode = 3;
	}	
}