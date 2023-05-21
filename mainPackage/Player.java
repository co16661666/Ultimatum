package mainPackage;

public class Player extends PlayCharacter
{
    public Player(String name, String imageURL, int health, int x, int y, int width, int height, int frameCountRow, int frameCountCol)
    {
        super(name, imageURL, health, x, y, width, height, frameCountRow, frameCountCol);
    }
    
    public void update(boolean[] moves)
    {
        if (moves[0])
        {
            //w
            super.setY(super.getY() - 1);
        }
        
        if (moves[1])
        {
            //a
            super.setX(super.getX() - 1);
        }
        
        if (moves[2])
        {
            //s
            super.setY(super.getY() + 1);
        }
        
        if (moves[3])
        {
            //d
            super.setX(super.getX() + 1);
        }
    }
}
