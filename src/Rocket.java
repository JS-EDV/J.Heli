
//import java.awt.Color;        <-activate for testing-purpose
//import java.awt.Graphics;     <-activate for testing-purpose
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Rocket extends Sprite{
    private static final long serialVersionUID = 1L;
    
    int verticalSpeed = 70;
//  int verticalspeed = 70; <- by Tutorial. Why not camelCase ?
    
    Rectangle2D.Double target;
    boolean locked =false;
    
    public Rocket(BufferedImage[] i, double x, double y, long delta, GamePanel p){
        super(i, x, y, delta, p);
        
        if(getY()<parent.getHeight()/2){
            setVerticalSpeed(verticalSpeed);
        }else{
            setVerticalSpeed(-verticalSpeed);
        }
        
    }
    
    @Override
    public void doLogic(long delta){
        super.doLogic(delta);
        
        if(getHorizontalSpeed()>0){
            target = new Rectangle2D.Double(getX()+getWidth(), getY(), parent.getWidth()-getX(),getHeight());
        } else {
            target = new Rectangle2D.Double(0,getY(), getX(), getHeight());
        }
        
        if(!locked && parent.copter.intersects(target)){
            setVerticalSpeed(0);
            locked = true;
        }
        
        if(locked){
            if(getY()<parent.copter.getY()){
                setVerticalSpeed(40);
            }
            if(getY()>parent.copter.getY()+parent.copter.getHeight()){
                setVerticalSpeed(-40);
            }
        }
        
        if(getHorizontalSpeed()>0 && getX()>parent.getWidth()){
            remove = true;
        }
        if(getHorizontalSpeed()<0 && getX()+getWidth()<0){
            remove = true;                    
        }
    }
    @Override
    public void setHorizontalSpeed(double d) {
        super.setHorizontalSpeed(d);
    
        if(getHorizontalSpeed()>0){
            setLoop(4,7);
        } else {
            setLoop(0,3);
        }
    }
    /*
    * Testing Purpose 
   `* Collison-Model
    /
    @Override
    public void drawObjects(Graphics g){
        super.drawObjects(g);
        g.setColor(Color.ORANGE);
        g.drawRect((int)target.x, (int)target.y, (int)target.width, (int)target.height);
    }
    */
}