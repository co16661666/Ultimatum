public class Bot extends Player
{
    public Bot(String name, String imageURL, int health, int x, int y, int width, int height, int frameCountRow, int frameCountCol)
    {
        super(name, imageURL, health, x, y, width, height, frameCountRow, frameCountCol);
    }
    
    @Override
    public void update(Player other)
    {
        boolean[] moves = new boolean[10];
        super.update(keys)
    }
}
