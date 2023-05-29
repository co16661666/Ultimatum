package mainPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A little driver in case you want a stand-alone application.
 */
public class Main extends JFrame
{
    private static final long serialVersionUID = 7148504528835036003L;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //Creates a Drawer class that has a method that will constantly redraw the screen
            Drawer panel = new Drawer();
            
            //Character(String name, String imageURL, int health, int x, int y, int width, int height, int frameCountRow, int frameCountCol)
            
            //creating object for background
            panel.addCharacter(new PlayCharacter("bg", "https://codehs.com/uploads/6f98f64dfec467225d723bae02fe6c0a", 10000000, 0, 0, 1280, 720, 1, 1));
            //creates the character that the user will play
            panel.addCharacter(new Player("character0", "https://codehs.com/uploads/092f9cdddfc942d63c7f2ff28f317d65", 100, 0, 0, 330, 324, 4, 8));
            //creates the character that the user will face off again
            ///panel.addCharacter(new Bot("character1", "https://codehs.com/uploads/14cba35834e0c4b2db1ab584437131ae", 100, 0, 0, 288, 600, 2, 6));
            
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
