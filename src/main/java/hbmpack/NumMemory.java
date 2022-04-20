package hbmpack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**********************************************************
 * Opens a GUI that displays the Number Memory game. Upon starting
 * the game, the user has 5 seconds to memorize a number before
 * they must type the number back to the system. Upon success, a new,
 * longer, number appears and the score increments
 *
 * @author Kaden Rookus & Alex Bergers
 * @version 02/20/2022
 * **********************************************************/

public class NumMemory extends JFrame implements ActionListener {

    private final JFrame numMemory = new JFrame();
    private final JLabel numberTest = new JLabel("Memorize", SwingConstants.CENTER);
    private final JButton homeBut;
    private final JButton startGame;
    private final JPanel gamePanel;
    private final JTextField numbers;
    private Timer delay;
    private final JLabel scoreTracker;
    private int level = 1;
    private int score = 0;
    private int shownNumber = 0;

    private final HomePage.Profile numcurrent;

    NumMemory(HomePage.Profile current) {
        numcurrent = current;
        JLabel label = new JLabel("Number Memory");
        label.setBounds(200, 20, 1000, 50);
        label.setFont(new Font(null, Font.PLAIN, 25));

        numMemory.add(label);

        numMemory.setTitle("Number Memory"); // Sets Title
        numMemory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit Application
        numMemory.setLayout(null);
        numMemory.setSize(600, 600); //set dimension
        numMemory.setVisible(true); //make frame visible

        // game takes place in panel
        gamePanel = new JPanel();
        gamePanel.setBackground(Color.gray);
        gamePanel.setBounds(150, 150, 300, 250);
        numMemory.add(gamePanel, BorderLayout.CENTER);
        gamePanel.setVisible(false);
        gamePanel.setLayout(null);

        scoreTracker = new JLabel("Score: " + score, SwingConstants.CENTER);
        gamePanel.add(scoreTracker);
        scoreTracker.setBounds(100 + gamePanel.getInsets().bottom,
                175 + gamePanel.getInsets().right, 100, 50);

        numberTest.setText("Memorize: ");
        numberTest.setBounds(50 + gamePanel.getInsets().bottom,
                80 + gamePanel.getInsets().right, 200, 50);
        numberTest.setVisible(true);
        numberTest.setFont((new Font("Serif", Font.PLAIN, 40)));
        gamePanel.add(numberTest);

        // start button begins game
        startGame = new JButton("start");
        startGame.setBounds(220, 250, 150, 50);
        startGame.addActionListener(this);
        startGame.setVisible(true);
        numMemory.add(startGame);

        // textField for entering answer
        numbers = new JTextField();
        numbers.setPreferredSize(new Dimension(100, 40));
        numbers.setBounds(100 + gamePanel.getInsets().left, 150 + getInsets().right, 100, 40);
        numbers.setVisible(false);
        numbers.setFont(new Font(null, Font.PLAIN, 16));
        numbers.addActionListener(this);
        numbers.setFocusable(true);
        numbers.setToolTipText("Enter the number: ");
        gamePanel.add(numbers);
        // add home button
        homeBut = new JButton();
        numMemory.add(homeBut);
        homeBut.setBounds(220, 475, 150, 50); // sets location and size of button
        homeBut.setFocusable(false);
        homeBut.setText("Return to Menu");
        homeBut.addActionListener(this);

    }

    /**********************************************************
     * action listeners for the various game buttons and textfields.
     * The home button returns you to the main menu.
     * The start game button starts the number memory game/timer.
     * Hitting enter on the input field submits an answer to be checked.
     * **********************************************************/

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homeBut) {
            numMemory.dispose();
            new HomePage(numcurrent);
        }
        if (e.getSource() == startGame) {
            startGame.setVisible(false);
            theGame(level);
            score = 0;
            level = 1;
            scoreTracker.setText("Score: " + score);
        }
        /*
        * this is the action listener for the Jtextfield where the
        * user inputs their answers. When someone presses enter
        * while the textfield is in focus, it will evaluate their answer
         */
        if (e.getSource() == numbers) {
            int answer = Integer.parseInt(numbers.getText());
            if (answer == shownNumber) {
                level++;
                theGame(level);
                score++;
                scoreTracker.setText("Score: " + score);
                if (score >= numcurrent.getNumbScore()) {
                    numcurrent.setNumbScore(score);
                }
            } else {
                numbers.setVisible(false);
                scoreTracker.setText("Final Score: " + score);
                if (score >= numcurrent.getNumbScore()) {
                    numcurrent.setNumbScore(score);
                }
                gamePanel.add(startGame);
                startGame.setBounds(100 + gamePanel.getInsets().left,
                        150 + getInsets().right, 100, 40);
                startGame.setText("Play Again");
                startGame.setVisible(true);
                score = 0;
                level = 1;
            }
        }
        //occurs after the 5 second delay
        if (e.getSource() == delay) {
            numberTest.setVisible(false);
            numbers.setVisible(true);
        }
    }

    /**********************************************************
     * Shows the game panel and the number to be memorized while
     * starting the 5 second timer. After the timer finishes, the
     * number is hidden and the input box appears.
     *
     * @param curlevel current level the user is on determines the length
     * of the goal number
     * **********************************************************/

    public void theGame(int curlevel) {
        //shows the gamepanel and numbers, hides the input field
        gamePanel.setVisible(true);
        numberTest.setVisible(true);
        numbers.setVisible(false);
        //sets the textfield back to blank
        numbers.setText("");
        //generates a random number of size relative to level
        shownNumber = getRandomNumber((int) Math.pow(10, curlevel - 1), (int) Math.pow(10, curlevel));
        //sets the text to the random number
        numberTest.setText(String.valueOf(shownNumber));

        //pauses the program for 5 seconds before calling the action listener
        delay = new Timer(5000, this);
        delay.setRepeats(false);
        delay.start();

    }

    /**********************************************
     * random number generator between min and max
     *
     * @param min the minimum number we want to generate
     * @param max the max number we want to generate
     * @return an integer between the min and max
     **********************************************/
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}