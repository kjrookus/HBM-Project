package hbmpack;
import javax.swing.*;
import java.awt.*;

public class NumMemory {

    JFrame NumMemory = new JFrame();
    JLabel label = new JLabel("Number Memory");
    NumMemory(){

        label.setBounds(0,0,1000,50);
        label.setFont(new Font(null, Font.PLAIN, 25));

        NumMemory.add(label);

        NumMemory.setTitle("Number Memory"); // Sets Title
        NumMemory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit Application
        NumMemory.setLayout(null);
        NumMemory.setSize(600,600); //set dimension
        NumMemory.setVisible(true); //make frame visible
    }
}

