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
            moves[0] = false;
            moves[1] = false;
            moves[3] = false;
            
            yVelocity += 2;
        }
        else
        {
            yVelocity = 0;
            super.setY(301);
            
            if (!moves[1] && !moves[3])
                xVelocity /= 3;
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
            if (super.getY() <= 301)
                yVelocity -= 30;
                moves[0] = false;
        }
        
        if (moves[1])
        {
            //a
            if (!(xVelocity < -10))
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
            if (!(xVelocity > 10))
                xVelocity += 1;
        }
            
        super.setX(super.getX() + (int) xVelocity);
        super.setY(super.getY() + (int) yVelocity);
    }
}
