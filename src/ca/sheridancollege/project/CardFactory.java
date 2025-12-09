/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author stoml
 * This will be our factory class for creating MyCard objects.
 * Which allows the deck to create cards in one place only,
 * to make it easier to update or change card creation later.
 * 
 * Date: 2025-12-09
 */
public class CardFactory {
    
    /**
     *We will use this to create a card with the given suit and rank
     *as well as return a new MyCard object
     */
    public static MyCard createCard(MyCard.Suit suit, MyCard.Rank rank)
    {
        return new MyCard(suit, rank);
    }
    
}
