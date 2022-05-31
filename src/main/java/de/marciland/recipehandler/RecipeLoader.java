package de.marciland.recipehandler;

import static de.marciland.utilities.Constants.recipePath;

import de.marciland.ingredienthandler.Ingredient;

import java.io.File;
import java.util.ArrayList;

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
     * Load all recipes from files into an array.
     *
     * @return an array of all recipes found in resource folder.
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
     *
     * @param file file to be read.
     * @return a recipe entity read from the given file.
     */
    public static Recipe loadRecipe(String file) {
        String name = null;
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        String description = null;
        // TODO reading
        if (name == null || ingredients.isEmpty() || description == null) {
            System.out.println("Failed to load recipe: " + file);
            System.exit(1);
        }
        return new Recipe(name, ingredients, description);
    }

}
