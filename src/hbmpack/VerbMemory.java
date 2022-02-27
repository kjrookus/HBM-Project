package hbmpack;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VerbMemory extends JFrame implements ActionListener {
    JFrame VerbMemory = new JFrame();
    JLabel label = new JLabel("Verbal Memory");
    JButton home_but;
    HomePage.Profile verbcurrent;
    VerbMemory(HomePage.Profile current){
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
        home_but.setBounds(300,0,150,50);// sets location and size of button
        home_but.setFocusable(false);
        home_but.setText("Return to Menu");
        home_but.addActionListener(this);
    }

    public final void actionPerformed(ActionEvent e){
        if (e.getSource()== home_but)
            VerbMemory.dispose();
            new HomePage(verbcurrent);
    }
}

