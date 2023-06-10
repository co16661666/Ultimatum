package mainPackage;

import java.util.*;

import java.util.*;

/**
 * @author      Aryan Patel
 * @version     1.0
 * @since       1.0
 */
public class Bot extends Player
{
    /**
     * Count variable used to calculate the number of frames which have passed
     */ 
    private int count = 0;
    
    /**
     * Variable used to randomize the direction for the Bot
     */
    private double random = -1;
    
    /**
     * Variable used to check if the Bot is about to have a collision with the boundary 
     */
    private boolean notWall = true;
    
    /**
     * Boolean used to check if the Bot is performing an action or not
     */
    private boolean move = false; 
    
    /**
     * Default constuctor with the parameters used from the Player class
     */
    public Bot(String name, String imageURL, int health, int x, int y, int width, int height, int frameCountRow, int frameCountCol)
    {
        super(name, imageURL, health, x, y, width, height, frameCountRow, frameCountCol);
    }
    
    /**
     * Overides the update method of the Player class
     * <p>
     * This allows the Bot to perform seperate actions which does not require any user inputs
     * @param other The user
     */
    public void update(Player other)
    {
        boolean[] moves = new boolean[10];
        
        //The bot has a chance of attacking the player if they were to get into a range of 300 pixels from them 
        if (Math.abs(other.getX() - super.getX()) < 300&&random<0.1)
        {
            System.out.println("1");
            move = false;
            
            if (!super.isAttacking() || super.getCurMove().equals("none"))
                moves[5] = true;
            else
                moves[5] = false;
                
            if(count>400){
                System.out.println("2");
                move = true;
            }
        }
        
        /**
         * Makes the Bot move closer to the player from a count interval of 400 to when count is equal to 500
         * This will not trigger if the bot is performing an attack
         * The bot will move to the right until it is within a random range to the player
         */
        if(other.getX()-super.getX()>((Math.random()*300)+200)&&count>400&&count<500&&move)
        {
            moves[3] = true;
            move = false;
        }
        
        /**
         * Also makes the Bot move closer to the Player but for when the Bot is to the left of the Player
         * The Bot will move the the left until it is within a random range to the player
         */
        if(other.getX()-super.getX()<((Math.random()*-300)+200)&&count>400&&count<500&&move)
        {
            moves[1] = true;
            move = false;
        }
        
        //For the interval of 0 to 200 for the count variable, it makes the Bot crouch
        if(count<200&&!move)
        {
            moves[2] = true;
        }
        
        /**
         * If the Bot makes contact with the left boundary, it will cause the Bot to move in the opposite direction for a set amount of frames
         * the notWall boolean is also set to false which will not update until the update method iterates a few more times.
         */
        if(super.getX()<-90&&move)
        {
            moves[3] = true;
            notWall = false;
            count = (int)((Math.random()*200)+301);
            random = 0.8;
        }
        /**
         * If the Bot makes contact with the right boundary, it will cause the Bot to move in the opposite diection for a set amount of frames
         * the notWall boolean is also set to false which will not update until the update method iterates a few more times.
         */
        else if(super.getX()>1130&&move)
        {
            moves[1] = true;
            notWall = false;
            count = (int)((Math.random()*200)+301);
            random = 0.1;
        }
        /**
         * Whenver there have been 200 frames which have passed, it assigns a random double to the random variable. 
         * The method also adds a random value to the count variable
         */
        if(count == 200)
        {
            random = Math.random();
            count += (Math.random()*100);
        }
        
        /**
         * This method makes the Bot move a random direction for 100 frames. 
         * There is a 50% chance the Bot will either move to the right or to the left 
         */
        if(count<300&&count>200&&notWall&&move)
        {
            if(random<0.5&&random>=0)
            {
                moves[1] = true;
            }
            else if(random>=0)
            {
                moves[3] = true;
            }
        }
        /**
         * This is used for the special cases of when the Bot makes contact with the wall
         * The method will force the Bot to move to the opposite diection of the wall instead of a random direction
         */
        else if(count<300&&count>200&&!notWall&&move)
        {
            if(random<0.5&&random>=0)
            {
                moves[1] = true;
            }
            else if(random>=0)
            {
                moves[3] = true;
            }
        }
        
        //Increments the count variable which counts the number of frames which have passed
        count++;
        //Resets the boolean move which checks to see if an attack action was performed
        move = true;
        
        /**
         * Resets the count variable when count reaches past 600.
         * This also resets the notWall boolean which checked if the Bot made contact with a boundary.
         */
        if(!notWall&&count>600)
        {
            notWall = true;
            count = 0;
        }
        
        //Only resets the count variable when count reaches over 600
        if(count>600)
        {
            count = 0;
        }
        
        //iterates though the entire method again which runs the programs and allows it to update every frame
        super.update(moves, other);
    }
}
