package ConfigData;

import Client.Constants;
import org.w3c.dom.Document;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by Sofiya
 */

/**
 *  Stałe parametry inicjializacyjne wymagane do startu programu
 */

public class ConstantsFile {

    /**
     * Zmienna określająca liczbe wierszow w panelu
     */
    public static int GameFieldRow = 0;

    /**
     * Zmienna określająca liczbe kolumn w panelu
     */
    public static int GameFieldColumn = 0;

    /**
     * Zmienna określająca wymiary pojedynczej postaci
     */
    public static int RectangleSize;

    /**
     * Zmienna określająca prędkość obiektów dla lev1
     */
    public static int speed1;

    /**
     * Zmienna określająca prędkość obiektów dla lev2
     */
    public static int speed2;

    /**
     * Zmienna określająca prędkość obiektów bullet
     */
    public static int bulletSpeed;

    /**
     * Zmienna określająca prędkość obiektów gracza
     */
    public static int playerSpeed;

    /**
     * Zmienna zawierająca teks wyświetlany w oknie dialogowym który pojawia się podczas nieprawidłowego zczytania pliku
     */
    public static String errorHighScoreMess;


    /**
     * Zmienna zawierająca wygląd (png icon) ivadera
     */
    public static BufferedImage invaderImg;

    /**
     * Zmienna zawierająca wygląd (png icon) bariery
     */
    public static BufferedImage barrierImg;

    /**
     * Zmienna zawierająca wygląd (png icon) gracza który porusza się wlewo
     */
    public static BufferedImage playerLeftImg;

    /**
     * Zmienna zawierająca wygląd (png icon) gracza który porusza się wprawo
     */
    public static BufferedImage playerRightImg;

    /**
     * Zmienna zawierająca wygląd (png icon) bomby zrzucanej przez invaderów
     */
    public static BufferedImage bombImg;

    /**
     * Zmienna zawierająca wygląd (png icon) bomby zrzucanej przez invaderów
     */
    public static BufferedImage bulletImg;

    /**
     * Zmienna zawierająca tekst przycisku play
     */
    public static String playBtnTxt;

    /**
     * Zmienna zawierająca tekst przycisku help
     */
    public static String helpBtnTxt;

    /**
     * Zmienna zawierająca tekst przycisku highScore
     */
    public static String highScoresBtnTxt;

    /**
     * Zmienna zawierająca tekst przycisku quit
     */
    public static String quitBtnTxt;

    /**
     * Zmienna zawierająca tekst przycisku pause
     */
    public static String pauseBtnTxt;

    /**
     * Zmienna określająca wymiar szerokości bomby
     */
    public static int bombWidth;

    /**
     * Zmienna określająca wymiar wysokości bomby
     */
    public static int bombHeight;

    /**
     * Zmienna określająca prędkść bomby
     */
    public static int bombSpeed;

    /**
     * Zmienna określająca tekst wyświetlany w przycisku okna dialogowego które pojawia się podczas zamykania gry
     */
    public static String yes;

    /**
     * Zmienna określająca tekst wyświetlany w przycisku okna dialogowego które pojawia się podczas zamykania gry
     */
    public static String no;

    /**
     * Zmienna określająca tekst wyświetlany w w oknie dialogowym które pojawia się podczas zamykania gry
     */
    public static String quitTheGameMess;

    /**
     * Zmienna określająca tytuł okna dialogowego wyświetlanego podczas zamykania gry
     */
    public static String quitWindowTitle;

    /**
     * Zmienna określająca ilość życia gracza
     */
    public static int maxLife;

    /**
     * Zmienna określająca ścieżki do plików
     */
    private static Constants c = new Constants();

    private static DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

    /**
     * Metoda do inicjacji zmienych obrazkowych
     */
    public void readIconImages() {
        try {
            invaderImg = ImageIO.read(getClass().getResource("/configuration/Invaders/Invader0.png"));
            barrierImg = ImageIO.read(getClass().getResource("/configuration/Barriers/Barrier.png"));
            playerLeftImg = ImageIO.read(getClass().getResource("/configuration/Player_LEFT.png"));
            playerRightImg = ImageIO.read(getClass().getResource("/configuration/Player_RIGHT.png"));
            bombImg = ImageIO.read(getClass().getResource("/configuration/Bomb.png"));
            bulletImg = ImageIO.read(getClass().getResource("/configuration/Bullet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Metoda służy do zczytania pliku konfugracyjnego i inicjalizacji zmiennych
     */
    public static void readConfigFiles() {
        try {
            File xmlInputFile = new File(c.field_config);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlInputFile);
            doc.getDocumentElement().normalize();

            bombHeight = Integer.parseInt(doc.getElementsByTagName("BombHeight").item(0).getTextContent());
            bombSpeed = Integer.parseInt(doc.getElementsByTagName("BombSpeed").item(0).getTextContent());
            bombWidth = Integer.parseInt(doc.getElementsByTagName("BombWidth").item(0).getTextContent());
            GameFieldRow = Integer.parseInt(doc.getElementsByTagName("GameFieldRow").item(0).getTextContent());
            GameFieldColumn = Integer.parseInt(doc.getElementsByTagName("GameFieldColumn").item(0).getTextContent());

            speed1 = Integer.parseInt(doc.getElementsByTagName("level_1").item(0).getTextContent());
            speed2 = Integer.parseInt(doc.getElementsByTagName("level_2").item(0).getTextContent());

            maxLife = Integer.parseInt(doc.getElementsByTagName("maxLife").item(0).getTextContent());
            bulletSpeed = Integer.parseInt(doc.getElementsByTagName("BulletSpeed").item(0).getTextContent());
            playerSpeed = Integer.parseInt(doc.getElementsByTagName("PlayerSpeed").item(0).getTextContent());

            //int Timer = Integer.parseInt(doc.getElementsByTagName("Timer").item(0).getTextContent());
            RectangleSize= Integer.parseInt(doc.getElementsByTagName("RectangleSize").item(0).getTextContent());
            playBtnTxt = doc.getElementsByTagName("playBtnLabel").item(0).getTextContent();
            quitBtnTxt = doc.getElementsByTagName("quitBtnLabel").item(0).getTextContent();
            pauseBtnTxt = doc.getElementsByTagName("pauseBtnLabel").item(0).getTextContent();
            helpBtnTxt = doc.getElementsByTagName("helpBtnLabel").item(0).getTextContent();
            highScoresBtnTxt = doc.getElementsByTagName("highScorseBtnLabel").item(0).getTextContent();

            errorHighScoreMess = doc.getElementsByTagName("errorHighScoreMess").item(0).getTextContent();
            yes = doc.getElementsByTagName("yes").item(0).getTextContent();
            no = doc.getElementsByTagName("no").item(0).getTextContent();
            quitTheGameMess= doc.getElementsByTagName("quitTheGameMess").item(0).getTextContent();
            quitWindowTitle = doc.getElementsByTagName("quitWondowTitle").item(0).getTextContent();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

