package mainPackage;

import java.util.*;

public class Player extends PlayCharacter
{
    private double xVelocity;
    private double yVelocity;
    private int ground;
    private int direction;
    private int yBox;
    private int xBox;
    private Combos combozo;
    
    public Player(String name, String imageURL, int health, int x, int y, int width, int height, int frameCountRow, int frameCountCol)
    {
        super(name, imageURL, health, x, y, width, height, frameCountRow, frameCountCol);
        
        xVelocity = 0;
        yVelocity = 0;
        
        ground = 300;
        
        direction = 0;
        
        combozo = new Combos();
    }
    
    public void update(boolean[] keyInputs)
    {
        combozo.update(keyInputs);
        
        // for (int i = 0; i < 60; i++)
        // {
        //     for (int j = 0; j < 7; j++)
        //     {
        //         System.out.print(combozo.getCombo()[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        // System.out.println();
        
        if (super.getY() <= ground)
        {
            keyInputs[0] = false;
            
            yVelocity += 2;
            
        }
        else
        {
            yVelocity = 0;
            super.setY(ground + 1);
            
            if (!keyInputs[1] && !keyInputs[3])
                xVelocity /= 3;
        }
        
        if (keyInputs[4])
        {
            //u
            if (combozo.checkCombo(4, 50, 57) && !combozo.checkCombo(4, 56, 60) || !combozo.checkCombo(4, 54, 59))
            {
                if (combozo.checkCombo(4, 50, 57) && !combozo.checkCombo(4, 56, 60))
                    System.out.println("combo");
                if(direction == 0)
                    super.setImage(direction, 1);
                else
                    super.setImage(direction,4);
            }
        }
        else
        {
            super.setImage(direction, 0);
        }
        
        if (keyInputs[0])
        {
            //w
            if (super.getY() <= ground + 1)
            {
                if(direction == 0)
                    super.setImage(0,4);
                else
                    super.setImage(1,1);
                yVelocity -= 30;
                keyInputs[0] = false;
            }
        }
        if (keyInputs[1])
        {
            //a
            if (/*super.getY() > ground && combozo.checkCombo(1, 50, 59)*/!combozo.checkCombo(1, 59, 60))
            {
                System.out.println("dash");
                xVelocity -= 30;
            }
            else if (!(xVelocity < -10) && super.getY() > ground)
            {
                xVelocity -= 1;
            }
            direction = 0;
        }
        if (keyInputs[2])
        {
            //s
            if(direction == 0)
                super.setImage(direction, 3);
            else
                super.setImage(direction, 2);
        }
        if (keyInputs[3])
        {
            //d
            if (!(xVelocity > 10) && super.getY() > ground)
                xVelocity += 1;
            direction = 1;
        }
        
        if (super.getY() <= ground)
            if(direction == 0)
                super.setImage(direction,4);
            else
                super.setImage(direction,1);
            
        super.setX(super.getX() + (int) xVelocity);
        super.setY(super.getY() + (int) yVelocity);
    }
    
    public boolean[][] getCombo()
    {
        return combozo.getCombo();
    }
}
