package Client;

/**
 * Created by Sofiya
 */

/**
 * Klasa opisująca barierę na polu gry
 */
public class Barrier extends Unit {
    /**
     * Konstruktor klasy Barrier
     * @param row pozycja w pionie
     * @param col pozycja w poziomie
     * @param gameLevelPanel aktualna panel gry
     * @param levelNo aktualny numer poziomu
     * ustawiamy lokalizację i prędkość
     */
    public Barrier (int row, int col, GameLevelPanel gameLevelPanel, int levelNo) {
        super(row, col, gameLevelPanel, levelNo);
    }
}
