import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	JFrame frame;

	long delta = 0;
	long last = 0;
	long fps = 0;

	public static void main(String[] args) {
		new GamePanel(800, 600);
	}

	public GamePanel(int w, int h) {
		this.setPreferredSize(new Dimension(w, h));
		frame = new JFrame(
				"Jörgs Helicopter-Game adepted by a \"Java-Forum.org\"-Tutorial ");
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
		doInitializations();

		Thread th = new Thread(this);
		th.start();
	}

	private void doInitializations() {
		last = System.nanoTime();

	}

	@Override
	public void run() {
		while (frame.isVisible()) {
			computeDelta();

			checkKeys();
			doLogic();
			moveObjects();

			repaint();
			try {
				Thread.sleep(15); // max.FrameRate=30FPS @ Thread.sleep(33)++
			} catch (InterruptedException e) {

			}
		}
	}

	private void moveObjects() {
		// TODO Auto-generated method stub

	}

	private void doLogic() {
		// TODO Auto-generated method stub

	}

	private void checkKeys() {
		// TODO Auto-generated method stub

	}

	private void computeDelta() {
		delta = System.nanoTime() - last;
		last = System.nanoTime();
		fps = ((long) 1e9) / delta;

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.RED);
		g.drawString("FPS: " + Long.toString(fps), 20, 10);
	}

}
