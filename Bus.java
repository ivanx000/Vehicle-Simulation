import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Bus subclass
 */
public class Bus extends Vehicle
{
    private boolean wait = false;
    private int busCount;
    
    public Bus(VehicleSpawner origin){
        super (origin); // call the superclass' constructor first
        
        //Set up values for Bus
        maxSpeed = 1.5 + ((Math.random() * 10)/5);
        speed = maxSpeed;
        // because the Bus graphic is tall, offset it a up (this may result in some collision check issues)
        yOffset = 15;
    }

    /**
     * Act - do whatever the Bus wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // for some reason this doesn't indent properly and I forgot the command 
       super.act();
       if (wait) {
        busCount++;
            if (busCount >= 60) {
                busCount = 0;
                wait = false;
                moveAgain();
            }   
        }
    }

    public boolean checkHitPedestrian () {
        Pedestrian p = (Pedestrian)getOneIntersectingObject(Man.class);
        Pedestrian p2 = (Pedestrian)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Robber.class);
        Pedestrian p3 = (Pedestrian)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Cop.class);
        //Picks up pedestrian 
        if (p != null && p.isAwake()) {
            stopMe();
            wait = true;
            getWorld().removeObject(p);
            return true; 
        }
        //Kills Cop or robber if they get hit
        if (p2 != null) {
            getWorld().addObject(new Death(), p2.getX(), p2.getY());
            getWorld().removeObject(p2); 
            return true;
        }
        if (p3 != null) {
            getWorld().addObject(new Death(), p3.getX() , p3.getY());
            getWorld().removeObject(p3);
            return true;
        }
        return false;
    }
    //method to stop bus
    public void stopMe () {
        moving = false;
        maxSpeed = 0;
        speed = maxSpeed;
    }
    //method to make the bus move again
    public void moveAgain() {
        moving = true;
        maxSpeed = 1.5 + ((Math.random() * 10)/5);
        speed = maxSpeed;
    }
}
