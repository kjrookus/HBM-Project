package hbmpack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Math;

public class NumMemory extends JFrame implements ActionListener {

    JFrame NumMemory = new JFrame();
    JLabel label = new JLabel("Number Memory");
    JLabel numberTest = new JLabel();
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

    NumMemory(HomePage.Profile current){
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
        game_panel.setBounds(150, 150, 300, 100);
        NumMemory.add(game_panel);
        game_panel.setVisible(false);
        game_panel.setLayout(new GridLayout());

        score_tracker = new JLabel("Score: " + score);
        game_panel.add(score_tracker);
        score_tracker.setVerticalAlignment(JLabel.BOTTOM);
        score_tracker.setHorizontalAlignment(JLabel.LEFT);

        numberTest.setText("Memorize: ");
        numberTest.setBounds(50, 100, 100, 50);
        numberTest.setVisible(true);
        game_panel.add(numberTest);

        // start button begins game
        start_game = new JButton("start");
        start_game.setBounds(220, 200, 150, 50);
        start_game.addActionListener(this);
        start_game.setVisible(true);
        NumMemory.add(start_game);
        // textField for entering answer
        numbers = new JTextField();
        numbers.setPreferredSize(new Dimension(100, 40));
        numbers.setHorizontalAlignment(JTextField.LEFT);
        numbers.setVisible(false);
        numbers.setFont(new Font(null, Font.PLAIN, 16));
        numbers.addActionListener(this);
        numbers.setFocusable(true);
        game_panel.add(numbers);
        // add home button
        home_but = new JButton();
        NumMemory.add(home_but);
        home_but.setBounds(220, 475, 150, 50);// sets location and size of button
        home_but.setFocusable(false);
        home_but.setText("Return to Menu");
        home_but.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == home_but) {
            NumMemory.dispose();
            new HomePage(numcurrent);
        }
        if (e.getSource() == start_game) {
            start_game.setVisible(false);
            theGame(level);
        }
        /*
        * this is the action listener for the Jtextfield where the
        * user inputs their answers. When someone presses enter
        * while the textfield is in focus, it will evaluate their answer
         */
        if (e.getSource() == numbers){
            int answer = Integer.parseInt(numbers.getText());
            if (answer == shown_number) {
                level++;
                theGame(level);
                score += 1;
                score_tracker.setText("Score: " + score);
            }
            else {
                game_end = true;
            }
        }
        //occurs after the 5 second delay
        if(e.getSource() == delay){
            numberTest.setVisible(false);
            numbers.setVisible(true);
        }
    }

    // actual game
    public void theGame(int level) {
        //shows the gamepanel and numbers, hides the input field
        game_panel.setVisible(true);
        numberTest.setVisible(true);
        numbers.setVisible(false);
        //sets the textfield back to blank
        numbers.setText("");
        //generates a random number of size relative to level
        shown_number = getRandomNumber((int)Math.pow(10, level-1), (int)Math.pow(10, level));
        //sets the text to the random number
        numberTest.setText(String.valueOf(shown_number));

        //pauses the program for 5 seconds, before calling the action listener with delay as the source
        delay = new Timer(5000, this);
        delay.setRepeats(false);
        delay.start();

    }

    //random number generator between min and max
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}