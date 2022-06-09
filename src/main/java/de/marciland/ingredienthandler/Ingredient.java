package de.marciland.ingredienthandler;

/**
 * Ingredients are used in recipe creation.
 * They are saved in files and are loaded from those.
 *
 * @see IngredientLoader
 */
public class Ingredient {

    private String name = "";

    // type: 0 = undefined / 1 = gram / 2 = ml / 3 = pieces
    private int type = 0;

    // nutrition information for 100g/100ml/1piece of ingredient.
    private float kcal = 0;
    private float fat = 0;
    private float carbs = 0;
    private float protein = 0;

    /**
     * Every ingredient will be defined in a file when created.
     *
     * @param name    name of the ingredient.
     * @param type    type: 1 gram, 2 ml, 3 pieces.
     * @param kcal    amount of kcal per 100g/100ml/1piece.
     * @param fat     amount of fat per 100g/100ml/1piece.
     * @param carbs   amount of carbs per 100g/100ml/1piece.
     * @param protein amount of protein per 100g/100ml/1piece.
     */
    public Ingredient(String name, int type, float kcal, float fat, float carbs, float protein) {
        this.name = name;
        if (type != 1 && type != 2 && type != 3) {
            System.out.println("Ingredient: " + getName() + " has an invalid type!");
            System.exit(1);
        }
        this.type = type;
        this.kcal = kcal;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
    }

    /**
     * Override method to define how ingredients should be visualized in the app.
     *
     * @return ingredient information formatted.
     * @see Ingredient
     */
    @Override
    public String toString() {
        String typeStr = null;
        switch (this.type) {
            case 1:
                typeStr = " hat auf 100g:";
                break;
            case 2:
                typeStr = " hat auf 100ml:";
                break;
            case 3:
                typeStr = " hat pro Stück:";
                break;
        }
        if (typeStr == null) {
            System.out.println("Ingredient: " + getName() + " has an invalid type!");
            System.exit(1);
        }
        return getName() + typeStr
                + " Kcal: " + getKcal()
                + " Fett: " + getFat()
                + " Zucker: " + getCarbs()
                + " Eiweiß: " + getProtein();
    }

    /**
     * Gets the name of the ingredient.
     *
     * @return name of the ingredient.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the type of the ingredient.
     * 0 = undefined / 1 = gram / 2 = ml / 3 = pieces
     *
     * @return type of the ingredient.
     */
    public int getType() {
        return type;
    }

    /**
     * Gets the kcal per 100g/100ml/1piece of the ingredient.
     *
     * @return kcal of the ingredient.
     */
    public float getKcal() {
        return this.kcal;
    }

    /**
     * Gets the fat per 100g/100ml/1piece of the ingredient.
     *
     * @return fat of the ingredient.
     */
    public float getFat() {
        return this.fat;
    }

    /**
     * Gets the carbs per 100g/100ml/1piece of the ingredient.
     *
     * @return carbs of the ingredient.
     */
    public float getCarbs() {
        return this.carbs;
    }

    /**
     * Gets the protein per 100g/100ml/1piece of the ingredient.
     *
     * @return protein of the ingredient.
     */
    public float getProtein() {
        return this.protein;
    }
}
