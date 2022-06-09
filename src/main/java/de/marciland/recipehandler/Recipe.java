package de.marciland.recipehandler;

import java.util.ArrayList;

import de.marciland.ingredienthandler.Ingredient;

/**
 * Recipes are used to create meal plans.
 * Each recipe contains multiple ingredients.
 * Those can be used to calculate the average nutrition information for a recipe
 *
 * @see Ingredient
 */
public class Recipe {

    // TODO cooking duration, in desc?
    private String name;
    private ArrayList<String> ingredients;
    private int kcal;
    private int fat;
    private int carbs;
    private int protein;
    // TODO tooltip description
    private String description;

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
        int[] nutri = RecipeLoader.generateNutritionInformation(ingredients);
        this.kcal = nutri[0];
        this.fat = nutri[1];
        this.carbs = nutri[2];
        this.protein = nutri[3];
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

    /**
     * Gets the kcal per 100g of the recipe.
     *
     * @return kcal/100g.
     */
    public int getKcal() {
        return kcal;
    }

    /**
     * Gets the fat per 100g of the recipe.
     *
     * @return fat/100g.
     */
    public int getFat() {
        return fat;
    }

    /**
     * Gets the carbs per 100g of the recipe.
     *
     * @return carbs/100g.
     */
    public int getCarbs() {
        return carbs;
    }

    /**
     * Gets the protein per 100g of the recipe.
     *
     * @return protein/100g.
     */
    public int getProtein() {
        return protein;
    }
}