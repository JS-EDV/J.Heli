import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    JFrame frame;
    
    public static void main(String[] args) {
	new GamePanel(800, 600); 
    }
    public GamePanel(int w, int h) {
	this.setPreferredSize(new Dimension(w, h));
	frame = new JFrame("GameDemo");
	frame.setLocation(100,100);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.add(this);
	frame.pack();
	frame.setVisible(true);
    }
    
    

}
