package RatMaze;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolutionView extends JPanel {
    private JButton[][] buttonGrid;
    private char[][] indexes;
    private int dimensions;
    private boolean found;

    public SolutionView(char[][] indexes) {
        this.indexes = indexes;
        this.dimensions = this.indexes[0].length;
        buttonGrid = new JButton[dimensions][dimensions];
        this.setLayout(new GridLayout(dimensions, dimensions));
        this.setBackground(new Color(115, 194, 251));
        indexes[0][0] = '0';
        DFS_Class dfs = new DFS_Class(indexes, dimensions);
        this.indexes = dfs.getAnswer();
        this.found = dfs.isFound();
        this.indexes[0][0] = '2';
        drawGrid();

      
    }

    private void drawGrid() {
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                JButton selectedBtn = (JButton) evt.getSource();
                if (selectedBtn.getText().equals("Start")) {
                    JOptionPane.showMessageDialog(null, "Start game again!");
                    SolutionView.this.removeAll();
                    revalidate();
                    repaint();
                    new MainFrame(); // Recreate the main frame
                    JFrame terminate = (JFrame) SwingUtilities.getWindowAncestor(getParent());
                    terminate.dispose();
                } else if (selectedBtn.getText().equals("End")) {
                    JOptionPane.showMessageDialog(null, "See you later!");
                    SolutionView.this.removeAll();
                    revalidate();
                    repaint();
                    JFrame terminate = (JFrame) SwingUtilities.getWindowAncestor(getParent());
                    terminate.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, found ? "Green path is the solution!" : "No path found!");
                }
            }
        };
        String text;
        for (int row = 0; row < dimensions; row++) {
            for (int col = 0; col < dimensions; col++) {
                if (row == col && (row == 0)) {
                    text = "Start";
                } else if (row == col && (row == dimensions - 1)) {
                    text = "End";
                } else {
                    text = "";
                }
                buttonGrid[row][col] = new JButton(text);

                if (indexes[row][col] == '0') {
                    buttonGrid[row][col].setBackground(new Color(200, 0, 0));
                    buttonGrid[row][col].setForeground(new Color(255, 255, 255));
                } else if (indexes[row][col] == '2') {
                    buttonGrid[row][col].setBackground(new Color(0, 100, 0));
                    buttonGrid[row][col].setForeground(new Color(255, 255, 255));
                } else {
                    buttonGrid[row][col].setBackground(new Color(255, 255, 255));
                    buttonGrid[row][col].setForeground(new Color(0, 0, 0));
                }
                buttonGrid[row][col].addActionListener(buttonListener);
                this.add(buttonGrid[row][col]);
            }
        }
    }
}