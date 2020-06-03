import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Listens for MouseClicks to control the Tank.
 * @author Jonathan Huang
 */
public class PlayerMouseListener extends MouseAdapter {
	private PlayerTank tank;
	
	/**
	 * Constructor for PlayerMouseListener
	 * @param t associated Tank
	 */
	public PlayerMouseListener(PlayerTank t) {
		tank = t;
	}
	
	/**
	 * Detects when the mouse is released.
	 * Fires a projectile at the mouse's current coordinates.
	 */
    public void mouseReleased(MouseEvent e) {
    	tank.fire();
    }

}
