package de.marciland.planhandler;

import static de.marciland.utilities.Constants.planColumns;
import static de.marciland.utilities.Constants.planFile;
import static de.marciland.utilities.Constants.planRows;
import static javax.swing.SwingConstants.CENTER;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import de.marciland.recipehandler.Recipe;
import de.marciland.recipehandler.RecipeLoader;
import de.marciland.utilities.Tools;

/**
 * Loader class for the meal plan. Used to load from and save to files.
 *
 * @see MealPlan
 */
public class MealPlanLoader {

    /**
     * Tries to load a saved plan. If no plan exists null is returned.
     *
     * @return the plan found in resource file.
     *         If no plan can be found, returns null.
     * @see MealPlan
     */
    public static JLabel[][] loadMealPlan() {
        long startTime = System.currentTimeMillis();
        if (!Tools.fileExists(planFile)) {
            return null;
        }
        JLabel[][] plan = prepareEmptyPlan();
        Recipe[] recipes = new Recipe[7];
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(new File(planFile)));
            String line;
            reading: while (true) {
                line = reader.readLine();
                if (line == null) {
                    break reading;
                }
                if (line.startsWith("Montag")) {
                    line = line.replace("Montag = ", "").trim();
                    recipes[0] = RecipeLoader.loadRecipe(line + ".rec");
                }
                if (line.startsWith("Dienstag")) {
                    line = line.replace("Dienstag = ", "").trim();
                    recipes[1] = RecipeLoader.loadRecipe(line + ".rec");
                }
                if (line.startsWith("Mittwoch")) {
                    line = line.replace("Mittwoch = ", "").trim();
                    recipes[2] = RecipeLoader.loadRecipe(line + ".rec");
                }
                if (line.startsWith("Donnerstag")) {
                    line = line.replace("Donnerstag = ", "").trim();
                    recipes[3] = RecipeLoader.loadRecipe(line + ".rec");
                }
                if (line.startsWith("Freitag")) {
                    line = line.replace("Freitag = ", "").trim();
                    recipes[4] = RecipeLoader.loadRecipe(line + ".rec");
                }
                if (line.startsWith("Samstag")) {
                    line = line.replace("Samstag = ", "").trim();
                    recipes[5] = RecipeLoader.loadRecipe(line + ".rec");
                }
                if (line.startsWith("Sonntag")) {
                    line = line.replace("Sonntag = ", "").trim();
                    recipes[6] = RecipeLoader.loadRecipe(line + ".rec");
                }
            }
            reader.close();
        } catch (FileNotFoundException f) {
            // should not happen due to checks earlier in this method.
        } catch (IOException e) {
            System.out.println("Error while reading " + planFile);
            System.exit(1);
        }
        if (Tools.arrayContainsNull(recipes)) {
            System.out.println("Reading meal plan from file failed! Not all recipes have been saved!");
            System.exit(1);
        }
        plan = writeRecipesToPlan(recipes, plan);
        long endTime = System.currentTimeMillis();
        System.out.println("Loading meal plan took " + (endTime - startTime) + "ms");
        return plan;
    }

    /**
     * Write recipes into the meal plan.
     * Uses all recipes to write and the current plan.
     * Recipes to write must be exactly 7.
     * Meal plan needs to be of the extact size (8x7).
     *
     * @param recipes recipes that should be written into the plan.
     * @param plan    plan that should be modified.
     * @return modified plan with given recipes.
     * @see MealPlan
     * @see Recipe
     */
    public static JLabel[][] writeRecipesToPlan(Recipe[] recipes, JLabel[][] plan) {
        if (Tools.arrayContainsNull(recipes) || recipes.length != 7) {
            System.out.println("Error in recipes!");
            System.exit(1);
        }
        if (plan.length != 8 && plan[0].length != 7) {
            System.out.println("Plan has the wrong size: " + plan.length + " " + plan[0].length);
            System.exit(1);
        }
        for (int i = 0; i < plan.length - 1; i++) {
            if (Tools.arrayContainsNull(plan[i])) {
                System.out.println("Plan has labels that are not initialized!");
                System.exit(1);
            }
        }
        for (int i = 1; i < 8; i++) {
            for (int j = 1; j < 7; j++) {
                switch (j) {
                    case 1:
                        plan[i][j].setText(recipes[i - 1].getName());
                        break;
                    case 2:
                        ArrayList<String> list = recipes[i - 1].getIngredients();
                        String text = "<html>";
                        for (String string : list) {
                            text = text + string + "<br/>";
                        }
                        text = text + "</html>";
                        plan[i][j].setText(text);
                        break;
                    case 3:
                        plan[i][j].setText(recipes[i - 1].getKcal() + "kcal");
                        break;
                    case 4:
                        plan[i][j].setText(recipes[i - 1].getFat() + "g");
                        break;
                    case 5:
                        plan[i][j].setText(recipes[i - 1].getCarbs() + "g");
                        break;
                    case 6:
                        plan[i][j].setText(recipes[i - 1].getProtein() + "g");
                        break;
                }
                plan[i][j].setHorizontalAlignment(CENTER);
            }
        }
        return plan;
    }

    /**
     * Returns a recipe array containing all recipes saved in the plan.
     *
     * @param plan a valid meal plan.
     * @return recipes from the plan.
     */
    public static Recipe[] getRecipesFromPlan(JLabel[][] plan) {
        Recipe[] recipes = new Recipe[7];
        for (int i = 1; i < 8; i++) {
            recipes[i - 1] = RecipeLoader.loadRecipe(plan[i][1].getText() + ".rec");
        }
        return recipes;
    }

    /**
     * Prepares an empty meal plan.
     * Creates new labels for each cell and sets text for fixed labels.
     *
     * @return an empty plan.
     * @see MealPlan
     */
    public static JLabel[][] prepareEmptyPlan() {
        JLabel[][] plan = new JLabel[planColumns][planRows];
        for (int i = 0; i < plan.length; i++) {
            for (int j = 0; j < plan[0].length; j++) {
                plan[i][j] = new JLabel();
                plan[i][j].setBorder(BorderFactory.createLineBorder(Color.RED));
                ;
            }
        }
        plan[0][1].setText("Rezept: ");
        plan[0][2].setText("Zutaten: ");
        plan[0][3].setText("Kcal: ");
        plan[0][4].setText("Fett: ");
        plan[0][5].setText("Kohlenhydrate: ");
        plan[0][6].setText("Eiweiß: ");
        plan[1][0].setText("Montag");
        plan[2][0].setText("Dienstag");
        plan[3][0].setText("Mittwoch");
        plan[4][0].setText("Donnerstag");
        plan[5][0].setText("Freitag");
        plan[6][0].setText("Samstag");
        plan[7][0].setText("Sonntag");
        plan[0][1].setHorizontalAlignment(CENTER);
        plan[0][2].setHorizontalAlignment(CENTER);
        plan[0][3].setHorizontalAlignment(CENTER);
        plan[0][4].setHorizontalAlignment(CENTER);
        plan[0][5].setHorizontalAlignment(CENTER);
        plan[0][6].setHorizontalAlignment(CENTER);
        plan[1][0].setHorizontalAlignment(CENTER);
        plan[2][0].setHorizontalAlignment(CENTER);
        plan[3][0].setHorizontalAlignment(CENTER);
        plan[4][0].setHorizontalAlignment(CENTER);
        plan[5][0].setHorizontalAlignment(CENTER);
        plan[6][0].setHorizontalAlignment(CENTER);
        plan[7][0].setHorizontalAlignment(CENTER);
        return plan;
    }

    /**
     * Saves the given plan to a file.
     * If the file already exists, nothing will be saved.
     *
     * @param plan plan that should be saved to a file.
     *             Only recipe names of that plan will be saved.
     * @see MealPlan
     */
    public static void savePlan(JLabel[][] plan) {
        if (Tools.fileExists(planFile)) {
            System.out.println("Tried to save plan, but plan already exists!");
            return;
        }
        String day = "";
        String[] lines = new String[7];
        for (int i = 0; i < 7; i++) {
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
            lines[i] = day + " = " + plan[i + 1][1].getText();
        }
        FileWriter writer;
        try {
            writer = new FileWriter(new File(planFile));
            for (String string : lines) {
                writer.write(string);
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while writing to " + planFile);
            System.exit(1);
        }
    }

}
