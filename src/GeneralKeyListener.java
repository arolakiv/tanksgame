import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Listens for KeyReleases during the title screen
 * @author Jonathan Huang
 */
public class GeneralKeyListener implements KeyListener {
	GamePanel gp;
	
	/**
	 * Constructor for GeneralKeyListener for an associated GamePanel
	 * @param ngp GamePanel
	 */
	public GeneralKeyListener(GamePanel ngp) {
		gp = ngp;
	}
	
	/**
	 * Must be defined, but empty.
	 */
	public void keyPressed(KeyEvent e) {}
	
	/**
	 * Tracks when the space bar is pressed to start the game.
	 */
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_R:
				gp.gameStart();
				break;
		}
	}
	
	/**
	 * Must be defined, but empty.
	 */
	public void keyTyped(KeyEvent e) {}
}
