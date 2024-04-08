import java.awt.EventQueue;

import Controller.MainMenuController;
import View.MainMenu;

public class MyApp {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainMenu MainMenu = new MainMenu();
                @SuppressWarnings("unused")
                MainMenuController mainMenuController = new MainMenuController(MainMenu);
            }
        });
    }
}