import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Word to guess
        String wordToGuess = "PROGRAMMING"; // Replace this with any word.
        char[] wordArray = wordToGuess.toCharArray();
        char[] displayArray = new char[wordArray.length];
        Arrays.fill(displayArray, '_'); // Initially display underscores

        // Game variables
        int attemptsLeft = 6;
        boolean wordGuessed = false;
        HashSet<Character> guessedLetters = new HashSet<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Hangman!");
        System.out.println("You have " + attemptsLeft + " attempts to guess the word.");

        // Game Loop
        while (attemptsLeft > 0 && !wordGuessed) {
            System.out.print("\nWord: ");
            printArray(displayArray);
            System.out.println("\nAttempts left: " + attemptsLeft);
            System.out.print("Enter a letter: ");
            String input = scanner.next().toUpperCase();

            // Validate input
            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Invalid input. Please enter a single letter.");
                continue;
            }

            char guess = input.charAt(0);

            // Check if letter has already been guessed
            if (guessedLetters.contains(guess)) {
                System.out.println("You already guessed that letter. Try again.");
                continue;
            }

            // Add guess to the guessed letters set
            guessedLetters.add(guess);

            // Check if the letter exists in the word
            boolean correctGuess = false;
            for (int i = 0; i < wordArray.length; i++) {
                if (wordArray[i] == guess) {
                    displayArray[i] = guess; // Update the display array
                    correctGuess = true;
                }
            }

            // If incorrect guess, decrease attempts
            if (!correctGuess) {
                System.out.println("Wrong guess!");
                attemptsLeft--;
            } else {
                System.out.println("Good guess!");
            }

            // Check if the word is completely guessed
            if (Arrays.equals(displayArray, wordArray)) {
                wordGuessed = true;
            }
        }

        // End Game Conditions
        if (wordGuessed) {
            System.out.println("\nCongratulations! You guessed the word: " + wordToGuess);
        } else {
            System.out.println("\nGame Over! The correct word was: " + wordToGuess);
        }

        scanner.close();
    }

    // Helper method to print character array
    private static void printArray(char[] array) {
        for (char c : array) {
            System.out.print(c + " ");
        }
        System.out.println();
    }
}
