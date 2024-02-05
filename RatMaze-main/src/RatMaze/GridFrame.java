package RatMaze;

import javax.swing.*;
import java.awt.*;

public class GridFrame extends JFrame {

    public GridFrame(int dimensions) {
        // Set icon
        try {
            Image image = new ImageIcon(this.getClass().getResource("./RatMazeicon.png")).getImage();
            this.setIconImage(image);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "App icon not found!");
        }

        // Frame settings
        this.setTitle("Maze Grid");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set background color
        this.getContentPane().setBackground(Color.WHITE); // Change Color.WHITE to the color of your choice

        // Create and add the GridView
        GridView gridView = new GridView(dimensions);
        this.add(gridView);

        // Make the frame visible
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GridFrame(10); // Adjust the dimensions as needed
        });
    }
}
