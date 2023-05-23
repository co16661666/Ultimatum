package mainPackage;

import java.util.ArrayList;

public class Combos
{
    private int frameCount;
    private boolean[][] combo = new boolean[60][7];
  
    public Combos()
    {
        frameCount = 0;
    }
    
    public void update(boolean[] curKeys)
    {
        for (int i = 1; i < 60; i++)
        {
            boolean[] temp = combo[i];
            combo[i - 1] = temp;
        }
        
        combo[combo.length - 1] = curKeys;
    }
    
    public boolean checkCombo(int key, rangeStart, rangeEnd)
    {
        for (int i = rangeStart; i < rangeEnd; i++)
        {
            if (combo[i][key])
                return true;
            
            return false;
        }
    }
}