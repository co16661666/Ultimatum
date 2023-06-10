package mainPackage;

import java.util.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Point;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.image.BufferedImage;
import java.io.*;  
import javax.imageio.ImageIO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author      Chinli Ong <chinli.ong@icloud.com>
 * @version     1.0
 * @since       1.0
 */
public class Drawer extends JPanel implements KeyListener
{
    private static final long serialVersionUID = 7148504528835036003L;
    
    /**
     * Records previous time that the frame was run
     */
    private long prevT = 0L;
    /**
     * Number of current frame since beginning of program
     */
    public long frame = 0;
    
    /**
     * Records a history of all key inputs in the last 60 frames
     */
    //public static boolean[][] keyData = new boolean[60][10];
    
    /**
     * Array of current key inputs
     */
    private boolean[] keyInputs = new boolean[10];
    
    /**
     * ArrayList of all objects that need to be drawn
     */
    private ArrayList<PlayCharacter> sprity = new ArrayList<PlayCharacter>();
    
    /**
     * Constructor for Drawer class
     * <p>
     * Sets up the Drawer class for keyListener and also sets up double buffering
     */
    public Drawer()
    {
        //Adds the keyListener to receive key inputs
        addKeyListener(this);
        
        //Allows the Panel to be focused on
        setFocusable(true);
        
        //Allows the keys to be focused
        setFocusTraversalKeysEnabled(true);
        
        //Allows double buffering of frames
        setDoubleBuffered(true);
    }
    
