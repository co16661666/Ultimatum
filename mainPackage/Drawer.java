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
 * A panel maintaining a picture of a Do Not Enter sign.
 */
public class Drawer extends JPanel implements KeyListener
{
    private static final long serialVersionUID = 7148504528835036003L;
    private long prevT = 0L;
    //public Character bg = new Character("bg", "https://codehs.com/uploads/6f98f64dfec467225d723bae02fe6c0a", 10000000, 0, 0);
    
    private boolean[] keyInputs = new boolean[10]
    
    private ArrayList<PlayCharacter> sprity = new ArrayList<PlayCharacter>();
    
    public Drawer()
    {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        setDoubleBuffered(true);
    }
    
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
    
    /**
     * Called by the runtime system whenever the panel needs painting.
     */
    public void paintComponent(Graphics g)
    {
        Toolkit.getDefaultToolkit().sync();
        
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
        super.paintComponent(g);

        for (PlayCharacter x : sprity)
        {
            if (x instanceof Player)
                ((Player) x).update(moves, attacks);
        }
        
        for (PlayCharacter x : sprity)
        {
            g.drawImage(x.getImage(),x.getX(),x.getY(),null);
        }
        
        repaint();
        revalidate();
    }
}
