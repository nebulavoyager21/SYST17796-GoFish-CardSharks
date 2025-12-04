package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * A class representing a player's hand in Go Fish.
 * I can add, remove, or check cards in this hand.
 * 
 * Edited by: Abdul Rahman Pennino
 * Date: 2025-12-02
 */
public class Hand extends GroupOfCards {

    /**
     * Constructor: create an empty hand.
     * I start with 0 cards in hand.
     */
    public Hand() {
        super(0);                        // call parent constructor with size 0
        super.setCards(new ArrayList<>()); // initialize the cards list as empty
    }

    /**
     * Add a card to my hand.
     * @param card the card to add
     */
    public void addCard(Card card) {
        super.getCards().add(card);          // put the card in the list
        super.setSize(super.getCards().size()); // update the size of the hand
    }

    /**
     * Remove a card from my hand.
     * @param card the card to remove
     * @return true if the card was removed, false otherwise
     */
    public boolean removeCard(Card card) {
        boolean removed = super.getCards().remove(card); // try to remove the card
        super.setSize(super.getCards().size());         // update hand size
        return removed;                                 // tell if removal succeeded
    }

    /**
     * Show all cards in my hand as a string.
     * @return a string listing all cards in my hand
     */
    public String showHand() {
        StringBuilder handString = new StringBuilder();     // start empty
        for (Card card : super.getCards()) {               // go through each card
            handString.append(card.toString()).append("\n"); // add card to string
        }
        return handString.toString();                      // give back the string
    }

    /**
     * Check how many cards are currently in my hand.
     * @return number of cards in hand
     */
    public int getHandSize() {
        return super.getCards().size(); // return the current count of cards
    }

    /**
     * Give all cards of a certain rank from my hand.
     * @param rank the rank to give
     * @return a list of cards given
     */
    public ArrayList<Card> giveCardsOfRank(MyCard.Rank rank) {
        ArrayList<Card> cardsToGive = new ArrayList<>();   // start empty
        for (Card c : super.getCards()) {                  // look at each card
            if (((MyCard)c).getRank() == rank) {          // if rank matches
                cardsToGive.add(c);                        // add to give list
            }
        }
        super.getCards().removeAll(cardsToGive);          // remove given cards from hand
        super.setSize(super.getCards().size());           // update hand size
        return cardsToGive;                               // give the cards
    }

    /**
     * Check if my hand has a specific rank.
     * @param rank the rank to check for
     * @return true if I have at least one card of that rank
     */
    public boolean hasRank(MyCard.Rank rank) {
        for (Card c : super.getCards()) {                // look at each card
            if (((MyCard)c).getRank() == rank) return true; // found a matching card
        }
        return false;                                    // no matching cards
    }
}