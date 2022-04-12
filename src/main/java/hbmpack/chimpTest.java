package hbmpack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class chimpTest extends JFrame implements ActionListener {
    JFrame chimpTest = new JFrame();
    JLabel title = new JLabel();
    JLabel subtitle = new JLabel();
    JButton start = new JButton("Start");
    JButton home_but = new JButton();
    JPanel gamePanel = new JPanel();
    JLabel finalScore = new JLabel();
    int size = 4;
    int scoreTracker = 0;
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

        finalScore.setBounds(0,200,600,100);
        finalScore.setFont(new Font(null,Font.PLAIN,20));
        finalScore.setVisible(false);
        this.add(finalScore);

        start.setHorizontalAlignment(SwingConstants.CENTER);
        start.setBounds(250, 300, 100, 50);
        start.addActionListener(this);
        start.setVisible(true);
        this.add(start);

        // add home button
        this.add(home_but);
        home_but.setBounds(220, 475, 150, 50); // sets location and size of button
        home_but.setFocusable(false);
        home_but.setText("Return to Menu");
        home_but.addActionListener(this);

        gamePanel.setVisible(false);
        gamePanel.setBounds(100,100,400,300);
        gamePanel.setBackground(Color.gray);
        gamePanel.setLayout(new GridLayout(8,8,10,10));
        this.add(gamePanel);
    }
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
                if(e.getSource() == butArray[size-1]){
                    size = size + 1;
                    scoreTracker =0;
                    initialize(size);
                }
            }
            else if(e.getSource()==butArray[r] && r != scoreTracker){
                gamePanel.setVisible(false);}
        }
    }


    private void theGame(){
        start.setVisible(false);
        title.setVisible(false);
        subtitle.setVisible(false);
        gamePanel.setVisible(true);
        initialize(size);
    }

    private void initialize(int size){
        for(int x = 0; x < size; x++){
            butArray[x] = new JButton(String.valueOf(x + 1));
            butArray[x].addActionListener(this);
            butArray[x].setSize(20,20);
            gamePanel.add(butArray[x]);
        }
    }

}
