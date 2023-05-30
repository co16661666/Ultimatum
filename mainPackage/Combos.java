package mainPackage;

import java.util.*;

public class Combos
{
    private int frameCount;
    
    private String stage;
  
    public Combos()
    {
        frameCount = 0;
        
        stage = "neutral";
    }
    
    public void update(String stage)
    {
        this.stage = stage;
    }
    
    public boolean checkCanMove(String type)
    {
        if (stage.equals("stunned"))
            return false;
        
        if (type.equals("light") && stage.equals("recovery") || type.equals("heavy") && stage.equals("neutral"))
            return true;
        return false;
    }
    
    public String stage()
    {
        return stage;
    }
}
