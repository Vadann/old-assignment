import java.util.Scanner; // will use to take input
import java.util.Random; //  will be usign random package to handle random generators
import java.util.Arrays; // will use this to access array methods

public class AwesomestGame {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-help")) {
            displayHelp();
        }
        else {
            Scanner input = new Scanner(System.in);
            Random random = new Random();

            System.out.println("Welcome to the Adventure Game!");
            System.out.println("You find yourself in a dark forest with two open paths. Choose your path: (left/right)");
            String pathChoice = input.nextLine();

            // left path
            if (pathChoice.equalsIgnoreCase("left")) {
                System.out.println("You chose the left path.");
                System.out.println("As you walk along the path, you encounter a monster");
                System.out.println("What do you want to do? (run/fight)");
                String actionChoice = input.nextLine();

                if (actionChoice.equalsIgnoreCase("run")) {
                    System.out.println("You try to run, but you fail to outrun the monster. Game over!");
                    System.exit(1);
                }
                else if (actionChoice.equalsIgnoreCase("fight")) {
                    System.out.println("You decide to fight the monster.");
                    System.out.println("It's time to play a game of Rock-Paper-Scissors!");
                    System.out.println("Enter Rock, Paper, or Scissors");

                    String gameChoice = input.next();
                    // calls the method i made which handles the rock paper scissors game
                    System.out.println(rpsHandler(gameChoice));
                }
                // right path
            }
            else if (pathChoice.equalsIgnoreCase("right")) {
                System.out.println("You chose the right path.");
                System.out.println("You find a safe path that leads you to the exit. Congratulations!");

                // waits 3 seconds
                walk("   ");
                System.out.println("A secret path has been found.");

                // -------- MARIAS GAME ----------- i changed it a bit to make it easier x.x because i was not guessing a number between 1-20
                int value;
                int rand = random.nextInt(5) + 1; // both inclusive thats why i add + 1 because it starts from 0 and ends at 2, so if i + 1, then 0 starts at 1 and, 2 goes to 3
                System.out.println("Enter a number Between 1-5 ");
                // line 49 should be commented out but leave it for testing purposes
                System.out.println(rand);
                value = input.nextInt();
                if (value == rand) {
                    System.out.println("A secret path has been unlocked. You find a treasure chest at the end of the path. Enter the correct combination on 1s and 0s to unlock the chest.");
                } else {
                    System.out.println("You failed to unlock the secret path... the number was " + rand);
                    System.exit(1);
                }

                // ----------------- ARRAYS --------------------------------
                // Generate random lock combination
                int[] lockCombination = new int[3];
                for (int i = 0; i < lockCombination.length; i++) {
                    lockCombination[i] = random.nextInt(2);
                }

                // keep this for testing purposes
                System.out.println("Lock combination: " + Arrays.toString(lockCombination));
                // Check user input against lock combination
                int attempts = 1;
                while (attempts <= 3) {
                    // Prompt user for input
                    int[] userInput = new int[3];
                    for (int i = 0; i < userInput.length; i++) {
                        System.out.println("Enter digit " + (i+1) + " (0 or 1): ");

                        //--- for this code i didnt know how to not give an error if they put a character so i used stackoverflow.
                        while (!input.hasNextInt()) {
                            System.out.println("Invalid input. Please enter an integer (0 or 1): ");
                            input.next();
                        }
                        //---------------------------------------------------------------
                        userInput[i] = input.nextInt();
                    }

                    if (Arrays.equals(userInput, lockCombination)) {
                        System.out.println("Congratulations! You have unlocked the treasure chest and found valuable loot!");
                        break;
                    }
                    else {
                        System.out.println("Incorrect combination. You failed to unlock the chest (" + (3 - attempts) + " attempts remaining):");
                        attempts++;
                        continue;
                    }
                }
            }
            // this is if you enter something other than left or right
            else {
                System.out.println("Invalid choice. Game over!");
                System.exit(1);
            }
        }
    }

    // ------------ METHODS ----------------------------
    public static int generateMonsterChoice() {
        Random random = new Random();
        return random.nextInt(3);
    }

    public static int getPlayerChoiceNum(String playerChoice) {
        if (playerChoice.equalsIgnoreCase("rock")) {
            return 0;
        } else if (playerChoice.equalsIgnoreCase("paper")) {
            return 1;
        } else if (playerChoice.equalsIgnoreCase("scissors")) {
            return 2;
        } else {
            return -1;
        }
    }

    public static String rpsHandler(String playerChoice) {
        int monsterChoice = generateMonsterChoice(); // 0 for rock, 1 for paper, 2 for scissors
        String monsterStr;
        if (monsterChoice == 0) {
            monsterStr = "rock";
        } else if (monsterChoice == 1) {
            monsterStr = "paper";
        } else {
            monsterStr = "scissors";
        }

        int playerChoiceNum = getPlayerChoiceNum(playerChoice);

        // check if the player's choice is valid
        if (playerChoiceNum == -1) {
            return "Invalid choice. Game over!";
        }

        // check the outcome of the game
        if (playerChoiceNum == monsterChoice) {
            return "It's a tie! You both chose " + monsterStr +  " You failed to defeat the monster. Game over!";
        } else if ((playerChoiceNum == 0 && monsterChoice == 2)
                || (playerChoiceNum == 1 && monsterChoice == 0)
                || (playerChoiceNum == 2 && monsterChoice == 1)) {
            return "You won! You chose " + playerChoice + " and the monster played " + monsterStr + " You defeated the monster and continue on your journey!";
        } else {
            return "You lost! The monster chose " + monsterStr + " and defeats you. Game over!";
        }
    }

    // the source for this code is from the demo in the moduels commando.java
    public static void walk(String str) {
        for(int i = 0; i < str.length(); i++) {
            System.out.print(str.charAt(i));
            pause((int)(Math.random() * 1500));
            System.out.println(".");
        }
    }

    public static void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {}
    }

    public static void displayHelp() {
        System.out.println("Welcome to the Adventure Game!");
        System.out.println("In this game, you find yourself in a dark forest with two open paths.");
        System.out.println("Choose your path by typing 'left' or 'right'.");
        System.out.println("If you choose the left path, you will encounter a monster and will have to play Rock-Paper-Scissors to defeat it.");
        System.out.println("If you choose the right path, you will encounter a secret path, where you must guess the correct number to unlock it.");
        System.out.println("If you guess correctly, you will unlock the path and a treasure chest by guessing a three-digit binary code.");
        System.out.println("You have three attempts to guess the correct code. Good luck!");
    }
}
