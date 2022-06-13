package Client;

import java.awt.*;
import java.io.*;
/**
 * Created by Sofiya.
 */
public class Main {
    public static void main(String args[]){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GameStart g = new GameStart();
                    g.runGame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
