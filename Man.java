import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class man here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Man extends Pedestrian
{
    private int money; 
    private int maxMoney; 
    private int moneyLost; 
    private SuperStatBar statBar;
    public Man(int direction) {
        super(direction); 
        money = VehicleWorld.MAX_MONEY;
        maxMoney = money;
        moneyLost = 10;
        statBar = new SuperStatBar (maxMoney, money, this, 40, 8, -32, Color.GREEN, Color.RED, true, Color.YELLOW, 1); 
    }
    /**
     * Act - do whatever the man wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act(); 
        checkAndRemove();
    }
    //tried adding a statbar but it doesn't show
    public void addedToWorld (World w)
    {
        w.addObject (statBar, getX(), getY());
        statBar.update(maxMoney);
    }
    //similar code to Mr. Cohen's flower class in the bug simulation 
    public int getRobbed ()
    {
        if (money >= moneyLost) 
        {
            money -= moneyLost;
            return moneyLost;
        }
        else 
        {
            int tempMoney = money;
            money = 0;
            return tempMoney;
        }
    }
    private void checkAndRemove ()
    {
        if (money == 0) 
        {
            getWorld().addObject(new Death(), getX(), getY());
            getWorld().removeObject(this); 
        }
    }
}
