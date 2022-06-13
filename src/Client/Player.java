package Client;

import static ConfigData.ConstantsFile.maxLife;
import static ConfigData.ConstantsFile.playerSpeed;

/**
 * Created by Sofiya Makarenka.
 */

/**
 * Klasa opisująca gracza i go zachowanie
 */

public class Player extends UnitView {

    /**
     *  Zmienna określająca kierynek ruchu
     */
    String direction = new String("right");

    /**
     *  Zmienna określająca prędkość gracza
     */
    private int playerSpd;

    /**
     *  Zmienna określająca ile lifes u niego jest
     */
    private int life = maxLife;
    //Bullet bullet;

    /**
     * Konstrukor klasy Player
     * @param unit jednostka postaci
     * @param glp aktualna planszsa gry
     * @param keyHandlerPlayer zmiana
     */
    public Player(Unit unit, GameLevelPanel glp, KeyHandlerPlayer keyHandlerPlayer) {
        super(unit, glp, keyHandlerPlayer);
    }

    /**
     * Metoda pobierająca lifes
     * @return pozostałe życia
     */
    public int getLife() {
        return life;
    }

    /**
     * Metoda ustawia życia
     * @param i ile aktualnie pozostało żyć
     */
    public void setLife(int i) {
        life = i;
    }

    /*public Bullet getBullet() {
        return bullet;
    }*/

    /**
     * Metoda implemetująca logikę poruszenia się wprawo
     */
    public void goRight() {
        playerSpd = playerSpeed;
    }

    /**
     * Metoda implemetująca logikę poruszenia się wlewo
     */
    public void goLeft() {
        playerSpd = -playerSpeed;
    }

    /**
     * Metoda swraca kierunek w którym porusza się gracz
     * @return kierunek
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Metoda implemetująca logikę odświeżania ruchu gracza w zależności
     * od naciśniętej klawiszy
     */
    public void update() {
        if (keyHandlerPlayer.goRight == true) {
            direction = "right";
            goRight();
            unit.moveHorizontally(playerSpd);
            xPos = unit.getxCoordinate();

        } else if (keyHandlerPlayer.goLeft == true) {
            direction = "left";
            goLeft();
            unit.moveHorizontally(playerSpd);
            xPos = unit.getxCoordinate();
        }  /*else if (keyHandlerPlayer.fire == true) {
            bullet = new Bullet(xPos, yPos*2);
        }*/

    }
}


