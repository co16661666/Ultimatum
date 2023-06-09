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
    private int width;
    private int height;
    
    private Combos combozo;
    private boolean attacking;
    private int attackPointX;
    private int attackPointY;
    private int damage;
    private int width1;
    private int height1;
    
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
        attacking = false;
        attackPointX = 0;
        attackPointY = 0;
        damage = 0;
        width1 = x+108;
        height1 = y+285;
    }
    
    public boolean isAttacking()
    {
        return attacking;
    }
    
    public int attackX()
    {
        return attackPointX;
    }
    
    public int attackY()
    {
        return attackPointY;
    }
    
    public int damage()
    {
        if (xVelocity > 10)
            return (int) (damage * 1.5);
        else
            return damage;
    }
    
    public void update(boolean[] keyInputs, Player other)
    {
//         for (int i = 0; i < 60; i++)
//         {
//             System.out.print(i + ": ");
            
//             for (int j = 0; j < 7; j++)
//             {
//                 System.out.print(Drawer.keyData[i][j] + " ");
//             }
//             System.out.println();
//         }
//         System.out.println();

        if (other.getX() - super.getX() < 0)
            direction = 0;
        else
            direction = 4;
        
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
        
        if (keyInputs[4] && !Drawer.keyData[58][4])
        {
            //u
            if (combozo.checkCanMove("light"))
            {
                curMove = "lightPunch";
                curMoveStage = 0;
                curMoveDuration = 8;
                combozo.update("neutral");
                
                if (direction == 0)
                    attackPointX = super.getX() + 30;
                else
                    attackPointX = super.getX() + 300;
                    
                attackPointY = 216;
                damage = 5;
            }
        }
        
        if (keyInputs[5] && !Drawer.keyData[58][5])
        {
            //u
            if (combozo.checkCanMove("heavy"))
            {
                curMove = "heavyPunch";
                curMoveStage = 0;
                curMoveDuration = 10;
                combozo.update("neutral");
                
                if (direction == 0)
                    attackPointX = super.getX() + 70;
                else
                    attackPointX = super.getX() + 260;
                    
                attackPointY = 216;
                damage = 10;
            }
        }
        
        if (keyInputs[7] && !Drawer.keyData[58][7])
        {
            if (combozo.checkCanMove("heavy"))
            {
                curMove = "kick";
                curMoveStage = 0;
                curMoveDuration = 18;
                combozo.update("neutral");
                
                if (direction == 0)
                    attackPointX = super.getX() + 20;
                else
                    attackPointX = super.getX() + 310;
                    
                attackPointY = 250;
                
                damage = 15;
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
            
            if (!(xVelocity < -10) && super.getY() > ground)
            {
                boolean temp = false;
                if (!Drawer.keyData[58][1])
                {
                    for (int i = 50; i < 58; i++)
                    {
                        if (Drawer.keyData[i][1])
                        {
                            temp = true;
                            break;
                        }
                    }
                    
                    if (temp)
                    {
                        xVelocity -= 20;
                    }
                    else
                    {
                        xVelocity -= 1;
                    }
                }
                else
                {
                    xVelocity -= 1;
                }
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
                boolean temp = false;
                if (!Drawer.keyData[58][3])
                {
                    for (int i = 50; i < 58; i++)
                    {
                        if (Drawer.keyData[i][3])
                        {
                            temp = true;
                            break;
                        }
                    }
                    
                    if (temp)
                    {
                        xVelocity += 20;
                    }
                    else
                    {
                        xVelocity += 1;
                    }
                }
                else
                {
                    xVelocity += 1;
                }
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
            if (curMoveStage < 4)
            {
                super.setImage(direction, curMoveStage);
                combozo.update("startup");
                attacking = false;
            }
            else if (curMoveStage == 4)
            {
                super.setImage(direction, 3);
                combozo.update("active");
                attacking = true;
            }
            else
            {
                super.setImage(direction, 8 - curMoveStage);
                combozo.update("recovery");
                attacking = false;
            }
            
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
                combozo.update("neutral");
            }
        }
        
        if (curMove.equals("heavyPunch"))
        {
            if (curMoveStage < 4)
            {
                super.setImage(direction + 1, curMoveStage);
                combozo.update("startup");
                attacking = false;
            }
            else if (curMoveStage < 7)
            {
                super.setImage(direction + 1, 3);
                combozo.update("active");
                attacking = true;
            }
            else
            {
                super.setImage(direction + 1, 10 - curMoveStage);
                combozo.update("recovery");
                attacking = false;
            }
            
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
                combozo.update("neutral");
            }
        }
        
        if (curMove.equals("kick"))
        {
            if (curMoveStage < 8)
            {
                super.setImage(direction + 2, curMoveStage / 2);
                combozo.update("startup");
                attacking = false;
            }
            else if (curMoveStage < 12)
            {
                super.setImage(direction + 3, 0);
                combozo.update("active");
                attacking = true;
            }
            else
            {
                super.setImage(direction + 2, 8 - (curMoveStage / 2));
                combozo.update("recovery");
                attacking = false;
            }
            
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
                combozo.update("neutral");
            }
            
        }
        
        if (!(super.getX() < -100) && !(super.getX() > 1280 - 150))
            super.setX(super.getX() + (int) xVelocity);
        else if (!(super.getX() > 1280 - 150))
            super.setX(super.getX() + 1);
        else
            super.setX(super.getX() - 1);
            
        super.setY(super.getY() + (int) yVelocity);
        
//         if(super.getX()<-100)
//         {
//             super.setX(-100);
//         }
//         if(super.getX()>1280 - 330){
//             super.setX(1280 - 330);
//         }
        
        
        if(other.getX()+37.5<=width1&&other.getX()>=width1&&(other.getY()>super.getY()||other.getY()<width1)){
            super.setImage(1,3);
        }
        width1 = super.getX()+220;
        height1 = super.getY()+285;
    }
    
    public boolean selfHit(int xAttack, int yAttack)
    {
        if(xAttack >= super.getX() + 110 && xAttack <= width1){
            return true;
        }
        System.out.println(super.getX()+"  "+xAttack+"   " +width1);
        return false; 
    }
    public void updateHit(Player other)
    {
        if (other.isAttacking() && selfHit(other.attackX(), other.attackY()))
        {
            super.takeDamage(other.damage());
            System.out.println("DAMAGEEEEEE");
        }
    }
}
