import javax.swing.ImageIcon;
import java.awt.Color;

public class GreenTank extends Tank {
	private static final int projMax = 2;
	private static final int mineMax = 0;
	private static final int MAX_TIME = 100;
	private int timeAct;
	
	public GreenTank(double x, double y, GamePanel gp) {
		super(x, y, ProjectileType.SuperFast, projMax, mineMax, gp);
	}
	
	public void act() {
		if(timeAct == 0) {
			Tank t = new PlayerTank(getGP());
			
			for(int i = 0; i < GamePanel.tanks.size(); i++) {
				if(GamePanel.tanks.get(i) instanceof PlayerTank) {
					t = GamePanel.tanks.get(i);
					break;
				}
			}
			
			Point ori = t.getPoint();
			boolean fire = false;
			
			for(int i = 0; i < 360; i++) {
				if(fire) break;
				setDirBarrel(i);
				Projectile test = new Projectile(ProjectileType.SuperFastTest, getBarrelX(), getBarrelY(), i, this);
				
				while(test.getExist()) {
					test.move();
					t.move();
					
					if(test.getHitbox().intersect(t.getHitbox())) {
						fire();
						fire = true;
						break;
					}
				}
			}
			
			t.setPoint(ori);
			
			timeAct = MAX_TIME;
		}
		timeAct--;
	}
	
	public void die() {
		GamePanel.tanks.remove(this);
	}
	
	public void move() {
		
	}
	
	public Color getColor() {
		return new Color(102, 255, 102);
	}
}
