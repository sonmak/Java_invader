package Client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandlerPlayer implements KeyListener {

    public boolean goLeft, goRight;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == 39) {
            goRight = true;
        }
        if (code == 37) {
            goLeft = true;
        }
        /*if (code == 32) {
            fire = true;
        }*/

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == 39) {
            goRight = false;
        }
        if (code == 37) {
            goLeft = false;
        }
        /*if (code == 32) {
            fire = false;
        }*/
    }
}
