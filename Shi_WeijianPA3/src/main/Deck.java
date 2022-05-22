/**
* Weijian Shi
* weijianshi@brandeis.edu
* Feb 27, 2022
* PA3
* This is a Deck class that define a deck.
* Known Bugs: NA 
*/

package main;
import java.util.*;

public class Deck {
	
	/**
	 * they are four fields of this class
	 * Deck: an array contains 52 cards
	 * discardPile: contains the card which has been drawn
	 * drawNestCardCount: count how many card has been drawn
	 * discardCount: how many card has been put in discardPile
	 */
	private Card[] Deck = new Card[52]; //an array with 52 spaces
	private Card[] discardPile = new Card[52];
	private int drawnNextCardCount = 0;
	private int discardCount = 0;
	
	/**
	 * This is a method fill the array with 52 different cards in sequence, then shuffle it. 
	 */
	public Deck() {
		for(int i = 0; i < 13; i++) {
			Deck[i] = new Card(i+1, "Hearts");
		}
		for(int i = 13; i < 26; i++) {
			Deck[i] = new Card(i-12, "Diamonds");
		}
		for(int i = 26; i < 39; i++) {
			Deck[i] = new Card(i-25, "Spades");
		}
		for(int i = 39; i < 52; i++) {
			Deck[i] = new Card(i-38, "Clubs");
		}
		shuffle();
		
		

	}
	
	/**
	 * This is a method that shuffle the cards in the deck. 
	 */
	public void shuffle() {
		Random rand = new Random();
		for(int i = 51; i >= 1; i--) {
			if(Deck[i] != null) {
				int j = rand.nextInt(i+1); //get the random number smaller or equal to i
				Card temp = Deck[i]; //create temporary variable storing the value
				Deck[i] = Deck[j];
				Deck[j] = temp;
			}
		}
	}
	
	
	/**
	 * This is a method draw the next card in the deck and discard it after drawing. After draw all the cards, put all cards back to the deck and shuffle. 
	 * @return the next card player draws
	 */
	public Card drawNextCard() {
		if(drawnNextCardCount < 52) {
			Card tempcard = Deck[drawnNextCardCount];
			Deck[drawnNextCardCount] = null; //put the card which has been drawn into discard pile.
			this.discard(tempcard);
			drawnNextCardCount += 1;
			discardCount += 1;
			return tempcard;
		}
		else {
			Deck = discardPile.clone(); //when all the cards are drawn, put all cards back to deck
			discardPile = new Card[52];
			this.shuffle(); //shuffle the deck
			drawnNextCardCount = 1;
			discardCount = 0;
			this.discard(Deck[0]);
			discardCount += 1;
			
			return Deck[0]; //draw the first card
		}
	}
	
	
    /**
     * This is a method that discard a card. 
	 * @param c: discard a card, which means put the discarded card in discard pile.
	 */
	public void discard(Card c) {
		discardPile[discardCount] = c;
	}
}






