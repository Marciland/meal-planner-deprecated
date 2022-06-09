package de.marciland.planhandler;

import static de.marciland.utilities.Constants.planColumns;
import static de.marciland.utilities.Constants.planRows;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import java.awt.Color;

/**
 * Loader class for the meal plan. Used to load from and save to files.
 * @see MealPlan
 */
public class MealPlanLoader {

    // TODO doc
    public static JLabel[][] loadMealPlan() {
        // if no file exists return null
        JLabel[][] labels = new JLabel[planColumns][planRows];
        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels[0].length; j++) {
                labels[i][j] = new JLabel();
                labels[i][j].setBorder(BorderFactory.createLineBorder(Color.RED));
                ;
            }
        }
        labels = prepareEmptyPlan(labels);
        // TODO read meal plan from file
        return labels;
    }

    // TODO doc
    private static JLabel[][] prepareEmptyPlan(JLabel[][] labels) {
        // TODO prep empty plan
        labels[0][1].setText("Rezept: ");
        labels[0][2].setText("Zutaten: ");
        labels[0][3].setText("Kcal: ");
        labels[0][4].setText("Fett: ");
        labels[0][5].setText("Kohlenhydrate: ");
        labels[0][6].setText("Eiweiß: ");
        labels[1][0].setText("Montag");
        labels[2][0].setText("Dienstag");
        labels[3][0].setText("Mittwoch");
        labels[4][0].setText("Donnerstag");
        labels[5][0].setText("Freitag");
        labels[6][0].setText("Samstag");
        labels[7][0].setText("Sonntag");
        return labels;
    }

}
