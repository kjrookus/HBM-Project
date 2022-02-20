package hbmpack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Math;
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

    JFrame NumMemory = new JFrame();
    JLabel label = new JLabel("Number Memory");
    JLabel numberTest = new JLabel("Memorize", SwingConstants.CENTER);
    JButton home_but;
    JButton start_game;
    JPanel game_panel;
    JTextField numbers;
    Timer delay;
    JLabel score_tracker;
    boolean game_end = false;
    int level = 1;
    int score = 0;
    int shown_number = 0;

    HomePage.Profile numcurrent;

    NumMemory(HomePage.Profile current) {
        numcurrent = current;
        label.setBounds(200, 20, 1000, 50);
        label.setFont(new Font(null, Font.PLAIN, 25));

        NumMemory.add(label);

        NumMemory.setTitle("Number Memory"); // Sets Title
        NumMemory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit Application
        NumMemory.setLayout(null);
        NumMemory.setSize(600, 600); //set dimension
        NumMemory.setVisible(true); //make frame visible

        // game takes place in panel
        game_panel = new JPanel();
        game_panel.setBackground(Color.gray);
        game_panel.setBounds(150, 150, 300, 250);
        NumMemory.add(game_panel, BorderLayout.CENTER);
        game_panel.setVisible(false);
        game_panel.setLayout(null);

        score_tracker = new JLabel("Score: " + score, SwingConstants.CENTER);
        game_panel.add(score_tracker);
        score_tracker.setBounds(100 + game_panel.getInsets().bottom,
                175 + game_panel.getInsets().right, 100, 50);

        numberTest.setText("Memorize: ");
        numberTest.setBounds(50 + game_panel.getInsets().bottom,
                80 + game_panel.getInsets().right, 200, 50);
        numberTest.setVisible(true);
        numberTest.setFont((new Font("Serif", Font.PLAIN, 40)));
        game_panel.add(numberTest);

        // start button begins game
        start_game = new JButton("start");
        start_game.setBounds(220, 250, 150, 50);
        start_game.addActionListener(this);
        start_game.setVisible(true);
        NumMemory.add(start_game);

        // textField for entering answer
        numbers = new JTextField();
        numbers.setPreferredSize(new Dimension(100, 40));
        numbers.setBounds(100 + game_panel.getInsets().left, 150 + getInsets().right, 100, 40);
        numbers.setVisible(false);
        numbers.setFont(new Font(null, Font.PLAIN, 16));
        numbers.addActionListener(this);
        numbers.setFocusable(true);
        numbers.setToolTipText("Enter the number: ");
        game_panel.add(numbers);
        // add home button
        home_but = new JButton();
        NumMemory.add(home_but);
        home_but.setBounds(220, 475, 150, 50); // sets location and size of button
        home_but.setFocusable(false);
        home_but.setText("Return to Menu");
        home_but.addActionListener(this);

    }

    /**********************************************************
     * action listeners for the various game buttons and textfields.
     * The home button returns you to the main menu.
     * The start game button starts the number memory game/timer.
     * Hitting enter on the input field submits an answer to be checked.
     * **********************************************************/

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == home_but) {
            NumMemory.dispose();
            new HomePage(numcurrent);
        }
        if (e.getSource() == start_game) {
            start_game.setVisible(false);
            theGame(level);
            score = 0;
            level = 1;
            score_tracker.setText("Score: " + score);
        }
        /*
        * this is the action listener for the Jtextfield where the
        * user inputs their answers. When someone presses enter
        * while the textfield is in focus, it will evaluate their answer
         */
        if (e.getSource() == numbers) {
            int answer = Integer.parseInt(numbers.getText());
            if (answer == shown_number) {
                level++;
                theGame(level);
                score++;
                score_tracker.setText("Score: " + score);
                if (score >= numcurrent.getNumbScore()) {
                    numcurrent.setNumbScore(score);
                }
            } else {
                numbers.setVisible(false);
                score_tracker.setText("Final Score: " + score);
                game_end = true;
                if (score >= numcurrent.getNumbScore()) {
                    numcurrent.setNumbScore(score);
                }
                game_panel.add(start_game);
                start_game.setBounds(100 + game_panel.getInsets().left,
                        150 + getInsets().right, 100, 40);
                start_game.setText("Play Again");
                start_game.setVisible(true);
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
     * @param level current level the user is on determines the length
     * of the goal number
     * **********************************************************/

    public void theGame(int level) {
        //shows the gamepanel and numbers, hides the input field
        game_panel.setVisible(true);
        numberTest.setVisible(true);
        numbers.setVisible(false);
        //sets the textfield back to blank
        numbers.setText("");
        //generates a random number of size relative to level
        shown_number = getRandomNumber((int) Math.pow(10, level - 1), (int) Math.pow(10, level));
        //sets the text to the random number
        numberTest.setText(String.valueOf(shown_number));

        //pauses the program for 5 seconds before calling the action listener
        delay = new Timer(5000, this);
        delay.setRepeats(false);
        delay.start();

    }

    //random number generator between min and max
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}