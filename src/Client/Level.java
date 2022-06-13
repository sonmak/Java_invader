package Client;

import java.util.ArrayList;

/**
 * Created by Sofiya
 */

/**
 * Klasa poziomu, zawiera listy postaci
 */
public class Level {

    /**
     * Lista wszystkich invaderów
     */
    public ArrayList<Alien> aliens;

    /**
     * Lista wszystkich barier
     */
    private ArrayList<Barrier> barriers;

    /**
     * Konstrukor klasy Level
     */
    public Level() {
        aliens = new ArrayList<Alien>();
        barriers = new ArrayList<Barrier>();
    }

    /**
     * Metoda służąca do pobrania listy invaderów
     * @return lista invaderów tego poziomu
     */
    public ArrayList<Alien> getAliens() {
        return aliens;
    }

    /**
     * Metoda służąca do pobrania listy barier
     * @return lista barier tego poziomu
     */
    public ArrayList<Barrier> getBarriers() {
        return barriers;
    }

    /**
     * Metoda służąca do dodania invadera do listy invaderów tego poziomu
     * @param alien invader
     */
    public void addAlien(Alien alien) {
        aliens.add(alien);
    }

    /**
     * Metoda służąca do dodania bariery do listy wszystkich barier tego poziomu
     * @param barrier bariera
     */
    public void addBarrier(Barrier barrier) {
        barriers.add(barrier);
    }

}
