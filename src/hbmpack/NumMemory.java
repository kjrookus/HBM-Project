package hbmpack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Random;

public class NumMemory extends JFrame implements ActionListener {

    JFrame NumMemory = new JFrame();
    JLabel label = new JLabel("Number Memory");
    JLabel numberTest = new JLabel();
    Random randomizer = new Random();
    int first_number = randomizer.nextInt(10) + 1;
    JButton home_but;
    JButton start_game;
    JPanel game_panel;
    JTextArea numbers;
    boolean game_end = true;
    int score = 0;
    int level = 0;

    HomePage.Profile numcurrent;

    NumMemory(HomePage.Profile current) throws InterruptedException {
        numcurrent = current;
        label.setBounds(0, 0, 1000, 50);
        label.setFont(new Font(null, Font.PLAIN, 25));

        NumMemory.add(label);

        numberTest.setText("" + first_number);
        numberTest.setBounds(50, 50, 50, 50);
        numberTest.setVisible(true);


        NumMemory.setTitle("Number Memory"); // Sets Title
        NumMemory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit Application
        NumMemory.setLayout(null);
        NumMemory.setSize(600, 600); //set dimension
        NumMemory.setVisible(true); //make frame visible
        // game takes place in panel
        game_panel = new JPanel();
        game_panel.setBackground(Color.gray);
        game_panel.setBounds(200, 200, 300, 300);
        NumMemory.add(game_panel);
        game_panel.setVisible(false);
        game_panel.add(numberTest);

        // start button begins game
        start_game = new JButton("start");
        start_game.setBounds(200, 200, 100, 50);
        start_game.addActionListener(this);
        start_game.setVisible(true);
        NumMemory.add(start_game);
        // textArea for entering answer
        numbers = new JTextArea();
        numbers.setPreferredSize(new Dimension(100, 40));
        numbers.setVisible(false);
        numbers.setLineWrap(true);
        numbers.setWrapStyleWord(true);
        numbers.setFont(new Font(null, Font.PLAIN, 16));
        numbers.addKeyListener((KeyListener) this);
        numbers.setFocusable(true);
        game_panel.add(numbers);
        // add home button
        home_but = new JButton();
        NumMemory.add(home_but);
        home_but.setBounds(300, 0, 150, 50);// sets location and size of button
        home_but.setFocusable(false);
        home_but.setText("Return to Menu");
        home_but.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == home_but) {
            NumMemory.dispose();
            new HomePage(numcurrent);
        }
        else if (e.getSource() == start_game) {
            game_panel.setVisible(true);
            theGame();
        }
    }

    // actual game
    public void theGame() {
        start_game.setVisible(false);
        game_end = false; // loop until game is over
        while(!game_end){
           /* try
            {
                Thread.sleep(5000); // gives 5 seconds to memorize number
            }
            catch (InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }*/
            numbers.setVisible(true);
            numberTest.setVisible(false);
            int answer = Integer.parseInt(numbers.getText());
            if (answer == first_number) {
                score += 1;
                level += 1;
            }
            else {
                game_end = true;
            }
        }

    }


}