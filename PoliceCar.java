import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PoliceCar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PoliceCar extends Vehicle
{
    private boolean wait = false;
    private int countActs = 0;
    private GreenfootSound policeSound;
    public PoliceCar (VehicleSpawner origin) {
        super(origin); 
        GreenfootImage myImage = getImage(); 
        myImage.scale((int) myImage.getWidth() / 4, (int) myImage.getHeight() / 4); 
        
        yOffset = 10; 
        maxSpeed = 3;
        speed = maxSpeed; 
        
        policeSound = new GreenfootSound("PoliceSound.mp3");
        policeSound.setVolume(40);
    }
    /**
     * Act - do whatever the PoliceCar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act(); 
        //chance to spawn a cop from the cop car
        int temp = Greenfoot.getRandomNumber(1000);
        if (temp == 0) {
            spawnCop();
        }
        if (wait) { 
            countActs++;
            if (countActs >= 60) {
                countActs = 0;
                wait = false;
                moveAgain();
            }   
        }
    }
    public boolean checkHitPedestrian() {
        Pedestrian p = (Pedestrian)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Man.class);
        Pedestrian p2 = (Pedestrian)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Robber.class);
        //knocks down pedestrians if they get hit by police car
        if (p != null){
            p.knockDown();
            return true;
        }
        //kills robbers and does nothing to cops
        if (p2 != null) {
            getWorld().addObject(new Death(), p2.getX() , p2.getY());
            getWorld().removeObject(p2); 
            return true;
        }
        return false; 
    }
    public void spawnCop() {
        policeSound.play();
        getWorld().addObject(new Cop(1), getX(), getY()); 
        wait = true;
        stopMe(); 
    }
    //code to stop police car when its spawning a cop
    public void stopMe () {
        moving = false;
        maxSpeed = 0;
        speed = maxSpeed;
    }
    public void moveAgain() {
        moving = true;
        maxSpeed = 1.5 + ((Math.random() * 10)/5);
        speed = maxSpeed;
    }
}
