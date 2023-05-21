package mainPackage;

public class Player extends PlayCharacter
{
    private double xVelocity;
    private double yVelocity;
    
    public Player(String name, String imageURL, int health, int x, int y, int width, int height, int frameCountRow, int frameCountCol)
    {
        super(name, imageURL, health, x, y, width, height, frameCountRow, frameCountCol);
        
        xVelocity = 0;
        yVelocity = 0;
    }
    
    public void update(boolean[] moves, boolean[] attacks)
    {
        if (super.getY() <= 300)
        {
            yVelocity += 2;
        }
        else
        {
            yVelocity = 0;
            super.setY(301);
        }
        
        if (attacks[0])
        {
            //u
            super.setImage(0,1);
        }
        else
        {
            super.setImage(0,0);
        }
        
        if (moves[0])
        {
            //w
            yVelocity -= 15;
        }
        
        if (moves[1])
        {
            //a
            xVelocity -= 1;
        }
        
        if (moves[2])
        {
            //s
            ;
        }
        
        if (moves[3])
        {
            //d
            xVelocity += 1;
        }
            
        super.setX(super.getX() + (int) xVelocity);
        super.setY(super.getY() + (int) yVelocity);
    }
}
