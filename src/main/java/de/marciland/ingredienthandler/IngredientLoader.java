package de.marciland.ingredienthandler;

import static de.marciland.utilities.Constants.ingredientPath;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.charset.Charset;

import java.util.ArrayList;

public class IngredientLoader {

    /**
     * Writes the given ingredient to a file.
     *
     * @param ing ingredient to be saved in a file.
     */
    public static void saveIngredient(Ingredient ing) {
        if (ing == null) {
            System.out.println("Can't save empty ingredient. Ignore this message if creation process was canceled.");
            return;
        }
        ArrayList<String> data = new ArrayList<>();
        data.add("Name = " + ing.getName());
        data.add("Typ = " + ing.getType());
        data.add("kcal = " + ing.getKcal());
        data.add("Protein = " + (double) ing.getProtein());
        data.add("Zucker = " + ing.getCarbs());
        data.add("Fett = " + ing.getFat());
        FileWriter writer;
        try {
            writer = new FileWriter(new File(ingredientPath + ing.getName() + ".ing"), Charset.forName("UTF-8"));
            for (String string : data) {
                writer.write(string);
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to save ingredient: " + ing.getName() + System.lineSeparator() + e);
            System.exit(1);
        }
    }

    /**
     * Reads all ingredients found in resource/ingredients
     * and returns an array of ingredients.
     *
     * @return every ingredient found in an array.
     */
    public static Ingredient[] loadAllIngredients() {
        /*
         * check all files in ingredient directory.
         * Name of file is added to ingredientList if it's a file and ends with ".ing".
         */
        long startTime = System.currentTimeMillis();
        File ingredientFolder = new File(ingredientPath);
        File[] allIngredientFiles = ingredientFolder.listFiles();
        ArrayList<String> ingredientList = new ArrayList<>();
        for (File file : allIngredientFiles) {
            /*
             * Do not print an error message for .gitkeep.
             */
            if (file.getName().contains(".gitkeep")) {
                continue;
            }
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

    /**
     * This function is mainly used in load all ingredients.
     * Reads a given file and stores the data found in an ingredient entity.
     *
     * @param file filename of the ingredient to read from.
     * @return an ingredient entity containing all information found in the file.
     */
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
            reader.close();
        } catch (IOException e) {
            System.out.println("Failed to load ingredient: " + file + System.lineSeparator() + e);
            System.exit(1);
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

    /**
     * Convert an ingredient array to a string array
     * containing the names of the ingredients.
     *
     * @param ingredients an array of ingredients which should be read.
     * @return an array of strings containing the names of the ingredients.
     */
    public static String[] getIngredientNames(Ingredient[] ingredients) {
        ArrayList<String> names = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            names.add(ingredient.getName());
        }
        if (!names.isEmpty() && names != null) {
            String[] namesStrings = new String[names.size()];
            for (int i = 0; i < names.size(); i++) {
                namesStrings[i] = names.get(i);
            }
            // TODO sort list of ingredients
            return namesStrings;
        } else {
            return new String[0];
        }
    }

}