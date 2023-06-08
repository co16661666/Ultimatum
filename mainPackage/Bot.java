package mainPackage;

import java.util.*;

import java.util.*;

public class Bot extends Player
{
    public Bot(String name, String imageURL, int health, int x, int y, int width, int height, int frameCountRow, int frameCountCol)
    {
        super(name, imageURL, health, x, y, width, height, frameCountRow, frameCountCol);
    }
    
    public void update(Player other)
    {
        boolean[] moves = new boolean[10];
        
        if (Math.abs(other.getX() - super.getX()) < 30)
        {
            moves[4] = true;
        }
        if(other.getX()-super.getX()<500)
        {
            moves[3] = true;
        }
        if(other.getX()-super.getX()<-500)
        {
            moves[1] = true;
        }
        
        
        super.update(moves, other);
    }
     public void updateHit(Player other)
    {
        ;
    }
}
