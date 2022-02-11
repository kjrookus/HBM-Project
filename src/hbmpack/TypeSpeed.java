package hbmpack;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalTime;
import javax.swing.*;

/**********************************************************
 * Opens a GUI that displays one of three random typing tests.
 * Allows the user to type in a corresponding box while measuring
 * the speed they type in words per minute. Incorrect letters reduce
 * the users WPM
 *
 * @author Kaden Rookus & Alex Bergers
 * @version 02/10/2022
 * **********************************************************/

public class TypeSpeed implements KeyListener, ActionListener {
    //variable initilization for wpm calculating
    double start = 0;
    double end = 0;
    double timeTaken;
    int WPM = 0;
    int totalChars =0;

    JFrame frame = new JFrame();
    JLabel label = new JLabel("Typing Speed.");
    JLabel wpm = new JLabel("Words per minute: " + WPM);
    JTextArea testWords = new JTextArea();
    JTextArea typeWords = new JTextArea();
    JButton home_but = new JButton();
    HomePage.Profile typecurrent;



    String test0 = "The landlady informed me that he had left the house shortly after eight"
            + " o'clock in the morning. I sat down beside the fire, however, with the intention "
            + "of awaiting him, however long he might be. I was already deeply interested in "
            + "his inquiry, for, though it was surrounded by none of the grim and strange "
            + "features which were associated with the two crimes which I have already recorded, "
            + "still, the nature of the case and the exalted station of his client gave it a "
            + "character of its own";

    String test1 = "To learn to type quickly, practice often and adopt the proper technique. "
            + "Fix your posture, have adequate lighting, position your hands correctly over the "
            + "keyboard, look at the screen and use all your fingers to hit the keys. At first, "
            + "concentrate on accuracy over speed. This will help you develop muscle memory and "
            + "create automatic reflexes. Keep practicing and gradually pick up the pace. "
            + "You'll see results after just a few weeks!";

    String test2 = "A late 20th century trend in typing, primarily used with devices with "
            + "small keyboards (such as PDAs and Smartphones), is thumbing or thumb typing. "
            + "This can be accomplished using one or both thumbs. Similar to desktop keyboards "
            + "and input devices, if a user overuses keys which need hard presses and/or have "
            + "small and unergonomic layouts, it could cause thumb tendinitis or other "
            + "repetitive strain injury.";

    String[] tests = {test0, test1, test2};

    TypeSpeed(HomePage.Profile current) {
        typecurrent = current;


        label.setBounds(50, 25, 1000, 50);
        label.setFont(new Font(null, Font.PLAIN, 25));

        wpm.setBounds(50, 425, 200, 40);
        wpm.setFont(new Font(null, Font.PLAIN, 16));

        frame.add(label);
        frame.add(wpm);

        frame.setTitle("Typing Speed"); // Sets Title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit Application
        frame.setLayout(null);
        frame.setSize(600, 600); //set dimension
        frame.setVisible(true); //make frame visible

        //adds in a random test in a uneditable text box that wraps words without breaking words up.
        testWords.setLineWrap(true);
        testWords.setWrapStyleWord(true);
        testWords.setEditable(false);
        testWords.setFont(new Font(null, Font.PLAIN, 16));
        testWords.setText(tests[(int) Math.floor(Math.random() * 3)]);
        testWords.setBounds(50, 75, 500, 150);

        typeWords.setLineWrap(true);
        typeWords.setWrapStyleWord(true);
        typeWords.setFont(new Font(null, Font.PLAIN, 16));
        typeWords.setBounds(50, 230, 500, 150);
        typeWords.setFocusable(true);
        typeWords.addKeyListener(this);

        frame.add(testWords);
        frame.add(typeWords);

        // add home button
        frame.add(home_but);
        home_but.setBounds(450,500, 125,50);// sets location and size of button
        home_but.setFocusable(false);
        home_but.setText("Return to Menu");
        home_but.addActionListener(this);


    }

    @Override
    public void keyTyped(KeyEvent e) {
        //gets the length of the current test
        totalChars = testWords.getText().length();

        if(typeWords.hasFocus() && typeWords.getText().length() ==0 ){
            start = LocalTime.now().toNanoOfDay(); //stores the time when the player starts
        }

        if(typeWords.hasFocus() && typeWords.getText().length() == testWords.getText().length()){
            typeWords.setEditable(false);
            System.out.println("The total chars: " + totalChars + "The timetaken in seconds: " + timeTaken);
            System.out.println(WPM);
            typecurrent.setWpmScore(WPM);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(typeWords.hasFocus() && typeWords.getText().length() < testWords.getText().length()) {
            int typedSoFar = typeWords.getText().length();
            end = LocalTime.now().toNanoOfDay(); //stores the time when finished
            timeTaken = ((end - start) / 1000000000.0); //calculates the time taken in seconds
            WPM = (int) (((typedSoFar / 5) / timeTaken) * 60);
            wpm.setText("Words per minute: " + WPM);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== home_but)
            new HomePage(typecurrent);
            frame.dispose();
    }
}