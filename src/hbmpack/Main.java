package hbmpack;

/**********************************************************
 * Opens the Homepage GUI that allows for game selection.
 * Additionally creates a local array userbase to store
 * scores across games and profiles
 *
 * @author Kaden Rookus & Alex Bergers
 * @version 02/20/2022
 * **********************************************************/

public class Main {
    private static HomePage.Profile[] userbase = new HomePage.Profile[10];
    private static int usercount = 0;

    public static void main(String[] arg) {
        HomePage.Profile current = new HomePage.Profile("None");
        new HomePage(current);
    }

    /**********************************************************
     * Helpbase class is used to provide getters and setters
     * for profiles in the profile userbase.
     * **********************************/
    public static class Helpbase {

        public HomePage.Profile[] getUserbase() {
            //System.out.println("hi" + userbase);
            return userbase;
        }

        public void setUserbase(HomePage.Profile[] userbase2) {
            userbase = userbase2;
        }

        public int getUsercount() {
            System.out.println("getUsercount: " + usercount);
            return usercount;
        }

        public void setUsercount(int usercount2) {
            usercount = usercount2;
            System.out.println("setUsercount: " + usercount);
        }
    }
}