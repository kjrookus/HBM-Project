package hbmpack;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**********************************************************
 * Opens a GUI that generates random words and has the player
 * evaluate whether or not they have seen the word before.
 * Player earns 1 score for correct answers and loses 1
 * life for incorrect answers.
 *
 * @author Kaden Rookus & Alex Bergers
 * @version 02/20/2022
 * **********************************************************/

public class VerbMemoryFront extends JFrame implements ActionListener {
    private final JFrame verbMemory = new JFrame();

    private final JLabel scorelabel = new JLabel();
    private final JLabel lifeLabel = new JLabel();
    private final JLabel title = new JLabel();
    private final JLabel subtitle1 = new JLabel();
    private final JLabel subtitle2 = new JLabel();
    private final JLabel subtitle3 = new JLabel();

    private JButton homeBut;
    private JButton startGame;
    private JButton seenBut;
    private JButton newBut;
    private JButton tryagainBut;
    private final JLabel goalword = new JLabel();
    private final HomePage.Profile verbcurrent;

    VerbMemoryFront (HomePage.Profile current) {
        verbcurrent = current;

        setLabels();
        setButtons();
        VerbMemoryBack.setScore(0);
        VerbMemoryBack.setLives(3);
        VerbMemoryBack.setCount(0);
        updatescorelives();

        verbMemory.setTitle("Verbal Memory"); // Sets Title
        verbMemory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit Application
        verbMemory.setLayout(null);
        verbMemory.setSize(600,600); //set dimension
        verbMemory.setVisible(true); //make frame visible


    }

    /*************************************
     * Private helper method to organize
     * the generation of the Jlabels.
     ************************************/
    private void setLabels() {
        goalword.setHorizontalAlignment(SwingConstants.CENTER);
        goalword.setBounds(0, 250, 600, 100);
        goalword.setFont(new Font(null, Font.PLAIN, 35));

        scorelabel.setHorizontalAlignment(SwingConstants.CENTER);
        scorelabel.setBounds(175, 125, 125, 100);
        scorelabel.setFont(new Font(null, Font.PLAIN, 25));
        scorelabel.setVisible(false);

        lifeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lifeLabel.setBounds(300, 125, 125, 100);
        lifeLabel.setFont(new Font(null, Font.PLAIN, 25));
        lifeLabel.setVisible(false);

        title.setText("Verbal Memory Test");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(0, 100, 600, 100);
        title.setFont(new Font(null, Font.PLAIN, 40));

        subtitle1.setText("You will be shown words, one at a time.");
        subtitle1.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle1.setBounds(100, 175, 400, 50);
        subtitle1.setFont(new Font(null, Font.PLAIN, 20));

        subtitle2.setText("If you've seen a word during the test, click SEEN.");
        subtitle2.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle2.setBounds(75, 200, 450, 50);
        subtitle2.setFont(new Font(null, Font.PLAIN, 20));

        subtitle3.setText("If it's a new word, click NEW.");
        subtitle3.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle3.setBounds(100, 225, 400, 50);
        subtitle3.setFont(new Font(null, Font.PLAIN, 20));



        verbMemory.add(goalword);
        verbMemory.add(scorelabel);
        verbMemory.add(lifeLabel);
        verbMemory.add(title);
        verbMemory.add(subtitle1);
        verbMemory.add(subtitle2);
        verbMemory.add(subtitle3);
    }

    /*************************************
     * Private helper method to organize
     * the generation of the Jbuttons.
     ************************************/
    public final void setButtons() {

        //home button
        homeBut = new JButton();
        verbMemory.add(homeBut);
        homeBut.setBounds(225, 475, 150, 50);// sets location and size of button
        homeBut.setFocusable(false);
        homeBut.setText("Return to Menu");
        homeBut.addActionListener(this);

        // start button begins game
        startGame = new JButton("start");
        startGame.setBounds(225, 350, 150, 50);
        startGame.addActionListener(this);
        startGame.setVisible(true);
        verbMemory.add(startGame);

        seenBut = new JButton("Seen");
        seenBut.setBounds(200, 400, 100, 75);
        seenBut.addActionListener(this);
        seenBut.setVisible(false);
        verbMemory.add(seenBut);

        newBut = new JButton("New");
        newBut.setBounds(300, 400, 100, 75);
        newBut.addActionListener(this);
        newBut.setVisible(false);
        verbMemory.add(newBut);

        tryagainBut = new JButton("Try Again?");
        tryagainBut.setBounds(225, 425, 150, 50);
        tryagainBut.addActionListener(this);
        tryagainBut.setVisible(false);
        verbMemory.add(tryagainBut);
    }

    /**************************************************
     * handles the code that responds to specific
     * action calls by action listeners.
     *
     * @param e The action listener performing an action
     **************************************************/
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homeBut) {

            verbMemory.dispose();
            new HomePage(verbcurrent);
        }
        if (e.getSource() == startGame) {
           frontGame();
            run();
        }
        if (e.getSource() == newBut) {
            if (!VerbMemoryBack.neweval()) {
                updatescorelives();
                endgame();
            } else {
                updatescorelives();
                run();
            }
        }
        if (e.getSource() == seenBut) {
            if (!VerbMemoryBack.seeneval()) {
                updatescorelives();
                endgame();
            } else {
                updatescorelives();
                run();
            }
        }
        if (e.getSource() == tryagainBut) {
            if (verbcurrent.getVerbScore() < VerbMemoryBack.getScore()) {
                verbcurrent.setVerbScore(VerbMemoryBack.getScore());
            }
            verbMemory.dispose();
            new VerbMemoryFront(verbcurrent);
        }
    }

    /*******************************************************
     * Helper method that updates the score and lives labels.
     *******************************************************/
    private void updatescorelives() {
        scorelabel.setText("Score: " + VerbMemoryBack.getScore());
        lifeLabel.setText("Lives: " + VerbMemoryBack.getLives());
    }

    private final String[] checked = new String[100];

    /**************************************
     * clears the starting menu to prep for
     * the game to appear.
     *************************************/
    public final void frontGame() {
        title.setVisible(false);
        subtitle1.setVisible(false);
        subtitle2.setVisible(false);
        subtitle3.setVisible(false);
        scorelabel.setVisible(true);
        lifeLabel.setVisible(true);
        seenBut.setVisible(true);
        newBut.setVisible(true);
        startGame.setVisible(false);
        goalword.setVisible(true);
        homeBut.setVisible(false);
    }

    /*********************************************************
     * runs the verbal memory game by calling VerbMemoryBack.
     *********************************************************/
    private void run() {
        String currentword = VerbMemoryBack.runVerbmem(checked);
        //add word
        int count = VerbMemoryBack.getCount();
        checked[count] = currentword;
        goalword.setText(currentword);
    }

    /***************************************
     * Function that ends the game when the
     * player runs out of lives. Assigns new
     * high score if applicable and clears
     * the game from the gui.
     ***************************************/
    private void endgame() {
        if (verbcurrent.getVerbScore() < VerbMemoryBack.getScore()) {
            verbcurrent.setVerbScore(VerbMemoryBack.getScore());
        }
        title.setVisible(true);
        lifeLabel.setVisible(false);
        scorelabel.setBounds(0, 200, 600, 100);
        scorelabel.setFont(new Font(null, Font.PLAIN, 40));
        seenBut.setVisible(false);
        newBut.setVisible(false);
        goalword.setVisible(false);
        homeBut.setVisible(true);
        tryagainBut.setVisible(true);
    }
}

