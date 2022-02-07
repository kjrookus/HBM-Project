package hbmpack;

import javax.swing.*;
import java.awt.*;

public class TypeSpeed {

    JFrame frame = new JFrame();
    JLabel label = new JLabel("Typing Speed.");
    TypeSpeed(){

        label.setBounds(0,0,1000,50);
        label.setFont(new Font(null, Font.PLAIN, 25));

        frame.add(label);

        frame.setTitle("Typing Speed"); // Sets Title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit Application
        frame.setLayout(null);
        frame.setSize(600,600); //set dimension
        frame.setVisible(true); //make frame visible
    }
}