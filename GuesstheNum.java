import java.util.Scanner;
import java.util.Random;

public class GuesstheNum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Define game parameters
        int lowerBound = 1;       // The lowest possible number to guess
        int upperBound = 100;     // The highest possible number to guess
        int maxAttempts = 10;     // Maximum number of attempts per round
        int totalScore = 0;       // Accumulated score across rounds

        // Welcome message and instructions
        System.out.println("Welcome to the game of Guessing the Number!");
        System.out.println("Try to guess the number between " + lowerBound + " and " + upperBound);

        boolean playAgain = true;

        while (playAgain) {
            // Generate a random target number for each round
            int targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int currentAttempt = 1;
            boolean hasProvidedHint = false; // Track if a hint has been provided

            System.out.println("\nNew Round!");

            while (currentAttempt <= maxAttempts) {
                System.out.println("\nAttempt " + currentAttempt + " out of " + maxAttempts);
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();

                if (userGuess == targetNumber) {
                    // Calculate and display the round score
                    int roundScore = calculateScore(maxAttempts, currentAttempt);
                    totalScore += roundScore;
                    System.out.println("Congratulations! You guessed the number in " + currentAttempt + " attempts.");
                    System.out.println("Round Score: " + roundScore);
                    break; // Exit the current round loop
                } else if (userGuess < targetNumber) {
                    System.out.println("Try higher.");
                } else {
                    System.out.println("Try lower.");
                }
                
                // Provide a hint if it's not the last attempt and a hint hasn't been provided yet
                if (currentAttempt < maxAttempts && !hasProvidedHint) {
                    if (userGuess < targetNumber) {
                        System.out.println("Hint: The target number is greater than " + userGuess);
                    } else {
                        System.out.println("Hint: The target number is less than " + userGuess);
                    }
                    hasProvidedHint = true;
                }
                
                currentAttempt++;

                if (currentAttempt > maxAttempts) { 
                    System.out.println("\nOut of attempts!");
                    System.out.println("The number was: " + targetNumber);
                }
            }

            System.out.println("\nTotal Score: " + totalScore);
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainInput = scanner.next().toLowerCase();
            playAgain = playAgainInput.equals("yes");
        }
        
        scanner.close();

        System.out.println("\nThanks for playing!");
        System.out.println("Final Total Score: " + totalScore);
    }

    // Custom scoring function based on the number of attempts
    private static int calculateScore(int maxAttempts, int attempts) {
        int maxScore = 1000; // Maximum possible score for a round
        int minScore = 100;  // Minimum score for a round
        int scoreRange = maxScore - minScore;

        // Calculate a score based on the ratio of attempts to maxAttempts
        double scoreRatio = (double) (maxAttempts - attempts) / maxAttempts;
        int roundScore = (int) (minScore + scoreRatio * scoreRange);

        return roundScore;
    }
}
