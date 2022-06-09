package de.marciland.planhandler;

import static de.marciland.utilities.Constants.planColumns;
import static de.marciland.utilities.Constants.planFile;
import static de.marciland.utilities.Constants.planRows;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
        File file = new File(planFile);
        if (!file.exists() || file.isDirectory()) {
            return null;
        }
        JLabel[][] labels = prepareEmptyPlan();
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
        labels = writeRecipesToPlan(recipes, labels);
        long endTime = System.currentTimeMillis();
        System.out.println("Loading meal plan took " + (endTime - startTime) + "ms");
        return labels;
    }

    // TODO doc
    public static JLabel[][] writeRecipesToPlan(Recipe[] recipes, JLabel[][] labels) {
        if (Tools.arrayContainsNull(recipes) || recipes.length != 7) {
            System.out.println("Error in recipes!");
            System.exit(1);
        }
        if (labels.length != 8 && labels[0].length != 7) {
            System.out.println("Plan has the wrong size: " + labels.length + " " + labels[0].length);
            System.exit(1);
        }
        for (int i = 0; i < labels.length - 1; i++) {
            if (Tools.arrayContainsNull(labels[i])) {
                System.out.println("Plan has null labels!");
                System.exit(1);
            }
        }
        for (int i = 1; i < 8; i++) {
            for (int j = 1; j < 7; j++) {
                switch (j) {
                    case 1:
                        labels[i][j].setText(recipes[i - 1].getName());
                        break;
                    case 2:
                        ArrayList<String> list = recipes[i - 1].getIngredients();
                        String text = "Zutaten: " + System.lineSeparator();
                        for (String string : list) {
                            text = text + string + System.lineSeparator();
                        }
                        labels[i][j].setText(text);
                        break;
                    case 3:
                        labels[i][j].setText(recipes[i - 1].getKcal() + "kcal");
                        break;
                    case 4:
                        labels[i][j].setText(recipes[i - 1].getFat() + "g");
                        break;
                    case 5:
                        labels[i][j].setText(recipes[i - 1].getCarbs() + "g");
                        break;
                    case 6:
                        labels[i][j].setText(recipes[i - 1].getProtein() + "g");
                        break;
                }
            }
        }
        return labels;
    }

    /**
     * Prepares an empty meal plan.
     * Creates new labels for each cell and sets text for fixed labels.
     *
     * @return an empty plan.
     * @see MealPlan
     */
    public static JLabel[][] prepareEmptyPlan() {
        JLabel[][] labels = new JLabel[planColumns][planRows];
        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels[0].length; j++) {
                labels[i][j] = new JLabel();
                labels[i][j].setBorder(BorderFactory.createLineBorder(Color.RED));
                ;
            }
        }
        labels[0][0].setText("-Alle Nährwerte pro 100g des Rezepts!-");
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
