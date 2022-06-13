package Client;

import java.awt.*;

import static ConfigData.ConstantsFile.*;
/**
 * Created by Sofiya
 */

/**
 * Klasa opisująca strzał gracza
 */

public class Bullet{
    /**
     * Początkowa koordynata x
     */
    private int xCoordinate;
    /**
     * Początkowa koordynata y
     */
    private int yCoordinate;

    /**
     * Lokalizaja stzału
     */
    private Point location;

    /**
     * Wymiary obrazu strzału
     */
    private Rectangle bounds;

    /**
     * Prędkość strzału
     */
    private int speed;

    /**
     * Konstruktor klasy Bullet
     * @param row pozycja w pionie
     * @param col pozycja w poziomie
     * ustawiamy lokalizację i prędkość
     */
    public Bullet (int row, int col) {
        xCoordinate = col * RectangleSize;
        yCoordinate = row * RectangleSize;
        location = new Point(xCoordinate, yCoordinate);
        bounds = new Rectangle(RectangleSize, RectangleSize);
        bounds.setLocation(location);
        speed = bulletSpeed;

    }

    /**
     * Metoda odświeżania pozycji strzału, rych do góry
     */
    public void update(){
        location.y += speed;
    }

}
