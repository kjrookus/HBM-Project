package hbmpack;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalTime;
import javax.swing.*;

public class seqMemory implements ActionListener{
    JFrame frame = new JFrame();
    private HomePage.Profile seqcurrent;

    JLabel title = new JLabel();
    JLabel subtitle = new JLabel();

    JButton home_but;
    JButton start_game;
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
    public void actionPerformed(ActionEvent e){
        if (e.getSource()== home_but) {

           frame.dispose();
           new HomePage(seqcurrent);
        }
    }
}
