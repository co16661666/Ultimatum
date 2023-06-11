package mainPackage;

import java.util.*;

/**
 * @author      Chinli Ong <chinli.ong@icloud.com>
 * @version     1.0
 * @since       1.0
 */
public class Player extends PlayCharacter
{
    /**
     * Current X-Velocity of Player object
     */
    private double xVelocity;
    
    /**
     * Current Y-Velocity of Player object
     */
    private double yVelocity;
    
    /**
     * Lowest Y-Position that the Player can go to
     */
    private int ground;
    
    /**
     * Current direction of image (0 - left facing, 4 - right facing)
     */
    private int direction;
    
    
    /**
     * Current move the object is performing (lightPunch, heavyPunch, kick)
     */
    private String curMove;
    
    /**
     * What stage the current move is in (startup, active, recovery)
     */
    private int curMoveStage;
    
    /**
     * How long the current move is (how many frames)
     */
    private int curMoveDuration;
    
    /**
     * Walking timer (to change animation state every 10 frames when walking)
     */
    private int timer;
    
    
    /**
     * Width of image
     */
    private int width;
    /**
     * Height of image
     */
    private int height;
    
    
    /**
     * Combo object to determine when an object can/can't perform a move
     */
    private Combos combozo;
    
    /**
     * Whether or not this object is attacking
     */
    private boolean attacking;
    
    /**
     * Attack point X of attack (where the object will deal damage)
     */
    private int attackPointX;
    
    /**
     * Attack point Y of attack (where the object will deal damage)
     */
    private int attackPointY;
    
    /**
     * Current damage output of object
     */
    private int damage;
    
    /**
     * Width of hitbox
     */
    private int width1;
    
    /**
     * Height of hitbox
     */
    private int height1;
    
    private boolean[][] keyData = new boolean[60][10];
    
    public boolean[][] getKeyData()
    {
        return keyData;
    }
    
    /**
     * This method is called every time the screen is updated and updates the key history
     * <p>
     * It moves all the previous key inputs "down" one index (e.g. keyData[1] -> keyData[0]) with the oldest set of key inputs (keyData[0]) 
     * being discarded and the 59th index (keyData[59]) being set to the current key inputs
     * @param in The current key inputs
     */
    public void updateKeyList(boolean[] in)
    {
        //For loop to iterate through the array and move the indexes down
        for (int i = 1; i < 60; i++)
        {
            //Sets previous key index to the next index (discards oldest key index)
            keyData[i - 1] = keyData[i];
        }
        
        //Sets the last index of the array to a *copy* of the latest key inputs to avoid errors with references to the current array
        keyData[59] = Arrays.copyOf(in, 10);
    }
    
    /**
     * Constructor for Player class
     * @param name Name of object
     * @param imageURL URL of image
     * @param health Initial health of object
     * @param x Initial X-Position
     * @param y Initial Y-Position
     * @param width Width of image
     * @param height Height of image
     * @param frameCountRow Number of rows on spritesheet
     * @paramframeCountCol Number of columns on spritesheet
     */
    public Player(String name, String imageURL, int health, int x, int y, int width, int height, int frameCountRow, int frameCountCol)
    {
        //Calls super constructor
        super(name, imageURL, health, x, y, width, height, frameCountRow, frameCountCol);
        
        //Sets initial X-Velocity to 0
        xVelocity = 0;
        
        //Sets initial Y-Velocity to 0
        yVelocity = 0;
        
        
        //Sets initial current move to none
        curMove = "none";
        
        //Sets initial move stage to 0
        curMoveStage = 0;
        
        //Sets initial move duration to 0
        curMoveDuration = 0;
        
        //Sets the walking time to 0
        timer = 0;
        
        
        //Sets the ground to 300
        ground = 300;
        
        //Sets the initial direction to left-facing
        direction = 0;
        
        //Instantiates combo object
        combozo = new Combos();
        
        //Sets the initial attacking to false
        attacking = false;
        
        //Sets the initial attack point X to 0
        attackPointX = 0;
        
        //Sets the initial attack point Y to 0
        attackPointY = 0;
        
        //Sets the initial damage
        damage = 0;
        
        //Sets the hitbox width to X-Position + hitbox width
        width1 = x+108;
        
        //Sets the hitbox height to Y-Position + hitbox height
        height1 = y+285;
    }
    
    /**
     * Getter method to see if the object is in an attacking state
     * @return Whether or not the object is attacking
     */
    public boolean isAttacking()
    {
        return attacking;
    }
    
    /**
     * Getter method for X-Position of the attack point
     * @return The X-Position of the attack
     */
    public int attackX()
    {
        return attackPointX;
    }
    
