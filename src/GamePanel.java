import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

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
	doInitializations();
	
	Thread th = new Thread(this);
	th.start();
    }
    private void doInitializations() {
	// TODO Auto-generated method stub
	
    }
    
    @Override
    public void run(){
	while(frame.isVisible()){
	    try {
		Thread.sleep(10);
	    } catch (InterruptedException e) {
		
	    }
	}
    }
    
    

}
