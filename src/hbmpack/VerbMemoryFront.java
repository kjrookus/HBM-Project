package hbmpack;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VerbMemoryFront extends JFrame implements ActionListener {
    JFrame VerbMemory = new JFrame();
    JLabel label = new JLabel("Verbal Memory");
    JButton home_but;
    JButton start_game;
    HomePage.Profile verbcurrent;
    boolean gamerunning = false;
    String goalWord;
    int score;


    VerbMemoryFront(HomePage.Profile current){
        verbcurrent = current;

        label.setBounds(0,0,1000,50);
        label.setFont(new Font(null, Font.PLAIN, 25));

        VerbMemory.add(label);

        VerbMemory.setTitle("Verbal Memory"); // Sets Title
        VerbMemory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit Application
        VerbMemory.setLayout(null);
        VerbMemory.setSize(600,600); //set dimension
        VerbMemory.setVisible(true); //make frame visible

        home_but = new JButton();
        VerbMemory.add(home_but);
        home_but.setBounds(220, 475, 150, 50);// sets location and size of button
        home_but.setFocusable(false);
        home_but.setText("Return to Menu");
        home_but.addActionListener(this);

        // start button begins game
        start_game = new JButton("start");
        start_game.setBounds(220, 250, 150, 50);
        start_game.addActionListener(this);
        start_game.setVisible(true);
        VerbMemory.add(start_game);

    }

    public final void actionPerformed(ActionEvent e){
        if (e.getSource()== home_but) {
            VerbMemory.dispose();
            new HomePage(verbcurrent);
        }
        if(e.getSource() == start_game){
            gamerunning = true;
            VerbMemoryBack.startVerbmem();
        }


    }
}

