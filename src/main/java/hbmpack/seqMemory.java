package hbmpack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

public class seqMemory implements ActionListener{
    JFrame frame = new JFrame();
    private HomePage.Profile seqcurrent;

    JLabel title = new JLabel();
    JLabel subtitle = new JLabel();
    JButton[] grid = new JButton[9];
    JLabel scorelabel = new JLabel();
    JLabel lifeLabel = new JLabel();
    JButton home_but;
    JButton start_game;
    Queue<Integer> memoryq = new LinkedList<>();
    Queue<Integer> tempmemoryq2;
    int lives = 3;
    int score = 0;
    boolean running = false;
    JButton clicked;

    seqMemory(HomePage.Profile current_profile){
        seqcurrent = current_profile;


        frame.setTitle("Sequence Memory"); // Sets Title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit Application
        frame.setLayout(null);
        frame.setSize(600, 600); //set dimension
        frame.setVisible(true); //make frame visible

        gameinstructions();
        setButtons();
    }

    private void gameinstructions(){
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
    public final void setButtons() {

        //home button
        home_but = new JButton();
        frame.add(home_but);
        home_but.setBounds(225, 475, 150, 50);// sets location and size of button
        home_but.setFocusable(false);
        home_but.setText("Return to Menu");
        home_but.addActionListener(this);

        // start button begins game
        start_game = new JButton("start");
        start_game.setBounds(225, 350, 150, 50);
        start_game.addActionListener(this);
        start_game.setVisible(true);
        frame.add(start_game);
    }

    public void cleartitlescreen(){
        home_but.setVisible(false);
        start_game.setVisible(false);
        title.setVisible(false);
        subtitle.setVisible(false);
    }
    public void createscorelives(){

        scorelabel.setHorizontalAlignment(SwingConstants.CENTER);
        scorelabel.setBounds(150, 60, 125, 100);
        scorelabel.setFont(new Font(null, Font.PLAIN, 25));
        scorelabel.setText("Score: ");
        scorelabel.setVisible(true);

        lifeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lifeLabel.setBounds(300, 60, 125, 100);
        lifeLabel.setFont(new Font(null, Font.PLAIN, 25));
        lifeLabel.setText("Lives: " + lives);
        lifeLabel.setVisible(true);

        frame.add(scorelabel);
        frame.add(lifeLabel);
    }
    private void setscorelabel(int score){
        scorelabel.setText("Score: " + score);

    }
    private void loselife(){
        lives--;
        lifeLabel.setText("Lives: " + lives);
    }
    protected void creategrid(){
        for(int i =0; i<grid.length; i++) {
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

    public void actionPerformed(ActionEvent e){
        if (e.getSource()== home_but) {
            frame.dispose();
            new HomePage(seqcurrent);
        }else if(e.getSource() == start_game){
            cleartitlescreen();
            creategrid();
            createscorelives();
            addtoseq();
            showsequence();
        }else{
            clicked = (JButton) e.getSource();
            if(!running) {
                tempmemoryq2 = new LinkedList<>(memoryq);
                running = true;
            }
            boolean add = false;
            add = compareseq();
            //if they got one wrong
            if(!add){
                //resets sequence and shows again
                running = false;
                showsequence();
            }
            //if they get them all right.
            if(tempmemoryq2.size() == 0) {
                if (add) {
                    addtoseq();
                }
                running = false;
                showsequence();
            }
        }
    }

    private boolean compareseq(){
            if(clicked == grid[tempmemoryq2.peek()]){
                tempmemoryq2.remove();
                score++;
                setscorelabel(score);
                return true;
            }else{
                loselife();
                return false;
            }

    }

    private void showsequence(){
        Queue<Integer> tempmemoryq = new LinkedList<>(memoryq);

        //prevents the grid from being clicked while showing sequence
        for(int i =0; i<grid.length; i++) {
            grid[i].setEnabled(false);
        }
        //black out returns tiles to black 1 second after being blue
        final int[] selectedtile = {tempmemoryq.peek()};
            Timer blackout = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    grid[selectedtile[0]].setBackground(Color.black);
                    System.out.println("end");
                    if(tempmemoryq.size() ==0){
                        System.out.println("size is 0");
                        for(int i =0; i<grid.length; i++) {
                            grid[i].setEnabled(true);
                        }
                    }
                }
            });
            //blue out turns the first tile in queue to blue
            Timer blueout = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tempmemoryq.size() == 0){
                    ((Timer)e.getSource()).setRepeats(false);
                    ((Timer)e.getSource()).stop();
                    return;
                }else {
                    System.out.println("size is anything but zero");
                    selectedtile[0] = tempmemoryq.remove();
                    grid[selectedtile[0]].setBackground(Color.blue);
                    blackout.start();
                }
                }
            });
            //allow blueout to be repeated for the entire queue
            blueout.setRepeats(true);
            //blackout should only occur once, after each blueout
            blackout.setRepeats(false);
            //begins the color sequence
            blueout.start();
        }

    private void addtoseq(){
        int temp = (int)(Math.random()*9);
        memoryq.add(temp);
    }
}
