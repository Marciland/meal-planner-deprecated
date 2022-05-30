package de.marciland.windowhandler;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.marciland.ingredienthandler.Ingredient;
import de.marciland.utilities.Tools;

public class Dialog {

    // TODO doc
    public static Ingredient getIngredientInformation(String name, JFrame mainFrame) {
        int type = 0;
        float kcal = 0;
        float fat = 0;
        float carbs = 0;
        float protein = 0;

        // TODO ask for type by buttons

        // TODO ask for input depending on type
        /////////////////////////////////
        String input = null;
        input = JOptionPane.showInputDialog(mainFrame);
        if (input == null) {
            // break
        }
        if (!Tools.checkFloat(input)) {
            // wrong input
        } else {
            kcal = Float.parseFloat(input);
        }
        /////////////////////////////////
        input = JOptionPane.showInputDialog(mainFrame);
        if (input == null) {
            // break
        }
        if (!Tools.checkFloat(input)) {
            // wrong input
        } else {
            fat = Float.parseFloat(input);
        }
        /////////////////////////////////
        input = JOptionPane.showInputDialog(mainFrame);
        if (input == null) {
            // break
        }
        if (!Tools.checkFloat(input)) {
            // wrong input
        } else {
            carbs = Float.parseFloat(input);
        }
        /////////////////////////////////
        input = JOptionPane.showInputDialog(mainFrame);
        if (input == null) {
            // break
        }
        if (!Tools.checkFloat(input)) {
            // wrong input
        } else {
            protein = Float.parseFloat(input);
        }
        /////////////////////////////////
        if (name == null) {
            System.out.println("Name empty while getting ingredient information");
            System.exit(1);
        }
        if (type != 1 || type != 2 || type != 3) {
            System.out.println("Wrong type while getting ingredient information");
            System.exit(1);
        }
        if (fat == 0 && carbs == 0 && protein == 0) {
            System.out.println("Nutrition information empty while getting ingredient information!");
            System.exit(1);
        }
        return new Ingredient(name, type, kcal, fat, carbs, protein);
    }

}
