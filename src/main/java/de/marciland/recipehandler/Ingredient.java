package de.marciland.recipehandler;

//TODO ingredient either is liquid/solid/or countable
public class Ingredient {

    private String name = "";
    /*
     * nutrition information for 100g of ingredient.
     */
    private float kcal = 0;
    private float fat = 0;
    private float carbs = 0;
    private float protein = 0;

    public Ingredient(String name, float kcal, float fat, float carbs, float protein) {
        this.name = name;
        this.kcal = kcal;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
    }

    @Override
    public String toString() {
        return getName() + " hat auf 100g:"
                + " Kcal: " + getKcal()
                + " Fett: " + getFat()
                + " Zucker: " + getCarbs()
                + " Eiweiß: " + getProtein();
    }

    /*
     * getters
     */
    public String getName() {
        return this.name;
    }

    public float getKcal() {
        return this.kcal;
    }

    public float getFat() {
        return this.fat;
    }

    public float getCarbs() {
        return this.carbs;
    }

    public float getProtein() {
        return this.protein;
    }
}
