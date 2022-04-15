package hbmpack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**************************************************
 * Opens GUI that starts the chimp test game.
 * The user must memorize the position of the buttons
 * in order of 1 - whichever level they are on, beginning
 * with 4. Each successful attempt brings the user to the
 * next level. Upon failure the score is shown and the user
 * may start over.
 * @author Kaden Rookus & Alex Bergers
 * @version
 **************************************************/

public class chimpTest extends JFrame implements ActionListener {
    JFrame chimpTest = new JFrame();
    JLabel title = new JLabel();
    JLabel subtitle = new JLabel();
    JButton start = new JButton("Start");
    JButton home_but = new JButton();
    JPanel gamePanel = new JPanel();
    JLabel finalScore = new JLabel();
    int size;
    int scoreTracker;
    JButton[]butArray = new JButton[100];

    HomePage.Profile chimpCurrent;
    public chimpTest(HomePage.Profile current){
        chimpCurrent = current;

        this.setTitle("Chimp Test"); // Sets Title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit Application
        this.setLayout(null);
        this.setSize(600, 600); //set dimension
        this.setVisible(true); //make frame visible

        title.setText("Are You Smarter Than a Chimpanzee?");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(0, 150, 600, 100);
        title.setFont(new Font(null, Font.PLAIN, 25));
        title.setVisible(true);
        this.add(title);

        subtitle.setText("Click the Squares in order according to there number.");
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setBounds(0, 200, 600, 100);
        subtitle.setFont(new Font(null, Font.PLAIN, 14));
        subtitle.setVisible(true);
        this.add(subtitle);

        finalScore.setBounds(248,200,200,100);
        finalScore.setFont(new Font(null,Font.PLAIN,32));
        this.add(finalScore);

        start.setHorizontalAlignment(SwingConstants.CENTER);
        start.setBounds(250, 300, 100, 50);
        start.addActionListener(this);
        start.setVisible(true);
        this.add(start);

        // add home button
        this.add(home_but);
        home_but.setBounds(220, 500, 150, 50); // sets location and size of button
        home_but.setFocusable(false);
        home_but.setText("Return to Menu");
        home_but.addActionListener(this);

        gamePanel.setVisible(false);
        gamePanel.setBounds(100,100,400,400);
        gamePanel.setBackground(Color.gray);
        gamePanel.setLayout(null);
        this.add(gamePanel);
    }

    /******************************************************
     * Action listener for each button added to the GUI
     * The start button begins the game and disables all
     * instances of rules shown to the user.
     * the return home button brings the user to the homepage
     * and removes the current page.
     * the for loop, goes through the array of buttons used for the
     * game, making sure the buttons are clicked in order.
     *******************************************************/
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == home_but) {
            new HomePage(chimpCurrent);
            chimpTest.dispose();
        }
        if (e.getSource() == start){
            theGame();
        }
        for(int r=0;r<size;r++){
            if(e.getSource() == butArray[r] &&  r == scoreTracker){
                butArray[r].setVisible(false);
                scoreTracker++;
                if (size > 4) {
                    for (int x = 0; x < size; x++) {
                        butArray[x].setText("");
                        butArray[x].setForeground(Color.white);
                        butArray[x].setBackground(Color.white);
                    }
                }
                // test if the last button in the sequence is pressed and start the next round
                if(e.getSource() == butArray[size-1]){
                    // adds 1 to the size. size = level
                    size = size + 1;
                    scoreTracker =0;
                    gamePanel.repaint();
                    initialize(size);
                }
            }
            // if wrong button is clicked end the game and display scores and play again button
            else if(e.getSource()==butArray[r] && r != scoreTracker){
                gamePanel.setVisible(false);
                start.setVisible(true);
                finalScore.setVisible(true);
                // if size = 4, user failed to complete any test
                if(size == 4){size = 0;}
                else{size = size -1;}
                // display the score to user
                finalScore.setText("Score: "+size);
                // check to see if high score for user is beaten
                if(size> chimpCurrent.getChimp()){
                    chimpCurrent.setChimp(size);
                }
            }
        }
    }

    /****************************************
     * begins the process of the actual game.
     * removes any unneeded buttons and labels
     * clears the board from any previous games.
     * initializes the buttons to begin appearing
     ****************************************/
    private void theGame(){
        // clear the game panel and reset variables
        gamePanel.repaint();
        gamePanel.revalidate();
        gamePanel.removeAll();
        size = 4;
        scoreTracker = 0;
        start.setVisible(false);
        finalScore.setVisible(false);
        title.setVisible(false);
        subtitle.setVisible(false);
        gamePanel.setVisible(true);
        initialize(size);
    }

    private void initialize(int size){
        // loop through the array to create buttons and set locations
        for(int z = 0; z < size; z++){

            butArray[z] = new JButton(String.valueOf(z + 1));
            butArray[z].addActionListener(this);
            butArray[z].setVisible(true);
            butArray[z].setFocusable(false);
            butArray[z].setFont(new Font(null,Font.PLAIN,15));
            butArray[z].setBounds(getRandomNumber(0,350),getRandomNumber(0,350),50,50);
            // check to see if the position of the new button overlaps any buttons already in existence
            // use boolean done to represent that all buttons have their own boundaries
            boolean done = false;
            while(!done){
                for(int x = 0; x < z; x++){
                    if(butArray[x].getBounds().intersects(butArray[z].getBounds())){
                        butArray[z].setBounds(getRandomNumber(0,350),getRandomNumber(0,350),50,50);
                        x = -1;
                    }
                }
                done = true;
            }
            gamePanel.add(butArray[z]);
        }
    }
    // generate a random number in the bounds of the game panel for the location of the button
    public int getRandomNumber(int min, int max){
        return (int) ((Math.random() *(max-min)) + min);
    }
}