    /**
     * Is called whenever a key is pressed down
     * <p>
     * This method sets the keyInputs array items to true if a certain key is pressed (w, a, s, d, u, i, o, j, k, l). This method implements the KeyListener class method
     * @param e The key user's key interaction
     */
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == 85)
        {
            keyInputs[4] = true;
        }
        else if (e.getKeyCode() == 73)
        {
            keyInputs[5] = true;
        }
        else if (e.getKeyCode() == 79)
        {
            keyInputs[6] = true;
        }
        else if (e.getKeyCode() == 74)
        {
            keyInputs[7] = true;
        }
        else if (e.getKeyCode() == 75)
        {
            keyInputs[8] = true;
        }
        else if (e.getKeyCode() == 76)
        {
            keyInputs[9] = true;
        }
        
        if (e.getKeyCode() == 87)
        {
            keyInputs[0] = true;
        }
        if (e.getKeyCode() == 65)
        {
            keyInputs[1] = true;
        }
        if (e.getKeyCode() == 83)
        {
            keyInputs[2] = true;
        }
        if (e.getKeyCode() == 68)
        {
            keyInputs[3] = true;
        }
    }
    
    /**
     * Is called whenever a key is released
     * <p>
     * This method sets the keyInputs array items to false if a certain key is released (w, a, s, d, u, i, o, j, k, or l). This method implements the KeyListener class method
     * @param e The key user's key interaction
     */
    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == 85)
        {
            keyInputs[4] = false;
        }
        if (e.getKeyCode() == 73)
        {
            keyInputs[5] = false;
        }
        if (e.getKeyCode() == 79)
        {
            keyInputs[6] = false;
        }
        if (e.getKeyCode() == 74)
        {
            keyInputs[7] = false;
        }
        if (e.getKeyCode() == 75)
        {
            keyInputs[8] = false;
        }
        if (e.getKeyCode() == 76)
        {
            keyInputs[9] = false;
        }
        
        if (e.getKeyCode() == 87)
        {
            keyInputs[0] = false;
        }
        if (e.getKeyCode() == 65)
        {
            keyInputs[1] = false;
        }
        if (e.getKeyCode() == 83)
        {
            keyInputs[2] = false;
        }
        if (e.getKeyCode() == 68)
        {
            keyInputs[3] = false;
        }
    }
    
    /**
     * Is called whenever a key is typed
     * <p>
     * This method is not used. This method implements the KeyListener class method
     * @param e The key user's key interaction
     */
    public void keyTyped(KeyEvent e)
    {
        ;
    }
    
    /**
     * This is how objects that need to be drawn are added to the ArrayList of the Drawer class
     * @param x A PlayCharacter object that will need to be drawn by the Drawer class
     */
    public void addCharacter(PlayCharacter x)
    {
        sprity.add(x);
    }
    
    /**
     * This method is called every time the screen is updated and updates the key history
     * <p>
     * It moves all the previous key inputs "down" one index (e.g. keyData[1] -> keyData[0]) with the oldest set of key inputs (keyData[0]) being discarded and the 59th index (keyData[59]) being set to the current key inputs
     * @param in The current key inputs
     */
    // public static void updateKeyList(boolean[] in)
    // {
    //     //For loop to iterate through the array and move the indexes down
    //     for (int i = 1; i < 60; i++)
    //     {
    //         //Sets previous key index to the next index (discards oldest key index)
    //         keyData[i - 1] = keyData[i];
    //     }
        
    //     //Sets the last index of the array to a *copy* of the latest key inputs to avoid errors with references to the current array
    //     keyData[59] = Arrays.copyOf(in, 10);
    // }
    
    /**
     * Getter method for the ArrayList of all PlayCharacters the need to be drawn
     * @return sprity Instance variable storing all PlayCharacters to be drawn
     */
    public ArrayList<PlayCharacter> getSprity()
    {
        return sprity;
    }
    
    /**
     * Calls itself repeatedly to repaint the screen
     * <p>
     * This method has a delay to ensure it runs at a maximum of 60 frames per second. It runs the update method (if applicable) for all PlayCharacter objects and the draws them to the Panel. It also draws the healthbars for the user and robot
     * @param g Graphics component for drawing
     */
    public void paintComponent(Graphics g)
    {
        //Syncs the os so that the movements are smoother*
        Toolkit.getDefaultToolkit().sync();
        
        //This is to ensure that the framerate is capped at 60 FPS, so it will delay the program and wait to draw until 1/60 th of a second has passed
        if (Math.abs(System.nanoTime() - prevT)  < 16670000)
        {
            try
            {
                //Sleeps for 1/60 of a second
                Thread.sleep(((int) Math.abs(System.nanoTime() - prevT)) / 1000000, ((int) Math.abs(System.nanoTime() - prevT)) % 1000000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            
            //Sets the previous time to current time
            prevT = System.nanoTime();
        }
        else
        {
            //Sets the previous time to current time
            prevT = System.nanoTime();
        }
        
        //Increase the current frame number by one
        frame++;
        
        //Calls the JComponent paintComponent method
        super.paintComponent(g);
        
        //Runs update for all applicable objects that can be drawn
        for (PlayCharacter x : sprity)
        {
            if (x instanceof Bot)
                ((Bot) x).update((Player) sprity.get(1));
            else if (x instanceof Player)
                ((Player) x).update(keyInputs, (Bot) sprity.get(2));
        }
        
        //Runs update for all applicable objects that can take damage or deal damage
        for (PlayCharacter x : sprity)
        {
            if (x.getName().equals("character0"))
                ((Player) x).updateHit((Player) sprity.get(2));
            else if (x.getName().equals("character1"))
                ((Player) x).updateHit((Player) sprity.get(1));
        }
        
        //Iterates through all objects, and displays them to the screen
        for (PlayCharacter x : sprity)
        {
            g.drawImage(x.getImage(),x.getX(),x.getY(),null);
        }
        
        //Draws up the background of the first healthbar
        g.fillRect(50, 650, 350, 20);
        
        //Draws up the background of the second healthbar
        g.fillRect(880, 650, 350, 20);
        
        //Changes the color for the primary color of the background (Green)
        g.setColor(new Color(0, 145, 15));
        
        //Draws up the meter of the first healthbar
        g.fillRect(50, 650, (int) (sprity.get(1).getHealth() * 350) / 100, 20);
        
        //Draws up the meter of the second healthbar
        g.fillRect(880 + (int) (350 - (sprity.get(2).getHealth() * 350) / 100), 650, (int) (sprity.get(2).getHealth() * 350) / 100, 20);
        
        //If statements to determine if the game has ended
        if (sprity.get(1).getHealth() < 0 && sprity.get(2).getHealth() < 0)
        {
            System.out.println("TIE: THE REAL VICTORY IS THE FRIENDS WE MADE ALONG THE WAY...");
        }
        else if (sprity.get(1).getHealth() < 0)
        {
            System.out.println("DEFEAT: YOU LOST EVERYTHING. SUCH IS THE NATURE OF AN ULTIMATUM.");
        }
        else if (sprity.get(2).getHealth() < 0)
        {
            System.out.println("VICTORY: YOU GOT AWAY... THIS TIME");
        }
        else
        {
            //Paints the image so the user can see it, then calls this method again to loop
            repaint();
            revalidate();
        }
    }
}
