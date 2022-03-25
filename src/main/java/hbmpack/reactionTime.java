package hbmpack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class reactionTime extends JFrame implements ActionListener {
    static JFrame reactionTime = new JFrame();
    JLabel title = new JLabel();
    JLabel subtitle = new JLabel();
    JLabel click = new JLabel();
    JButton start = new JButton("Start");
    JButton home_but = new JButton();
    JButton redBut = new JButton("test");
    Timer delay;

    HomePage.Profile reactCurrent;

    reactionTime(HomePage.Profile current) {

        reactCurrent = current;

        reactionTime.setTitle("Reaction Time"); // Sets Title
        reactionTime.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit Application
        reactionTime.setLayout(null);
        reactionTime.setSize(600, 600); //set dimension
        reactionTime.setVisible(true); //make frame visible

        title.setText("Reaction Time Test");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(0, 150, 600, 100);
        title.setFont(new Font(null, Font.PLAIN, 40));
        title.setVisible(true);
        reactionTime.add(title);

        subtitle.setText("When the red box turns green, click as quickly as you can!");
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setBounds(0, 200, 600, 100);
        subtitle.setFont(new Font(null, Font.PLAIN, 14));
        subtitle.setVisible(true);
        reactionTime.add(subtitle);

        click.setText("Click the start button to begin");
        click.setHorizontalAlignment(SwingConstants.CENTER);
        click.setBounds(0, 230, 600, 100);
        click.setFont(new Font(null, Font.PLAIN, 14));
        click.setVisible(true);
        reactionTime.add(click);

        start.setHorizontalAlignment(SwingConstants.CENTER);
        start.setBounds(250, 300, 100, 50);
        start.addActionListener(this);
        start.setVisible(true);
        reactionTime.add(start);

        // add home button
        reactionTime.add(home_but);
        home_but.setBounds(220, 475, 150, 50); // sets location and size of button
        home_but.setFocusable(false);
        home_but.setText("Return to Menu");
        home_but.addActionListener(this);

        redBut.setBounds(0,0,600,600);
        redBut.setVisible(false);
        redBut.setFocusable(true);
        redBut.addActionListener(this);
        redBut.setBackground(Color.red);
        redBut.setOpaque(true);
        redBut.setBorder(null);
        reactionTime.add(redBut);


    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == home_but) {
            new HomePage(reactCurrent);
            reactionTime.dispose();
        }
        if (e.getSource() == start){
            theGame();
        }
        if (e.getSource() == start){

        }

    }

    public void theGame(){
        redBut.setVisible(true);
        start.setVisible(false);
        click.setVisible(false);
        title.setVisible(false);
        subtitle.setVisible(false);
        home_but.setVisible(false);

        //delay = new Timer(5000, this);
        //delay.setRepeats(false);
        //delay.start();

    }
}