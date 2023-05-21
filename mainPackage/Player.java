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
            yVelocity -= 5;
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
        
        if (super.getY() <= 400)
        {
            yVelocity += 2;
        }
        else
        {
            System.out.println("low " + super.getY());
            yVelocity = 0;
            super.setY(400);
        }
            
        super.setX(super.getX() + (int) xVelocity);
        super.setY(super.getY() + (int) yVelocity);
    }
}
