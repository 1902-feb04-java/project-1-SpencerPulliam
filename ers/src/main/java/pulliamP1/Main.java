package main.java.pulliamP1;

import java.util.Scanner;
import java.io.*;

public class Main{
    public static void main(String[] args) {
    Login log = new Login();
    log.userPrompts();
    }
}

 class Login {
    
    Scanner sc = new Scanner(System.in);
    
    void userPrompts() {
        
        System.out.println("Please enter your Username. ");
        String user = sc.next();

         System.out.println("Please enter your password. ");
         String pass = sc.next();

        System.out.println("Your Username is: " + user);
         System.out.println("Your Password is: " + pass);

        sc.close();

    }
}
