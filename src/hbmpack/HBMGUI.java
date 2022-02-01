package hbmpack;

import javax.swing.*;
import java.awt.*;

public class HBMGUI {

    public static void main(String[] arg){
        //dimensions for the GUI
        Dimension GUIsize = new Dimension(800, 800);


        //constructor
        JFrame gui = new JFrame();
        //tells the GUI to close when exited
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //titles the GUI
        gui.setTitle("The Human Benchmark Test");
        //sets the dimensions of the GUI
        gui.setMinimumSize(GUIsize);
        gui.pack();

class Bpanel extends JPanel{
    int rows = 4;
    int cols =4;
    JButton b;
    public Bpanel(){
        gui.setLayout(new GridLayout(rows, cols));
        for(int row =0; row<rows; row++){
            for(int col=0; col<cols; col++){
                b = new JButton(row + "," + col);
                gui.add(b).setLocation(row,col);
            }
        }
    }
}
Bpanel hi = new Bpanel();



/*       gui.setLayout(new GridLayout(4, 4));
        JTextField hundredField;

        JButton b;
        b = new JButton("Game 1");
        JButton c = new JButton("Game 2");
        JButton d = new JButton("Game 3");
        JButton e = new JButton("Game 4");
        gui.add(b).setLocation(1,0);
        gui.add(c).setLocation(1,1);
        gui.add(d).setLocation(1,2);
        gui.add(e).setLocation(11,11);


        panel.add(new JButton("Game 1"));
        panel.add(new JButton("Game 2"));
        panel.add(new JButton("Game 3"));
        panel.add(new JButton("Game 4"));
        panel.add(new JButton("Game 5"));
        panel.add(new JButton("Game 6"));

         */

        //lets you see the GUI
        gui.pack();
        gui.setVisible(true);
    }
}