package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMainWindow extends JFrame implements ActionListener {

    private Dimension frameSize;

    private Timer timer;

    private JButton playBtn;
    private JButton backToMenuButton;

    private GameMainScreenPanel gameMainScreenPanel;
    private JPanel regulationPanel;

    private gameStates gameState;

    private Thread thread;

    public GameMainWindow(int width, int height){
        //thread= new Thread(gameMainScreenPanel);
        //thread.start();
        frameSize = new Dimension(width, height);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(frameSize);
        setBackground(Color.pink);
        gameMainScreenPanel = new GameMainScreenPanel(400,600);
        add(gameMainScreenPanel,BorderLayout.CENTER);
        regulationPanel = new JPanel();
        add(regulationPanel, BorderLayout.NORTH);
        playBtn = new JButton("cont or stop");
        playBtn.addActionListener(this);
        playBtn.setFocusable(false);
        regulationPanel.setLayout(new GridLayout(1, 3, 10, 10));
        backToMenuButton = new JButton("back to menu");
        backToMenuButton.addActionListener(this);
        backToMenuButton.setFocusable(false);
        regulationPanel.add(playBtn);
        regulationPanel.add(backToMenuButton);
        pack();
    }

    private enum gameStates {
        PAUSED,
        RUNNING
    }

    public void pause(){
        if(gameState==gameStates.PAUSED)
        {
            gameState=gameStates.RUNNING;
            startTimer();
        }
        else{
            gameState=gameStates.PAUSED;
            stopTimer();
        }
        gameMainScreenPanel.pause();
    }

    public void startTimer() { /*smth*/};;
    public void stopTimer() { /*smth*/};

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playBtn) {
            pause();
        } else if (e.getSource() == backToMenuButton) {
            this.setVisible(false);
            GameMainMenu gm = new GameMainMenu(500,700);
            gm.setVisible(true);
        }
    }


}
