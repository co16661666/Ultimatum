package mainPackage;

import java.util.*;

/**
 * @author      Chinli Ong <chinli.ong@icloud.com>
 * @version     1.0
 * @since       1.0
 */
public class Combos
{
    /**
     * Current stage of the move being performed (recovery, neutral)
     */
    private String stage;
    
    /**
     * Constructor for Combos class
     * <p>
     * Sets the initial stage to neutral
     */
    public Combos()
    {
        //Sets initial stage to neutral
        stage = "neutral";
    }
    
    /**
     * Updates the current stage of the move
     * @param stage The stage to be set
     */
    public void update(String stage)
    {
        this.stage = stage;
    }
    
    /**
     * Checks if a certain move can be performed
     * <p>
     * This code is important because moves can only be performed after the previous move finished. However, certain moves can be cancelled during their recovery periods.
     * <p>
     * For example, no move can be performed when the Player is stunned. If the previous move was a light move (e.g. lightPunch), then another move can be performed during its recovery stage. However, this is not true for a heavy move. Any move can be performed from a neutral state.
     * @param type The type of move that is currently being performed (light, heavy)
     */
    public boolean checkCanMove(String type)
    {
        //Checks if the Player is stunned
        if (stage.equals("stunned"))
            return false;
        
        //Checks if the move can be cancelled or performed directly (from a neutral state)
        if (type.equals("light") && (stage.equals("recovery") || stage.equals("neutral")) || type.equals("heavy") && stage.equals("neutral"))
        {
            stage = "neutral";
            return true;
        }
        
        return false;
    }
    
    /**
     * Getter method for the current stage
     * @return Returns the current stage
     */
    public String getStage()
    {
        return stage;
    }
}
