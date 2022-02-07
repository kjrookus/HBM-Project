package hbmpack;
// Add Imports
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Home Page For HBM
public class HomePage extends JFrame implements ActionListener {

    // create frame for the home page
    JFrame HomePage = new JFrame();

    // create buttons for each page
    JButton verb_mem_but;
    JButton num_mem_but;
    JButton type_speed_but;
    HomePage(){
        // button for verb memory page
        verb_mem_but = new JButton();//creates button
        verb_mem_but.setBounds(5,100,150,50);// sets location and size of button
        verb_mem_but.setFocusable(false);
        verb_mem_but.setText("Verbal Memory");
        verb_mem_but.addActionListener(this);

        // button for number memory page
        num_mem_but = new JButton();
        num_mem_but.setBounds(205,100,150,50);
        num_mem_but.setFocusable(false);
        num_mem_but.setText("Number Memory");
        num_mem_but.addActionListener(this);

        // button for typing speed page
        type_speed_but = new JButton();
        type_speed_but.setBounds(405,100,150,50);
        type_speed_but.setFocusable(false);
        type_speed_but.setText("Typing Speed");
        type_speed_but.addActionListener(this);

        HomePage.setTitle("Human BenchMark Test"); // Sets Title
        HomePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit Application
        HomePage.setLayout(null);
        HomePage.setSize(600,600); //set dimension
        HomePage.setVisible(true); //make frame visible

        // add buttons for each separate page
        HomePage.add(verb_mem_but);
        HomePage.add(num_mem_but);
        HomePage.add(type_speed_but);

        ImageIcon image = new ImageIcon("HBT.png"); // create icon for frame using HBT icon from website
        this.setIconImage(image.getImage()); //set the icon to the frame

    }
    // open windows when buttons are clocked
    public void actionPerformed(ActionEvent e){
        if (e.getSource()== verb_mem_but){
            HomePage.dispose();
            new VerbMemory(); // Verb memory page
        }
        else if (e.getSource()== num_mem_but){
            HomePage.dispose();
            new NumMemory(); // num memory page
        }
        else if (e.getSource()== type_speed_but){
            HomePage.dispose();
            new TypeSpeed(); // num memory page
        }
    }
}