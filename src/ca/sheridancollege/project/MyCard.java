package ca.sheridancollege.project;

/**
 * A class that represents a standard playing card for Go Fish.
 * Each card has a suit (Hearts, Diamonds, Clubs, Spades) and a rank (Ace to King).
 *
 * Edited by: Abdul Rahman Pennino
 * Date: 2025-12-02
 */
public class MyCard extends Card {

    // The four suits a card can have
    public enum Suit {HEARTS, DIAMONDS, CLUBS, SPADES}

    // The ranks a card can have
    public enum Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, 
        EIGHT, NINE, TEN, JACK, QUEEN, KING
    }

    private final Suit suit; // the suit of this card
    private final Rank rank; // the rank of this card

    /**
     * Constructor to create a card with a specific suit and rank
     * @param suit the suit of the card
     * @param rank the rank of the card
     */
    public MyCard(Suit suit, Rank rank) {
        super(); // call to the abstract Card constructor
        this.suit = suit; // assign suit
        this.rank = rank; // assign rank
    }

    // Returns the suit of this card
    public Suit getSuit() {
        return suit;
    }

    // Returns the rank of this card
    public Rank getRank() {
        return rank;
    }

    // Returns a readable string for this card, e.g., "ACE of HEARTS"
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}