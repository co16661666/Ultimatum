package mainPackage;

import java.util.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
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
import java.net.URI;
import java.net.URISyntaxException;
 
public class PlayCharacter
{
    private String name;
    private BufferedImage[] image;
    
    private int width;
    private int height;
    
    private int frameCount;
    private int curFrame;
    
    private int x;
    private int y;
    private double health;
    private boolean isAlive;
    
    public PlayCharacter(String name, String imageURL, int health, int x, int y, int width, int height, int frameCount)
    {
        this.name = name;
        this.image = new BufferedImage[frameCount];
        this.curFrame = 0;
        
        BufferedImage img = null;
        
        try
        {
            try
            {
               URI url = new URI(imageURL);
            }
            catch(URISyntaxException e)
            {
                e.printStackTrace();
            }
            img = ImageIO.read(url.toURL());
        }
        catch (IOException e)
        {
            System.out.println("You FAILURE :)");
            e.printStackTrace();
        }
        
        for (int i = 0; i < this.image.length; i++)
        {
            this.image[i] = img.getSubimage(i * width, 0, width, height);
        }
        
        this.health = health;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        if(health>0)
            isAlive = true;
    }
    public String getName(){
        return name;
    }
    public BufferedImage getImage(){
        return image[curFrame];
    }
    public double getHealth(){
        return health;
    }
    public void setName(String name){
        this.name = name;
    }
    // public void setImage(String image){
    //     this.image = image;
    // }
    public void setHealth(double health){
        this.health = health;
    }
    public void takeDamage(double damage){
        health -= damage;
        if(health <= 0){
            isAlive = false;
        }
    }
    
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
}
