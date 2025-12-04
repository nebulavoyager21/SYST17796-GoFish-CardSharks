package ca.sheridancollege.project;

import java.util.Scanner;

/**
 * Main class to run and test the Go Fish game.
 *
 * Edited by: Abdul Rahman Pennino
 * Date: 2025-12-02
 * Edited by: Sameer Neupane
 * Date: 2025-12-03
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Welcome message
        System.out.println("Welcome to Go Fish!");
        
        System.out.println("1. Start Game");
        System.out.println("2. Show Rules");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");

        int choice = sc.nextInt();
        sc.nextLine(); 

        if (choice == 2) {
            System.out.println("\nRULES:");
            System.out.println("- Ask for ranks you have.");
            System.out.println("- If opponent has it, they must give the cards.");
            System.out.println("- If not: Go Fish (draw from deck).");
            System.out.println("- 4 cards of the same rank = 1 BOOK.");
            System.out.println("- Player with most books wins.");
            System.out.println("- The Player with the most books at the end wins the game.");
            System.out.println();
        }

        if (choice == 3) {
            System.out.println("Exit Game!");
            return;
        }

        // Ask how many players will play
        System.out.print("Enter number of players (2-4 recommended): ");
        int numPlayers = sc.nextInt();
        sc.nextLine(); // consume leftover newline

        // Create a Go Fish game instance
        GoFishGame game = new GoFishGame(); // game name is already set to "Go Fish"

        // Add players to the game
        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter name for player " + i + ": ");
            String name = sc.nextLine();
            game.addPlayer(new GoFishPlayer(name));
        }

        // Deal starting hands
        int startingCards = (numPlayers <= 3) ? 7 : 5; // 7 cards for 2-3 players, 5 for 4 players
        game.dealStartingHands(startingCards);

        // Start game logic inside GoFishGame
        game.play();

        // Game is over, declare winner(s)
        game.declareWinner();

        sc.close(); // close scanner
    }
}
