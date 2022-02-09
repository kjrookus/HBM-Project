package hbmpack;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TypeSpeed {

    JFrame frame = new JFrame();
    JLabel label = new JLabel("Typing Speed.");
    JLabel wpm = new JLabel("Words per minute:");
    JTextArea testWords = new JTextArea();
    JTextArea typeWords = new JTextArea();

    String test0 = "The landlady informed me that he had left the house shortly after eight o'clock in the morning. " +
            "I sat down beside the fire, however, with the intention of awaiting him, however long he might be. " +
            "I was already deeply interested in his inquiry, for, though it was surrounded by none of the grim and " +
            "strange features which were associated with the two crimes which I have already recorded, still, the " +
            "nature of the case and the exalted station of his client gave it a character of its own";

    String test1 = "To learn to type quickly, practice often and adopt the proper technique. Fix your posture, " +
            "have adequate lighting, position your hands correctly over the keyboard, look at the screen and " +
            "use all your fingers to hit the keys. At first, concentrate on accuracy over speed. This will help " +
            "you develop muscle memory and create automatic reflexes. Keep practicing and gradually pick up the pace. " +
            "You'll see results after just a few weeks!";

    String test2 = "A late 20th century trend in typing, primarily used with devices with small keyboards " +
            "(such as PDAs and Smartphones), is thumbing or thumb typing. This can be accomplished using one or both thumbs." +
            " Similar to desktop keyboards and input devices, if a user overuses keys which need hard presses and/or" +
            " have small and unergonomic layouts, it could cause thumb tendonitis or other repetitive strain injury.";

    String [] tests = {test0, test1, test2};

    TypeSpeed(){

        label.setBounds(50,25,1000,50);
        label.setFont(new Font(null, Font.PLAIN, 25));

        wpm.setBounds(50, 425, 200, 40);
        wpm.setFont(new Font(null, Font.PLAIN, 16));

        frame.add(label);
        frame.add(wpm);

        frame.setTitle("Typing Speed"); // Sets Title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit Application
        frame.setLayout(null);
        frame.setSize(600,600); //set dimension
        frame.setVisible(true); //make frame visible

        //adds in a random test in a uneditable text box that wraps words without breaking words up.
        testWords.setLineWrap(true);
        testWords.setWrapStyleWord(true);
        testWords.setEditable(false);
        testWords.setFont(new Font(null, Font.PLAIN, 16));
        testWords.setText(tests[(int)Math.floor(Math.random()*3)]);
        testWords.setBounds(50, 75, 500, 150);


        typeWords.setLineWrap(true);
        typeWords.setWrapStyleWord(true);
        typeWords.setFont(new Font(null, Font.PLAIN, 16));
        typeWords.setBounds(50, 230, 500, 150);


        frame.add(testWords);
        frame.add(typeWords);
    }
}