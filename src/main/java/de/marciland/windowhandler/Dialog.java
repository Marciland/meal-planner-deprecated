package de.marciland.windowhandler;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import de.marciland.ingredienthandler.Ingredient;
import de.marciland.planhandler.MealPlan;
import de.marciland.planhandler.MealPlanLoader;
import de.marciland.recipehandler.Recipe;
import de.marciland.recipehandler.RecipeLoader;
import de.marciland.utilities.Tools;

/**
 * Contains all functions to create new dialogs.
 */
public class Dialog {

    /**
     * Asks the user for input to create a new ingredient.
     *
     * @param name      name of the ingredient that should be created.
     * @param mainFrame frame on which the dialog should be based upon.
     * @return ingredient entity with given input.
     * @see Ingredient
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

    /**
     * Asks the user for input to create a meal plan.
     * The user needs to input a recipe name for every day of the week.
     *
     * @return the plan based on input of the user.
     *         If the user cancels the creation, null will be returned.
     * @see MealPlan
     * @see MealPlanLoader
     */
    public static JLabel[][] createMealPlan(JFrame frame) {
        Recipe[] allRecipes = RecipeLoader.loadAllRecipes();
        if (Tools.arrayContainsNull(allRecipes) || allRecipes.length == 0) {
            JOptionPane.showMessageDialog(frame.getContentPane(), "Keine Rezepte vorhanden!", "Fehler!",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
        Object[] recipeNames = RecipeLoader.getRecipeNames(allRecipes);
        String day = "";
        String[] inputs = new String[7];
        // ask for a recipe for every day of the week and save the input
        for (int i = 0; i < 7; i++) {
            // change day for every iteration
            switch (i) {
                case 0:
                    day = "Montag";
                    break;
                case 1:
                    day = "Dienstag";
                    break;
                case 2:
                    day = "Mittwoch";
                    break;
                case 3:
                    day = "Donnerstag";
                    break;
                case 4:
                    day = "Freitag";
                    break;
                case 5:
                    day = "Samstag";
                    break;
                case 6:
                    day = "Sonntag";
                    break;
            }
            String input = (String) JOptionPane.showInputDialog(frame, "Was würdest du gerne am " + day + " essen?",
                    "Wähle ein Rezept aus!", JOptionPane.PLAIN_MESSAGE, null, recipeNames, recipeNames[0]);
            // if user cancels at any point returns null
            if (input == null) {
                return null;
            }
            inputs[i] = input;
        }
        // load all recipes after user finished input
        Recipe[] recipes = new Recipe[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            recipes[i] = RecipeLoader.loadRecipe(inputs[i] + ".rec");
        }
        JLabel[][] plan = MealPlanLoader.prepareEmptyPlan();
        plan = MealPlanLoader.writeRecipesToPlan(recipes, plan);
        MealPlanLoader.savePlan(plan);
        return plan;
    }

    // TODO doc
    public static JLabel[][] editMealPlan(JFrame frame, JLabel[][] plan) {
        // TODO edit meal plan
        Recipe[] recipes = MealPlanLoader.getRecipesFromPlan(plan);
        Recipe[] existingRecipes = RecipeLoader.loadAllRecipes();
        String[] existingRecipesNames = RecipeLoader.getRecipeNames(existingRecipes);
        JDialog dialog = new JDialog(frame, true);
        dialog.setSize(frame.getWidth() / 4 * 3, frame.getHeight() / 2);
        dialog.setUndecorated(true);
        dialog.setLocationRelativeTo(frame);
        dialog.setLayout(new GridLayout(0, 7));
        JLabel monday = new JLabel("Montag");
        JLabel tuesday = new JLabel("Dienstag");
        JLabel wednesday = new JLabel("Mittwoch");
        JLabel thursday = new JLabel("Donnerstag");
        JLabel friday = new JLabel("Freitag");
        JLabel saturday = new JLabel("Samstag");
        JLabel sunday = new JLabel("Sonntag");
        dialog.add(monday);
        dialog.add(tuesday);
        dialog.add(wednesday);
        dialog.add(thursday);
        dialog.add(friday);
        dialog.add(saturday);
        dialog.add(sunday);
        JComboBox<String> mondayBox = new JComboBox<>(existingRecipesNames);
        mondayBox.setSize(dialog.getWidth()/7,dialog.getHeight()/3);
        // add buttons, ok button = get selected and write them to recipes
        dialog.add(mondayBox);
        dialog.setVisible(true);
        return MealPlanLoader.writeRecipesToPlan(recipes, plan);
    }

}
