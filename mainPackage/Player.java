package mainPackage;

public class Player extends PlayCharacter
{
    private double xVelocity;
    private double yVelocity;
    private int ground;
    private int direction;
    
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
        
        for (int i = 0; i < 60; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                System.out.print(combozo.getCombo()[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        
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
            if (combozo.checkCombo(4, 54, 57) && !combozo.checkCombo(4, 56, 60) || !combozo.checkCombo(4, 54, 59))
            {
                System.out.println("combo");
                super.setImage(direction, 1);
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
                super.setImage(0,4);
                yVelocity -= 30;
                keyInputs[0] = false;
            }
        }
        if (keyInputs[1])
        {
            //a
            if (!(xVelocity < -10) && super.getY() > ground)
                xVelocity -= 1;
        }
        if (keyInputs[2])
        {
            //s
            super.setImage(direction, 3);
        }
        if (keyInputs[3])
        {
            //d
            if (!(xVelocity > 10) && super.getY() > ground)
                xVelocity += 1;
        }
        
        if (super.getY() <= ground)
            super.setImage(direction,4);
            
        super.setX(super.getX() + (int) xVelocity);
        super.setY(super.getY() + (int) yVelocity);
    }
}
