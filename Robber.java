import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Woman here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Robber extends Pedestrian
{
    private ArrayList<Man> people;
    private Man targetMan;
    private GreenfootSound help;
    
    public Robber(int direction) {
        super(direction); 
        enableStaticRotation();
        
        help = new GreenfootSound("help.mp3");
        help.setVolume(50);       
    }
    /**
     * Act - do whatever the Woman wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        //looks for and targets closest man/pedestrian 
        if (targetMan != null && targetMan.getWorld() == null){
            targetMan = null;
        }
        if (targetMan == null || getDistance (this, targetMan) > 20){
            targetClosestPerson ();
        }
        if (targetMan != null)
        {
            moveTowardOrRob();
        }
        else
        {
            moveRandomly();
        }
    }
    // Mr.Cohen's code from bug simulation
    private void targetClosestPerson ()
    {
        double closestTargetDistance = 0;
        double distanceToActor;

        people = (ArrayList<Man>)getObjectsInRange(40, Man.class);
        if (people.size() == 0){

            people = (ArrayList<Man>)getObjectsInRange(140, Man.class);
        } 
        if (people.size() == 0){

            people = (ArrayList<Man>)getObjectsInRange(350, Man.class);
        } 

        if (people.size() > 0)
        {
            targetMan = people.get(0);

            closestTargetDistance = getDistance(this, targetMan);

            for (Man o : people)
            {

                distanceToActor = getDistance(this, o);

                if (distanceToActor < closestTargetDistance)
                {
                    targetMan = o;
                    closestTargetDistance = distanceToActor;
                }
            }
            turnTowards(targetMan.getX(), targetMan.getY());
        }
    }
    private void moveTowardOrRob ()
    {
        VehicleWorld vw = (VehicleWorld)getWorld();
        if (getDistance (this, targetMan) < 18)
        {
            help.play(); 
            targetMan.knockDown();
            targetMan.getRobbed();
        }
        else
        {
            if (vw.getNight()) {
                move (getSpeed() * 2);
            } else {
                move (getSpeed());
            }
        }
    }
    private void moveRandomly ()
    {   
        VehicleWorld vw = (VehicleWorld)getWorld();
        if (Greenfoot.getRandomNumber (100) == 50)
        {
            turn (Greenfoot.getRandomNumber(360));
        }
        else {
            if (vw.getNight()) {
                move (getSpeed() * 2);
            } else {
                move (getSpeed());
            }
        }
    }
    //getting arrested code, just makes it transparent until you can't see it
    //small bug: if the cop dies while arresting, the robber will remain half transparent 
    public void getArrested() {
        this.getImage().setTransparency(getImage().getTransparency() - 5);
        if (this.getImage().getTransparency() < 5)
        {
            getWorld().removeObject(this);
        }
    }
}
