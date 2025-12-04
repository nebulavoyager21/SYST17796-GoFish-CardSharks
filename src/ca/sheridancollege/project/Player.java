/*
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

/**
 * A class that models each Player in the game.
 * Players now have a Hand object to manage their cards.
 *
 * Edited by: Abdul Rahman Pennino
 * Date: 2025-12-02
 */
public abstract class Player {

    private String name; // unique player name
    private Hand hand;   // the player's hand of cards

    /**
     * Constructor to set the player's name and create an empty hand
     * @param name the player's unique name
     */   
    public Player(String name) {
        this.name = name;
        this.hand = new Hand(); // start with empty hand
    }

    /**
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set or change the player's name
     * @param name the new player name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the player's hand
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Add a card to the player's hand
     * @param card the card to add
     */
    public void addCardToHand(Card card) {
        hand.addCard(card);
    }

    /**
     * Remove a card from the player's hand
     * @param card the card to remove
     * @return true if the card was removed, false otherwise
     */
    public boolean removeCardFromHand(Card card) {
        return hand.removeCard(card);
    }

    /**
     * Display all cards in the player's hand
     * @return a string representation of the hand
     */
    public String showHand() {
        return hand.showHand();
    }

    /**
     * Abstract method for gameplay logic.
     * Subclasses must implement this to define player actions.
     */
    public abstract void play();
}