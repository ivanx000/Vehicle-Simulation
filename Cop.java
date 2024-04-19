import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Cop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cop extends Pedestrian
{
    private ArrayList<Robber> robbers;
    private Robber targetRobber;
    
    public Cop(int direction) {
        super(direction);
        enableStaticRotation();
    }
    /**
     * Act - do whatever the Cop wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        //moves randomly and looks for closest robber
        if (targetRobber != null && targetRobber.getWorld() == null){
            targetRobber = null;
        }
        if (targetRobber == null || getDistance (this, targetRobber) > 20){
            targetClosestPerson ();
        }
        if (targetRobber != null)
        {
            moveTowardOrArrest();
        }
        else
        {
            moveRandomly();
        }
    }
    //Mr. Cohen's code from the bug simulation
    private void targetClosestPerson ()
    {
        double closestTargetDistance = 0;
        double distanceToActor;

        robbers = (ArrayList<Robber>)getObjectsInRange(40, Robber.class);
        if (robbers.size() == 0){

            robbers = (ArrayList<Robber>)getObjectsInRange(140, Robber.class);
        } 
        if (robbers.size() == 0){

            robbers = (ArrayList<Robber>)getObjectsInRange(350, Robber.class);
        } 

        if (robbers.size() > 0)
        {
            targetRobber = robbers.get(0);

            closestTargetDistance = getDistance(this, targetRobber);

            for (Robber o : robbers)
            {
 
                distanceToActor = getDistance(this, o);

                if (distanceToActor < closestTargetDistance)
                {
                    targetRobber = o;
                    closestTargetDistance = distanceToActor;
                }
            }
            turnTowards(targetRobber.getX(), targetRobber.getY());
        }
    }
    private void moveTowardOrArrest ()
    {
        if (getDistance (this, targetRobber) < 18)
        {
            targetRobber.knockDown();
            targetRobber.getArrested();
        }
        else
        {
            move(getSpeed());
        }
    }
    private void moveRandomly ()
    {
        if (Greenfoot.getRandomNumber (100) == 50)
        {
            turn (Greenfoot.getRandomNumber(360));
        }
        else
            move (getSpeed());
    }
}
