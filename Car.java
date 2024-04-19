import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Car subclass
 */
public class Car extends Vehicle
{
    private GreenfootSound carHonk;
    public Car(VehicleSpawner origin) {
        super(origin); // call the superclass' constructor
        
        GreenfootImage myImage = getImage(); 
        myImage.scale((int) myImage.getWidth() / 3 + 20, (int) myImage.getHeight() / 3 + 20); 
        myImage.mirrorHorizontally();
        yOffset = -5; 
        
        maxSpeed = 1.5 + ((Math.random() * 30)/5);
        speed = maxSpeed;
        int z;
        followingDistance = 6;
        
        carHonk = new GreenfootSound("carhorn2.mp3"); 
        carHonk.setVolume(35); 
    }

    public void act()
    {
        super.act();
    }

    /**
     * When a Car hit's a Pedestrian, it should knock it over
     */
    public boolean checkHitPedestrian () {
        Pedestrian p = (Pedestrian)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Man.class);
        Pedestrian p2 = (Pedestrian)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Robber.class);
        Pedestrian p3 = (Pedestrian)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Cop.class);
        //the car knocks down pedestrians
        if (p != null)
        {
            p.knockDown();
            carHonk.play();
            return true;
        }
        //the car kills cops and robbers
        if (p2 != null)
        {
            getWorld().addObject(new Death(), p2.getX() , p2.getY());
            getWorld().removeObject(p2); 
            carHonk.play();
            return true;
        }
        if (p3 != null) {
            getWorld().addObject(new Death(), p3.getX() , p3.getY());
            getWorld().removeObject(p3);
            carHonk.play();
            return true;
        }
        return false;
        
    }
}
