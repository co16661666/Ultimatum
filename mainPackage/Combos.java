package mainPackage;

import java.util.*;

public class Combos
{
    private int frameCount;
    private boolean[][] combo = new boolean[60][10];
  
    public Combos()
    {
        frameCount = 0;
    }
    
    public void update(boolean[] curKeys)
    {
        System.out.println("updating combos");
        for (int i = 1; i < 60; i++)
        {
            combo[i - 1] = combo[i];
        }
        
        combo[combo.length - 1] = Arrays.copyOf(curKeys, 10);
    }
    
    public boolean[][] getCombo()
    {
        return combo;
    }
    
    public boolean checkCombo(int key, int rangeStart, int rangeEnd)
    {
        for (int i = rangeStart; i < rangeEnd; i++)
        {
            if (combo[i][key])
                return true;
        }
        
        return false;
    }
}
