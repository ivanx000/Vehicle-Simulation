import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Ambulance subclass
 */
public class Ambulance extends Vehicle
{
    private GreenfootSound healPedestrianSound;
    public Ambulance(VehicleSpawner origin){
        super (origin); // call the superclass' constructor first
        
        GreenfootImage myImage = getImage(); 
        myImage.scale((int) myImage.getWidth() / 3 + 40, (int) myImage.getHeight() / 3 + 40); 
        myImage.mirrorHorizontally();
        
        yOffset = 5; 
        maxSpeed = 2.5;
        speed = maxSpeed;
        
        healPedestrianSound = new GreenfootSound("thankyou.mp3");
        healPedestrianSound.setVolume(50); 
    }
    /**
     * Act - do whatever the Ambulance wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    public boolean checkHitPedestrian () {
        // heals pedestrians
        Man p = (Man)getOneIntersectingObject(Man.class);
        if (p != null && !p.isAwake())
        {
            healPedestrianSound.play();
            p.healMe();
            return true;
        }
        return false;
    }
    
}
