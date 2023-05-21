package mainPackage;

public class Player extends PlayCharacter
{
    private double xVelocity;
    private double yVelocity;
    private int ground;
    private int direction;
    
    public Player(String name, String imageURL, int health, int x, int y, int width, int height, int frameCountRow, int frameCountCol)
    {
        super(name, imageURL, health, x, y, width, height, frameCountRow, frameCountCol);
        
        xVelocity = 0;
        yVelocity = 0;
        
        ground = 300;
        
        direction = 0;
    }
    
    public void update(boolean[] moves, boolean[] attacks)
    {
        if (super.getY() <= ground)
        {
            moves[0] = false;
            
            yVelocity += 2;
        }
        else
        {
            yVelocity = 0;
            super.setY(ground + 1);
            
            if (!moves[1] && !moves[3])
                xVelocity /= 3;
        }
        
        if (attacks[0])
        {
            //u
            super.setImage(direction, 1);
        }
        else
        {
            super.setImage(direction, 0);
        }
        
        if (moves[0])
        {
            //w
            if (super.getY() <= ground + 1)
                super.setImage(0,4);
                yVelocity -= 30;
                moves[0] = false;
        }
        
        if (moves[1])
        {
            //a
            if (!(xVelocity < -10) && super.getY() > ground)
                xVelocity -= 1;
        }
        
        if (moves[2])
        {
            //s
            super.setImage(direction, 3);
        }
        
        if (moves[3])
        {
            //d
            if (!(xVelocity > 10) && super.getY() > ground)
                xVelocity += 1;
        }
        
        if (super.getY() <= ground)
            super.setImage(direction,4);
            
        super.setX(super.getX() + (int) xVelocity);
        super.setY(super.getY() + (int) yVelocity);
    }
}
