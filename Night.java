import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Night here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Night extends Effect
{
    private int actsLeft; 
    private int transparency;
    private GreenfootSound nightSound;
    /**
     * Act - do whatever the Night wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Night() {
        image = new GreenfootImage(1024, 800);
        image.setColor(Color.BLACK);
        image.fill();
        transparency = 0;
        image.setTransparency(transparency);
        setImage(image); 
        
        actsLeft = 300;
        
        nightSound = new GreenfootSound("Night.mp3");
        nightSound.setVolume(100);
        nightSound.play();
    }
    public void act()
    {
        //the night fades in and fades out
        //Not modular or resuable
        if (transparency < 150 && actsLeft != 0) {
            transparency++;
            image.setTransparency(transparency);
            setImage(image); 
        }
        if (transparency == 150 && actsLeft != 0) {
            actsLeft--;
        }
        if (actsLeft == 0) {
            transparency--;
            image.setTransparency(transparency);
            setImage(image);
        }
        if (transparency == 0 && actsLeft == 0) {
            VehicleWorld vw = (VehicleWorld)getWorld();
            vw.setNight(false); 
            getWorld().removeObject(this); 
        }
    }

    
}
