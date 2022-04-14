package hbmpack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class reactionTime extends JFrame implements ActionListener {
    static JFrame reactionTime = new JFrame();
    JLabel title = new JLabel();
    JLabel subtitle = new JLabel();
    JLabel click = new JLabel();
    JButton start = new JButton("Start");
    JButton home_but = new JButton();
    JButton redBut = new JButton();
    JPanel panel = new JPanel();
    Timer delay;
    JLabel results = new JLabel();

    int elapsedTime;
    boolean test;

    HomePage.Profile reactCurrent;

    public reactionTime(HomePage.Profile current) {

        reactCurrent = current;

        this.setTitle("Reaction Time"); // Sets Title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit Application
        this.setLayout(null);
        this.setSize(600, 600); //set dimension
        this.setVisible(true); //make frame visible

        panel.setBounds(0,0,600,600);
        panel.setVisible(false);
        panel.setLayout(null);
        this.add(panel);

        results.setVisible(false);
        results.setFont((new Font("Serif", Font.PLAIN, 25)));
        this.add(results);

        title.setText("Reaction Time Test");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(0, 150, 600, 100);
        title.setFont(new Font(null, Font.PLAIN, 40));
        title.setVisible(true);
        this.add(title);

        subtitle.setText("When the red box turns green, click as quickly as you can!");
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
        home_but.setBounds(220, 475, 150, 50); // sets location and size of button
        home_but.setFocusable(false);
        home_but.setText("Return to Menu");
        home_but.addActionListener(this);

    }
    Timer timer = new Timer(1, e -> elapsedTime+=1);

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == home_but) {
            new HomePage(reactCurrent);
            reactionTime.dispose();
        }
        if (e.getSource() == start){
            theGame();
        }
        if (e.getSource() == redBut) {
            if (!test) {
                results.setVisible(true);
                panel.setVisible(false);
                start.setText("Try Again");
                start.setVisible(true);
                home_but.setVisible(true);
                results.setBounds(100,200,600,100);
                results.setText("Too Soon! Press Try Again to restart!");
            }
            if (test) {
                timer.stop();
                panel.setVisible(false);
                results.setVisible(true);
                start.setText("Try Again");
                start.setVisible(true);
                home_but.setVisible(true);
                reactCurrent.setReactScore(elapsedTime);
                results.setBounds(240,200,600,100);
                results.setText("Time: " + elapsedTime + "ms");
            }
        }
        if (e.getSource() == delay){
            redBut.setBackground(Color.GREEN);
            test = true;
            timer.start();
        }
    }

    public void theGame(){
        redBut.setBounds(0,0,600,600);
        redBut.setVisible(false);
        redBut.setFocusable(true);
        redBut.setOpaque(true);
        redBut.setBackground(Color.red);
        redBut.setBorderPainted(false);
        redBut.addActionListener(this);
        panel.add(redBut);
        redBut.setVisible(true);
        results.setBounds(50,200,600,100);
        panel.setVisible(true);
        start.setVisible(false);
        click.setVisible(false);
        title.setVisible(false);
        subtitle.setVisible(false);
        home_but.setVisible(false);

        elapsedTime = 0;
        test= false;
        int randomDelay = 3000 + (int)(Math.random()*((10000 - 3000)+1));
        delay = new Timer(randomDelay, this);
        delay.setRepeats(true);
        delay.start();
    }

}