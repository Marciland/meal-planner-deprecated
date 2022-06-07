package de.marciland.windowhandler;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import de.marciland.ingredienthandler.Ingredient;
import de.marciland.utilities.Tools;

public class Dialog {

    /**
     * Asks the user for input to create a new ingredient.
     *
     * @param name      name of the ingredient that should be created.
     * @param mainFrame frame on which the dialog should be based upon.
     * @return ingredient entity with given input.
     */
    public static Ingredient getIngredientInformation(String name, JFrame mainFrame) {
        int type = 0;
        float kcal = 0;
        float fat = 0;
        float carbs = 0;
        float protein = 0;
        int typeInput = JOptionPane.showOptionDialog(mainFrame, "Wie soll die Zutat gespeichert werden?",
                "Bitte Typ auswählen!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new Object[] { "gramm", "milliliter", "Stück" }, null);
        if (typeInput == -1) {
            return null;
        }
        String typeString = null;
        if (typeInput == 0) {
            type = 1;
            typeString = "100g";
        }
        if (typeInput == 1) {
            type = 2;
            typeString = "100ml";
        }
        if (typeInput == 2) {
            type = 3;
            typeString = "1stk";
        }
        if (typeInput != 0 && typeInput != 1 && typeInput != 2) {
            System.out.println("Impossible type selected for ingredient!");
            System.exit(1);
        }
        String input = null;
        waiting: while (true) {
            input = JOptionPane.showInputDialog(mainFrame.getContentPane(),
                    "Wie viel Kcal hat die Zutat auf " + typeString, "Bitte Kcal angeben!",
                    JOptionPane.QUESTION_MESSAGE);
            if (input == null) {
                return null;
            }
            if (!Tools.checkFloat(input)) {
                wrongInputFrame(mainFrame);
            } else {
                kcal = Float.parseFloat(input);
                break waiting;
            }
        }
        waiting: while (true) {
            input = JOptionPane.showInputDialog(mainFrame.getContentPane(),
                    "Wie viel Fett hat die Zutat auf " + typeString, "Bitte Fett angeben!",
                    JOptionPane.QUESTION_MESSAGE);
            if (input == null) {
                return null;
            }
            if (!Tools.checkFloat(input)) {
                wrongInputFrame(mainFrame);
            } else {
                fat = Float.parseFloat(input);
                break waiting;
            }
        }
        waiting: while (true) {
            input = JOptionPane.showInputDialog(mainFrame.getContentPane(),
                    "Wie viel Kohlenhydrate hat die Zutat auf " + typeString, "Bitte Kohlenhydrate angeben!",
                    JOptionPane.QUESTION_MESSAGE);
            if (input == null) {
                return null;
            }
            if (!Tools.checkFloat(input)) {
                wrongInputFrame(mainFrame);
            } else {
                carbs = Float.parseFloat(input);
                break waiting;
            }
        }
        waiting: while (true) {
            input = JOptionPane.showInputDialog(mainFrame.getContentPane(),
                    "Wie viel Eiweiß hat die Zutat auf " + typeString, "Bitte Eiweiß angeben!",
                    JOptionPane.QUESTION_MESSAGE);
            if (input == null) {
                return null;
            }
            if (!Tools.checkFloat(input)) {
                wrongInputFrame(mainFrame);
            } else {
                protein = Float.parseFloat(input);
                break waiting;
            }
        }
        if (name == null) {
            System.out.println("Name empty while getting ingredient information");
            System.exit(1);
        }
        if (type != 1 && type != 2 && type != 3) {
            System.out.println("Wrong type while getting ingredient information");
            System.exit(1);
        }
        if (fat == 0 && carbs == 0 && protein == 0) {
            System.out.println("Nutrition information empty while getting ingredient information!");
            System.exit(1);
        }
        return new Ingredient(name, type, kcal, fat, carbs, protein);
    }

    /**
     * Shows an error message because of wrong user input.
     *
     * @param mainFrame frame on which the error message should be based upon.
     */
    public static void wrongInputFrame(JFrame mainFrame) {
        JOptionPane.showMessageDialog(mainFrame.getContentPane(), "Ungültige Eingabe!", "Fehler!",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Shows an error message because of wrong user input.
     *
     * @param dialog dialog on which the error message should be based upon.
     */
    public static void wrongInputDialog(JDialog dialog) {
        JOptionPane.showMessageDialog(dialog, "Ungültige Eingabe!", "Fehler!",
                JOptionPane.ERROR_MESSAGE);
    }

    // TODO doc
    public static JLabel[][] createMealPlan() {
        // TODO get user input, never return null
        return null;
    }

}
