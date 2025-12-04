package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A class that represents any group of cards in a game.
 * I can use this for a deck, a player's hand, or any other collection of cards.
 *
 * Edited by: Abdul Rahman Pennino
 * Date: 2025-12-02
 */
public class GroupOfCards {

    private ArrayList<Card> cards; // the list that holds all the cards
    private int size;              // how many cards the group is supposed to have

    /**
     * Constructor: create a group of cards with a given size.
     * @param size how many cards this group should have
     */
    public GroupOfCards(int size) {
        this.size = size;               // remember the intended size
        this.cards = new ArrayList<>(); // start with an empty list of cards
    }

    /**
     * Get all the cards in this group.
     * @return the list of cards
     */
    public ArrayList<Card> getCards() {
        return cards; // give back the cards so I can see or modify them
    }

    /**
     * Replace the cards in this group with new ones.
     * @param cards the new list of cards
     */
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards; // set the cards to whatever I give it
    }

    /**
     * Shuffle the cards so their order is random.
     */
    public void shuffle() {
        Collections.shuffle(cards); // mix up the cards randomly
    }

    /**
     * See how many cards this group is supposed to have.
     * @return the number of cards
     */
    public int getSize() {
        return size; // return the size I set
    }

    /**
     * Change the intended size of the group.
     * @param size the new size
     */
    public void setSize(int size) {
        this.size = size; // update the size value
    }
} // end class