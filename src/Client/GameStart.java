package Client;

import ConfigData.ConstantsFile;
/**
 * Created by Sofiya.
 */

/**
 * Klasa służąca do odpalenia UI, ustawia okno na visible
 */
public class GameStart {

    /**
     * Metoda odpalenia gry
     */
    public static void runGame(){
        readAllConfig();
        GameFrame gf= new GameFrame();
        gf.setVisible(true);
    }

    /**
     * Metoda zczytuje wszystkie konfiguracje
     */
    public static void readAllConfig() {
        ConstantsFile t = new ConstantsFile();
        t.readConfigFiles();
        t.readIconImages();
    }
}
