package RatMaze;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainView extends JPanel {

    private JLabel welcome = new JLabel("Welcome");
    private JLabel dimensionLabel = new JLabel("Enter dimensions of the maze: ");
    private JLabel noteLabel = new JLabel("(If dimensions are N*N, only enter N)");
    private JTextField dimensionsField = new JTextField();
    private JButton buildItButton = new JButton("Build it!");
    private int dimensions;

    private OnButtonClick click = new OnButtonClick();

    private ImageIcon backgroundImage;

    public MainView() {
        this.setLayout(null);

        // Load background image
        backgroundImage = new ImageIcon(getClass().getResource("moazz2.jfif"));

        this.add(welcome);
        this.add(dimensionLabel);
        this.add(noteLabel);
        this.add(dimensionsField);
        this.add(buildItButton);

        welcome.setFont(new Font("Arial", Font.BOLD, 60));
        welcome.setForeground(new Color(0, 0, 0));
        dimensionLabel.setFont(new Font("Arial", Font.ITALIC, 25));
        dimensionLabel.setForeground(new Color(0, 0, 0));
        noteLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        noteLabel.setForeground(new Color(0, 0, 0));
        dimensionsField.setFont(new Font("Arial", Font.PLAIN, 20));
        dimensionsField.setForeground(new Color(0, 0, 0));
        buildItButton.setFont(new Font("Arial", Font.BOLD, 25));

        welcome.setBounds(240, 200, 350, 50);
        dimensionLabel.setBounds(220, 280, 350, 30);
        noteLabel.setBounds(250, 300, 350, 30);
        dimensionsField.setBounds(300, 350, 150, 30);
        buildItButton.setBounds(300, 450, 150, 50);

        buildItButton.setBackground(new Color(0, 0, 0));
        buildItButton.setForeground(new Color(255, 255, 255));

        buildItButton.addActionListener(click);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }

    private class OnButtonClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buildItButton) {
                try {
                    if (!dimensionsField.getText().isEmpty()) {
                        dimensions = Integer.parseInt(dimensionsField.getText());
                        if (dimensions >= 3) {
                            MainView.this.removeAll();
                            revalidate();
                            repaint();
                            new GridFrame(dimensions);
                            JFrame terminate = (JFrame) SwingUtilities.getWindowAncestor(getParent());
                            terminate.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Dimensions must be 3 or more!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Enter dimensions first!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }
    }
}
