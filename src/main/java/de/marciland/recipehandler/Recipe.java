package de.marciland.recipehandler;

import java.util.ArrayList;

public class Recipe {

    private String name = "";
    private ArrayList<String> ingredients = new ArrayList<>();
    private String description = "";

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

    /*
     * getters
     */
    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public ArrayList<String> getIngredients() {
        return this.ingredients;
    }

}