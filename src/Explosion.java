import java.awt.image.BufferedImage;

public class Explosion extends Sprite{
    private static final long serialVersionUID = 1L;
    int oldpic  = 0;
    
    public Explosion(BufferedImage[] i,double x, double y, long delay, GamePanel p){
        super(i, x, y, delay, p);
    }
    
    @Override
    public void doLogic(long delta){
        oldpic = currentpic;
        super.doLogic(delta);
        if(currentpic==0 && oldpic==0){
            remove = true;
        }
    }

    @Override
    public boolean colliededWith(Sprite s) {
        return false;
    }
    
}
