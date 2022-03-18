package hbmpack;

import com.github.dhiraj072.randomwordgenerator.RandomWordGenerator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VerbMemoryFront extends JFrame implements ActionListener {
    JFrame VerbMemory = new JFrame();
    JLabel label = new JLabel("Verbal Memory");

    JLabel scorelabel = new JLabel("");
    JLabel lifeLabel = new JLabel("");

    JButton home_but;
    JButton start_game;
    JButton seen_but;
    JButton new_but;

    JLabel goalword = new JLabel();
    HomePage.Profile verbcurrent;

    VerbMemoryFront(HomePage.Profile current){
        verbcurrent = current;
        setLabels();
        setButtons();
        updatescorelives();

        VerbMemory.setTitle("Verbal Memory"); // Sets Title
        VerbMemory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit Application
        VerbMemory.setLayout(null);
        VerbMemory.setSize(600,600); //set dimension
        VerbMemory.setVisible(true); //make frame visible


    }
    private final void setLabels(){

        label.setBounds(0,0,1000,50);
        label.setFont(new Font(null, Font.PLAIN, 25));

        goalword.setBounds(220, 250, 200, 100);
        goalword.setFont(new Font(null, Font.PLAIN, 25));

        scorelabel.setBounds(175, 125, 125, 100);
        scorelabel.setFont(new Font(null, Font.PLAIN, 25));

        lifeLabel.setBounds(300, 125, 125, 100);
        lifeLabel.setFont(new Font(null, Font.PLAIN, 25));

        VerbMemory.add(label);
        VerbMemory.add(goalword);
        VerbMemory.add(scorelabel);
        VerbMemory.add(lifeLabel);
    }

    public final void setButtons(){

        //home button
        home_but = new JButton();
        VerbMemory.add(home_but);
        home_but.setBounds(225, 475, 150, 50);// sets location and size of button
        home_but.setFocusable(false);
        home_but.setText("Return to Menu");
        home_but.addActionListener(this);

        // start button begins game
        start_game = new JButton("start");
        start_game.setBounds(225, 250, 150, 50);
        start_game.addActionListener(this);
        start_game.setVisible(true);
        VerbMemory.add(start_game);

        seen_but = new JButton("Seen");
        seen_but.setBounds(200, 400, 100, 75);
        seen_but.addActionListener(this);
        seen_but.setVisible(false);
        VerbMemory.add(seen_but);

        new_but = new JButton("New");
        new_but.setBounds(300, 400, 100, 75);
        new_but.addActionListener(this);
        new_but.setVisible(false);
        VerbMemory.add(new_but);


    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource()== home_but) {
            VerbMemory.dispose();
            new HomePage(verbcurrent);
        }
        if(e.getSource() == start_game){
           frontGame();
        }
        if(e.getSource() == new_but){
            VerbMemoryBack.neweval();
            updatescorelives();
            run();
        }
        if(e.getSource() == seen_but){
            VerbMemoryBack.seeneval();
            updatescorelives();
            run();
        }
    }

    private final void updatescorelives(){
        scorelabel.setText("Score: " + VerbMemoryBack.score);
        lifeLabel.setText("Lives: " + VerbMemoryBack.lives);
    }

    boolean gamerunning = false;
    String goalWord;
    String[] checked = new String[100];
    public static int count = 0;
    public boolean wordSeen = false;

    public final void frontGame(){
        gamerunning = true;
        seen_but.setVisible(true);
        new_but.setVisible(true);
        start_game.setVisible(false);
        goalword.setVisible(true);
        home_but.setVisible(false);
        run();
    }
    public void run(){
        String currentword = VerbMemoryBack.runVerbmem(checked);
        //add word
        count = VerbMemoryBack.count;
        checked[count] = currentword;
        goalword.setText(currentword);
    }
}

