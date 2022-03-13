package hbmpack;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import com.github.dhiraj072.randomwordgenerator.RandomWordGenerator;

public class VerbMemoryBack extends VerbMemoryFront implements ActionListener{

    VerbMemoryBack(HomePage.Profile current) {
        super(current);
    }
    public static void startVerbmem(){
        String goalWord;
        boolean continuegame = true;
        String[] checked = new String[100];
        int count = 0;
        boolean wordSeen;

        while(continuegame){
            //half the time generates a new word, half the time takes an existing word
            if((Math.random()*2) % 2 == 0) {
                goalWord = wordGen(count, checked);
                checked[count] = goalWord;
                count++;
                wordSeen = false;
            }else{
                goalWord = checked[(int)Math.random() *count];
                wordSeen = true;
            }
        }

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
}
