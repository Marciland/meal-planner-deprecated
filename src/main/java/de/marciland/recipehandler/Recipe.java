package de.marciland.recipehandler;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private List<Ingredient> ingredients;
    private String description;

    public Recipe(List<Ingredient> ingredients, String description) {
        this.ingredients = new ArrayList<>(ingredients);
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
    public String getDescription() {
        return description;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

}