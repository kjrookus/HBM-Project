package hbmpack;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VerbMemoryFront extends JFrame implements ActionListener {
    JFrame VerbMemory = new JFrame();

    JLabel scorelabel = new JLabel();
    JLabel lifeLabel = new JLabel();
    JLabel title = new JLabel();
    JTextArea subtitle = new JTextArea();

    JButton home_but;
    JButton start_game;
    JButton seen_but;
    JButton new_but;
    JButton tryagain_but;
    JLabel goalword = new JLabel();
    HomePage.Profile verbcurrent;

    VerbMemoryFront(HomePage.Profile current){
        verbcurrent = current;

        setLabels();
        setButtons();
        VerbMemoryBack.score = 0;
        VerbMemoryBack.lives = 3;
        VerbMemoryBack.count = 0;
        updatescorelives();

        VerbMemory.setTitle("Verbal Memory"); // Sets Title
        VerbMemory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit Application
        VerbMemory.setLayout(null);
        VerbMemory.setSize(600,600); //set dimension
        VerbMemory.setVisible(true); //make frame visible


    }
    private void setLabels(){
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


        subtitle.setText("You will be shown words, one at a time. If you've seen a word during the test, click SEEN. If it's a new word, click NEW.");
        subtitle.setLineWrap(true);
        subtitle.setWrapStyleWord(true);
        subtitle.setOpaque(false);
        subtitle.setFocusable(false);
        subtitle.setEditable(false);
        subtitle.setBackground(UIManager.getColor("Label.background"));
        subtitle.setFont(UIManager.getFont("Label.font"));
        subtitle.setBorder(UIManager.getBorder("Label.border"));
        subtitle.setBounds(0, 200, 600, 150);
        subtitle.setFont(new Font(null, Font.PLAIN, 20));


        VerbMemory.add(goalword);
        VerbMemory.add(scorelabel);
        VerbMemory.add(lifeLabel);
        VerbMemory.add(title);
        VerbMemory.add(subtitle);
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
        start_game.setBounds(225, 350, 150, 50);
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

        tryagain_but = new JButton("Try Again?");
        tryagain_but.setBounds(225, 425, 150, 50);
        tryagain_but.addActionListener(this);
        tryagain_but.setVisible(false);
        VerbMemory.add(tryagain_but);

    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource()== home_but) {

            VerbMemory.dispose();
            new HomePage(verbcurrent);
        }
        if(e.getSource() == start_game){
           frontGame();
            run();
        }
        if(e.getSource() == new_but){
            if(!VerbMemoryBack.neweval()){
                updatescorelives();
                endgame();
            }else {
                updatescorelives();
                run();
            }
        }
        if(e.getSource() == seen_but){
            if(!VerbMemoryBack.seeneval()){
                updatescorelives();
                endgame();
            }else {
                updatescorelives();
                run();
            }
        }
        if(e.getSource() == tryagain_but){
            verbcurrent.setVerbScore(VerbMemoryBack.score);
            VerbMemory.dispose();
            new VerbMemoryFront(verbcurrent);
        }
    }

    private void updatescorelives(){
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
        title.setVisible(false);
        subtitle.setVisible(false);
        scorelabel.setVisible(true);
        lifeLabel.setVisible(true);
        seen_but.setVisible(true);
        new_but.setVisible(true);
        start_game.setVisible(false);
        goalword.setVisible(true);
        home_but.setVisible(false);
    }
    public void run(){
        String currentword = VerbMemoryBack.runVerbmem(checked);
        //add word
        count = VerbMemoryBack.count;
        checked[count] = currentword;
        goalword.setText(currentword);
    }
    private void endgame(){
        verbcurrent.setVerbScore(VerbMemoryBack.score);
        title.setVisible(true);
        lifeLabel.setVisible(false);
        scorelabel.setBounds(0, 200, 600, 100);
        scorelabel.setFont(new Font(null, Font.PLAIN, 40));
        seen_but.setVisible(false);
        new_but.setVisible(false);
        goalword.setVisible(false);
        home_but.setVisible(true);
        tryagain_but.setVisible(true);
    }
}

