package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * My Go Fish game class.
 *
 * @author Abdul Rahman Pennino
 * Date: 2025-12-02
 */
public class GoFishGame extends Game {

    private Deck deck; // The deck I use in my game

    // Constructor: sets up the deck and players list
    public GoFishGame() {
        super(); // Initialize players list
        deck = new Deck(); // Create deck
    }

    // Add a player
    public void addPlayer(GoFishPlayer player) {
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
                    ((GoFishPlayer)p).getHand().addCard(dealtCard);
                }
            }
        }
    }

    // Show all hands
    public void showAllHands() {
        for (Player p : getPlayers()) {
            System.out.println(p.getName() + "'s hand:");
            System.out.println(((GoFishPlayer)p).getHand().showHand());
            System.out.println("-------------------");
        }
    }

    // Get deck
    public Deck getDeck() {
        return deck;
    }

    // Let player choose an opponent
    private GoFishPlayer chooseOpponent(GoFishPlayer current, ArrayList<GoFishPlayer> players, Scanner sc) {
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
private MyCard.Rank chooseRank(GoFishPlayer player, Scanner sc) {
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
private boolean gameOver(ArrayList<GoFishPlayer> players) {
    if (!deck.getCards().isEmpty()) return false;

    for (GoFishPlayer p : players) {
        if (p.getHand().getHandSize() > 0) {
            return false;
        }
    }
    return true;
}

// Handle a single player's turn
private void takeTurn(GoFishPlayer player, ArrayList<GoFishPlayer> players, Scanner sc) {
    System.out.println(player.getName() + "'s turn.");
    System.out.println("Your hand:");
    System.out.println(player.getHand().showHand());

    GoFishPlayer opponent = chooseOpponent(player, players, sc);
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

    ArrayList<GoFishPlayer> players = new ArrayList<>();
    for (Player p : getPlayers()) players.add((GoFishPlayer)p);

    while (!gameOver(players)) {
        for (GoFishPlayer player : players) {
            if (player.getHand().getHandSize() > 0 || !deck.getCards().isEmpty()) {
                takeTurn(player, players, sc);
            }
        }
    }
}


    // Check if any player has cards
    private boolean playersHaveCards(ArrayList<GoFishPlayer> players) {
        for (GoFishPlayer p : players) {
            if (p.getHand().getHandSize() > 0) return true;
        }
        return false;
    }

    // Declare winner(s)
    @Override
    public void declareWinner() {
        int maxBooks = -1;
        ArrayList<GoFishPlayer> winners = new ArrayList<>();
        for (Player p : getPlayers()) {
            int books = ((GoFishPlayer)p).getCompletedBooks().size();
            if (books > maxBooks) {
                maxBooks = books;
                winners.clear();
                winners.add((GoFishPlayer)p);
            } else if (books == maxBooks) {
                winners.add((GoFishPlayer)p);
            }
        }

        System.out.println("Game Over!");
        if (winners.size() == 1) {
            System.out.println("Winner: " + winners.get(0).getName() + " with " + maxBooks + " books!");
        } else {
            System.out.print("It's a tie between: ");
            for (GoFishPlayer p : winners) System.out.print(p.getName() + " ");
            System.out.println("with " + maxBooks + " books each!");
        }
    }
}