import java.util.Random;
import java.util.Scanner;

public class Task_1_NumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to the Guess the Number Game!");

        boolean playAgain = true;
        int totalRoundsWon = 0;

        while (playAgain) {
            int roundsWon = playOneRound(random, scanner);
            totalRoundsWon += roundsWon;

            System.out.println("\nRounds Won: " + totalRoundsWon);

            playAgain = askToPlayAgain(scanner);
        }

        System.out.println("Thanks for playing! Your final score:");
        System.out.println("Total Rounds Won: " + totalRoundsWon);

        scanner.close();
    }

    private static int playOneRound(Random random, Scanner scanner) {
        int targetNumber = random.nextInt(100) + 1;
        int attempts = 0;

        while (true) {
            System.out.print("Guess the number between 1 and 100: ");

            if (scanner.hasNextInt()) {
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == targetNumber) {
                    System.out.println("Congratulations! You guessed the correct number " + targetNumber +
                            " in " + attempts + " attempts.");
                    return 1; // Player won the round
                } else if (userGuess < targetNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }

                if (attempts == 10) {
                    System.out.println("Sorry, you've reached the maximum number of attempts. " +
                            "The correct number was " + targetNumber + ".");
                    return 0; // Player lost the round
                }
            } else {
                System.out.println("Please enter a valid number.");
                scanner.next(); // consume the invalid input
            }
        }
    }

    private static boolean askToPlayAgain(Scanner scanner) {
        System.out.print("Do you want to play again? (yes/no): ");
        String playAgain = scanner.next().toLowerCase();
        return playAgain.equals("yes");
    }
}
