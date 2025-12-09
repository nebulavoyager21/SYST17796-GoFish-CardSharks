package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * My Go Fish game class.
 * GoFishGame represents the game of Go Fish.
 * Each player tries to collect sets of four cards of the same rank (books).
 * On their turn, a player asks another for a specific rank. If the other player has it, they give it and the turn continues.
 * If not, the player draws from the deck ("Go Fish").
 * The game ends when all books are collected. The player with the most books wins.
 *
 * @author Abdul Rahman Pennino
 * Date: 2025-12-02
 */
public class MyGame extends Game {

    private Deck deck; // The deck I use in my game

    // Constructor: sets up the deck and players list
    public MyGame() {
        super(); // Initialize players list
        deck = new Deck(); // Create deck
    }

    // Add a player
    public void addPlayer(MyPlayer player) {
        getPlayers().add(player);
    }

    // Shuffle deck
    public void shuffleDeck() {
        deck.shuffle();
    }

    // Deal starting hands
    public void dealStartingHands(int cardsPerPlayer) {
        shuffleDeck();
        for (int i = 0; i < cardsPerPlayer; i++) {
            for (Player p : getPlayers()) {
                Card dealtCard = deck.dealCard();
                if (dealtCard != null) {
                    ((MyPlayer)p).getHand().addCard(dealtCard);
                }
            }
        }
    }

    // Show all hands
    public void showAllHands() {
        for (Player p : getPlayers()) {
            System.out.println(p.getName() + "'s hand:");
            System.out.println(((MyPlayer)p).getHand().showHand());
            System.out.println("-------------------");
        }
    }

    // Get deck
    public Deck getDeck() {
        return deck;
    }

    // Let player choose an opponent
    private MyPlayer chooseOpponent(MyPlayer current, ArrayList<MyPlayer> players, Scanner sc) {
    while (true) {
        System.out.println("Choose a player to ask:");
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i) != current) {
                System.out.println(i + ": " + players.get(i).getName());
            }
        }
        int index = sc.nextInt();
        sc.nextLine();

        if (index >= 0 && index < players.size() && players.get(index) != current) {
            return players.get(index);
        }
        System.out.println("Invalid choice. Try again.");
    }
}

// Let player choose a valid rank
private MyCard.Rank chooseRank(MyPlayer player, Scanner sc) {
    while (true) {
        System.out.print("Enter rank (ACE, TWO...KING): ");
        String input = sc.nextLine().toUpperCase();

        try {
            MyCard.Rank rank = MyCard.Rank.valueOf(input);
            if (player.getHand().hasRank(rank)) {
                return rank;
            }
            System.out.println("You must have that rank.");
        } catch (Exception e) {
            System.out.println("Invalid rank.");
        }
    }
}

// Check if game should end
private boolean gameOver(ArrayList<MyPlayer> players) {
    if (!deck.getCards().isEmpty()) return false;

    for (MyPlayer p : players) {
        if (p.getHand().getHandSize() > 0) {
            return false;
        }
    }
    return true;
}

// Handle a single player's turn
private void takeTurn(MyPlayer player, ArrayList<MyPlayer> players, Scanner sc) {
    System.out.println(player.getName() + "'s turn.");
    System.out.println("Your hand:");
    System.out.println(player.getHand().showHand());

    MyPlayer opponent = chooseOpponent(player, players, sc);
    MyCard.Rank requestedRank = chooseRank(player, sc);

    ArrayList<Card> cardsGiven = opponent.getHand().giveCardsOfRank(requestedRank);
    if (!cardsGiven.isEmpty()) {
        for (Card c : cardsGiven) player.getHand().addCard(c);
        System.out.println(opponent.getName() + " gives " + cardsGiven.size() + " card(s)!");
        player.checkForBooks();
        System.out.println(player.getName() + " gets another turn for successful match!");
    } else {
        System.out.println(opponent.getName() + " says Go Fish!");
        Card drawn = deck.dealCard();
        if (drawn != null) {
            player.getHand().addCard(drawn);
            System.out.println("You drew: " + drawn.toString());
        }
        player.checkForBooks();
    }
}

    // Play the game
    @Override
    public void play() {
    Scanner sc = new Scanner(System.in);

    ArrayList<MyPlayer> players = new ArrayList<>();
    for (Player p : getPlayers()) players.add((MyPlayer)p);

    while (!gameOver(players)) {
        for (MyPlayer player : players) {
            if (player.getHand().getHandSize() > 0 || !deck.getCards().isEmpty()) {
                takeTurn(player, players, sc);
            }
        }
    }
}


    // Check if any player has cards
    private boolean playersHaveCards(ArrayList<MyPlayer> players) {
        for (MyPlayer p : players) {
            if (p.getHand().getHandSize() > 0) return true;
        }
        return false;
    }

    // Declare winner(s)
    @Override
    public void declareWinner() {
        int maxBooks = -1;
        ArrayList<MyPlayer> winners = new ArrayList<>();
        for (Player p : getPlayers()) {
            int books = ((MyPlayer)p).getCompletedBooks().size();
            if (books > maxBooks) {
                maxBooks = books;
                winners.clear();
                winners.add((MyPlayer)p);
            } else if (books == maxBooks) {
                winners.add((MyPlayer)p);
            }
        }

        System.out.println("Game Over!");
        if (winners.size() == 1) {
            System.out.println("Winner: " + winners.get(0).getName() + " with " + maxBooks + " books!");
        } else {
            System.out.print("It's a tie between: ");
            for (MyPlayer p : winners) System.out.print(p.getName() + " ");
            System.out.println("with " + maxBooks + " books each!");
        }
    }
}