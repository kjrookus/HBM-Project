package hbmpack;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalTime;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;


/**********************************************************
 * Opens a GUI that displays one of three random typing tests.
 * Allows the user to type in a corresponding box while measuring
 * the speed they type in words per minute. Incorrect letters reduce
 * the users adjusted WPM
 *
 * @author Kaden Rookus & Alex Bergers
 * @version 02/20/2022
 * **********************************************************/

public class TypeSpeed implements KeyListener, ActionListener {
    //variable initilization for wpm calculating
    private double start = 0;
    private double timeTaken;
    private int intWpm = 0;

    private final JFrame frame = new JFrame();
    private final JLabel wpm = new JLabel("Words per minute: " + intWpm);
    private final JLabel adjustedWpm = new JLabel("Adjusted words per minute: " + intWpm);
    private final JTextArea testWords = new JTextArea();
    private final JTextArea typeWords = new JTextArea();
    private final JButton homeBut = new JButton();
    private final HomePage.Profile typecurrent;


    TypeSpeed(HomePage.Profile current) {
        typecurrent = current;


        JLabel label = new JLabel("Typing Speed.");
        label.setBounds(50, 25, 1000, 50);
        label.setFont(new Font(null, Font.PLAIN, 25));
        wpm.setBounds(50, 425, 200, 40);
        wpm.setFont(new Font(null, Font.PLAIN, 16));
        adjustedWpm.setBounds(50, 465, 400, 40);
        adjustedWpm.setFont(new Font(null, Font.PLAIN, 16));

        frame.add(label);
        frame.add(wpm);
        frame.add(adjustedWpm);

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
        String test0 = "The landlady informed me that he had left the house shortly after eight"
                + " o'clock in the morning. I sat down beside the fire, however, with the intention "
                + "of awaiting him, however long he might be. I was already deeply interested in "
                + "his inquiry, for, though it was surrounded by none of the grim and strange "
                + "features which were associated with the two crimes which I have already recorded, "
                + "still, the nature of the case and the exalted station of his client gave it a "
                + "character of its own.";
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
        testWords.setText(tests[(int) Math.floor(Math.random() * 3)]);
        testWords.setBounds(50, 75, 500, 165);

        typeWords.setLineWrap(true);
        typeWords.setWrapStyleWord(true);
        typeWords.setFont(new Font(null, Font.PLAIN, 16));
        typeWords.setBounds(50, 250, 500, 150);
        typeWords.setFocusable(true);
        typeWords.addKeyListener(this);

        frame.add(testWords);
        frame.add(typeWords);

        // add home button
        frame.add(homeBut);
        homeBut.setBounds(450, 500, 125, 50); // sets location and size of button
        homeBut.setFocusable(false);
        homeBut.setText("Return to Menu");
        homeBut.addActionListener(this);
        homeBut.setVisible(false);


    }

    /**********************************************************************
     *method to calculate the accuracy of the users currently typed message.
     *
     * @param input value is the string the user has inputted
     * @param goal is the targetted string that the input is compared against
     *
     *@return a double equal to correct characters divided by total characters
     **********************************************************************/

    public double adjust(String input, String goal) {
        int errors = 0;
        double adjustment = 0.0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != goal.charAt(i)) {
                errors++;
            }
        }
        if (input.length() != 0) {
            adjustment = (double) (input.length() - errors) / (double) input.length();
            System.out.println(adjustment);
        }
        return adjustment;
    }

    @Override
    public final void keyTyped(KeyEvent e) {
        //gets the length of the current test
        int totalChars = testWords.getText().length();

        if (typeWords.hasFocus() && typeWords.getText().length() == 0) {
            start = LocalTime.now().toNanoOfDay(); //stores the time when the player starts
        }

        if (typeWords.hasFocus() && typeWords.getText().length() == testWords.getText().length()) {
            typeWords.setEditable(false);
            System.out.println("The total chars: " + totalChars
                    + "The timetaken in seconds: " + timeTaken);
            System.out.println(intWpm);
            typecurrent.setWpmScore(intWpm);
            homeBut.setVisible(true);
        }
    }

    @Override
    public final void keyPressed(KeyEvent e) {

    }

    @Override
    public final void keyReleased(KeyEvent e) {
        if (typeWords.hasFocus() && typeWords.getText().length() < testWords.getText().length()) {
            int typedSoFar = typeWords.getText().length();
            double end = LocalTime.now().toNanoOfDay(); //stores the time when finished
            timeTaken = ((end - start) / 1000000000.0); //calculates the time taken in seconds
            intWpm = (int) (((typedSoFar / 5) / timeTaken) * 60);
            wpm.setText("Words per minute: " + intWpm);
            adjustedWpm.setText("Adjusted words per minute: " +
                    ((int) (intWpm * adjust(typeWords.getText(), testWords.getText()))));
        }
    }

    @Override
    public final void actionPerformed(ActionEvent e) {
        if (e.getSource() == homeBut) {
            new HomePage(typecurrent);
            frame.dispose();
        }
    }
}