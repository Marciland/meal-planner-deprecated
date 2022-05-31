package de.marciland.utilities;

import de.marciland.ingredienthandler.Ingredient;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Tools {

    /**
     * Checks if input is a float.
     *
     * @param input String that will be checked.
     * @return true if input is a float.
     */
    public static boolean checkFloat(String input) {
        try {
            Float.parseFloat(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if input is an int.
     *
     * @param input String that will be checked.
     * @return true if input is an int.
     */
    public static boolean checkInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if input is a double.
     *
     * @param input String that will be checked.
     * @return true if input is a double.
     */
    public static boolean checkDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * https://www.baeldung.com/java-string-encode-utf-8
     *
     * @param string string to encode using utf-8
     *
     * @return utf-8 encoded string
     */
    public static String encode(String string) {
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(string);
        return StandardCharsets.UTF_8.decode(buffer).toString();
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
            return null;
        }
    }

    /**
     * Convert a list of strings to an array of strings.
     * This was needed due to .toArray() returning an Object[];
     *
     * @param list list that should be converted.
     * @return an array containing the elements of the list.
     */
    public static String[] stringListToArray(List<String> list) {
        if (list == null) {
            return new String[0];
        }
        String[] array = new String[list.size()];
        if (list.isEmpty()) {
            return array;
        }
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

}
