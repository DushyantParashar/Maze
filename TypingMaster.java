package TerminalTypingMaster;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TypingMaster {
	private static final String LEADERBOARD_FILE = "leaderboard.txt";

    public static void updateLeaderboard(String username, int wpm) {
        try {
            List<String> leaderboard = readLeaderboard();
            leaderboard.add(username + " - " + wpm + " WPM");
            Collections.sort(leaderboard, Collections.reverseOrder());
            leaderboard = leaderboard.subList(0, Math.min(leaderboard.size(), 5));
            Files.write(Paths.get(LEADERBOARD_FILE), leaderboard);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    public static void showLeaderboard() 
    {
        try {
            List<String> leaderboard = readLeaderboard();
            System.out.println("\nLeaderboard:");
            for (int i = 0; i < leaderboard.size(); i++) {
                System.out.println((i + 1) + ". " + leaderboard.get(i));
            }
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    private static List<String> readLeaderboard() throws IOException {
        File file = new File(LEADERBOARD_FILE);
        if (!file.exists()) {
            file.createNewFile();
        }
        return new ArrayList<>(Files.readAllLines(Paths.get(LEADERBOARD_FILE)));
    }
    public static List<String> loadWordsFromCategory(String category)
    {
        try {
            String fileName = category.toLowerCase() + "_words.txt";
            return Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            System.out.println("Words file for category '" + category + "' not found.");
            return Collections.emptyList();
        }
    }
    public static String getUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    
    
    public static void main(String[] args) {
    	
            System.out.println("Welcome to Terminal Typing Master!");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();

            while (true) {
                System.out.println("\nOptions:");
                System.out.println("1. Start Typing Test");
                System.out.println("2. Show Leaderboard");
                System.out.println("3. Exit");

                System.out.print("Select an option (1/2/3): ");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        System.out.println("Typing Test:");
                        List<String> words = generateRandomWords(5); // Adjust the number of words as needed

                        System.out.println("Words to type: " + String.join(" ", words));
                        System.out.println("Press Enter to start typing. Timer will start immediately.");
                        scanner.nextLine();
                        long startTime = System.currentTimeMillis();

                        System.out.println("Type the given words:");
                        String typedWords = getUserInput();
                        long endTime = System.currentTimeMillis();

                        double timeTaken = (endTime - startTime) / 1000.0;
                        int wordsTyped = typedWords.split("\\s+").length;
                        int wpm = (int) ((wordsTyped / timeTaken) * 60);

                        System.out.println("\nTyping Metrics:");
                        System.out.println("Words Typed: " + wordsTyped);
                        System.out.println("Time Taken: " + timeTaken + " seconds");
                        System.out.println("Words Per Minute (WPM): " + wpm);

                        updateLeaderboard(username, wpm);
                        break;

                    case "2":
                        showLeaderboard();
                        break;

                    case "3":
                        System.out.println("Exiting Terminal Typing Master. Goodbye!");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid option. Please choose again.");
                        break;
                }
            }
    	}
    	
        

        private static List<String> generateRandomWords(int numberOfWords) {
            List<String> wordList = new ArrayList<>();
            // Add your words or load from a file here
            wordList.add("apple");
            wordList.add("banana");
            wordList.add("computer");
            wordList.add("programming");
            wordList.add("java");
            wordList.add("eclipse");
            wordList.add("developer");
            wordList.add("keyboard");
            wordList.add("monitor");
            wordList.add("internet");

            Collections.shuffle(wordList, new Random());
            return wordList.subList(0, numberOfWords);
        }
    }



