package Client;


import ConfigData.LevelLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static ConfigData.ConstantsFile.*;
import static ConfigData.ConstantsFile.RectangleSize;

/**
 * Created by Sofiya
 */

/**
 * Klasa opisująca panel głowny gry
 */
public class GameLevelPanel extends JPanel implements Runnable {

    /**
     * Zmianna KeyHandlerPlayer która słusza na zdarzenia od gracza
     */
    KeyHandlerPlayer keyHandlerPlayer = new KeyHandlerPlayer();

    /**
     * Zmianna obrazku postaci
     */
    public static BufferedImage imgPlayer;

    //public static BufferedImage bomb;

    /**
     * Gracz
     */
    Player player;

    /**
     * Rozmiar panelu
     */
    private Dimension panelSize;

    /**
     * Zmienna określająca czy gra jest w stanie pauzy czy nie
     */
    private boolean gamePaused;

    private boolean gameOver;

    /**
     * Zmienna aktualnego poziomu
     */
    private Level level;

    /**
     * Zmienna okna głownego
     */
    GameFrame gameFrame;

    /**
     * Zmienna głównego wątku
     */
    Thread gameThread;

    /**
     * Zmienna określająca ilość Frames Per Second
     */
    int FPS = 90;

    /**
     * Kontruktor panelu gry
     * @param panelWidth szerokość panelu gry
     * @param panelHeight wysokość paneli gry
     * @param gameFrame głowne okno
     * ladujemy poziom i gracza, nasłuchiwamy na zdarzenia
     */

    public GameLevelPanel(int panelWidth, int panelHeight, GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        panelSize = new Dimension(panelWidth, panelHeight);
        setPreferredSize(panelSize);
        setBackground(Color.black);
        setVisible(true);
        setDoubleBuffered(true);
        addKeyListener(keyHandlerPlayer);
        level = LevelLoader.getInstance().loadNextLevel(this);
        player = new Player(new Unit(18,10,this, LevelLoader.getInstance().getLevelNo()), this, keyHandlerPlayer);
        startGameThread();
        //gamePaused=true;
        //gameOver=false;
    }

    /**
     * Metoda służąca do zatrzymania gry
     */
    public void pause(){
        if(gamePaused)
        {
            gamePaused=false;
        }
        else if(!gamePaused){
            gamePaused=true;
        }
    }


    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    /**
     * Metoda służy do rysowania invaderów
     * @param g obiekt Graphics po to zeby umiesci później to w paintComponent
     */
    private void drawAliens(Graphics g) {

        for (Alien alien : level.getAliens()) {
            g.drawImage(invaderImg , alien.getxCoordinate(), alien.getyCoordinate(), this);

            /*if (alien.isDying()) {
                alien.die();
            }*/
        }
    }

    /*private void drawBullet(Graphics g) {
         if (bullet.isVisible()) {
              g.drawImage(bulletImg, bullet.getxCoordinate(), bullet.getyCoordinate(), this);
         }

    }*/

    /**
     * Metoda służy do rysowania gracza
     * @param g obiekt Graphics po to zeby umiesci później to w paintComponent
     */
    private void drawPlayer(Graphics g) {
        String direction = new String(player.getDirection());
        switch (direction) {
            case "right":
                imgPlayer = playerRightImg;
                break;
            case "left":
                imgPlayer = playerLeftImg;
                break;
        }
        g.drawImage(imgPlayer, player.xPos, player.yPos, RectangleSize, RectangleSize, null);
    }

    /**
     * Metoda służy do rysowania bariery
     * @param g obiekt Graphics po to zeby umiesci później to w paintComponent
     */
    private void drawBarrier(Graphics g) {
        for (Barrier barrier : level.getBarriers()) {
            g.drawImage(barrierImg, barrier.getxCoordinate(), barrier.getyCoordinate(), this);
        }
    }

    /**
     * Metoda utwarza i pozwala na urochomienie wątku głównego
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Run wątku głównego
     */
    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
                currentTime = System.nanoTime();
                delta += (currentTime - lastTime) / drawInterval;
                lastTime = currentTime;

                if (delta >= 1 && !gamePaused) {
                    update();
                    repaint();
                    delta--;
                }

        }

    }

    /**
     * Metoda słyży do updatowania panelu podczas gdy wątek działa
     */
    public void update() {
        player.update();
        for (Alien alien : level.getAliens()) {
            alien.move();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        doDrawing(g);
        g2.dispose();
    }

    /**
     * Metoda wykona wszystkie drawery na raz, żeby nie wywoływać każdy oddzielnie
     */
    private void doDrawing(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
            drawAliens(g2);
            drawPlayer(g2);
            drawBarrier(g);
            //drawBullet(g);
           // drawBombing(g);

            Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Usuwamy wątek
     */
    public void stopAnimationThread(){
        gameThread = null;
        //timer.cancel();
    }

    /**
     * Metoda ta słyży do potwierdzenia końca gry na wskutek przegrania albo wygrania
     * dzięki tej metodzie po zakończeniu gry możemy odpalić ją z powrotem
     */
    public void gameOver(){
        System.out.println(player.getLife() );
        if(player.getLife() <= 0) {
            gameOver = true;
            stopAnimationThread();
            String[] options = new String[]{"Yes", "No"};
            int d = JOptionPane.showOptionDialog(this, "Do you want to play again?", "End of the game",
                    JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
            if (d == JOptionPane.NO_OPTION) {
                gameFrame.dispose();
            } else if (d == JOptionPane.YES_OPTION) {
                LevelLoader.getInstance().reset();
                gameFrame.newGame();
            }
        }else {
                player.setLife(player.getLife()-1);
            }
    }
}
