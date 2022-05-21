package de.marciland.recipehandler;

public class Ingredient {

    private String name = "";
    /*
     * type:
     * 0 = undefined
     * 1 = solid, should be in gramms
     * 2 = liquid, should be in milliliters
     * 3 = countable, should be in amount by pieces
     */
    private int type = 0;
    /*
     * nutrition information for 100g/100ml/1piece of ingredient.
     */
    private float kcal = 0;
    private float fat = 0;
    private float carbs = 0;
    private float protein = 0;

    public Ingredient(String name, int type, float kcal, float fat, float carbs, float protein) {
        this.name = name;
        if (type != 1 && type != 2 && type != 3) {
            // TODO handle wrong type
        }
        this.type = type;
        this.kcal = kcal;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
    }

    // TODO change toString for ing
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

    public int getType() {
        return type;
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
