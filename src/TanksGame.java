import java.awt.*;
import javax.swing.*;

/**
 * This class begins the game.
 * @author Jonathan Huang
 */
public class TanksGame extends JPanel {
	
	/**
	 * Main class that begins the game
	 * @param args useless
	 */
	public static void main(String[] args) {
		JFrame w = new JFrame("Tanks Game");
        w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        w.setSize( new Dimension( GamePanel.WIDTH, GamePanel.HEIGHT ) );
        w.add( new GamePanel() );
        w.setLocationRelativeTo( null );
        w.setResizable( false );
        w.setVisible( true );
        w.setState(JFrame.MAXIMIZED_BOTH);
	}
}