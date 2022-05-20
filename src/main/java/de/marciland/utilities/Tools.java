package de.marciland.utilities;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import javax.swing.ListModel;

public class Tools {

    public static boolean checkFloat(String input) {
        try {
            Float.parseFloat(input);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * https://www.baeldung.com/java-string-encode-utf-8
     */
    public static String encode(String string) {
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(string);
        return StandardCharsets.UTF_8.decode(buffer).toString();
    }

    public static boolean validateIngredientsList(ListModel<Object> model) {

        // min 2 ingredients
        // every ingredient has g or ml or amount by pieces
        // TODO validate list
        for (int i = 0; i < model.getSize(); i++) {
            model.getElementAt(i);
        }
        // return true; // list valid
        return false; // list invalid
    }

}
