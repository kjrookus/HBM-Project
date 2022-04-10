package hbmpack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**********************************************************
 * Opens a GUI that displays the Homepage which allows for
 * users to input a name to either create a profile, or
 * pull one up from the userbase. Additionally allows the
 * user to navigate to the other games.
 *
 * @author Kaden Rookus & Alex Bergers
 * @version 02/20/2022
 * **********************************************************/

public class HomePage extends JFrame implements ActionListener {

    // create frame for the home page
    JFrame HomePage = new JFrame();

    // create buttons for each page
    JButton verb_mem_but;
    JButton num_mem_but;
    JButton type_speed_but;
    JButton reaction_time_but;
    JButton sequence_mem_but;
    JButton chimpTestBut;
    private Profile current;
    private boolean userExists = false;
    Main.Helpbase UserDatabase = new Main.Helpbase();
    Profile[] userbase = UserDatabase.getUserbase();
    int usercount = UserDatabase.getUsercount();

    HomePage(Profile current_profile) {
        current = current_profile;
        // button for verb memory page
        verb_mem_but = new JButton(); //creates button
        verb_mem_but.setBounds(5, 200, 150, 50); // sets location and size of button
        verb_mem_but.setFocusable(false);
        verb_mem_but.setText("Verbal Memory");
        verb_mem_but.addActionListener(this);

        // button for number memory page
        num_mem_but = new JButton();
        num_mem_but.setBounds(205, 200, 150, 50);
        num_mem_but.setFocusable(false);
        num_mem_but.setText("Number Memory");
        num_mem_but.addActionListener(this);

        // button for typing speed page
        type_speed_but = new JButton();
        type_speed_but.setBounds(405, 200, 150, 50);
        type_speed_but.setFocusable(false);
        type_speed_but.setText("Typing Speed");
        type_speed_but.addActionListener(this);

        //reaction time button
        reaction_time_but = new JButton();
        reaction_time_but.setBounds(5,300,150,50);
        reaction_time_but.setFocusable(false);
        reaction_time_but.setText("Reaction Time");
        reaction_time_but.addActionListener(this);

        //sequence memory button
        sequence_mem_but = new JButton();
        sequence_mem_but.setBounds(205, 300, 150, 50);
        sequence_mem_but.setFocusable(false);
        sequence_mem_but.setText("Sequence Memory");
        sequence_mem_but.addActionListener(this);
        NameField(HomePage);

        HomePage.setTitle("Human BenchMark Test"); // Sets Title
        HomePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit Application
        HomePage.setLayout(null);
        HomePage.setSize(600, 600); //set dimension
        HomePage.setVisible(true); //make frame visible

        chimpTestBut = new JButton();
        chimpTestBut.setBounds(405,300,150,50);
        chimpTestBut.setFocusable(false);
        chimpTestBut.setText("Chimp Test");
        chimpTestBut.addActionListener(this);

        // add buttons for each separate page
        HomePage.add(verb_mem_but);
        HomePage.add(num_mem_but);
        HomePage.add(type_speed_but);
        HomePage.add(reaction_time_but);
        HomePage.add(sequence_mem_but);
        HomePage.add(chimpTestBut);

        // create icon for frame using HBT icon from website
        ImageIcon image = new ImageIcon("HBT.png");
        this.setIconImage(image.getImage());
    }


