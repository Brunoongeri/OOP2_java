package neww;


import java.util.Scanner;

/**
 Bruno Ongeri.
 BSE-01-0034/2025.
 10/3/2025.
 java program to simulate a login function.
 */
public class LoginSimulator {

    // Define the login details
    private static final String CORRECT_USERNAME = "bruno";
    private static final String CORRECT_PASSWORD = "champion112";
    private static final int MAX_TRIES = 3;

    public static void main(String[] args) {
       
        Scanner scanner = new Scanner(System.in);
        // Flag to track if the login was successful
        boolean Authenticated = false;
        
        for (int tries = 1; tries <= MAX_TRIES; tries++) {
                     
            // Prompt and read username
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            
            // Prompt and read password
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            
            System.out.print("Password entered: ");
            for (int i = 0; i < password.length(); i++) {
                System.out.print("*");
            }
            System.out.println(); 
            
            // login check 
            if (username.equals(CORRECT_USERNAME) && password.equals(CORRECT_PASSWORD)) {
                
                //login sccess
                System.out.println("\nwelcome " + username + ".");
                Authenticated = true;
                break; 
                
            } else {
                
                // login failed
                int remainingTries = MAX_TRIES - tries;
                
                System.out.println("\n incorrect username or password.");
                
                if (remainingTries > 0) {
                    System.out.println("You have " + remainingTries + " attempts remaining.");
                }
                
            }
            
        }

        scanner.close();
    }
}