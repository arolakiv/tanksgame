import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Listens for KeyPresses to control the Tank.
 * @author Jonathan Huang
 */
public class PlayerKeyListener implements KeyListener {
	private PlayerTank tank;
	
	/**
	 * Constructor for PlayerKeyListener
	 * @param t associated Tank
	 */
	public PlayerKeyListener(PlayerTank t) {
		tank = t;
	}
	
	/**
	 * Detects when a key is pressed.
	 * WASD controls the movements of the tank.
	 * Space lays a mine at the current location.
	 */
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_W:
				tank.setMovement(tank.getMovement() | 8);
				break;
			case KeyEvent.VK_A:
				tank.setMovement(tank.getMovement() | 4);
				break;
			case KeyEvent.VK_D:
				tank.setMovement(tank.getMovement() | 2);
				break;
			case KeyEvent.VK_S:
				tank.setMovement(tank.getMovement() | 1);
				break;
			case KeyEvent.VK_SPACE:
				tank.createMine();
				break;
		}
	}
	
	/**
	 * Must be defined, but empty.
	 */
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_W:
				tank.setMovement(tank.getMovement() ^ 8);
				break;
			case KeyEvent.VK_A:
				tank.setMovement(tank.getMovement() ^ 4);
				break;
			case KeyEvent.VK_D:
				tank.setMovement(tank.getMovement() ^ 2);
				break;
			case KeyEvent.VK_S:
				tank.setMovement(tank.getMovement() ^ 1);
				break;
			case KeyEvent.VK_SPACE:
				tank.createMine();
				break;
		}
	}
	
	/**
	 * Must be defined, but empty.
	 */
	public void keyTyped(KeyEvent e) {}
}
