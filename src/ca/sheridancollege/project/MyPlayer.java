package ca.sheridancollege.project;

import ca.sheridancollege.project.Card;
import ca.sheridancollege.project.Deck;
import ca.sheridancollege.project.Hand;
import ca.sheridancollege.project.MyCard;
import ca.sheridancollege.project.Player;
import java.util.ArrayList;

/**
 * Represents a Go Fish player.
 * Each player has a hand and can collect completed books.
 * 
 * Edited by: Abdul Rahman Pennino
 * Date: 2025-12-02
 */
public class MyPlayer extends Player {

    private Hand hand; // The player's hand of cards
    private ArrayList<MyCard.Rank> completedBooks; // The ranks for which player has collected 4 cards

    // Constructor: sets the player's name and creates an empty hand and book list
    public MyPlayer(String name) {
        super(name);
        hand = new Hand();
        completedBooks = new ArrayList<>();
    }

    // Returns the player's hand
    public Hand getHand() {
        return hand;
    }

    // Returns the list of completed books
    public ArrayList<MyCard.Rank> getCompletedBooks() {
        return completedBooks;
    }

    // Check if the player has 4 cards of the same rank and create a book
    public void checkForBooks() {
        ArrayList<MyCard.Rank> ranksToCheck = new ArrayList<>();
        for (Card c : hand.getCards()) {
            MyCard.Rank rank = ((MyCard)c).getRank();
            if (!ranksToCheck.contains(rank)) ranksToCheck.add(rank);
        }

        for (MyCard.Rank rank : ranksToCheck) {
            int count = 0;
            for (Card c : hand.getCards()) {
                if (((MyCard)c).getRank() == rank) count++;
            }

            if (count == 4) { // If 4 cards of same rank are in hand
                completedBooks.add(rank); // Add to completed books
                ArrayList<Card> cardsToRemove = new ArrayList<>();
                for (Card c : hand.getCards()) {
                    if (((MyCard)c).getRank() == rank) cardsToRemove.add(c);
                }
                for (Card c : cardsToRemove) hand.removeCard(c); // Remove cards from hand
                System.out.println(getName() + " completed a book of " + rank + "!");
            }
        }
    }

    // Player takes their turn
    public void takeTurn(ArrayList<MyPlayer> players, Deck deck) {
        if (hand.getHandSize() == 0) { // If no cards, draw one
            System.out.println(getName() + " has no cards, drawing one from deck...");
            Card drawn = deck.dealCard();
            if (drawn != null) hand.addCard(drawn);
            checkForBooks();
            return;
        }

        // Pick a rank from own hand to ask for
        MyCard.Rank requestedRank = ((MyCard)hand.getCards().get(0)).getRank();

        // Pick a random opponent
        MyPlayer opponent;
        do {
            opponent = players.get((int)(Math.random() * players.size()));
        } while(opponent == this);

        System.out.println(getName() + " asks " + opponent.getName() + " for " + requestedRank + "s.");

        // Ask opponent for cards of that rank
        ArrayList<Card> cardsGiven = opponent.getHand().giveCardsOfRank(requestedRank);
        if (!cardsGiven.isEmpty()) { // If opponent has cards
            for (Card c : cardsGiven) hand.addCard(c);
            System.out.println(opponent.getName() + " gives " + cardsGiven.size() + " card(s) to " + getName());
            checkForBooks();
            takeTurn(players, deck); // Continue turn if successful
        } else { // Go Fish
            System.out.println(opponent.getName() + " says Go Fish!");
            Card drawn = deck.dealCard();
            if (drawn != null) {
                hand.addCard(drawn);
                System.out.println(getName() + " draws a card from the deck.");
            }
            checkForBooks();
        }
    }

    @Override
    public void play() {
        System.out.println(getName() + " is taking their turn.");
    }
}