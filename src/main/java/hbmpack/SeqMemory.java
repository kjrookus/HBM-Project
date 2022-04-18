package hbmpack;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/******************************************************
 * Creates a 3x3 grid for sequence memory, which lights up
 * buttons at random for the player to memorize and repeat
 * back to the player. Player earns one point per correct
 * button, and loses a life upon an incorrect button
 *
 * @author Kaden Rookus & Alex Bergers
 * @version 2
 *
 *******************************************************/
public class SeqMemory implements ActionListener {
    JFrame frame = new JFrame();
    private final HomePage.Profile seqcurrent;

    JLabel title = new JLabel();
    JLabel subtitle = new JLabel();
    JButton[] grid = new JButton[9];
    JLabel scorelabel = new JLabel();
    JLabel lifeLabel = new JLabel();
    JLabel loselabel = new JLabel();
    JLabel loselabel2 = new JLabel();
    JButton homebut;
    JButton startgame;
    Queue<Integer> memoryq = new LinkedList<>();
    Queue<Integer> tempmemoryq2;
    int lives = 3;
    int score = 0;
    boolean running = false;
    JButton clicked;
    JButton tryagainbut;

    SeqMemory(HomePage.Profile currentprofile) {
        seqcurrent = currentprofile;


        frame.setTitle("Sequence Memory"); // Sets Title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit Application
        frame.setLayout(null);
        frame.setSize(600, 600); //set dimension
        frame.setVisible(true); //make frame visible

        gameinstructions();
        setButtons();
    }

    private void gameinstructions() {
        title.setText("Sequence Memory Test");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(0, 100, 600, 100);
        title.setFont(new Font(null, Font.PLAIN, 40));


        subtitle.setText("Memorize and repeat the pattern.");
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setBounds(0, 150, 600, 150);
        subtitle.setFont(new Font(null, Font.PLAIN, 20));

        frame.add(title);
        frame.add(subtitle);
    }

    /**************************************************
    * Set Buttons is a method that initializes the buttons
    * in an organized area to allow easy changing of
    * bounds, text, and actionlisteners.
     *************************************************/
    public final void setButtons() {
        //home button
        homebut = new JButton();
        frame.add(homebut);
        homebut.setBounds(225, 475, 150, 50); // sets location and size of button
        homebut.setFocusable(false);
        homebut.setText("Return to Menu");
        homebut.addActionListener(this);

        // start button begins game
        startgame = new JButton("start");
        startgame.setBounds(225, 350, 150, 50);
        startgame.addActionListener(this);
        startgame.setVisible(true);
        frame.add(startgame);
    }

    /***********************************************
     * Clear title screen sets all the introduction
     * titles and buttons to invisible.
     **********************************************/
    public void cleartitlescreen() {
        homebut.setVisible(false);
        startgame.setVisible(false);
        title.setVisible(false);
        subtitle.setVisible(false);
    }
    /***********************************************
     * Sets up the Score and Lives labels to appear
     * after the title screen is closed.
     **********************************************/

    public void createscorelives() {
        scorelabel.setHorizontalAlignment(SwingConstants.CENTER);
        scorelabel.setBounds(150, 60, 125, 100);
        scorelabel.setFont(new Font(null, Font.PLAIN, 25));
        scorelabel.setText("Score: " + score);
        scorelabel.setVisible(true);

        lifeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lifeLabel.setBounds(300, 60, 125, 100);
        lifeLabel.setFont(new Font(null, Font.PLAIN, 25));
        lifeLabel.setText("Lives: " + lives);
        lifeLabel.setVisible(true);

        frame.add(scorelabel);
        frame.add(lifeLabel);
    }

    /************************************************
     * setter for the score label.
     *
     * @param score score for the label to be set to.
     *************************************************/
    private void setscorelabel(int score) {
        scorelabel.setText("Score: " + score);
    }

    /**************************************
     * private method that subtracts a life
     * and updates the appropriate label.
     **************************************/
    private void loselife() {
        lives--;
        lifeLabel.setText("Lives: " + lives);
        if (lives == 0) {
            for (JButton jButton : grid) {
                jButton.setVisible(false);
            }
            tryagainbut = new JButton("Try Again?");
            tryagainbut.setBounds(225, 425, 150, 50);
            tryagainbut.addActionListener(this);
            tryagainbut.setVisible(true);
            frame.add(tryagainbut);

            loselabel.setText("Sorry, You Lose.");
            loselabel.setBounds(100, 250, 400, 50);
            loselabel.setHorizontalAlignment(SwingConstants.CENTER);
            loselabel.setVisible(true);
            loselabel.setFont(new Font(null, Font.PLAIN, 25));

            loselabel2.setText("Final Score: " + score);
            loselabel2.setBounds(100, 300, 400, 50);
            loselabel2.setHorizontalAlignment(SwingConstants.CENTER);
            loselabel2.setVisible(true);
            loselabel2.setFont(new Font(null, Font.PLAIN, 25));

            frame.add(loselabel2);
            frame.add(loselabel);

            homebut.setVisible(true);
        }
    }

