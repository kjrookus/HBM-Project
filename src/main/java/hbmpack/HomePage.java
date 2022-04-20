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
    private final JFrame homePage = new JFrame();

    // create buttons for each page
    private final JButton verbmembut;
    private final JButton nummembut;
    private final JButton typespeedbut;
    private final JButton reactiontimebut;
    private final JButton sequencemembut;
    private final JButton chimpTestBut;
    private Profile current;
    private boolean userExists = false;
    private final Main.Helpbase userDatabase = new Main.Helpbase();

    private Profile[] userbase = userDatabase.getUserbase();

    private int usercount = userDatabase.getUsercount();

    /***********************************
     * getter for the userbase variable
     * @return returns the userbase
     **********************************/
    public Profile[] getUserbase() {
        return userbase;
    }

    /***************************************
     * getter for the number of current users
     * @return number of current users
     ***************************************/
    public int getUsercount() {
        return usercount;
    }

    HomePage(Profile currentProfile) {
        current = currentProfile;
        // button for verb memory page
        verbmembut = new JButton(); //creates button
        verbmembut.setBounds(5, 200, 150, 50); // sets location and size of button
        verbmembut.setFocusable(false);
        verbmembut.setText("Verbal Memory");
        verbmembut.addActionListener(this);

        // button for number memory page
        nummembut = new JButton();
        nummembut.setBounds(205, 200, 150, 50);
        nummembut.setFocusable(false);
        nummembut.setText("Number Memory");
        nummembut.addActionListener(this);

        // button for typing speed page
        typespeedbut = new JButton();
        typespeedbut.setBounds(405, 200, 150, 50);
        typespeedbut.setFocusable(false);
        typespeedbut.setText("Typing Speed");
        typespeedbut.addActionListener(this);

        //reaction time button
        reactiontimebut = new JButton();
        reactiontimebut.setBounds(5,300,150,50);
        reactiontimebut.setFocusable(false);
        reactiontimebut.setText("Reaction Time");
        reactiontimebut.addActionListener(this);

        //sequence memory button
        sequencemembut = new JButton();
        sequencemembut.setBounds(205, 300, 150, 50);
        sequencemembut.setFocusable(false);
        sequencemembut.setText("Sequence Memory");
        sequencemembut.addActionListener(this);
        nameField();

        homePage.setTitle("Human BenchMark Test"); // Sets Title
        homePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit Application
        homePage.setLayout(null);
        homePage.setSize(600, 600); //set dimension
        homePage.setVisible(true); //make frame visible

        chimpTestBut = new JButton();
        chimpTestBut.setBounds(405,300,150,50);
        chimpTestBut.setFocusable(false);
        chimpTestBut.setText("Chimp Test");
        chimpTestBut.addActionListener(this);

        // add buttons for each separate page
        homePage.add(verbmembut);
        homePage.add(nummembut);
        homePage.add(typespeedbut);
        homePage.add(reactiontimebut);
        homePage.add(sequencemembut);
        homePage.add(chimpTestBut);

        // create icon for frame using HBT icon from website
        ImageIcon image = new ImageIcon("HBT.png");
        this.setIconImage(image.getImage());
    }


    //generates the name field and corresponding data
    final void nameField() {
        //creates two labels and a textfield
        JLabel labelName = new JLabel("Please enter your name: ");
        JLabel currentName = new JLabel("Current user: " + current.getName());
        JLabel typeScore = new JLabel("WPM: " + current.getWpmScore());
        JLabel numScore = new JLabel("Number Memory Score: " + current.getNumbScore());
        JLabel verbScore = new JLabel("Verbal Memory Score: " + current.getVerbScore());
        JLabel seqScore = new JLabel("Sequence Memory Score: " + current.getSeqScore());
        JLabel reactScore = new JLabel("Reaction Time Score: " + current.getReactScore() + "ms");
        JLabel chimpScore = new JLabel("Chimp Score: " + current.getChimp());
        JTextField enterName = new JTextField();

        currentName.setFont(new Font("Bold", Font.BOLD, 18));
        //sets the bounds of the labels and textfield
        labelName.setBounds(150, 400, 200, 25);
        enterName.setBounds(300, 400, 150, 25);
        currentName.setBounds(150, 440, 300, 25);

        typeScore.setBounds(405, 480, 200, 50);
        numScore.setBounds(205, 480, 200, 50);
        verbScore.setBounds(5, 480, 200, 50);
        seqScore.setBounds(205, 500,200, 50);
        reactScore.setBounds(5,500,200,50);
        chimpScore.setBounds(405,500,200,50);

        //implements an action listener to the textfield
        enterName.addActionListener(e -> {
            //assumes new user
            userExists = false;
            //checks if the user is in the userbase
            for (int i = 0; i < usercount; i++) {
                if (Objects.equals(userbase[i].getName(), enterName.getText())) {
                    current = userbase[i];
                    userExists = true;
                    System.out.println("same");
                }
            }
            if (!userExists) {
                current = new Profile(enterName.getText());
                insert(current);
            }
            //resets the page but with current user
            enterName.setText("");
            currentName.setText("Current user: " + current.getName());
            typeScore.setText("WPM: " + current.getWpmScore());
            numScore.setText("Number Memory Score: " + current.getNumbScore());
            verbScore.setText("Verbal Memory Score: " + current.getVerbScore());
            seqScore.setText("Sequence Memory Score: " + current.getSeqScore());
            reactScore.setText("Reaction Time Score: " + current.getReactScore() + "ms");
            chimpScore.setText("Best Chimp Round: " + current.getChimp());
            userDatabase.setUserbase(userbase);
            userDatabase.setUsercount(usercount);
        });
        //adds labels and textfield to homepage
        homePage.add(labelName);
        homePage.add(enterName);
        homePage.add(currentName);
        homePage.add(typeScore);
        homePage.add(numScore);
        homePage.add(verbScore);
        homePage.add(seqScore);
        homePage.add(reactScore);
        homePage.add(chimpScore);
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
        if (e.getSource() == verbmembut) {
            new VerbMemoryFront(current); // Verb memory page
            homePage.dispose();
        }
        else if (e.getSource() == nummembut) {
            new NumMemory(current); // num memory page
            homePage.dispose();
        }
        else if (e.getSource() == typespeedbut) {
            new TypeSpeed(current); // type Speed page
            homePage.dispose();
        }
        else if (e.getSource()== reactiontimebut){
            new reactionTime(current);
            homePage.dispose();
        }else if(e.getSource() == sequencemembut){
            new SeqMemory(current);
            homePage.dispose();
        }else if(e.getSource()==chimpTestBut){
            new chimpTest(current);
            homePage.dispose();
        }
    }



    static class Profile {
        private final String name;
        private int wpmScore = 0;
        private int verbScore = 0;
        private int numbScore = 0;
        private int seqScore = 0;
        private int react = 0;
        private int chimpScore = 0;

        public int getSeqScore() {
            return seqScore;
        }

        public void setSeqScore(int newseqScore) {
            this.seqScore = newseqScore;
        }


        //profile constructor
        Profile(String username) {
            this.name = username;
        }

        //getter and setter for profile name
        public String getName() {
            return name;
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
            return verbScore;
        }

        public void setVerbScore(int newverbScore) {
            this.verbScore = newverbScore;
        }
        //getter and setter for Number memory score

        public int getNumbScore() {
            return numbScore;
        }

        public void setNumbScore(int newnumbScore) {
            this.numbScore = newnumbScore;
        }

        public void setReactScore(int reactScore){react = reactScore;}

        public int getReactScore(){return react;}

        public void setChimp(int score){chimpScore = score;}

        public int getChimp(){return chimpScore;}

    }

}