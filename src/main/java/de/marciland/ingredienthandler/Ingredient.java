package de.marciland.ingredienthandler;

public class Ingredient {

    private String name = "";
    /*
     * type:
     * 0 = undefined
     * 1 = solid, should be in grams
     * 2 = liquid, should be in ml
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

    //TODO doc
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
