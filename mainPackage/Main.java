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
            Drawer panel = new Drawer();
            
            //Character(String name, String imageURL, int health, int x, int y, int width, int height, int frameCount)
            //Background object
            panel.addCharacter(new PlayCharacter("bg", "https://codehs.com/uploads/6f98f64dfec467225d723bae02fe6c0a", 10000000, 0, 0, 1280, 720, 1));
            //Character1
            panel.addCharacter(new Player("character1", "https://codehs.com/uploads/0e05dd3175993e93acb4426506cbab09", 100, 0, 0, 300, 240, 5));
            
            panel.setBackground(Color.BLACK.darker());
            JFrame frame = new JFrame("Ultimatum");
            frame.setSize(1280, 720);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(panel, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}
