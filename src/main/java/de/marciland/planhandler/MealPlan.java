package de.marciland.planhandler;

import static de.marciland.utilities.Constants.planColumns;
import static de.marciland.utilities.Constants.planRows;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Mealplan extends jpanel and is added to the mainframe.
 * The plan is created out of labels and is ordered as a table.
 */
public class MealPlan extends JPanel {

    private JLabel[][] plan;

    /**
     * Lays out the meal plan based on given plan.
     *
     * @param plan plan that should be laid out on MealPlan.
     *             Load this from file or get it from Dialog user input.
     * @see MealPlanLoader
     */
    public void layoutPlan(JLabel[][] plan) {
        if (plan == null) {
            System.out.println("Plan is null, this should not happen!");
            System.exit(1);
        }
        setLayout(null);
        this.plan = plan;
        if (plan.length != planColumns || plan[0].length != planRows) {
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
                // TODO labels not fitting!
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
     * @see MealPlanLoader
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
        if (column >= planColumns || column == 0) {
            System.out.println("Invalid column: " + column +
                    System.lineSeparator() + "Should be smaller than: " + planColumns + " and not 0");
            System.exit(1);
        }
        if (row >= planRows || row == 0) {
            System.out.println("Invalid row: " + row +
                    System.lineSeparator() + "Should be smaller than: " + planRows + " and not 0");
            System.exit(1);
        }
        this.plan[column][row].setText(text);
        repaint(); // TODO verify if repaint on mainframe ist still needed!!
    }

}
