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
    
    public PlayCharacter(String name, String imageURL, int health, int x, int y, int width, int height, int frameCountRow, int frameCountCol)
    {
        this.name = name;
        this.frameCountRow = frameCountRow;
        this.frameCountCol = frameCountCol;
        this.image = new BufferedImage[frameCountRow][frameCountCol];
        this.curFrameRow = 0;
        this.curFrameCol = 0;
        
        BufferedImage img = null;
        
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
    public String getName(){
        return name;
    }
    public BufferedImage getImage(){
        return image[curFrameRow][curFrameCol];
    }
    public double getHealth(){
        return health;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setImage(int row, int col){
        curFrameRow = row;
        curFrameCol = col;
    }
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
