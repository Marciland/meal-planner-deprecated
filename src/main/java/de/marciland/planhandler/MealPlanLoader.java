package de.marciland.planhandler;

import static de.marciland.utilities.Constants.planColumns;
import static de.marciland.utilities.Constants.planRows;

import javax.swing.JLabel;

public class MealPlanLoader {

    // TODO doc
    public static JLabel[][] loadMealPlan() {
        JLabel[][] labels = new JLabel[planColumns][planRows];
        // TODO read meal plan from file, if no file exists return null
        return labels;
    }

}
