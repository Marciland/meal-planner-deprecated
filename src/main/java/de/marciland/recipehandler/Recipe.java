package de.marciland.recipehandler;

import java.util.ArrayList;

import de.marciland.ingredienthandler.Ingredient;

//TODO recipe class
public class Recipe {

    private String name = "";
    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    private String description = "";

    public Recipe(String name, ArrayList<Ingredient> ingredients, String description) {
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
    }

    @Override
    public String toString() {
        String recipe = "";
        // write every ingredients name into the string and seperate them by linebreaks
        for (Ingredient ingredient : ingredients) {
            recipe = recipe + ingredient.getName() + System.lineSeparator();
        }
        // extra line between ingredients and description
        recipe = recipe + System.lineSeparator();
        recipe = recipe + description;
        return recipe;
    }

    /*
     * getters
     */

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public ArrayList<Ingredient> getIngredients() {
        return this.ingredients;
    }

}