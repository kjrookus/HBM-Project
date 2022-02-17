package hbmpack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class HBMTest {
    /*
    Junit tests for the HomePage
     */
    @Test
    public void testusercount(){
        HomePage.Profile current = new HomePage.Profile("None");
        HomePage s1 = new HomePage(current);
        Assertions.assertEquals(s1.usercount, 0);

        HomePage.Profile temp = new HomePage.Profile("test");
        for(int i = 1; i<100; i++){
            s1.insert(temp);
            Assertions.assertEquals(s1.usercount, i);
        }
    }
    @Test
    public void testProfileCreation(){
        HomePage.Profile current = new HomePage.Profile("None");
        HomePage s1 = new HomePage(current);
        HomePage.Profile temp = new HomePage.Profile("Kaden Rookus");
        s1.insert(temp);
        Assertions.assertEquals(s1.userbase[0].getName(), "Kaden Rookus");
        Assertions.assertEquals(s1.userbase[0].getWpmScore(), 0);
        Assertions.assertEquals(s1.userbase[0].getNumbScore(), 0);
        Assertions.assertEquals(s1.userbase[0].getVerbScore(), 0);
    }
}