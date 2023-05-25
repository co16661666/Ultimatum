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
    private BufferedImage[][] image;
    
    private int width;
    private int height;
    
    private int frameCountRow;
    private int frameCountCol;
    private int curFrameRow;
    private int curFrameCol;
    
    private int x;
    private int y;
    private double health;
    private boolean isAlive;
    
    //default constructor 
    public PlayCharacter(String name, String imageURL, int health, int x, int y, int width, int height, int frameCountRow, int frameCountCol)
    {
        this.name = name;
        this.frameCountRow = frameCountRow;
        this.frameCountCol = frameCountCol;
        this.image = new BufferedImage[frameCountRow][frameCountCol];
        this.curFrameRow = 0;
        this.curFrameCol = 0;
        
        BufferedImage img = null;
        
        //displays the image from a url
        try
        {
            URI url = null;
            try
            {
               url = new URI(imageURL);
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
        //iterates through arraylist of all images and prints each one
        for (int r = 0; r < this.image.length; r++)
        {
            for (int c = 0; c < this.image[0].length; c++)
            {
                this.image[r][c] = img.getSubimage(c * width, r * height, width, height);
            }
        }
        
        this.health = health;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        if(health>0)
            isAlive = true;
    }

    public String getName()//getter method which returns the String name
    {
        return name;
    }
    public BufferedImage getImage(){//returns the image which is printed
        return image[curFrameRow][curFrameCol];
    }
    public double getHealth(){//returns the health returned as a double
        return health;
    }
    public void setName(String name){//sets the name by taking a String input
        this.name = name;
    }
    public void setImage(int row, int col){//sets the image by taking 2 ints which serve as the position in a 2d array
        curFrameRow = row;
        curFrameCol = col;
    }
    public void setHealth(double health){//sets the health by taking in a double input
        this.health = health;
    }
    public void takeDamage(double damage){//subtracts the damage taken from the total health and also checks to see if the health is greater than 0
        health -= damage;
        if(health <= 0){
            isAlive = false;
        }
    }
    
    public int getX(){//returns the X position which is an int value
        return x;
    }
    public int getY(){//returns the y position which is an int value
        return y;
    }
    public void setX(int x){//sets the x position of the image with an int input
        this.x = x;
    }
    public void setY(int y){//sets the y position of the image with an int input 
        this.y = y;
    }
}
