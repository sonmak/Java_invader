package ConfigData;

import Client.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Sofiya.
 */

/**
 * Klasa która zczyta i inicjalizuje mapę poziomu i zarządza aktulanym stanem poziomu
 */

public class LevelLoader {

    /**
     * Aktualny poziom
     */
    int levelNo;

    /**
     * Instancja klasy LevelLoader
     */
    private static LevelLoader  instance;

    /**
     * Zmienna określająca ścieżki do plików
     */
    private static Constants c = new Constants();


    /**
     * Konstrukor klasy MainWindow
     * przypisuje instancje i defaltowy numer poziomu
     */
    private LevelLoader() {
        levelNo = 1;
        instance = this;
    }

    /**
     * Metoda getter numeru poziomu
     * @return  numer poziomu
     */
    public int getLevelNo() {
        return levelNo;
    }


    /**
     * Metoda dla otrzymywania instancji LevelLoadera
     * @return instancja
     */
    public static LevelLoader getInstance() {
        if (instance == null)
            new LevelLoader();
        return instance;
    }

    /**
     * Metoda służąca do zwiększenia numeru pozioma
     */
    public void increaseLevel(){
        levelNo++;
    }

    /**
     * Metoda służąca do resetu numeru poziomu do wartości defaltowej
     */
    public void reset(){
        levelNo=1;
    }


    /**
     * Metoda służąca inicjalizacji i utworzenia poziomu
     * Dzieku zczytanej mapy, utwarza Unity o odpowienim "gotunku
     * i dodaje ich do poziomu
     * @param gameLevelPanel aktualna plansza gry
     * @return poziom
     */
    public Level loadNextLevel(GameLevelPanel gameLevelPanel) {
        Level level = new Level();
        ArrayList<char[]> charMap = readMapFile();
        for (int row = 0; row < charMap.size(); row++) {
            for (int col = 0; col < charMap.get(0).length; col++) {
                Unit unit = Unit.getUnit(charMap.get(row)[col], row, col, gameLevelPanel, levelNo);
                if (unit == null)
                    continue;
                addUnitToLevel(unit, level);
            }
        }

        return level;
    }

    /**
     * Metoda służąca inicjalizacji i utworzenia poziomu
     * @param unit Obiekt postaci
     * @param level obiekt poziomu
     * Decyduje co to formy postaci i dodaje to do odpowiedniego kontenera poziomu
     */
    private void addUnitToLevel(Unit unit, Level level) {
        if (unit instanceof Alien)
            level.addAlien((Alien) unit);
        else if (unit instanceof Barrier)
            level.addBarrier((Barrier) unit);
        else
            throw new IllegalArgumentException("Unexpected entity");
    }


    /**
     * Metoda służąca inicjalizacji i utworzenia poziomu
     * Zczyta na początku plik z odpowienią mapą określonego poziomu
     * a poźniej zwraca tą mapę w postaci tablicy char
     * @return mapa poziomu w postaci tablicy char
     */
    public ArrayList<char[]> readMapFile() {
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new File(c.maps +"level_"+levelNo+".txt"));
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't read stage file"+levelNo);
            System.exit(1);
        }

        return loopOverMapFile(fileScanner);
    }

    /**
     * Zczytuje linijka po linijce wierszsy pliku mapy poziomu
     * i inicjalizuje tablicę zawierającą mapę poziomu
     * @return mapa poziomu w postaci tablicy char
     */
    private ArrayList<char[]> loopOverMapFile(Scanner fileScanner) {
        ArrayList<char[]> charMap = new ArrayList<char[]>();
        while (fileScanner.hasNextLine()) {
            char[] tileRow = fileScanner.nextLine().toCharArray();
            charMap.add(tileRow);
        }
        return charMap;
    }
}

