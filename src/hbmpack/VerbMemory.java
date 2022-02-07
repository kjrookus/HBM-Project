package hbmpack;
import javax.swing.*;
import java.awt.*;

public class VerbMemory {
    JFrame VerbMemory = new JFrame();
    JLabel label = new JLabel("Verbal Memory");
    VerbMemory(){

        label.setBounds(0,0,1000,50);
        label.setFont(new Font(null, Font.PLAIN, 25));

        VerbMemory.add(label);

        VerbMemory.setTitle("Verbal Memory"); // Sets Title
        VerbMemory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit Application
        VerbMemory.setLayout(null);
        VerbMemory.setSize(600,600); //set dimension
        VerbMemory.setVisible(true); //make frame visible
    }
}

