package de.marciland.recipehandler;

import static de.marciland.utilities.Constants.recipePath;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.swing.ListModel;

import de.marciland.ingredienthandler.Ingredient;

public class RecipeLoader {

    /**
     * Get all recipe names from an array of recipes.
     *
     * @param recipes the recipes which names should be returned.
     * @return the names of the recipes as string array.
     */
    public static String[] getRecipeNames(Recipe[] recipes) {
        if (recipes.length == 0) {
            return new String[0];
        }
        String[] recipeNames = new String[recipes.length];
        for (int i = 0; i < recipes.length; i++) {
            recipeNames[i] = recipes[i].getName();
        }
        return recipeNames;
    }

    /**
     * Load all recipes from the resource directory into an array.
     *
     * @return an array of all recipes found in resource folder.
     * @see Recipe
     */
    public static Recipe[] loadAllRecipes() {
        /*
         * check all files in recipe directory.
         * Name of file is added to recipeList if it's a file and ends with ".rec".
         */
        long startTime = System.currentTimeMillis();
        File recipeFolder = new File(recipePath);
        File[] allRecipeFiles = recipeFolder.listFiles();
        ArrayList<String> recipeList = new ArrayList<>();
        for (File file : allRecipeFiles) {
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
            if ((!file.getName().contains(".rec"))) {
                System.out.println(file + " is not a recipe file!");
                continue;
            }
            recipeList.add(file.getName());
        }
        /*
         * load every recipe into the array and return it.
         */
        Recipe[] allRecipes = new Recipe[recipeList.size()];
        for (int i = 0; i < recipeList.size(); i++) {
            allRecipes[i] = loadRecipe(recipeList.get(i));
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Loading all recipes took " + (endTime - startTime) + "ms");
        return allRecipes;
    }

    /**
     * Reads all information from given file
     * and stores the information into a recipe entity.
     * This is mainly used for loadAllRecipes().
     *
     * @param file file to be read from.
     *             The file needs to be existing and formatted.
     * @return a recipe entity read from the given file.
     * @see Recipe
     */
    public static Recipe loadRecipe(String file) {
        String name = null;
        ArrayList<String> ingredients = new ArrayList<>();
        String description = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File(recipePath + file)));
        } catch (FileNotFoundException e) {
            System.out.println("Could not find " + file);
            System.exit(1);
        }
        try {
            String line = null;
            reading: while (true) {
                line = reader.readLine();
                if (line == null) {
                    break reading;
                }
                if (line.startsWith("Name = ")) {
                    line = line.replace("Name = ", "").trim();
                    if (!line.isEmpty()) {
                        name = line;
                        continue;
                    }
                }
                if (line.startsWith("Zutaten:")) {
                    ingredients: while (true) {
                        line = reader.readLine();
                        if (line.startsWith("Beschreibung:")) {
                            break ingredients;
                        }
                        if (line.startsWith("-")) {
                            ingredients.add(line.replace("-", "").trim());
                        }
                    }
                }
                if (line.startsWith("Beschreibung:")) {
                    while (true) {
                        line = reader.readLine();
                        if (line == null) {
                            break reading;
                        }
                        if (description == null) {
                            description = line;
                            continue;
                        }
                        description = description + System.lineSeparator() + line;
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error while reading " + file);
            System.exit(1);
        }
        if (name == null || ingredients.isEmpty() || description == null) {
            System.out.println("Failed to load recipe: " + file);
            System.exit(1);
        }
        return new Recipe(name, ingredients, description);
    }

    /**
     * Validates an ingredient list. Returns true if list can be used for a recipe.
     * Requirements to fulfill are:
     * - at least 2 ingredients
     * - every ingredient has an amount specified.
     *
     * @param model list to be checked.
     *              Should contain amount and name of ingredient.
     * @return true if list can be used for a recipe.
     * @see Recipe
     * @see Ingredient
     */
    public static boolean validateRecipe(ListModel<String> model) {
        if (model.getSize() < 2) {
            return false;
        }
        String[] strings;
        for (int i = 0; i < model.getSize(); i++) {
            strings = model.getElementAt(i).split(" ");
            if (!strings[0].contains("g") && !strings[0].contains("ml") && !strings[0].contains("stk.")) {
                return false;
            }
        }
        return true;
    }

    /**
     * Saves a given recipe to a file.
     *
     * @param name        name of the recipe.
     * @param ingredients list of ingredients to be used in the recipe.
     * @param description the description of the recipe.
     * @return returns true if saving was successful.
     * @see Recipe
     * @see Ingredient
     */
    public static boolean saveRecipe(String name, ListModel<String> ingredients, String description) {
        if (name == null || name.isEmpty()) {
            System.out.println("Invalid name: " + name);
            return false;
        }
        if (!validateRecipe(ingredients)) {
            System.out.println("Recipe not valid. " + ingredients);
            return false;
        }
        ArrayList<String> list = new ArrayList<>();
        list.add("Name = " + name);
        list.add("Zutaten:");
        for (int i = 0; i < ingredients.getSize(); i++) {
            list.add("-" + ingredients.getElementAt(i));
        }
        list.add("Beschreibung:");
        list.add(description);
        FileWriter writer;
        try {
            writer = new FileWriter(new File(recipePath + name + ".rec"), Charset.forName("UTF-8"));
            for (String string : list) {
                writer.write(string);
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to save recipe! " + name);
            System.exit(1);
        }
        return true;
    }

}
