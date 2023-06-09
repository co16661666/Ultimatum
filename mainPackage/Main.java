package mainPackage;

import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author      Chinli Ong <chinli.ong@icloud.com>, Aryan Patel
 * @version     1.0
 * @since       1.0
 */
public class Main extends JFrame
{
    private static final long serialVersionUID = 7148504528835036003L;
    /**
     * Main method that sets up the game and the graphics
     * @param args Inputs to the main method
     */
    public static void main(String[] args) {
        //Instantiates a Scanner object for taking key inputs
        Scanner scan = new Scanner(System.in);
        
        //Instructions for the controls
        System.out.println("Welcome!!\nControls: Press W to jump, S to crouch, and use A and D to move left and right.\nTo attack, use U to light punch, I to heavy punch, and J to kick. Remember that you must wait for your previous move to finish before your next move can be performed.\nThe bar in the lower left corner is your current health points, while the bar in the lower right is your opponent's health points. The first person to lose all their health points is loser! Good Luck!\n\nI HAVE BEEN TASKED WITH YOUR DEATH. SURRENDER OR DIE, THIS IS MY ULTIMATUM. [Press ENTER to reject the Ultimatum]");
        
        //Waits until enter is pressed
        scan.nextLine();
        
        SwingUtilities.invokeLater(() -> {
            //Creates a Drawer class that has a method that will constantly redraw the screen
            Drawer panel = new Drawer();
            
            //creating object for background
            panel.addCharacter(new PlayCharacter("bg", "https://codehs.com/uploads/6f98f64dfec467225d723bae02fe6c0a", 10000000, 0, 0, 1280, 720, 1, 1));
            //creates the character that the user will play
            panel.addCharacter(new Player("character0", "https://codehs.com/uploads/1fffd8a936efb6ffad043090d17d59b3", 100, 0, 0, 330, 324, 8, 4));
            //creates the character that the user will face off again
            panel.addCharacter(new Bot("character1", "https://codehs.com/uploads/1fffd8a936efb6ffad043090d17d59b3", 100, 800, 0, 330, 324, 8, 4));
            
            //Setting up the JFrame to draw on
            panel.setBackground(Color.BLACK.darker()); //Black background
            JFrame frame = new JFrame("Ultimatum"); //Declaring/instantiating JFrame
            frame.setSize(1280, 720); //Size of JFrame
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Closes the frame if the window is closed
            frame.getContentPane().add(panel, BorderLayout.CENTER); //Adding a panel to draw content on
            frame.setVisible(true); //Displays the Screen
        });
    }
}
