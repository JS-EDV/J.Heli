import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.IOException;
import java.net.URL;
import java.util.ListIterator;
import java.util.Vector;

import javax.imageio.ImageIO;



public class GamePanel extends JPanel implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	JFrame frame;

	long delta = 0;
	long last = 0;
	long fps = 0;
	
	Sprite copter;
	Vector<Sprite> actors;
	Vector<Sprite> painter;
	
	boolean up;
	boolean down;
	boolean left;
	boolean right;
	boolean started;
	int speed = 50;
	

	public static void main(String[] args) {
		new GamePanel(800, 600);
	}

	public GamePanel(int w, int h) {
		this.setPreferredSize(new Dimension(w, h));
		frame = new JFrame("Helicopter-Game adepted by a \"Java-Forum.org\"-Tutorial ");
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.addKeyListener(this);
		frame.pack();
		frame.setVisible(true);
		doInitializations();

		Thread th = new Thread(this);
		th.start();
	}

	private void doInitializations() {
		last = System.nanoTime();
		
		BufferedImage[] heli = loadPics("pics/heli.gif", 4);
		
		actors = new Vector<Sprite>();
		painter = new Vector<Sprite>();
		copter = new Sprite(heli, 400, 300, 100, this);
		actors.add(copter);
		
		started = true;
	}

	@Override
	public void run() {
		while (frame.isVisible()) {
			computeDelta();

			checkKeys();
			doLogic();
			moveObjects();
			cloneVectors();

			repaint();
			try {
				Thread.sleep(15); // max.FrameRate=30FPS @ Thread.sleep(33)++
			} catch (InterruptedException e) {

			}
		}
	}
	
	@SuppressWarnings(value = { "unchecked" })
	private void cloneVectors() {
		painter = (Vector<Sprite>) actors.clone();
		
	}

	private void moveObjects() {
		for(ListIterator<Sprite> it = actors.listIterator();it.hasNext();){
			Sprite r = it.next();
			r.move(delta);
		}
	}

	private void doLogic() {
		for(ListIterator<Sprite> it = actors.listIterator();it.hasNext();){
			Sprite r = it.next();
			r.doLogic(delta);
		}
	}

	private void checkKeys() {
		if(up){
			copter.setVerticalSpeed(-speed);
		}
		if(down){
			copter.setVerticalSpeed(speed);
		}
		if(left){
			copter.setHorizontalSpeed(-speed);
		}
		if(right){
			copter.setHorizontalSpeed(speed);
		}
		if(!down && !up){
			copter.setVerticalSpeed(0);
		}
		if(!left && !right){
			copter.setHorizontalSpeed(0);
		}		
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
		
		if(!started){
			return;
		}
		
		for(ListIterator<Sprite> it = painter.listIterator();it.hasNext();){
			Sprite r = it.next();
			r.drawObjects(g);
		}
	}
	
	private BufferedImage[] loadPics(String path, int pics) {
		BufferedImage[] anim = new BufferedImage[pics];
		BufferedImage source = null;
		
		URL pic_url = getClass().getClassLoader().getResource(path);
		
		try{
			source = ImageIO.read(pic_url);
		} catch (IOException e){ }
		
		for(int x=0;x<pics;x++){
			anim[x] = source.getSubimage(x*source.getWidth()/pics, 0, source.getWidth()/pics, source.getHeight());
		}
		
		return anim;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP){
			up = true;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			down = true;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			left = true;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			right = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP){
			up = false;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			down = false;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			left = false;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			right = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
