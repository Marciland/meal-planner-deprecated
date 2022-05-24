package de.marciland.ingredienthandler;

import static de.marciland.utilities.Constants.ingredientPath;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.nio.charset.Charset;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ListModel;

public class IngredientLoader {

    /**
     * Reads all ingredients found in resource/ingredients
     * and returns an array of ingredients.
     *
     * @return every ingredient found in an array.
     */
    public static Ingredient[] loadAllIngredients() {
        /*
         * check all files in ingredient directory.
         * Name of file is added to ingredientList if it's a file and ends with ".ing".
         */
        long startTime = System.currentTimeMillis();
        File ingredientFolder = new File(ingredientPath);
        File[] allIngredientsFiles = ingredientFolder.listFiles();
        List<String> ingredientList = new ArrayList<>();
        for (File file : allIngredientsFiles) {
            /*
             * Do not print an error message for .gitkeep.
             */
            if (file.getName().contains(".gitkeep")) {
                continue;
            }
            if (!file.isFile()) {
                System.out.println(file + " is not a file!");
                continue;
            }
            if ((!file.getName().contains(".ing"))) {
                System.out.println(file + " is not an ingredient file!");
                continue;
            }
            ingredientList.add(file.getName());
        }
        /*
         * load every ingredient into the array and return it.
         */
        Ingredient[] allIngredients = new Ingredient[ingredientList.size()];
        for (int i = 0; i < ingredientList.size(); i++) {
            allIngredients[i] = loadIngredient(ingredientList.get(i));
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Loading all ingredients took " + (endTime - startTime) + "ms");
        return allIngredients;
    }

    /**
     * This function is mainly used in load all ingredients.
     * Reads a given file and stores the data found in an ingredient identity.
     *
     * @param file filename of the ingredient to read from.
     * @return an ingredient identity containing all information found in the file.
     */
    private static Ingredient loadIngredient(String file) {
        String name = null;
        int type = 0;
        float kcal = 0;
        float fat = 0;
        float carbs = 0;
        float protein = 0;
        BufferedReader reader;
        String line;
        try {
            reader = new BufferedReader(new FileReader(new File(ingredientPath + file), Charset.forName("UTF-8")));
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name")) {
                    line = line.replace("Name = ", "");
                    name = line;
                }
                if (line.startsWith("Typ")) {
                    line = line.replace("Typ = ", "");
                    type = Integer.parseInt(line);
                }
                if (line.startsWith("kcal")) {
                    line = line.replace("kcal = ", "");
                    kcal = Float.parseFloat(line);
                }
                if (line.startsWith("Protein")) {
                    line = line.replace("Protein = ", "");
                    protein = Float.parseFloat(line);
                }
                if (line.startsWith("Zucker")) {
                    line = line.replace("Zucker = ", "");
                    carbs = Float.parseFloat(line);
                }
                if (line.startsWith("Fett")) {
                    line = line.replace("Fett = ", "");
                    fat = Float.parseFloat(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (name == null || type == 0 || kcal == 0) {
            System.out.println(System.lineSeparator() + "Error when loading " + file + " ingredient:");
            System.out.println("name, type or kcal empty!" + System.lineSeparator());
        }
        if (fat == 0 && carbs == 0 && protein == 0) {
            System.out.println(System.lineSeparator() + "Error when loading " + file + " ingredient:");
            System.out.println("wrong information about nutrition!" + System.lineSeparator());
        }
        return new Ingredient(name, type, kcal, fat, carbs, protein);
    }

    /**
     * Validates an ingredient list. Returns true if list can be used for a recipe.
     * Requirements to fulfill are:
     * -at least 2 ingredients
     * - every ingredient has an amount specified.
     *
     * @param model list to be checked. should contain Strings
     * @return true if list can be used for a recipe
     */
    public static boolean validateIngredientsList(ListModel<Object> model) {

        // min 2 ingredients
        // every ingredient has g or ml or amount by pieces
        // TODO validate list
        for (int i = 0; i < model.getSize(); i++) {
            model.getElementAt(i);
        }
        // return true; // list valid
        return false; // list invalid
    }

    /**
     * Checks if a given ingredient name is already existing in given list.
     *
     * @param ingredientName name of the ingredient that should be checked
     * @param list           list in which the ingredient is searched.
     *                       The list should only contain names of ingredients.
     * @return true if ingredient already exists in the list.
     */
    public static boolean checkIngredientExists(String ingredientName, ListModel<String> list) {
        /*
         * If list is empty or not initialized false is returned.
         */
        if (list.getSize() == 0 || list == null) {
            return false;
        }
        /*
         * If name is found true is returned.
         */
        for (int i = 0; i < list.getSize(); i++) {
            if (list.getElementAt(i) == ingredientName) {
                return true;
            }
        }
        /*
         * If list is not empty and is initialized
         * but the ingredientName could not be found then true is returned.
         */
        return false;
    }

}
