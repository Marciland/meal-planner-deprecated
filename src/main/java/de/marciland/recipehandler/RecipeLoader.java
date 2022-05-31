package de.marciland.recipehandler;

import static de.marciland.utilities.Constants.recipePath;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
        List<String> recipeList = new ArrayList<>();
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

    // TODO doc
    public static Recipe loadRecipe(String file) {

    }

}
