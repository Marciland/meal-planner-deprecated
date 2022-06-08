package de.marciland.planhandler;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MealPlan extends JPanel {

    private final int columns = 8;
    private final int rows = 7;

    private JLabel[][] plan;

    /**
     * Lays out the meal plan based on given plan.
     *
     * @param plan plan that should be laid out on MealPlan.
     *             Load this from file or get it from Dialog user input.
     */
    public void layoutPlan(JLabel[][] plan) {
        if (plan == null) {
            System.out.println("Plan is null, this should not happen!");
            System.exit(1);
        }
        setLayout(null);
        this.plan = plan;
        if (plan.length != columns || plan[0].length != rows) {
            System.out.println("Invalid meal plan was loaded!" +
                    System.lineSeparator() + "expected: 8 x 7" +
                    System.lineSeparator() + "actual: " + plan.length + " x " + plan[0].length);
            System.exit(1);
        }
        for (int i = 0; i < plan.length; i++) {
            for (int j = 0; j < plan[0].length; j++) {
                plan[i][j].setSize(getWidth() / 8, getHeight() / 7);
                plan[i][j].setLocation(i * plan[i][j].getWidth(), j * plan[i][j].getHeight());
                add(plan[i][j]);
            }
        }
    }

    /**
     * Sets the text of JLabel at given position to given string.
     * Column and row should not be 0 and should be smaller than columns and rows.
     * Text should not be null.
     * Can only be called if plan was laid out before!
     *
     * @param text   text that should be displayed.
     * @param column column in which the label is.
     * @param row    row in which the label is.
     */
    public void setText(String text, int column, int row) {
        if (plan == null) {
            System.out.println("Plan is not laid out yet!");
            System.exit(1);
        }
        if (text == null) {
            System.out.println("Invalid text.");
            System.exit(1);
        }
        if (column >= columns || column == 0) {
            System.out.println("Invalid column: " + column +
                    System.lineSeparator() + "Should be smaller than: " + columns + " and not 0");
            System.exit(1);
        }
        if (row >= rows || row == 0) {
            System.out.println("Invalid row: " + row +
                    System.lineSeparator() + "Should be smaller than: " + rows + " and not 0");
            System.exit(1);
        }
        this.plan[column][row].setText(text);
        repaint(); // TODO verify if repaint on mainframe ist still needed!!
    }

}
