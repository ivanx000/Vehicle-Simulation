import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Explosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bomb extends Actor
{
    //Mr. Cohen's bomb demo code
    private boolean entering;
    private int destination;
    private int exitCountdown;
    private GreenfootImage explodeImage;

    public Bomb () {
        explodeImage = new GreenfootImage (300, 300);
        explodeImage.setColor(Color.RED);
        explodeImage.fillOval (0,0,299, 299);

        exitCountdown = 60;
        entering = true;
    }

    public void addedToWorld (World w){
        destination = 451;
    }

    /**
     * Act - do whatever the Explosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (entering){
            setLocation(getX(), getY() + 4);
            if (getY() >= destination){
                setLocation (getX(), destination);
                entering = false; // flip to other State - no longer "entering"
                setImage(explodeImage);
                //damageTouching();
            }

        } else {
            getWorld().addObject (new Explosion (300, 20), getX(), getY());
            getWorld().removeObject(this);
        }
    }
    private void damageTouching () {
        ArrayList<Vehicle> touching = (ArrayList<Vehicle>)getIntersectingObjects(Vehicle.class);
        for (Vehicle v : touching){
            getWorld().removeObject(v);
        }

    }
}
