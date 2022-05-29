package de.marciland.windowhandler;

import de.marciland.ingredienthandler.Ingredient;

public class Dialog {

    // TODO doc
    public static Ingredient getIngredientInformation() {
        String name = null;
        int type = 0;
        float kcal = 0;
        float fat = 0;
        float carbs = 0;
        float protein = 0;
        // TODO ask for input
        if (name == null) {
            System.out.println("Name empty while getting ingredient information");
            System.exit(1);
        }
        if (type != 1 || type != 2 || type != 3) {
            System.out.println("Wrong type while getting ingredient information");
            System.exit(1);
        }
        if (fat == 0 && carbs == 0 && protein == 0) {
            System.out.println("Nutrition information empty while getting ingredient information!");
            System.exit(1);
        }
        return new Ingredient(name, type, kcal, fat, carbs, protein);
    }

}
