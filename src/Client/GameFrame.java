package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


import static ConfigData.ConstantsFile.*;

/**
 * Created by Sofiya.
 */

/**
 * Klasa opisująca okno główne gry
 */

public class GameFrame extends JFrame implements ActionListener {

    /**
     * Rozmiar panelu
     */
    private Dimension frameSize;

    private Desktop desktop;

    /**
     * Przycisk nowej gry
     */
    private JButton playBtn;
    /**
     * Przycisk zatrzymania gry
     */
    private JButton pauseBtn;
    /**
     * Przycisk powrotu do menu
     */
    private JButton backToMenuButton;
    /**
     * Przycisk znajwyższych wyników
     */
    private JButton highScoreBtn;
    /**
     * Przycisk help
     */
    private JButton helpBtn;
    /**
     * Przycisk wyjścia z gry
     */
    private JButton quitBtn;

    /**
     * Panel gry głownej
     */
    private GameLevelPanel gameLevelPanel;
    /**
     * Panel regulacji gry: punktuacja, zatrzymywanie gry, życia
     */
    private JPanel regulationPanel;
    //private HighScorePanel scorePanel;
    /**
     * Panel pomocy
     */
    private HelpPanel helpPanel;
    /**
     * Panel menu
     */
    private MenuPanel menuPanel;

    /**
     * Szerokosc okna
     */
    private int FRAME_WIDTH;
    /**
     * Wysokość okna
     */
    private int FRAME_HEIGHT;

     /**
     * Konstruktor klasy GameFrame
      * ustala rozmiar i layout
     */
    public GameFrame(){
        FRAME_HEIGHT = RectangleSize*GameFieldRow;
        FRAME_WIDTH = RectangleSize*GameFieldColumn;
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMaximizedBounds(new Rectangle(0,0 , 900, 900));
        setResizable(false);
        printMenuPanel();
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Klas MenuPanel  i go inicjalizacja
     */
    private class MenuPanel extends JPanel {
        public MenuPanel(int width, int height, ActionListener menuListener) {
            frameSize = new Dimension(width, height);
            setPreferredSize(frameSize);
            setLayout(new GridLayout(4, 1, 20, 20));
            playBtn = new JButton(playBtnTxt);
            playBtn.addActionListener(menuListener);
            playBtn.setFocusable(false);
            highScoreBtn = new JButton(highScoresBtnTxt);
            highScoreBtn.addActionListener(menuListener);
            highScoreBtn.setFocusable(false);
            helpBtn = new JButton(helpBtnTxt);
            helpBtn.addActionListener(menuListener);
            helpBtn.setFocusable(false);
            quitBtn = new JButton(quitBtnTxt);
            quitBtn.addActionListener(menuListener);
            quitBtn.setFocusable(false);
            add(playBtn);
            add(highScoreBtn);
            add(helpBtn);
            add(quitBtn);
        }

    }

    /**
     * Klas RegulationPanel i go inicjalizacja
     */
    private class RegulationPanel extends JPanel {
        /**
         * Konstruktor klasy RegulationPanel
         */
        public RegulationPanel(ActionListener gameRegulationListener) {
            setLayout(new GridLayout(1, 2, 10, 10));
            pauseBtn = new JButton(pauseBtnTxt);
            pauseBtn.addActionListener(gameRegulationListener);
            pauseBtn.setFocusable(true);
            add(pauseBtn);
        }

    }

    /**
     * Klas HelpPanel i go inicjalizacja
     */
    private class HelpPanel extends JPanel {
        /**
         * Konstruktor klasy HelpPanel
         */
        public HelpPanel(int panelWidth, int panelHeight, ActionListener menuListener) {
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(panelWidth, panelHeight));
            JTextArea textRules = new JTextArea("rules");
            add(textRules, BorderLayout.CENTER);
            backToMenuButton = new JButton("back to menu");
            backToMenuButton.addActionListener(menuListener);
            backToMenuButton.setFocusable(false);
            add(backToMenuButton, BorderLayout.SOUTH);
            textRules.setVisible(true);
        }
    }

    /*private class HighScorePanel extends JPanel {

        public HighScorePanel (int width, int height) {
            setPreferredSize(new Dimension(width, height));
            setVisible(true);
        }
    }
*/
    /**
     * Metoda obsługjąca zdarzenia wciśnięcia przycisków w menu
     * @param e obiekt zdarzenia akcji zwiazanych z wcisnieciem przycisku
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playBtn) {
            JOptionPane d = new JOptionPane(null);
            String inputValue = JOptionPane.showInputDialog(null, "Tape your name", "Create gamer", JOptionPane.QUESTION_MESSAGE);
            d.setSize(300, 200);
            d.setVisible(true);

            while(inputValue==null || inputValue.length()==0){
                inputValue= JOptionPane.showInputDialog(null, "Tape your name", "Create gamer", JOptionPane.QUESTION_MESSAGE);
                if(inputValue==null){
                    break;
                }
            }
            if (inputValue != null) {
                printGameLevelPanel();
            }
        } else if (e.getSource() == highScoreBtn) {
            try {
                desktop.open(new File("nie ma takiego pliku"));
            } catch (Exception w) {
                JOptionPane.showMessageDialog(null, errorHighScoreMess);
            }
        } else if (e.getSource() == helpBtn) {
            printHelpPanel();
        } else if (e.getSource() == quitBtn) {
            String[] options = new String[] {yes, no};
            int d1 = JOptionPane.showOptionDialog(null, quitTheGameMess, quitWindowTitle, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);
            if (d1 == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
            else if (d1 == JOptionPane.NO_OPTION) {
            }

        } else if (e.getSource() == backToMenuButton) {
            printMenuPanel();
        } else if (e.getSource() == pauseBtn) {
            gameLevelPanel.pause();
        }
    }


    /**
     * Metoda rysująca Panel
     * @param panel który rysujemy
     */
    private void printPanel (JPanel panel) {
        getContentPane().removeAll();
        add(panel, BorderLayout.CENTER);
        repaint();
        revalidate();
    }

    /**
     * Metoda tworzy menuPanel i go rysuje wywołując printPanel
     */
    private void printMenuPanel() {
        if (menuPanel == null)
            menuPanel = new MenuPanel(FRAME_WIDTH, FRAME_HEIGHT,this);
        printPanel(menuPanel);

    }

    /**
     * Metoda tworzy HelpPanel i go rysuje wywołując printPanel
     */
    private void printHelpPanel() {
        if (helpPanel == null)
            helpPanel = new HelpPanel(FRAME_WIDTH, FRAME_HEIGHT,this);
        printPanel(helpPanel);

    }

    /**
     * Metoda tworzy GameLevelPanel i regulationPanel i ich rysuje wywołując printPanel
     */
    private void printGameLevelPanel() {
        if (gameLevelPanel == null)
            gameLevelPanel = new GameLevelPanel(FRAME_WIDTH, FRAME_HEIGHT, this);
        if (regulationPanel == null)
            regulationPanel = new RegulationPanel(this);
        printPanel(gameLevelPanel);
        add(regulationPanel, BorderLayout.NORTH);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Metoda służy do usuwania wszystkiego z panlu i rysowania mapypoziomu pierwszego
     */
    void newGame(){
        GameLevelPanel glp = new GameLevelPanel(FRAME_WIDTH, FRAME_HEIGHT, this);
        glp.removeAll();
        glp.updateUI();
        add(glp);
        pack();
    }


}
