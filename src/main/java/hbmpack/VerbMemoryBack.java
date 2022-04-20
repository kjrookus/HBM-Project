package hbmpack;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VerbMemoryBack extends VerbMemoryFront{

    VerbMemoryBack(HomePage.Profile current) {
        super(current);
    }

    private static int count = 0;
    private static boolean wordSeen = false;
    private static int score = 0;
    private static int lives = 3;

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
    public static boolean neweval(){
        if(!wordSeen){
            score++;
            return true;
        }else{
            lives--;
            return lives != 0;
        }
    }

    public static boolean seeneval(){
        if(wordSeen){
            score++;
            return true;
        }else{
            lives--;
            return lives != 0;
        }
    }

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
