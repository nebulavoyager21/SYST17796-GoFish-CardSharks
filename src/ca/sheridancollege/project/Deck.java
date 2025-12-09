package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * This class represents a standard 52-card deck for the Go Fish game.
 * I can shuffle this deck and deal cards to players.
 * 
 * Edited by: Abdul Rahman Pennino
 * Date: 2025-12-02
 */
public class Deck extends GroupOfCards {
    
    private static Deck instance;

    /**
     * Constructor: I want to create a full deck of 52 cards
     */
    public Deck() {
        // First, I tell the parent class that the deck will have 52 cards
        super(52);
        
        // I create an empty list where I will put all my cards
        ArrayList<Card> cards = new ArrayList<>();
        
        // Now I make every card by combining each suit with each rank
        for (MyCard.Suit suit : MyCard.Suit.values()) {
            for (MyCard.Rank rank : MyCard.Rank.values()) {
                // I make a card for this suit and rank
                //MyCard card = new MyCard(suit, rank);
                //Here we add the factory method
                cards.add(CardFactory.createCard(suit,rank));
                // I add this card to my list
                //cards.add(card);
            }
        }
        
        // Now that I have all my cards, I tell the parent class to use this list
        super.setCards(cards);
    }
    
    /**
     *This method will give access to the only deck in the game
     * and return the single Deck instance
     */
    public static Deck getInstance(){
        if(instance == null)
        {
            instance = new Deck();
        }
        return instance;
    }

    /**
     * I want to give the top card from the deck to a player
     * @return the top card, or null if there are no cards left
     */
    public Card dealCard() {
        // If the deck is empty, I just return null
        if (super.getCards().isEmpty()) {
            return null;
        }
        
        // Otherwise, I take the first card from the list
        //Card topCard = super.getCards().get(0);
        
        // I remove it from the deck so no one can take it again
        return super.getCards().remove(0);
        
        // Finally, I give the card to whoever asked for it
        //return topCard;
    }

    /**
     * I can shuffle the deck so the cards are in random order
     */
    public void shuffleDeck() {
        // I use the shuffle method from GroupOfCards
        super.shuffle();
    }
}