    /**
     * Getter method for Y-Position of the attack point
     * @return The Y-Position of the attack
     */
    public int attackY()
    {
        return attackPointY;
    }
    
    /**
     * Damage that the attack would deal
     * @return The amount of damage to be dealt
     */
    public int damage()
    {
        if (xVelocity > 10)
            return (int) (damage * 1.5);
        else
            return damage;
    }
    
    public String getCurMove()
    {
        return curMove;
    }
    
    /**
     * Updates properties of Player object
     * <p>
     * This method updates the attack states, the position, velocities and hitboxes of the object
     * @param keyInputs The current keys that are pressed (w, a, s, d, u, i, o, j, k, l)
     * @param other The opposing Player, the bot if this object is the user, the user if this Player is the bot
     */
    public void update(boolean[] keyInputs, Player other)
    {
        
        updateKeyList(keyInputs);
        
        //Sets up the direction that the object should be facing
        if (other.getX() - super.getX() < 0)
            direction = 0;
        else
            direction = 4;
        
        //Increases the Y-Velocity by 2 if the object is above the ground, or sets the Y-Position to the ground if it is below.
        //It also incorporates friction when on the ground.
        if (super.getY() <= ground)
        {
            //Prevents mid-air jumping
            keyInputs[0] = false;
            
            //Artificial gravity
            yVelocity += 2;
            
        }
        else
        {
            //Sets the yVelocity to 0 when hitting the ground
            yVelocity = 0;
            
            //Puts the object slightly above the ground if it is below
            super.setY(ground + 1);
            
            //Sets the jumping animation
            super.setImage(direction, 0);
            
            //Friction if the object is on the ground and no move keys are being pressed
            if (!keyInputs[1] && !keyInputs[3] || !curMove.equals("walk"))
                xVelocity /= 3;
        }
        
        //if lightPunch key is pressed (u) and it was not being pressed in the previous frame (to avoid press and hold to attack)
        if (keyInputs[4] && !keyData[58][4])
        {
            //Checking if you can perform the move
            if (combozo.checkCanMove("light"))
            {
                //Sets the current move to lightPunch
                curMove = "lightPunch";
                
                //Sets the current stage to the beginning of the move sequence
                curMoveStage = 0;
                
                //Sets the move duration of lightPunch which is 8
                curMoveDuration = 8;
                
                //Set the starting state to neutral
                combozo.update("neutral");
                
                //Sets the attack point X position depending on which way the object is facing
                if (direction == 0)
                    attackPointX = super.getX() + 30;
                else
                    attackPointX = super.getX() + 300;
                
                //Sets the attack point Y position
                attackPointY = 216;
                
                //Sets the base damage to be dealt
                damage = 5;
            }
        }
        
        //if heavyPunch key is pressed (i) and it was not being pressed in the previous frame (to avoid press and hold to attack)
        if (keyInputs[5] && !keyData[58][5])
        {
            //Checking if you can perform the move
            if (combozo.checkCanMove("heavy"))
            {
                //Sets the current move to heavyPunch
                curMove = "heavyPunch";
                
                //Sets the current stage to the beginning of the move sequence
                curMoveStage = 0;
                
                //Sets the move duration of heavyPunch which is 10
                curMoveDuration = 10;
                
                //Set the starting state to neutral
                combozo.update("neutral");
                
                //Sets the attack point X position depending on which way the object is facing
                if (direction == 0)
                    attackPointX = super.getX() + 70;
                else
                    attackPointX = super.getX() + 260;
                
                //Sets the attack point Y position
                attackPointY = 216;
                
                //Sets the base damage to be dealt
                damage = 3;
            }
        }
        
        //If kick key is pressed (j) and it was not being pressed in the previous frame (to avoid press and hold to attack)
        if (keyInputs[7] && !keyData[58][7])
        {
            //Checking if you can perform the move
            if (combozo.checkCanMove("heavy"))
            {
                //Sets the current move to kick
                curMove = "kick";
                
                //Sets the current stage to the beginning of the move sequence
                curMoveStage = 0;
                
                //Sets the move duration of kick which is 18
                curMoveDuration = 18;
                
                //Set the starting state to neutral
                combozo.update("neutral");
                
                //Sets the attack point X position depending on which way the object is facing
                if (direction == 0)
                    attackPointX = super.getX() + 20;
                else
                    attackPointX = super.getX() + 310;
                
                //Sets the attack point Y position
                attackPointY = 250;
                
                //Sets the base damage to be dealt
                damage = 5;
            }
        }
        
        //If the jump key (w) is pressed
        if (keyInputs[0])
        {
            //If the object is on the ground and can jump
            if (super.getY() <= ground + 1)
            {
                //Set image to jump animation
                super.setImage(direction + 3, 2);
                
                //Set the Y-Velocity to -30 (up direction)
                yVelocity -= 30;
                
                //Set the jump key input to 0
                keyInputs[0] = false;
            }
        }
        
        //If the move left key (a) is pressed, otherwise, if the current move is walk, set it to neutral
        if (keyInputs[1])
        {
            //Check if the object is on the ground and its X-Velocity is not greater than 10
            if (!(xVelocity < -10) && super.getY() > ground && combozo.checkCanMove("heavy"))
            {
                //Set the current move to walk
                curMove = "walk";
                
                //Set the starting state to neutral
                combozo.update("neutral");
                
                //Variable for if the button was double tapped
                boolean temp = false;
                
                //If the button was not pressed in the previous frame
                if (!keyData[58][1])
                {
                    //Looking for if the button was pressed from frame 50 - 57 (inclusive)
                    for (int i = 50; i < 58; i++)
                    {
                        if (keyData[i][1])
                        {
                            temp = true;
                            break;
                        }
                    }
                    
                    //If double-tapped, increase X-Velocity to -20. Otherwise, increase X-Velocity by -1 
                    if (temp)
                    {
                        xVelocity -= 20;
                    }
                    else
                    {
                        xVelocity -= 1;
                    }
                }
                else if (curMove.equals("walk"))
                {
                    //Increases X Velocity by -1
                    xVelocity -= 1;
                }
            }
        }
        
        //If crouch button is pressed (s)
        if (keyInputs[2])
        {
            //Set image to crouch
            super.setImage(direction + 3, 1);
        }
        
        //If the move right key (d) is pressed, otherwise, if the current move is walk, set it to neutral
        if (keyInputs[3])
        {
            //Check if the object is on the ground and its X-Velocity is not greater than 10
            if (!(xVelocity > 10) && super.getY() > ground && combozo.checkCanMove("heavy"))
            {
                //Set the current move to walk
                curMove = "walk";
                
                //Set the starting state to neutral
                combozo.update("neutral");
                
                //Variable for if the button was double tapped
                boolean temp = false;
                
                //If the button was not pressed in the previous frame
                if (!keyData[58][3])
                {
                    //Looking for if the button was pressed from frame 50 - 57 (inclusive)
                    for (int i = 50; i < 58; i++)
                    {
                        if (keyData[i][3])
                        {
                            temp = true;
                            break;
                        }
                    }
                    
                    //If double-tapped, increase X-Velocity to 20. Otherwise, increase X-Velocity by 1 
                    if (temp)
                    {
                        xVelocity += 20;
                    }
                    else
                    {
                        xVelocity += 1;
                    }
                }
                else if (curMove.equals("walk"))
                {
                    //Increases X Velocity by 1
                    xVelocity += 1;
                }
            }
        }
        
        //Checks if image is on the ground
        if (super.getY() <= ground)
        {
            //Sets image to neutral state
            super.setImage(direction + 3, 2);
        }
        
        //Checks if walking
        if (curMove.equals("walk"))
        {
            //If the current walking stage is even, change the walking image
            if (curMoveStage % 2 == 0)
                super.setImage(direction + 3, 3);
            
            //Increase stage by 1 if the walking timer is divisible by ten (every ten frames)    
            if (timer % 10 == 0)
                curMoveStage++;
            
            //Increase walking timer by 1
            timer++;
        }
        
        //Checks if lightPunching
        if (curMove.equals("lightPunch"))
        {
            //Checks if the move is in startup, active, or recovery state
            if (curMoveStage < 4)
            {
                //Change the image based on stage
                super.setImage(direction, curMoveStage);
                
                //Sets the state to startup
                combozo.update("startup");
                
                //Sets attacking to false (not attacking yet)
                attacking = false;
            }
            else if (curMoveStage == 4)
            {
                //Sets image to fully extended jab arm
                super.setImage(direction, 3);
                
                //Sets state to active
                combozo.update("active");
                
                //Sets attacking to true
                attacking = true;
            }
            else
            {
                //Changes image based on stage
                super.setImage(direction, 8 - curMoveStage);
                
                //Sets the state to recovery
                combozo.update("recovery");
                
                //Sets the attacking to false (recovering from attack)
                attacking = false;
            }
            
            //Checks if the move has finished
            if (!(curMoveStage == curMoveDuration))
            {
                //Increases the current move stage
                curMoveStage++;
            }
            else
            {
                //Sets the current move to none (because the move has finished)
                curMove = "none";
                
                //Sets the move duration to 0
                curMoveDuration = 0;
                
                //Sets the move stage to 0
                curMoveStage = 0;
                
                //Sets image to normal
                super.setImage(direction, 0);
                
                //Sets state to neutral
                combozo.update("neutral");
            }
        }
        
        //Checks if heavyPunching
        if (curMove.equals("heavyPunch"))
        {
            //Checks if the move is in startup, active, or recovery state
            if (curMoveStage < 4)
            {
                //Change the image based on stage
                super.setImage(direction + 1, curMoveStage);
                
                //Sets the state to startup
                combozo.update("startup");
                
                //Sets attacking to false
                attacking = false;
            }
            else if (curMoveStage < 7)
            {
                //Sets image to fully extended cross arm
                super.setImage(direction + 1, 3);
                
                //Sets state to active
                combozo.update("active");
                
                //Sets attacking to true
                attacking = true;
            }
            else
            {
                //Changes image based on stage
                super.setImage(direction + 1, 10 - curMoveStage);
                
                //Sets the state to recovery
                combozo.update("recovery");
                
                //Sets attacking to false
                attacking = false;
            }
            
            //Checks if the move has finished
            if (!(curMoveStage == curMoveDuration))
            {
                //Increases the current move stage
                curMoveStage++;
            }
            else
            {
                //Sets the current move to none (because the move has finished)
                curMove = "none";
                
                //Sets the move duration to 0
                curMoveDuration = 0;
                
                //Sets the move stage to 0
                curMoveStage = 0;
                
                //Sets image to normal
                super.setImage(direction, 0);
                
                //Sets state to neutral
                combozo.update("neutral");
            }
        }
        
        //Checks if kicking
        if (curMove.equals("kick"))
        {
            //Checks if the move is in startup, active, or recovery state
            if (curMoveStage < 8)
            {
                System.out.println("startup: " + curMoveStage);
                //Change the image based on stage
                super.setImage(direction + 2, curMoveStage / 2);
                
                //Sets the state to startup
                combozo.update("startup");
                
                //Sets attacking to false
                attacking = false;
            }
            else if (curMoveStage < 12)
            {
                System.out.println("active: " + curMoveStage);
                //Sets image to fully extended kick
                super.setImage(direction + 3, 0);
                
                //Sets state to active
                combozo.update("active");
                
                //Sets attacking to true
                attacking = true;
            }
            else
            {
                System.out.println("recovery: " + 8 - (curMoveStage / 2));
                //Changes image based on stage
                super.setImage(direction + 2, 8 - (curMoveStage / 2));
                
                //Sets the state to recovery
                combozo.update("recovery");
                
                //Sets attacking to false
                attacking = false;
            }
            
            //Checks if the move has finished
            if (!(curMoveStage == curMoveDuration))
            {
                //Increases the current move stage
                curMoveStage++;
            }
            else
            {
                //Sets the current move to none (because the move has finished)
                curMove = "none";
                
                //Sets the move duration to 0
                curMoveDuration = 0;
                
                //Sets the move stage to 0
                curMoveStage = 0;
                
                //Sets image to normal
                super.setImage(direction, 0);
                
                //Sets state to neutral
                combozo.update("neutral");
            }
        }
        
        //Sets the boudaries for the graphics output, which prevents any objects from moving off the screen
        if (!(super.getX() < -100) && !(super.getX() > 1280 - 150))
            super.setX(super.getX() + (int) xVelocity);
        else if (!(super.getX() > 1280 - 150))
            super.setX(super.getX() + 1);
        else
            super.setX(super.getX() - 1);
            
        super.setY(super.getY() + (int) yVelocity);
        
        //Creates the "hitbox" for the player, which is used for performing damage calculations 
        if(other.getX()+37.5<=width1&&other.getX()>=width1&&(other.getY()>super.getY()||other.getY()<width1)){
            super.setImage(1,3);
        }
        width1 = super.getX()+220;
        height1 = super.getY()+285;
    }
    
    /*
    * A method which takes the x and y coordinates of the attack, and checks to see if the attack is within the hitbox of the other object
    * The method returns true if the x and y coordinates are in the other object's hitbox and false otherwise
    */
    public boolean selfHit(int xAttack, int yAttack)
    {
        if(xAttack >= super.getX() + 110 && xAttack <= width1){
            return true;
        }
        
        return false; 
    }
    
    /*
    * A method which checks to see if the Player is performing an action which counts as an attack,
    * and also checks to see if the attack is within the hitbox. 
    * If the condition is true, it will call the takeDamage method in the PlayCharacter class which will perform damage claculations based
    * on the type of attack which was used.
    */
    public void updateHit(Player other)
    {
        if (other.isAttacking() && selfHit(other.attackX(), other.attackY()))
        {
            super.takeDamage(other.damage());
        }
    }
}
