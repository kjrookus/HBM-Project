package hbmpack;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**********************************************************
 * Controls the logic and generation of the Verbal memory game
 * Serves to randomly generate words and evaluate if the player
 * still has lives.
 *
 * @author Kaden Rookus & Alex Bergers
 * @version 02/20/2022
 * **********************************************************/

public class VerbMemoryBack extends VerbMemoryFront{

    /*****************************************
     * overloaded constructor
     * @param current current profile in use
     ****************************************/
    VerbMemoryBack(HomePage.Profile current) {
        super(current);
    }

    private static int count = 0;
    private static boolean wordSeen = false;
    private static int score = 0;
    private static int lives = 3;

    //getters and setters for the above variables
    public static int getCount() {
        return count;
    }
    public static void setCount(int newcount) {
        VerbMemoryBack.count = newcount;
    }
    public static int getScore() {
        return score;
    }
    public static void setScore(int newscore) {
        VerbMemoryBack.score = newscore;
    }
    public static int getLives() {
        return lives;
    }
    public static void setLives(int newlives) {
        VerbMemoryBack.lives = newlives;
    }

    /**************************************************
     * handles the generating of the word for the game
     * chooses randomly between seen and new words
     *
     * @param checked array containing seen words
     * @return a String containing the word
     *************************************************/
    public static String runVerbmem(String[] checked){
        String goalWord;
            //half the time generates a new word, half the time takes an existing word
            if(((int)(Math.random()*2) % 2 == 0) || count == 0) {
                goalWord = wordgen();
                checked[count] = goalWord;
                count++;
                wordSeen = false;
            }else{
                goalWord = checked[(int)(Math.random() *count)];
                wordSeen = true;
            }
            System.out.println(goalWord);
        return goalWord;
    }

    /************************************************************
     * If the player selects new, evaluates
     * whether or not they're correct and
     * adjusts the score and lives.
     *
     * @return a boolean based on if the player still has lives
     ************************************************************/
    public static boolean neweval(){
        if(!wordSeen){
            score++;
            return true;
        }else{
            lives--;
            return lives != 0;
        }
    }

    /**********************************************************
     * If the player selects seen, evaluates
     * whether or not they're correct and
     * adjusts the score and lives.
     *
     * @return boolean based on if the player still has lives
     **********************************************************/
    public static boolean seeneval(){
        if(wordSeen){
            score++;
            return true;
        }else{
            lives--;
            return lives != 0;
        }
    }

    /**************************************
     * Generates a random word based on
     * a locally stored txt file, designed
     * not to follow the same pattern each
     * time the game is played
     *
     * @return a string containing a random word
     ***************************************/
    public static String wordgen(){
        try{
            FileInputStream fis = new FileInputStream("src/main/java/hbmpack/1-1000.txt");
            Scanner scan = new Scanner(fis);
            for(int i = 1; i< Math.random()*1000; i++){
                scan.nextLine();
            }
            if(scan.hasNextLine()){
                return scan.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
