package hbmpack;
// Add Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

// Home Page For HBM
public class HomePage extends JFrame implements ActionListener {

    // create frame for the home page
    JFrame HomePage = new JFrame();

    // create buttons for each page
    JButton verb_mem_but;
    JButton num_mem_but;
    JButton type_speed_but;
    private Profile current;
    private boolean userExists = false;
    Main.Helpbase UserDatabase = new Main.Helpbase();
    Profile[] userbase = UserDatabase.getUserbase();
    int usercount = UserDatabase.getUsercount();
    JLabel humanBench;

    HomePage(Profile current_profile){
        current = current_profile;

        // Label with Menu Title
        humanBench = new JLabel("Human Benchmark", SwingConstants.CENTER);
        humanBench.setBounds(50,50,500,100);
        humanBench.setFont((new Font("Serif", Font.PLAIN, 40)));
        HomePage.add(humanBench);
        // button for verb memory page
        verb_mem_but = new JButton();//creates button
        verb_mem_but.setBounds(20,200,150,50);// sets location and size of button
        verb_mem_but.setFocusable(false);
        verb_mem_but.setText("Verbal Memory");
        verb_mem_but.addActionListener(this);

        // button for number memory page
        num_mem_but = new JButton();
        num_mem_but.setBounds(220,200,150,50);
        num_mem_but.setFocusable(false);
        num_mem_but.setText("Number Memory");
        num_mem_but.addActionListener(this);

        // button for typing speed page
        type_speed_but = new JButton();
        type_speed_but.setBounds(420,200,150,50);
        type_speed_but.setFocusable(false);
        type_speed_but.setText("Typing Speed");
        type_speed_but.addActionListener(this);

        NameField(HomePage);

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


    //generates the name field
    void NameField(JFrame Homepage){
        //creates two labels and a textfield
        JLabel LabelName = new JLabel("Please enter your name: ");
        JLabel CurrentName = new JLabel("Current user: " + current.getName());
        JLabel TypeScore = new JLabel("WPM: " + current.getWpmScore());
        JLabel NumScore = new JLabel("Number Memory Score: " + current.getNumbScore());
        JTextField EnterName = new JTextField();

        CurrentName.setFont(new Font("Bold", Font.BOLD, 18));
        //sets the bounds of the labels and textfield
        LabelName.setBounds(150, 400, 200, 25);
        EnterName.setBounds(300, 400, 150, 25);
        CurrentName.setBounds(150, 440, 300, 25);
        TypeScore.setBounds(25, 500, 150, 50);
        NumScore.setBounds(25, 480,200,50 );

        //implements an action listener to the textfield
        EnterName.addActionListener(e -> {
            //assumes new user
            userExists = false;
            //checks if the user is in the userbase
            for(int i = 0; i < usercount; i++){
                if(Objects.equals(userbase[i].getName(), EnterName.getText())){
                    current = userbase[i];
                    userExists = true;
                    System.out.println("same");
                }
            }
            if(!userExists) {
                current = new Profile(EnterName.getText());
                insert(current);
            }
            //resets the page but with current user
            EnterName.setText("");
            CurrentName.setText("Current user: " + current.getName());
            TypeScore.setText("WPM: " + current.getWpmScore());
            NumScore.setText("Number Memory Score: " + current.getNumbScore());
            UserDatabase.setUserbase(userbase);
            UserDatabase.setUsercount(usercount);
        });
        //adds labels and textfield to homepage
        Homepage.add(LabelName);
        Homepage.add(EnterName);
        Homepage.add(CurrentName);
        Homepage.add(TypeScore);
        HomePage.add(NumScore);
    }

    public void insert(Profile element){
        if (userbase.length == usercount){
            Profile[] newuserbase = new Profile[usercount*2];
            System.arraycopy(userbase, 0, newuserbase, 0, usercount);
            userbase = newuserbase;
        }
        userbase[usercount++] = element;
    }
    // open windows when buttons are clocked
    public void actionPerformed(ActionEvent e){
        if (e.getSource()== verb_mem_but){
            new VerbMemory(current); // Verb memory page
            HomePage.dispose();
        }
        else if (e.getSource()== num_mem_but){
            new NumMemory(current); // num memory page
            HomePage.dispose();
        }
        else if (e.getSource()== type_speed_but){
            new TypeSpeed(current); // type Speed page
            HomePage.dispose();
        }
    }



    static class Profile {
        private final String Name;
        private int wpmScore = 0;
        private int VerbScore = 0;
        private int NumbScore = 0;
        //profile constructor
        public Profile(String Name){
            this.Name = Name;
        }
        //getter and setter for profile name
        public String getName() {
            return Name;
        }

        //getter and setter for WPM score
        public int getWpmScore() {
            return wpmScore;
        }
        public void setWpmScore(int wpmScore) {
            this.wpmScore = wpmScore;
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
    }
}