    //generates the name field and corresponding data
    final void NameField(JFrame Homepage) {
        //creates two labels and a textfield
        JLabel LabelName = new JLabel("Please enter your name: ");
        JLabel CurrentName = new JLabel("Current user: " + current.getName());
        JLabel TypeScore = new JLabel("WPM: " + current.getWpmScore());
        JLabel NumScore = new JLabel("Number Memory Score: " + current.getNumbScore());
        JLabel verbScore = new JLabel("Verbal Memory Score: " + current.getVerbScore());
        JLabel seqScore = new JLabel("Sequence Memory Score: " + current.getSeqScore());
        JLabel reactScore = new JLabel("Reaction Time Score: " + current.getReactScore() + "ms");
        JTextField EnterName = new JTextField();

        CurrentName.setFont(new Font("Bold", Font.BOLD, 18));
        //sets the bounds of the labels and textfield
        LabelName.setBounds(150, 400, 200, 25);
        EnterName.setBounds(300, 400, 150, 25);
        CurrentName.setBounds(150, 440, 300, 25);

        TypeScore.setBounds(405, 480, 200, 50);
        NumScore.setBounds(205, 480, 200, 50);
        verbScore.setBounds(5, 480, 200, 50);
        seqScore.setBounds(205, 500,200, 50);
        reactScore.setBounds(5,500,200,50);

        //implements an action listener to the textfield
        EnterName.addActionListener(e -> {
            //assumes new user
            userExists = false;
            //checks if the user is in the userbase
            for (int i = 0; i < usercount; i++) {
                if (Objects.equals(userbase[i].getName(), EnterName.getText())) {
                    current = userbase[i];
                    userExists = true;
                    System.out.println("same");
                }
            }
            if (!userExists) {
                current = new Profile(EnterName.getText());
                insert(current);
            }
            //resets the page but with current user
            EnterName.setText("");
            CurrentName.setText("Current user: " + current.getName());
            TypeScore.setText("WPM: " + current.getWpmScore());
            NumScore.setText("Number Memory Score: " + current.getNumbScore());
            verbScore.setText("Verbal Memory Score: " + current.getVerbScore());
            seqScore.setText("Sequence Memory Score: " + current.getSeqScore());
            reactScore.setText("Reaction time Score: " + current.getReactScore() + "ms");
            UserDatabase.setUserbase(userbase);
            UserDatabase.setUsercount(usercount);
        });
        //adds labels and textfield to homepage
        HomePage.add(LabelName);
        HomePage.add(EnterName);
        HomePage.add(CurrentName);
        HomePage.add(TypeScore);
        HomePage.add(NumScore);
        HomePage.add(verbScore);
        HomePage.add(seqScore);
        Homepage.add(reactScore);
    }

    /**********************************************************
     * Accepts a profile as a parameter and attempts to insert
     * the profile into the existing userbase. If the userbase is
     * currently full, it will create a new userbase double in size and
     * copy over existing profiles
     *
     * @param element profile to be added to userbase
     * **********************************************************/

    public void insert(Profile element) {
        if (userbase.length == usercount) {
            Profile[] newuserbase = new Profile[usercount * 2];
            System.arraycopy(userbase, 0, newuserbase, 0, usercount);
            userbase = newuserbase;
        }
        userbase[usercount++] = element;
    }

    /**********************************************************
     * Action listeners that allow the user to navigate games
     * via the buttons and insert a profile name via the provided
     * textfield.
     * **********************************************************/

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == verb_mem_but) {
            new VerbMemoryFront(current); // Verb memory page
            HomePage.dispose();
        }
        else if (e.getSource() == num_mem_but) {
            new NumMemory(current); // num memory page
            HomePage.dispose();
        }
        else if (e.getSource() == type_speed_but) {
            new TypeSpeed(current); // type Speed page
            HomePage.dispose();
        }
        else if (e.getSource()== reaction_time_but){
            new reactionTime(current);
            HomePage.dispose();
        }else if(e.getSource() == sequence_mem_but){
            new seqMemory(current);
            HomePage.dispose();
        }else if(e.getSource()==chimpTestBut){
            new chimpTest(current);
            HomePage.dispose();
        }
    }



    static class Profile {
        private final String Name;
        private int wpmScore = 0;
        private int VerbScore = 0;
        private int NumbScore = 0;
        private int seqScore = 0;
        private int react = 0;

        public int getSeqScore() {
            return seqScore;
        }

        public void setSeqScore(int seqScore) {
            this.seqScore = seqScore;
        }

        //profile constructor
        Profile(String UserName) {
            this.Name = UserName;
        }

        //getter and setter for profile name
        public String getName() {
            return Name;
        }

        //getter and setter for WPM score
        public int getWpmScore() {
            return wpmScore;
        }

        public void setWpmScore(int givenwpmScore) {
            this.wpmScore = givenwpmScore;
        }

        //getter and setter for Verbal memory Score
        public int getVerbScore() {
            return VerbScore;
        }

        public void setVerbScore(int verbScore) {
            VerbScore = verbScore;
        }
        //getter and setter for Number memory score

        public int getNumbScore() {
            return NumbScore;
        }

        public void setNumbScore(int numbScore) {
            NumbScore = numbScore;
        }

        public void setReactScore(int reactScore){react = reactScore;}

        public int getReactScore(){return react;}
    }
}