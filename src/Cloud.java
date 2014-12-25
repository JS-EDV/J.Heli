
import java.awt.image.BufferedImage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Besitzer2
 */
public class Cloud extends Sprite{
    private static final long serialVersionUID = 1L;
    final int SPEED = 20;
    
    public Cloud(BufferedImage[] i,double x,double y,long delay, GamePanel p){
        super(i, x, y, delay, p);
        if((int)(Math.random()*2)<1){
           setHorizontalSpeed(-SPEED);
        } else {
           setHorizontalSpeed(SPEED);
       }
    } 
    
    
    @Override
    public void doLogic(long delta){
        super.doLogic(delta);
        if(getHorizontalSpeed()>0 && getX()>parent.getWidth()){
            x = -getWidth();
        }
        if(getHorizontalSpeed()<0 && (getX()+getWidth()<0)){
            x = parent.getWidth()+getWidth();
        }
    }
}
