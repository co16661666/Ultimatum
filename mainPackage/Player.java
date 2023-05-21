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
            yVelocity += 5;
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
        
        if (super.getY > 600)
            yVelocity -= 2;
            
        super.setX(super.getX() + xVelocity);
        super.setY(super.getY() + yVelocity);
    }
}
