package hbmpack;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import com.github.dhiraj072.randomwordgenerator.RandomWordGenerator;

public class VerbMemoryBack extends VerbMemoryFront{

    VerbMemoryBack(HomePage.Profile current) {
        super(current);
    }

    static int count = 0;
    static boolean wordSeen = false;
    static int score = 0;
    static int lives = 3;
    public static String runVerbmem(String[] checked){
        String goalWord;
            //half the time generates a new word, half the time takes an existing word
            if((int)(Math.random()*2) % 2 == 0) {
                goalWord = wordGen(count, checked);
                checked[count] = goalWord;
                count++;
                wordSeen = false;
            }else{
                goalWord = checked[(int)(Math.random() *count)];
                wordSeen = true;
            }
        return goalWord;
    }
    public static final String wordGen(int count, String[] checked){
        String newWord;
        newWord = RandomWordGenerator.getRandomWord();
        for(int i = 0; i<count; i++){
            if(newWord == checked[i]){
                newWord = RandomWordGenerator.getRandomWord();
            }
        }
        return newWord;
    }

    public static void neweval(){
        if(!wordSeen){
            score++;
        }else{
            lives--;
            if(lives==0){
                System.out.println("end2");
            }
        }
    }

    public static void seeneval(){
        if(wordSeen){
            score++;
        }else{
            lives--;
            if(lives==0){
                System.out.println("end");
            }
        }
    }

}
