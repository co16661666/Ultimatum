public class Combos
{
    private int frameCount;
    private boolean[][] combo = new boolean[60][7];
    private static ArrayList<Combos> comboList = new ArrayList<Combos>();
  
    public Combos()
    {
        frameCount = 0;
        allCom.add(this);
    }
    
    public static updateComboList(boolean[] curKeys)
    {
        for (boolean x : comboList)
        {
            x.update(curKeys);
        }
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
}
