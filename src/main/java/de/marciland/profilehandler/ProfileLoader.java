package de.marciland.profilehandler;

import static de.marciland.utilities.Constants.profilePath;

import de.marciland.utilities.Tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.charset.Charset;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ProfileLoader {

    public static String loadProfileButton(String profile) {
        String name = null;
        File profileFile;
        BufferedReader reader;
        String line;
        try {
            profileFile = new File(profilePath + profile + ".prof");
            if (profileFile.createNewFile()) {
                System.out.println("New " + profile + " file was created");
            }
            reader = new BufferedReader(new FileReader(profileFile, Charset.forName("UTF-8")));
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name")) {
                    line = line.replace("Name = ", "");
                    if (!line.isEmpty()) {
                        name = line;
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (name == null) {
            return profile;
        } else {
            return name;
        }
    }

    public static Profile loadProfile(String profile, JFrame parent) {
        long startTime = System.currentTimeMillis();
        String filePath = profilePath + profile + ".prof";
        String name = null;
        String gender = null;
        int age = 0;
        int height = 0;
        float weight = 0;
        int kcal = 0;
        String goal = null;
        boolean loading = false;
        boolean canceled = false;
        boolean written = false;
        BufferedReader reader;
        FileWriter writer;
        String line;
        List<String> data = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(new File(filePath), Charset.forName("UTF-8")));
            /*
             * Reads profile content from the profile file and decides
             * whether a new profile needs to be created
             * or an existing should be loaded.
             */
            wait: while (true) {
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Name")) {
                        line = line.replace("Name =", "").trim();
                        if (!line.isEmpty()) {
                            loading = true;
                            name = line;
                        } else {
                            String input = "";
                            /*
                             * wait until user input is valid.
                             */
                            while (input.length() < 3) {
                                input = JOptionPane.showInputDialog(parent.getContentPane(), "Wie lautet dein Name?",
                                        "Bitte Namen eingeben!", JOptionPane.QUESTION_MESSAGE);
                                if (input == null) {
                                    canceled = true;
                                    break wait;
                                }
                                if (input.isEmpty() || input.length() < 3) {
                                    JOptionPane.showMessageDialog(parent.getContentPane(), "Ungültiger Name!",
                                            "Fehler!",
                                            JOptionPane.ERROR_MESSAGE);
                                } else {
                                    name = input;
                                    data.add(input);
                                }
                            }
                        }
                    }
                    if (line.startsWith("Geschlecht")) {
                        line = line.replace("Geschlecht =", "").trim();
                        if (!line.isEmpty()) {
                            gender = line;
                        } else {
                            /*
                             * validate user input
                             */
                            int input = JOptionPane.showOptionDialog(parent.getContentPane(),
                                    "Wähle dein Geschlecht aus:", "Bitte Geschlecht angeben!",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                    new Object[] { "männlich", "weiblich" },
                                    null);
                            if (input == -1) {
                                canceled = true;
                                break wait;
                            }
                            if (input == 0) {
                                gender = "männlich";
                                data.add(gender);
                            }
                            if (input == 1) {
                                gender = "weiblich";
                                data.add(gender);
                            }
                        }
                    }
                    if (line.startsWith("Alter")) {
                        line = line.replace("Alter =", "").trim();
                        if (!line.isEmpty()) {
                            age = Integer.parseInt(line);
                        } else {
                            String input = "";
                            /*
                             * wait until user input is valid.
                             */
                            while (input.length() != 2) {
                                input = JOptionPane.showInputDialog(parent.getContentPane(), "Wie alt bist du?",
                                        "Bitte Alter eingeben!", JOptionPane.QUESTION_MESSAGE);
                                if (input == null) {
                                    canceled = true;
                                    break wait;
                                }
                                if (input.isEmpty() || input.length() != 2) {
                                    JOptionPane.showMessageDialog(parent.getContentPane(), "Ungültiges Alter!",
                                            "Fehler!",
                                            JOptionPane.ERROR_MESSAGE);
                                } else {
                                    if (Tools.checkInt(input)) {
                                        age = Integer.parseInt(input);
                                        data.add(input);
                                    } else {
                                        input = "";
                                        JOptionPane.showMessageDialog(parent.getContentPane(), "Ungültiges Alter!",
                                                "Fehler!", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }
                        }
                    }
                    if (line.startsWith("Groesse")) {
                        line = line.replace("Groesse =", "").trim();
                        if (!line.isEmpty()) {
                            height = Integer.parseInt(line);
                        } else {
                            String input = "";
                            /*
                             * wait until user input is valid.
                             */
                            while (input.length() != 3) {
                                input = JOptionPane.showInputDialog(parent.getContentPane(), "Wie Groß bist du?",
                                        "Bitte Größe eingeben!", JOptionPane.QUESTION_MESSAGE);
                                if (input == null) {
                                    canceled = true;
                                    break wait;
                                }
                                if (input.isEmpty() || input.length() != 3) {
                                    JOptionPane.showMessageDialog(parent.getContentPane(), "Ungültige Größe!",
                                            "Fehler!",
                                            JOptionPane.ERROR_MESSAGE);
                                } else {
                                    if (Tools.checkInt(input)) {
                                        height = Integer.parseInt(input);
                                        data.add(input);
                                    } else {
                                        input = "";
                                        JOptionPane.showMessageDialog(parent.getContentPane(), "Ungültiges Größe!",
                                                "Fehler!", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }
                        }
                    }
                    if (line.startsWith("Gewicht")) {
                        line = line.replace("Gewicht =", "").trim();
                        if (!line.isEmpty()) {
                            weight = Float.parseFloat(line);
                        } else {
                            String input = "";
                            /*
                             * wait until user input is valid.
                             */
                            weightWait: while (true) {
                                input = JOptionPane.showInputDialog(parent.getContentPane(), "Wie viel wiegst du?",
                                        "Bitte Gewicht eingeben!", JOptionPane.QUESTION_MESSAGE);
                                /*
                                 * input is null if user cancels the input
                                 * which breaks the "wait for input" loop.
                                 */
                                if (input == null) {
                                    canceled = true;
                                    break wait;
                                }
                                /*
                                 * if input is not a float, input is set to empty.
                                 */
                                if (!Tools.checkFloat(input)) {
                                    input = "";
                                }
                                /*
                                 * weight is valid if input is a float and has a length of 2 or 3.
                                 */
                                if (!input.isEmpty() && (input.length() == 2 || input.length() == 3)) {
                                    weight = Float.parseFloat(input);
                                    data.add(input);
                                    break weightWait;
                                } else {
                                    JOptionPane.showMessageDialog(parent.getContentPane(), "Ungültiges Gewicht!",
                                            "Fehler!", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    }
                    if (line.startsWith("Kcal")) {
                        line = line.replace("Kcal =", "").trim();
                        if (!line.isEmpty()) {
                            kcal = Integer.parseInt(line);
                        } else {
                            String input = "";
                            /*
                             * wait until user input is valid.
                             */
                            while (input.length() != 4) {
                                input = JOptionPane.showInputDialog(parent.getContentPane(),
                                        "Wie hoch ist dein Grundumsatz?", "Bitte Grundumsatz eingeben!",
                                        JOptionPane.QUESTION_MESSAGE);
                                if (input == null) {
                                    canceled = true;
                                    break wait;
                                }
                                if (input.isEmpty() || input.length() != 4) {
                                    JOptionPane.showMessageDialog(parent.getContentPane(), "Ungültiger Grundumsatz!",
                                            "Fehler!", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    if (Tools.checkInt(input)) {
                                        data.add(input);
                                        kcal = Integer.parseInt(input);
                                    } else {
                                        input = "";
                                        JOptionPane.showMessageDialog(parent.getContentPane(),
                                                "Ungültiges Grundumsatz!", "Fehler!", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }
                        }
                    }
                    if (line.startsWith("Ziel")) {
                        line = line.replace("Ziel =", "").trim();
                        if (!line.isEmpty()) {
                            goal = line;
                        } else {
                            String input = "";
                            /*
                             * wait until user input is valid.
                             */
                            while (input.isEmpty()) {
                                input = JOptionPane.showInputDialog(parent.getContentPane(), "Was ist dein Ziel?",
                                        "Bitte Ziel eingeben!", JOptionPane.QUESTION_MESSAGE);
                                if (input == null) {
                                    canceled = true;
                                    break wait;
                                }
                                if (input.isEmpty()) {
                                    JOptionPane.showMessageDialog(parent.getContentPane(), "Ungültiges Ziel!",
                                            "Fehler!", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    goal = input;
                                    data.add(input);
                                }
                            }
                        }
                    }
                }
                /*
                 * write profile information to file if all data was entered correctly.
                 */
                if (data.size() == 7) {
                    data.set(0, "Name = " + data.get(0));
                    data.set(1, "Geschlecht = " + data.get(1));
                    data.set(2, "Alter = " + data.get(2));
                    data.set(3, "Groesse = " + data.get(3));
                    data.set(4, "Gewicht = " + data.get(4));
                    data.set(5, "Kcal = " + data.get(5));
                    data.set(6, "Ziel = " + data.get(6));
                    try {
                        writer = new FileWriter(new File(filePath), Charset.forName("UTF-8"));
                        for (String string : data) {
                            writer.write(string);
                            writer.write(System.lineSeparator());
                        }
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break wait;
                }
                if (!written && data.isEmpty() && (name == null || gender == null || goal == null || age == 0
                        || height == 0 || weight == 0 || kcal == 0)) {
                    data.add(0, "Name = ");
                    data.add(1, "Geschlecht = ");
                    data.add(2, "Alter = ");
                    data.add(3, "Groesse = ");
                    data.add(4, "Gewicht = ");
                    data.add(5, "Kcal = ");
                    data.add(6, "Ziel = ");
                    try {
                        writer = new FileWriter(new File(filePath), Charset.forName("UTF-8"));
                        for (String string : data) {
                            writer.write(string);
                            writer.write(System.lineSeparator());
                        }
                        written = true;
                        data.clear();
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    break wait;
                }
            }
            reader.close();
        } catch (FileNotFoundException readerF) {
            System.out.println("File not found: " + filePath);
        } catch (IOException readerE) {
            readerE.printStackTrace();
        }
        if (canceled) {
            return null;
        }
        if (name == null || gender == null || goal == null || age == 0 || height == 0 || weight == 0 || kcal == 0) {
            System.out.println("Failed to read profile information from file!");
            return null;
        }
        long endTime = System.currentTimeMillis();
        if (loading) {
            System.out.println("Loading profile information for '" + name + "' took " + (endTime - startTime) + "ms");
        }
        return new Profile(name, gender, age, height, weight, kcal, goal);
    }

}
