package de.marciland.recipehandler;

import static de.marciland.utilities.Constants.ingredientPath;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.nio.charset.Charset;

import java.util.ArrayList;
import java.util.List;

public class Loader {

    public static Ingredient[] loadAllIngredients() {
        /*
         * check all files in ingredient directory.
         * Name of file is added to ingredientList if it's a file and ends with ".ing".
         */
        long startTime = System.currentTimeMillis();
        File ingredientFolder = new File(ingredientPath);
        File[] allIngredientsFiles = ingredientFolder.listFiles();
        List<String> ingredientList = new ArrayList<>();
        for (File file : allIngredientsFiles) {
            if (!file.isFile()) {
                System.out.println(file + " is not a file!");
                continue;
            }
            if ((!file.getName().contains(".ing"))) {
                System.out.println(file + " is not an ingredient file!");
                continue;
            }
            ingredientList.add(file.getName());
        }
        /*
         * load every ingredient into the array and return it.
         */
        Ingredient[] allIngredients = new Ingredient[ingredientList.size()];
        for (int i = 0; i < ingredientList.size(); i++) {
            allIngredients[i] = loadIngredient(ingredientList.get(i));
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Loading all ingredients took " + (endTime - startTime) + "ms");
        return allIngredients;
    }

    private static Ingredient loadIngredient(String file) {
        String name = null;
        int type = 0;
        float kcal = 0;
        float fat = 0;
        float carbs = 0;
        float protein = 0;
        BufferedReader reader;
        String line;
        try {
            reader = new BufferedReader(new FileReader(new File(ingredientPath + file), Charset.forName("UTF-8")));
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name")) {
                    line = line.replace("Name = ", "");
                    name = line;
                }
                if (line.startsWith("Typ")) {
                    line = line.replace("Typ = ", "");
                    type = Integer.parseInt(line);
                }
                if (line.startsWith("kcal")) {
                    line = line.replace("kcal = ", "");
                    kcal = Float.parseFloat(line);
                }
                if (line.startsWith("Protein")) {
                    line = line.replace("Protein = ", "");
                    protein = Float.parseFloat(line);
                }
                if (line.startsWith("Zucker")) {
                    line = line.replace("Zucker = ", "");
                    carbs = Float.parseFloat(line);
                }
                if (line.startsWith("Fett")) {
                    line = line.replace("Fett = ", "");
                    fat = Float.parseFloat(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (name == null || type == 0 || kcal == 0) {
            System.out.println(System.lineSeparator() + "Error when loading " + file + " ingredient:");
            System.out.println("name, type or kcal empty!" + System.lineSeparator());
        }
        if (fat == 0 && carbs == 0 && protein == 0) {
            System.out.println(System.lineSeparator() + "Error when loading " + file + " ingredient:");
            System.out.println("wrong information about nutrition!" + System.lineSeparator());
        }
        return new Ingredient(name, type, kcal, fat, carbs, protein);
    }
}
