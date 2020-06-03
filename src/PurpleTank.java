import javax.swing.ImageIcon;
import java.awt.Color;

public class PurpleTank extends Tank {
	private static final int vel = 3;
	private static final int projMax = 5;
	private static final int mineMax = 2;
	private static final int MAX_TIME = 50;
	private int timeAct;
	
	public PurpleTank(double x, double y, GamePanel gp) {
		super(x, y, ProjectileType.Normal, projMax, mineMax, gp);
	}
	
	public void act() {
		if(timeAct == 0) {
			if(Math.random() < 0.5) {
				for(int i = 0; i < GamePanel.tanks.size(); i++) {
					if(GamePanel.tanks.get(i) instanceof PlayerTank) {
						setDirBarrel(GamePanel.tanks.get(i).getX(), GamePanel.tanks.get(i).getY());
						fire();
					}
				}
			}
			
			else {
				createMine();
			}
			
			for(int i = 0; i < GamePanel.tanks.size(); i++) {
				if(GamePanel.tanks.get(i) instanceof PlayerTank) {
					setDirTank(GamePanel.tanks.get(i).getX(), GamePanel.tanks.get(i).getY());
				}
			}
			
			setDirTank(getDirTank() + (int) (Math.random() * 270 - 135));
			
			timeAct = MAX_TIME;
		}
		timeAct--;
	}
	
	public void die() {
		GamePanel.tanks.remove(this);
	}
	
	public void move() {
		Point oldPoint = getPoint();
		Point newPoint = new Point(getX() + vel * Math.cos(Math.toDegrees(getDirTank())), getY() + vel * Math.sin(Math.toDegrees(getDirTank())));
		
		setPoint(newPoint);
		
		if(!isPossible()) setPoint(oldPoint);
	}
	
	public Color getColor() {
		return new Color(209, 84, 84);
	}
}
