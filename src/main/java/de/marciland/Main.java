package de.marciland;

import de.marciland.windowhandler.Menu;

public class Main {
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
