package de.marciland.recipehandler;

import java.util.ArrayList;

import de.marciland.ingredienthandler.Ingredient;

public class Recipe {

    // TODO cooking duration, in desc?
    private String name = "";
    private ArrayList<String> ingredients = new ArrayList<>();
    // TODO tooltip description
    private String description = "";

    /**
     * Every recipe will be saved in a file when created.
     *
     * @param name        name of the recipe. This should be unique.
     * @param ingredients list of ingredients that are contained in this recipe.
     * @param description a description on how to cook/bake this recipe.
     */
    public Recipe(String name, ArrayList<String> ingredients, String description) {
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
    }

    // TODO recipe to string
    @Override
    public String toString() {
        String recipe = "";
        // write every ingredients name into the string and seperate them by linebreaks
        for (String string : ingredients) {
            recipe = recipe + string + System.lineSeparator();
        }
        // extra line between ingredients and description
        recipe = recipe + System.lineSeparator();
        recipe = recipe + description;
        return recipe;
    }

    /**
     * Return the name of this recipe. This should be unique.
     *
     * @return name of the recipe.
     * @see Recipe
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return the description of this recipe. Should be already formatted.
     *
     * @return description of the recipe.
     * @see Recipe
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Return the list of ingredients needed for this recipe.
     *
     * @return list of ingredients for creating the recipe.
     * @see Recipe
     * @see Ingredient
     */
    public ArrayList<String> getIngredients() {
        return this.ingredients;
    }

}