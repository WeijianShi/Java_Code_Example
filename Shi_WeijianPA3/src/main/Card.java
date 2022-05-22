/**
* Weijian Shi
* weijianshi@brandeis.edu
* Feb 27, 2022
* PA3
* This is a class defining card
* Known Bugs: NA 
*/

package main;

public class Card {

	/**
	 * This is 3 fields for card
	 * suit: the card's suit (hearts, diamonds, spades, clubs
	 * color: the card's color (red or black)
	 * value: card's value (1-13)
	 */
	private String suit = "";
	private String color = "";
	private int value = 0;
	
	
	/**
	 * This is a constructor initialize the Card and its variables.
	 * @param value: input the number of the card, which is from 1-13
	 * @param suit: either “Hearts”, “Diamonds,” “Spades”, or “Clubs”
	 */
	public Card(int value, String suit)  {
		this.value = value;
		this.suit = suit;
		if(suit.equals("Hearts") || suit.equals("Diamonds")) {
			this.color = "red";
		}else {
			this.color = "black";
		}

	}
	
	/**
	 * This is a get method returns the value of the card. 
	 * @return: value of the card
	 */
	public int getValue() {
		return this.value;

	}
	
	/**
	 * This is a get method returns the color of the card. 
	 * @return: color of the card
	 */
	public String getColor() {
		return this.color;

	}
	
	/**
	 * This is a get method returns the suit of the card. 
	 * @return: suit of the card
	 */
	public String getSuit() {
		return this.suit;		

	}
	

	@Override
	/**
	 * This is a method returns the info of the card. 
	 * @return: info of the card
	 */
	public String toString() {
		String result = "";
		if(this.value == 13){
			result += "King of " + this.suit;   //transform 13 to King
		}else if(this.value == 12){
			result += "Queen of " + this.suit;  //transform 12 to Queen
		}else if(this.value == 11){
			result += "Jack of " + this.suit;   //transform 11 to Jack
		}else if(this.value == 1){
			result += "Ace of " + this.suit;    //transform 1 to Ace
		}else{
			result += this.value + " of " + this.suit;
		}
		return result;
	}
}
