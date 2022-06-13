package Client;

import org.w3c.dom.Document;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;



public class GameMainMenu extends JFrame implements ActionListener{

    private Dimension frameSize;
    private JPanel menuPanel;
    private JButton playBtn;
    private JButton highScoreBtn;
    private JButton helpBtn;
    private JButton quitBtn;
    private JButton backToMenuButtonFromHelpPanel;

    private GameMainWindow gameMainWindow;

    private HighScorePanel scorePanel;
    private HelpPanel helpPanel;


    public GameMainMenu(int width, int height) {
        frameSize = new Dimension(width, height);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(frameSize);
        menuPanel = new JPanel();
        playBtn = new JButton("new game");
        playBtn.addActionListener(this);
        playBtn.setFocusable(false);
        highScoreBtn = new JButton("high score");
        highScoreBtn.addActionListener(this);
        highScoreBtn.setFocusable(false);
        helpBtn = new JButton("help");
        helpBtn.addActionListener(this);
        helpBtn.setFocusable(false);
        quitBtn = new JButton("exit");
        quitBtn.addActionListener(this);
        quitBtn.setFocusable(false);
        menuPanel.setLayout(new GridLayout(4, 1, 20, 20));
        menuPanel.add(playBtn);
        menuPanel.add(highScoreBtn);
        menuPanel.add(helpBtn);
        menuPanel.add(quitBtn);
        add(menuPanel, BorderLayout.CENTER);
        pack();
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playBtn) {
            this.setVisible(false);
            makeGameMainWindow();
        } else if (e.getSource() == highScoreBtn) {
            // do sth else
        } else if (e.getSource() == helpBtn) {
            helpPanel = new HelpPanel((int) frameSize.getWidth(), (int) frameSize.getHeight(),this);
            this.remove(menuPanel);
            this.add(helpPanel);
            this.revalidate();
            this.repaint();
        } else if (e.getSource() == quitBtn) {
            dispose();
        } else if (e.getSource() == backToMenuButtonFromHelpPanel) {
            this.remove(helpPanel);
            scorePanel = null;
            this.add(menuPanel);
            this.revalidate();
            this.repaint();
        }

    }

    private void makeGameMainWindow() {
            gameMainWindow = new GameMainWindow(500,700);
            gameMainWindow.setVisible(true);
    }

    private class HelpPanel extends JPanel {

        private String rules;

        public HelpPanel(int panelWidth, int panelHeight, ActionListener menuListener) {
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(panelWidth, panelHeight));
            JTextArea textRules = new JTextArea("rules");
            add(textRules, BorderLayout.CENTER);
            backToMenuButtonFromHelpPanel = new JButton("back to menu");
            backToMenuButtonFromHelpPanel.addActionListener(menuListener);
            backToMenuButtonFromHelpPanel.setFocusable(false);
            add(backToMenuButtonFromHelpPanel, BorderLayout.SOUTH);
            textRules.setVisible(true);
        }
    }



    private class HighScorePanel extends JPanel {

        public HighScorePanel (int width, int height) {
            setPreferredSize(new Dimension(width, height));
            setVisible(true);
        }

        private class Pair {
            private String nickname;
            private int score;

            public Pair(String nickname, int score) {
                this.nickname = nickname;
                this.score = score;

            }

        }

        ArrayList<Pair> scoreList = new ArrayList<Pair>();

        void creatingScoreList () {
            scoreList.add(new Pair("Sonia",400));
            scoreList.add(new Pair("Kirill", 200));
            scoreList.add(new Pair("Dolli", 300));
        }


        private JButton makeBackToMenuButton(ActionListener menuListener) {
            JButton backToMainMenuBtn = new JButton("back to menu");
            backToMainMenuBtn.setFocusable(false);
            backToMainMenuBtn.addActionListener(menuListener);
            backToMainMenuBtn.setActionCommand("BackToMenuFromScorePanel");
            return backToMainMenuBtn;
        }

    }


}
