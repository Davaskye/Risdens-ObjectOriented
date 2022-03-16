package Login;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.Arrays;

public class Security {

    private String username;
    private String password;
    private String userId;

    public Security(String username, String password) {
        this.username = username;
        this.password = password;
        // this.userId =
    }

    public String getUser() {
        return this.username;
    }

    public String getPass() {
        return this.password;
    }

    public String getId() {
        return this.userId;
    }

    public int isAuthorised() {

        try {
            String cred[];
            File file = new File("Users.dat");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String auth = (String) scan.nextLine();
                cred = auth.split(" ", 0);
                if (cred[0].compareTo(getUser()) == 0) {
                    if (cred[1].compareTo(getPass()) == 0) {
                        return 0; // Both password and username are correct
                    } else {
                        return 1; // Username is correct but password is incorrect- gives the chance to give error
                                  // message for password
                    }
                }
            }
            return 2; // Both password and username are incorrect
        } catch (Exception e) {
            System.out.println("There was an error \n");
            System.out.println(e);
            return 999; // An error occured where file is not found are file path is incorrect
        }

    }

    public static void main(String[] args) {

        Security lg = new Security("Romario", "Woah");
        System.out.println(lg.isAuthorised());
    }

}
