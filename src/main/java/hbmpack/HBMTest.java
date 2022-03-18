package hbmpack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**********************************************************
 * Class containing all test cases for HBM Project.
 * **********************************************************/

public class HBMTest {
    /*
    Junit tests for the HomePage
     */
    @Test
    public void testusercount() {
        HomePage.Profile current = new HomePage.Profile("None");
        HomePage s1 = new HomePage(current);
        Assertions.assertEquals(s1.usercount, 0);
    }

    @Test
    public void testusercount2() {
        HomePage.Profile temp = new HomePage.Profile("test");
        HomePage s2 = new HomePage(temp);
        for (int i = 1; i < 100; i++) {
            s2.insert(temp);
            Assertions.assertEquals(s2.usercount, i);
        }
    }

    @Test
    public void testProfileCreation() {
        HomePage.Profile current = new HomePage.Profile("None");
        HomePage s1 = new HomePage(current);
        HomePage.Profile temp = new HomePage.Profile("Kaden Rookus");
        s1.insert(temp);
        Assertions.assertEquals(s1.userbase[0].getName(), "Kaden Rookus");
        Assertions.assertEquals(s1.userbase[0].getWpmScore(), 0);
        Assertions.assertEquals(s1.userbase[0].getNumbScore(), 0);
        Assertions.assertEquals(s1.userbase[0].getVerbScore(), 0);
    }

    @Test
    public void testJbutton(){
        HomePage.Profile current = new HomePage.Profile("None");
        HomePage test = new HomePage(current);
        Assertions.assertDoesNotThrow(() -> test.verb_mem_but.doClick());
        Assertions.assertDoesNotThrow(() -> test.num_mem_but.doClick());
        Assertions.assertDoesNotThrow(() -> test.type_speed_but.doClick());
    }

    @Test
    public void testAdjust(){
        HomePage.Profile current = new HomePage.Profile("None");
        TypeSpeed test = new TypeSpeed(current);
        double value = test.adjust("hellp", "hello");
        Assertions.assertEquals(value, .8);
        value = test.adjust("longer string with space", "longer string with space");
        Assertions.assertEquals(value, 1);
        value = test.adjust("CapiTALs", "capitals");
        Assertions.assertEquals(value, .5);
        value = test.adjust("Grammar. is; the: most important", "Grammar. is; the: most important");
        Assertions.assertEquals(value, 1);

    }
}