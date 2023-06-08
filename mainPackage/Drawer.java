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

public class Drawer extends JPanel implements KeyListener
{
    private static final long serialVersionUID = 7148504528835036003L;
    
    //setup for timing frames
    private long prevT = 0L;
    public long frame = 0;
    public static boolean[][] keyData = new boolean[60][10];
    
    //creates array of keyInputs
    private boolean[] keyInputs = new boolean[10];
    
    //creates ArrayList of all objects that will be drawn
    private ArrayList<PlayCharacter> sprity = new ArrayList<PlayCharacter>();
    
    public Drawer()
    {
        //setting up key inputs
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        setDoubleBuffered(true);
    }
    
    //sets respective items in keyInputs to true when a key is pressed
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
    
    public void keyTyped(KeyEvent e)
    {
        ;
    }
    
    public void addCharacter(PlayCharacter x)
    {
        sprity.add(x);
    }
    
    public static void updateKeyList(boolean[] in)
    {
        for (int i = 1; i < 60; i++)
        {
            keyData[i - 1] = keyData[i];
        }
        
        keyData[59] = Arrays.copyOf(in, 10);
    }
    
    public ArrayList<PlayCharacter> getSprity()
    {
        return sprity;
    }
    
    /**
     * Called by the runtime system whenever the panel needs painting.
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
                Thread.sleep(((int) Math.abs(System.nanoTime() - prevT)) / 1000000, ((int) Math.abs(System.nanoTime() - prevT)) % 1000000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            prevT = System.nanoTime();
        }
        else
        {
            prevT = System.nanoTime();
        }
        
        //Increase the current frame number by one
        frame++;
        //System.out.println(frame);
        
        updateKeyList(keyInputs);
        
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
        
        for (PlayCharacter x : sprity)
        {
            if (x.getName().equals("character0"))
                ((Player) x).updateHit((Player) sprity.get(2));
            else if (x.getName().equals("character1"))
                ((Player) x).updateHit((Player) sprity.get(1));
        }
        
        //Iterates through all objects, and displays them to the screen.
        for (PlayCharacter x : sprity)
        {
            g.drawImage(x.getImage(),x.getX(),x.getY(),null);
        }
        
        g.fillRect(50, 650, 350, 20);
        
        g.fillRect(880, 650, 350, 20);
        
        g.setColor(new Color(0, 145, 15));
        
//         g.fillRect(50, 650, sprity.get(1).getHealth(), 20);
        
//         g.fillRect(880, 650, sprity.get(2).getHealth(), 20);

        g.fillRect(50, 650, (int) (sprity.get(1).getHealth() * 350) / 100, 20);
        
        g.fillRect(880 + (int) (350 - (sprity.get(2).getHealth() * 350) / 100), 650, (int) (sprity.get(2).getHealth() * 350) / 100, 20);
        
        //Paints the image so the user can see it, then calls this method again to loop
        repaint();
        revalidate();
    }
}
