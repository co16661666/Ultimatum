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
    
    private String curMove;
    private int curMoveStage;
    private int curMoveDuration;
    private int timer;
    
    private Combos combozo;
    
    public Player(String name, String imageURL, int health, int x, int y, int width, int height, int frameCountRow, int frameCountCol)
    {
        super(name, imageURL, health, x, y, width, height, frameCountRow, frameCountCol);
        
        xVelocity = 0;
        yVelocity = 0;
        
        curMove = "none";
        curMoveStage = 0;
        curMoveDuration = 0;
        timer = 0;
        
        ground = 300;
        
        direction = 0;
        
        combozo = new Combos();
    }
    
    public void update(boolean[] keyInputs)
    {
//         for (int i = 0; i < 60; i++)
//         {
//             System.out.print(i + ": ");
            
//             for (int j = 0; j < 7; j++)
//             {
//                 System.out.print(combozo.getCombo()[i][j] + " ");
//             }
//             System.out.println();
//         }
//         System.out.println();
        
        if (super.getY() <= ground)
        {
            keyInputs[0] = false;
            
            yVelocity += 2;
            
        }
        else
        {
            yVelocity = 0;
            super.setY(ground + 1);
            super.setImage(direction, 0);
            if (!keyInputs[1] && !keyInputs[3])
                xVelocity /= 3;
        }
        
        if (keyInputs[4])
        {
            //u
            if (combozo.checkCombo(4, 50, 56) && !combozo.checkCombo(4, 53, 59) || !combozo.checkCombo(4, 50, 59))
            {
                curMove = "lightPunch";
                curMoveStage = 0;
                curMoveDuration = 8;
                if (combozo.checkCombo(4, 50, 56) && !combozo.checkCombo(4, 56, 59))
                    System.out.println("combo");
            }
        }
        
        if (keyInputs[5])
        {
            //u
            if (combozo.checkCombo(5, 48, 52) && !combozo.checkCombo(5, 52, 59) || !combozo.checkCombo(5, 48, 59))
            {
                curMove = "heavyPunch";
                curMoveStage = 0;
                curMoveDuration = 10;
                if (combozo.checkCombo(5, 48, 52) && !combozo.checkCombo(5, 52, 59))
                    System.out.println("combo");
            }
        }
        
        if (keyInputs[0])
        {
            //w
            if (super.getY() <= ground + 1)
            {
                super.setImage(direction + 3, 2);
                yVelocity -= 30;
                keyInputs[0] = false;
            }
        }
        if (keyInputs[1])
        {
            //a
            curMove = "walk";
            
            if (/*super.getY() > ground && combozo.checkCombo(1, 50, 59)*/!combozo.checkCombo(1, 59, 60))
            {
                System.out.println("dash");
                xVelocity -= 30;
            }
            else if (!(xVelocity < -10) && super.getY() > ground)
            {
                xVelocity -= 1;
            }
        }
        else if (curMove == "walk")
        {
            curMove = "none";
        }
        
        if (keyInputs[2])
        {
            //s
            super.setImage(direction + 3, 1);
        }
        if (keyInputs[3])
        {
            //d
            curMove = "walk";
            
            if (!(xVelocity > 10) && super.getY() > ground)
            {
                xVelocity += 1;
            } 
        }
        else if (curMove == "walk")
        {
            curMove = "none";
        }
        
        if (super.getY() <= ground)
        {
            super.setImage(direction + 3, 2);
        }
        
        combozo.update(keyInputs);
        
        if (curMove.equals("walk"))
        {
            if (curMoveStage % 2 == 0)
                super.setImage(direction + 3, 3);
                
            if (timer % 10 == 0)
                curMoveStage++;
            
            timer++;
        }
        
        if (curMove.equals("lightPunch"))
        {
            System.out.println("lightPunching");
            
            if (curMoveStage < 4)
                super.setImage(direction, curMoveStage);
            else if (curMoveStage == 4)
                super.setImage(direction, 3);
            else
                super.setImage(direction, 8 - curMoveStage);
            
            if (!(curMoveStage == curMoveDuration))
            {
                curMoveStage++;
            }
            else
            {
                curMove = "none";
                curMoveDuration = 0;
                curMoveStage = 0;
                super.setImage(direction, 0);
            }
        }
        
        if (curMove.equals("heavyPunch"))
        {
            System.out.println("heavyPunching");
            
            if (curMoveStage < 4)
                super.setImage(direction + 1, curMoveStage);
            else if (curMoveStage < 7)
                super.setImage(direction, 3);
            else
                super.setImage(direction + 1, 10 - curMoveStage);
            
            if (!(curMoveStage == curMoveDuration))
            {
                curMoveStage++;
            }
            else
            {
                curMove = "none";
                curMoveDuration = 0;
                curMoveStage = 0;
                super.setImage(direction, 0);
            }
        }
            
        super.setX(super.getX() + (int) xVelocity);
        super.setY(super.getY() + (int) yVelocity);
    }
    
    public boolean[][] getCombo()
    {
        return combozo.getCombo();
    }
}
