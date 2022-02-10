package hbmpack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumMemory extends JFrame implements ActionListener {

    JFrame NumMemory = new JFrame();
    JLabel label = new JLabel("Number Memory");
    JButton home_but;

    NumMemory(){
        home_but = new JButton();
        label.setBounds(0,0,1000,50);
        label.setFont(new Font(null, Font.PLAIN, 25));

        NumMemory.add(label);

        NumMemory.setTitle("Number Memory"); // Sets Title
        NumMemory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit Application
        NumMemory.setLayout(null);
        NumMemory.setSize(600,600); //set dimension
        NumMemory.setVisible(true); //make frame visible

        // add home button
        NumMemory.add(home_but);
        home_but.setBounds(300,0,150,50);// sets location and size of button
        home_but.setFocusable(false);
        home_but.setText("Return to Menu");
        home_but.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource()== home_but)
            NumMemory.dispose();
            new HomePage();
    }
}

