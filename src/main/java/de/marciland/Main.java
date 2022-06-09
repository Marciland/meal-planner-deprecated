package de.marciland;

import de.marciland.windowhandler.Menu;

//TODO read from resource stream instead of Files
/**
 * Main class to run the app. Keep as clean as possible
 */
public class Main {
    /**
     * Main method should be as simple as possible.
     * Should be the only method in this class!
     *
     * @param args arguments that can be used when starting this app by console.
     */
    public static void main(String[] args) {
        Menu menu = new Menu();
        if (!menu.init()) {
            System.out.println("Failed to init menu!");
            System.exit(1);
        }
        menu.loadAllElements(); // TODO loading screen if time > 1sec?. use last load time for loading bar
        menu.create();
    }
}
