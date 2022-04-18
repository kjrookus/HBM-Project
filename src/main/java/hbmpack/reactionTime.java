package hbmpack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*************************************************************
 * Opens GUI that creates game for user to test reaction time.
 * Upon pressing start button the background will turn red.
 * the red button will turn green after a random amount of time.
 * The user must press as fast as possible.
 * Upon pressing the display will give the time and the option
 * to play again or return to homepage.
 * @author Kaden Rookus & Alex Bergers
 * @version 2
 *************************************************************/
public class reactionTime extends JFrame implements ActionListener {

    static JFrame reactionTime = new JFrame();
    JLabel title = new JLabel(); // explain rules to user
    JLabel subtitle = new JLabel();
    JLabel click = new JLabel(); // tells player what to do
    JButton start = new JButton("Start");
    JButton home_but = new JButton(); // returns home
    JButton redBut = new JButton(); // test is giant button that goes from red to green
    JPanel panel = new JPanel(); // panel made to add button to
    Timer delay; // time tracker
    JLabel results = new JLabel();

    int elapsedTime;
    boolean test;

    HomePage.Profile reactCurrent;

    public reactionTime(HomePage.Profile current) {

        reactCurrent = current; // profile

        this.setTitle("Reaction Time"); // Sets Title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit Application
        this.setLayout(null);
        this.setSize(600, 600); //set dimension
        this.setVisible(true); //make frame visible

        panel.setBounds(0,0,600,600); // panel where button is added
        panel.setVisible(false);
        panel.setLayout(null);
        this.add(panel);

        results.setVisible(false); // display for time upon completion
        results.setFont((new Font("Serif", Font.PLAIN, 25)));
        this.add(results);

        title.setText("Reaction Time Test"); // name of the game
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(0, 150, 600, 100);
        title.setFont(new Font(null, Font.PLAIN, 40));
        title.setVisible(true);
        this.add(title);

        subtitle.setText("When the red box turns green, click as quickly as you can!"); // Instructions
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setBounds(0, 200, 600, 100);
        subtitle.setFont(new Font(null, Font.PLAIN, 14));
        subtitle.setVisible(true);
        this.add(subtitle);

        click.setText("Click the start button to begin");
        click.setHorizontalAlignment(SwingConstants.CENTER);
        click.setBounds(0, 230, 600, 100);
        click.setFont(new Font(null, Font.PLAIN, 14));
        click.setVisible(true);
        this.add(click);

        start.setHorizontalAlignment(SwingConstants.CENTER);
        start.setBounds(250, 300, 100, 50);
        start.addActionListener(this);
        start.setVisible(true);
        this.add(start);

        // add home button
        this.add(home_but);
        home_but.setBounds(220, 475, 150, 50);
        home_but.setFocusable(false);
        home_but.setText("Return to Menu");
        home_but.addActionListener(this);

    }
    // keeps track of time passed since the start of the game
    // goes up by 1 ms for every 1ms passed
    Timer timer = new Timer(1, e -> elapsedTime+=1);

    /**************************************************
     * Action listener for various buttons in the game.
     * home button returns to homepage.
     * Start button begins the game.
     * redbut is the button that tests the reaction time.
     * If redbut is red, test is false, meaning they clicked
     * at the wrong time.
     * redbut is green, and test is true, meaning the test has
     * started, and they clicked at the correct instance.
     * Delay is the amount of time that has past set by the
     * random amount of time given. If that amount of time has
     * past, the redBut turns green and the test is now true.
     * @param e actionlistener input.
     **************************************************/
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == home_but) {
            new HomePage(reactCurrent);
            reactionTime.dispose();
        }
        if (e.getSource() == start){
            theGame();
        }
        if (e.getSource() == redBut) {
            // button has not turned green yet and the user pressed
            // they can try again or return to home page
            if (!test) {
                results.setVisible(true);
                panel.setVisible(false);
                start.setText("Try Again");
                start.setVisible(true);
                home_but.setVisible(true);
                results.setBounds(100,200,600,100);
                results.setText("Too Soon! Press Try Again to restart!");
            }
            // user pressed at correct time and that time is given
            if (test) {
                timer.stop();
                panel.setVisible(false);
                results.setVisible(true);
                start.setText("Try Again");
                start.setVisible(true);
                home_but.setVisible(true);
                results.setBounds(240,200,600,100);
                results.setText("Time: " + elapsedTime + "ms");
                // set new high score if previous score is higher than new one
                if(elapsedTime < reactCurrent.getReactScore()){
                    reactCurrent.setReactScore(elapsedTime);
                }
            }
        }
        // represents the amount of time passed by the delay
        if (e.getSource() == delay){
            redBut.setBackground(Color.GREEN);
            test = true;
            timer.start();
        }
    }

    /******************************************
     * represent the start if the game
     * all unused GUI objects are disabled and
     * new ones for the game are made visible.
     *******************************************/
    public void theGame(){
        redBut.setBounds(0,0,600,600); // Button being used as test
        redBut.setVisible(false);
        redBut.setFocusable(true);
        redBut.setOpaque(true);
        redBut.setBackground(Color.red); // originally red, will be changed to green
        redBut.setBorderPainted(false);
        redBut.addActionListener(this);
        panel.add(redBut);
        redBut.setVisible(true);
        results.setBounds(50,200,600,100);
        panel.setVisible(true);
        // set unused gui objects invisible
        start.setVisible(false);
        click.setVisible(false);
        title.setVisible(false);
        subtitle.setVisible(false);
        home_but.setVisible(false);

        // represents the milliseconds passed since start of timer
        elapsedTime = 0;
        // test is set to true when buttons turns green after time is passed
        test= false;

        // random amount of time to set to delay the button changing color
        // between 3 and 10 seconds
        int randomDelay = 3000 + (int)(Math.random()*((10000 - 3000)+1));
        delay = new Timer(randomDelay, this);
        delay.setRepeats(true);
        delay.start();
    }

}