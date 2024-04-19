import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class truck here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RobberCar extends Vehicle
{    
    private boolean wait = false;
    private int actCount;
    private GreenfootSound carHorn;
    public RobberCar(VehicleSpawner origin) {
        super(origin);
        
        GreenfootImage myImage = getImage(); 
        myImage.scale((int) myImage.getWidth() / 19, (int) myImage.getHeight() / 19); 
        
        yOffset = 5;
        maxSpeed = 3;
        speed = maxSpeed; 
        
        carHorn = new GreenfootSound("vroom.mp3");
        carHorn.setVolume(50); 
    }
    /**
     * Act - do whatever the truck wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        // Add your action code here.
        if (wait) {
            actCount++;
            if (actCount >= 45) {
                actCount = 0;
                wait = false;
                moveAgain();
            }   
        }
    }
    
    public boolean checkHitPedestrian () {
        Pedestrian p = (Pedestrian)getOneIntersectingObject(Robber.class);
        Pedestrian p2 = (Pedestrian)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Man.class);
        Pedestrian p3 = (Pedestrian)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Cop.class);
        //picks up robber
        if (p != null) {
            stopMe();
            wait = true;
            getWorld().removeObject(p);
            carHorn.play(); 
            return true; 
        }
        //knocks down pedestrians
        if (p2 != null){
            p2.knockDown();
            return true;
        }
        //kills cops
        if (p3 != null) {
            getWorld().addObject(new Death(), p3.getX(), p3.getY());
            getWorld().removeObject(p3); 
            return true;
        }
        return false;
    }
    //code to stop robber car 
    public void stopMe () {
        moving = false;
        maxSpeed = 0;
        speed = maxSpeed;
    }
    //doubles the speed after the robber car picks up a robber
    public void moveAgain() {
        moving = true;
        maxSpeed = 6; 
        speed = maxSpeed;
    }
}