    /*********************************************
     * create grid is a protected method that will
     * always generate a 3x3 grid to be used for
     * the sequence memory.
     *********************************************/
    protected void creategrid() {
        for (int i = 0; i < grid.length; i++) {
            JButton gridpiece = new JButton();
            gridpiece.addActionListener(this);
            gridpiece.setBackground(Color.black);
            grid[i] = gridpiece;
            frame.add(grid[i]);
        }
        grid[0].setBounds(150, 150, 100, 100);
        grid[1].setBounds(250, 150, 100, 100);
        grid[2].setBounds(350, 150, 100, 100);
        grid[3].setBounds(150, 250, 100, 100);
        grid[4].setBounds(250, 250, 100, 100);
        grid[5].setBounds(350, 250, 100, 100);
        grid[6].setBounds(150, 350, 100, 100);
        grid[7].setBounds(250, 350, 100, 100);
        grid[8].setBounds(350, 350, 100, 100);

    }

    /**************************************************************
     * actionPerformed sets up the action listeners for the buttons
     * this includes the home button, start game button, and all 9
     * buttons in the sequence grid.
     *
     * @param e takes in the object sending an ActionEvent.
     *************************************************************/
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homebut) {
            if(seqcurrent.getSeqScore() < score) {
                seqcurrent.setSeqScore(score);
            }
            frame.dispose();
            new HomePage(seqcurrent);
        } else if (e.getSource() == startgame) {
            cleartitlescreen();
            creategrid();
            createscorelives();
            addtoseq();
            showsequence();
        }else if (e.getSource() == tryagainbut) {
            if(seqcurrent.getSeqScore() < score) {
                seqcurrent.setSeqScore(score);
            }
            frame.dispose();
            new SeqMemory(seqcurrent);
        } else {
            clicked = (JButton) e.getSource();
            if (!running) {
                tempmemoryq2 = new LinkedList<>(memoryq);
                running = true;
            }
            boolean add;
            add = compareseq();
            //if they got one wrong
            if (!add){
                //resets sequence and shows again
                running = false;
                showsequence();
            }
            //if they get them all right.
            if (tempmemoryq2.size() == 0) {
                if (add) {
                    addtoseq();
                }
                running = false;
                showsequence();
            }
        }
    }

    /**********************************************************************
     * compareseq() is a private method used by the action listener
     * to determine whether the correct button was hit, and appropriately
     * adjusts the score and lives based on the button hit.
     *
     * @return returns true if the correct button is hit, false otherwise.
     *********************************************************************/
    @SuppressWarnings("ConstantConditions")
    private boolean compareseq() {
            if (clicked == grid[tempmemoryq2.peek()]) {
                tempmemoryq2.remove();
                score++;
                setscorelabel(score);
                return true;
            } else {
                loselife();
                return false;
            }

    }

    /***************************************************************
     * showsequence is a method that iterates through the current
     * sequence queue and blinks the resepctive square to blue. This
     * is done for one second, every 2 seconds and repeats until
     * the sequence is empty.
     ****************************************************************/
    private void showsequence() {
        Queue<Integer> tempmemoryq = new LinkedList<>(memoryq);

        //prevents the grid from being clicked while showing sequence
        for (JButton jButton : grid) {
            jButton.setEnabled(false);
        }
        //black out returns tiles to black 1 second after being blue
        //noinspection ConstantConditions
        final int[] selectedtile = {tempmemoryq.peek()};
            Timer blackout = new Timer(1000, e -> {
                grid[selectedtile[0]].setBackground(Color.black);
                if (tempmemoryq.size() == 0) {
                    for (JButton jButton : grid) {
                        jButton.setEnabled(true);
                    }
                }
            });
            //blue out turns the first tile in queue to blue
            Timer blueout = new Timer(2000, e -> {
                if (tempmemoryq.size() == 0) {
                    ((Timer) e.getSource()).setRepeats(false);
                    ((Timer) e.getSource()).stop();
                } else {
                    selectedtile[0] = tempmemoryq.remove();
                    grid[selectedtile[0]].setBackground(Color.blue);
                    blackout.start();
                }
                });
            //allow blueout to be repeated for the entire queue
            blueout.setRepeats(true);
            //blackout should only occur once, after each blueout
            blackout.setRepeats(false);
            //begins the color sequence
            blueout.start();
        }

    /*******************************************
     * addtoseq generates a random number 0-8
     * and adds it to the queue that represents
     * the current sequence.
     ******************************************/
    private void addtoseq() {
        int temp = (int) (Math.random() * 9);
        memoryq.add(temp);
    }
}
