package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static ConfigData.ConstantsFile.*;
/**
 * Created by Sofiya Makarenka.
 */

/**
 * Klasa opisująca postać i jej zachowanie
 */

public class Unit {

    /**
     * Zmienna określająca położenie pozątku x postaci
     */
    protected int xCoordinate;
    /**
     * Zmienna określająca położenie pozątku y postaci
     */
    protected int yCoordinate;

    /**
     * Point klasa określająca położenie pozątku x i y postaci
     */
    protected Point location;

    /**
     * Zmienna określająca prostokąt zajmowany przez postać na ekranie
     */
    protected Rectangle bounds;

    /**
     * Zmienna panelu ekranu
     */
    protected GameLevelPanel gameLevelPanel;

    /**
     * Zmienna ustawia szybkość kiedy obiekt się porusza
     */
    protected double speed;

    /**
     * Zmienna określa poziom na którym jeśmy, dzięki jej możemy specyfikować double speed
     */
    private int levelNo;

    /**
     * Konstrutor klasy Unit
     * @param row liczba wierszy ekranu
     * @param column liczba column ekranu
     * @param gameLevelPanel aktualna panel gry
     * @param levelNo aktualny poziom
     * Określa położenie, rozmiary i prędkość obiektu
     */

    public Unit(int row, int column, GameLevelPanel gameLevelPanel, int levelNo) {
        xCoordinate = column * RectangleSize;
        yCoordinate = row * RectangleSize;
        location = new Point(xCoordinate, yCoordinate);
        bounds = new Rectangle(RectangleSize, RectangleSize);
        bounds.setLocation(location);
        this.gameLevelPanel = gameLevelPanel;
        this.levelNo = levelNo;
        setSpeed();
    }

    /**
     * Metoda ustawia prędkość w zależności od aktualnego poziomu gry
     */
    public void setSpeed() {
        if(levelNo == 1) {
            speed = speed1;
        } else if(levelNo ==2) {
            speed = speed2;
        }
    }

    /**
     * Metoda sprawdza czy jesteśmy w aktualnym frajmie
     * @param px prędkość postaci
     * @return boolean odpowiedz na pytanie - posatć mieści się w oknie?
     */
    private boolean isInBounds(double px) {
        return (location.getX() + px) > 0
                && (location.getX() + px) < (GameFieldColumn*RectangleSize - RectangleSize);
    }

    /**
     * Metoda określa jak ma się poruszać się obiekt w poziomie
     * @param px prędkość postaci
     */
    public void moveHorizontally(double px) {
        if (isInBounds(px))
            setLocation(location.getX() + px, location.getY());
    }

    /**
     * Metoda ustawia lokalizację obiektu
     * @param x położenie początku postaci x
     * @param y położenie początku postaci y
     */
    public void setLocation(double x, double y) {
        location.setLocation(x, y);
        bounds.setLocation(location);
    }


    /**
     * Metoda zwraca instance wyspecyfikowanego obiektu Unit
     * @param tileChar char na mapie poziomu
     * @param row w którym wierszu umieścić
     * @param col w której kolumnie umieścić
     * @param levelNo aktualny poziom gry
     * @return postać: alien lub bariera
     */
    public static Unit getUnit(char tileChar, int row, int col, GameLevelPanel gameLevelPanel, int levelNo) {
        if (tileChar == 'x')
            return new Alien(row, col, gameLevelPanel, levelNo);
        else if (tileChar == 'b')
            return new Barrier(row, col, gameLevelPanel, levelNo);
        else
            return null;
    }

    /**
     * Metoda zwraca  położenie początku x
     * @return postać: położenie początku x
     */
    public int getxCoordinate() { return (int)location.getX();}

    /**
     * Metoda zwraca  położenie początku y
     * @return postać: położenie początku y
     */
    public int getyCoordinate() {return (int)location.getY();}


}
