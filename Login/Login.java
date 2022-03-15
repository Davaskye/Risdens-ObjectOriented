package Login;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.Arrays;

public class Login {

    private String username;
    private String password;
    private String userId;

    public Login(String username, String password) {
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
            File file = new File("Users.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String auth = (String) scan.nextLine();
                cred = auth.split(" ", 0);
                if (cred[0].compareTo(getUser()) == 0) {
                    if (cred[1].compareTo(getPass()) == 0) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            }
            return 2;
        } catch (Exception e) {
            System.out.println("There was an error \n");
            System.out.println(e);
            return 999;
        }

    }

    public static void main(String[] args) {

        Login lg = new Login("Romario", "Woah");
        System.out.println(lg.isAuthorised());
    }

}
