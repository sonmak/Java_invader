package Client;

/**
 * Created by Sofiya Makarenka.
 */

/**
 * Klasa wyglądu postaci
 */

public class UnitView {
    /**
     * sama postać "fizyczna"
     */
    public Unit unit;

    /**
     * aktualna plansza gry
     */
    public GameLevelPanel glp;

    /**
     * keyHandler powiązamy z naszą postacią
     */
    public KeyHandlerPlayer keyHandlerPlayer;

    /**
     * pozycja w poziomie
     */
    protected int xPos;

    /**
     * pozycja w pionie
     */
    protected int yPos;

    /**
     * Kontruktor widoku postaci
     * @param unit sama postać "fizyczna"
     * @param glp aktualna plansza gry
     * @param keyHandlerPlayer powiązamy z naszą postacią
     * Określa położenie obiektu i go przynależność do paneli
     */
    public UnitView (Unit unit, GameLevelPanel glp, KeyHandlerPlayer keyHandlerPlayer) {
        this.glp = glp;
        this.unit = unit;
        this.keyHandlerPlayer = keyHandlerPlayer;
        xPos = unit.getxCoordinate();
        yPos = unit.getyCoordinate();
    }


}
