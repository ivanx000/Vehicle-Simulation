import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A Dead Bug ... Actually a skull. Starts off opaque and slowly turns transparent,
 * and then eventually removes itself from the World.
 */
public class Death extends Actor
{
    //Mr. Cohen's DeadBug class from bug simulation
    public Death ()
    {
        GreenfootImage myImage = getImage(); 
        myImage.scale((int) myImage.getWidth() / 2, (int) myImage.getHeight() / 2);
        this.getImage().setTransparency(250);
    }
    public void act()
    {
        this.getImage().setTransparency(getImage().getTransparency() - 1);
        if (this.getImage().getTransparency() < 5)
        {
            getWorld().removeObject(this);
        }
    }
}
