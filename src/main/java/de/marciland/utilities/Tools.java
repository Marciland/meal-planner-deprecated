package de.marciland.utilities;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.swing.ListModel;

/**
 * Contains all functions that can not be sorted into other classes.
 * Mainly contains functions to verify, compare and get information.
 */
public class Tools {

    /**
     * Checks if input is a float.
     *
     * @param input String that will be checked.
     * @return true if input is a float.
     * @see Float
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
     * @see Integer
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
     * @see Double
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
     * @return utf-8 encoded string
     * @see StandardCharsets
     */
    public static String encode(String string) {
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(string);
        return StandardCharsets.UTF_8.decode(buffer).toString();
    }

    /**
     * Convert a list of strings to an array of strings.
     * This was needed due to .toArray() returning an Object[];
     *
     * @param list list that should be converted.
     * @return an array containing the elements of the list.
     */
    public static String[] stringListToArray(ArrayList<String> list) {
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

    /**
     * Checks if a given name is already existing in given list.
     *
     * @param name name of the ingredient that should be checked.
     * @param list list in which the name is searched.
     * @return true if name already exists in the list.
     */
    public static boolean checkNameExists(String name, ListModel<String> list) {
        /*
         * If list is empty or not initialized false is returned.
         */
        if (list.getSize() == 0 || list == null) {
            return false;
        }
        /*
         * If name is found true is returned.
         */
        for (int i = 0; i < list.getSize(); i++) {
            if (list.getElementAt(i).equals(name)) {
                return true;
            }
        }
        /*
         * If list is not empty and is initialized
         * but the name could not be found then true is returned.
         */
        return false;
    }

    /**
     * Checks if a given name could exist in a given list.
     *
     * @param name name that should be checked.
     * @param list list in which the name is searched.
     * @return true if name could exist based on given list.
     */
    public static boolean checkNameCouldExist(String name, ListModel<String> list) {
        if (checkNameExists(name, list)) {
            return true;
        }
        for (int i = 0; i < list.getSize(); i++) {
            if (name.toLowerCase().contains(list.getElementAt(i).toLowerCase())) {
                return true;
            }
            if (list.getElementAt(i).toLowerCase().contains(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get all names matching the given list.
     * This should only be called if "checkNameCouldExist" returned true.
     *
     * @param name name that should match.
     * @param list list that is looked through for a match.
     * @return an array of strings based on matches.
     */
    public static String[] getSimilarNames(String name, ListModel<String> list) {
        ArrayList<String> similarNamesList = new ArrayList<>();
        for (int i = 0; i < list.getSize(); i++) {
            if (name.toLowerCase().contains(list.getElementAt(i).toLowerCase())) {
                similarNamesList.add(list.getElementAt(i));
            } else if (list.getElementAt(i).toLowerCase().contains(name.toLowerCase())) {
                similarNamesList.add(list.getElementAt(i));
            }
        }
        String[] similarNamesArray = new String[similarNamesList.size()];
        for (int i = 0; i < similarNamesArray.length; i++) {
            similarNamesArray[i] = similarNamesList.get(i);
        }
        if (similarNamesArray == null || similarNamesArray.length == 0) {
            System.out.println("Found no similarity, but should find some!");
            System.exit(1);
        }
        return similarNamesArray;
    }

}
