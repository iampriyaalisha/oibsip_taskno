import java.util.Scanner;
import java.util.Random;

public class GuesstheNum{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int maxAttempts = 10;
        int totalScore = 0;

        System.out.println("Welcome to the game of Guessing the Number!");
        System.out.println("Try to guess the number between " + lowerBound + " and " + upperBound);

        boolean playAgain = true;

        while (playAgain) {
            int targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int currentAttempt = 1;

            System.out.println("\nNew Round!");
            while (currentAttempt <= maxAttempts) {
                System.out.println("\nAttempt " + currentAttempt + " out of " + maxAttempts);
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();

                if (userGuess == targetNumber) {
                    int roundScore = calculateScore(maxAttempts, currentAttempt);
                    totalScore += roundScore;
                    System.out.println("Congratulations! You guessed the number in " + currentAttempt + " attempts.");
                    System.out.println("Round Score: " + roundScore);
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Try higher.");
                } else {
                    System.out.println("Try lower.");
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
        int minScore = 100; // Minimum score for a round
        int scoreRange = maxScore - minScore;

    // Calculate a score based on the ratio of attempts to maxAttempts
        double scoreRatio = (double) (maxAttempts - attempts) / maxAttempts;
        int roundScore = (int) (minScore + scoreRatio * scoreRange);

        return roundScore;

    }
}
