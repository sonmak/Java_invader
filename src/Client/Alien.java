package Client;

import static ConfigData.ConstantsFile.GameFieldColumn;
import static ConfigData.ConstantsFile.RectangleSize;

/**
 * Created by Sofiya
 */

/**
 * Klasa opisująca barierę na polu gry
 */

public class Alien extends Unit {
    /**
     * Kierunek ruchu
     */
    int xDirection = 1;
    /**
     * Konstruktor klasy Barrier
     * @param row pozycja w pionie
     * @param col pozycja w poziomie
     * @param gameLevelPanel aktualna panel gry
     * @param levelNo aktualny numer poziomu
     * ustawiamy lokalizację i prędkość
     */

    public Alien (int row, int col, GameLevelPanel gameLevelPanel, int levelNo) {
        super(row, col, gameLevelPanel,levelNo);
    }

    /**
     * Metoda określa jak ma się poruszać się obiekt w poziomie i pionie
     * wykrywamy kolizje z oknem i zwiększamy prędkość
     */

    public void move(){
        if(getxCoordinate()<=0){
            xDirection = 1;
            location.y += speed;
            setLocation(location.getX(), location.getY());
            System.out.println(speed);
        }
        else if(location.x+ RectangleSize > (RectangleSize*GameFieldColumn-10) ) {
            xDirection = -1;
            location.y += speed;
            setLocation(location.getX(), location.getY());
        }
        location.x+=xDirection*speed;
        speed = speed + 0.00002;
        if(location.y+RectangleSize>RectangleSize*18){
             gameLevelPanel.gameOver();
        }
    }

}
