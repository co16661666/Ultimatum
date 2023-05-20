package mainPackage;

import java.util.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImage.*;
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
    //public Character bg = new Character("bg", "https://codehs.com/uploads/6f98f64dfec467225d723bae02fe6c0a", 10000000, 0, 0);
    
    private boolean[] moves = new boolean[4];
    
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
        System.out.println(e.getKeyCode());
        
        if (e.getKeyCode() == 87)
        {
            moves[0] = true;
        }
        if (e.getKeyCode() == 65)
        {
            moves[1] = true;
        }
        if (e.getKeyCode() == 83)
        {
            moves[2] = true;
        }
        if (e.getKeyCode() == 68)
        {
            moves[3] = true;
        }
    }
    
    public void keyReleased(KeyEvent e)
    {
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == 87)
        {
            moves[0] = false;
        }
        if (e.getKeyCode() == 65)
        {
            moves[1] = false;
        }
        if (e.getKeyCode() == 83)
        {
            moves[2] = false;
        }
        if (e.getKeyCode() == 68)
        {
            moves[3] = false;
        }
    }
    
    public void keyTyped(KeyEvent e)
    {
        System.out.println();
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
        super.paintComponent(g);
        
        BufferedImage bufferedImage = new BufferedImage(500, 500, BufferedImage.TYPE_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        //paint using g2d ...
        
        for (PlayCharacter x : sprity)
        {
            if (x instanceof Player)
                ((Player) x).update(moves);
        }
        
        for (PlayCharacter x : sprity)
        {
            g2d.drawImage(x.getImage(),x.getX(),x.getY(),null);
        }

        Graphics2D g2dComponent = (Graphics2D) g;
        g2dComponent.drawImage(bufferedImage, null, 0, 0);
        
        repaint();
        revalidate();
    }
}
