/**
* Weijian Shi
* weijianshi@brandeis.edu
* Feb 27, 2022
* PA3
* This is a Casino class that allow user to play the game.
* Known Bugs: NA 
*/

package main;

import java.util.Scanner;

public class Casino {

	/**
	 * Create a global variable usermoney. I have consulted about 
	 * it in ask class forum and has been approved to do so.
	 */
	static int usermoney;

	
	
	/**
	 * This is a method showing the progress of the game. 
	 * @param play: a deck, which contains 52 cards
	 * @param bet: how much a player bet each time. 
	 * @return result: the current progress and summary of the game. 
	 */
	public static String game(Deck play, double bet) {
		String result = "";
		Card user = play.drawNextCard();
		int usernumber = user.getValue(); //get the number of user
		Card computer = play.drawNextCard();
		int computernumber = computer.getValue(); //get the number of computer
		if(usernumber > computernumber) {
			usermoney += bet;
			result += "Congratulations, you win! Your card is " + user.toString() +
					", but the computer number is " + computer.toString() + ". Your currently have " + usermoney;
			
			
		}else{
			usermoney -= bet;
			result += "Sorry, you loose! Your card is " + user.toString() +
					", but the computer number is " + computer.toString() + ". Your currently have " + usermoney;
			
		}
		return result;
		
	}
	
	
	/**
	 * This is a method checking whether user is inputing the usable value. 
	 * @param console: a scanner interact with user
	 */
	public static void check(Scanner console) {
		System.out.print("How much value you want to start with? Please input a positive number: ");
		usermoney = console.nextInt();
		while(usermoney <= 0) {
			System.out.print("How much value you want to start with? Please input a positive number: ");
			usermoney = console.nextInt();

		}
		
	}
	
    /**
     * This is a method checking whether user is inputing the usable value, and keep asking for the usable value until user gives it. 
     * @param console: a scanner interact with user
     * @return bet: the value user would like to bet
     */
	public static double bet(Scanner console) {
		System.out.print("How much value you want to bet? Please input a positive number: ");
		double bet = console.nextDouble();
		while(bet <= 0) { //must bet a positive value
			System.out.print("How much value you want to bet? Please input a positive number: ");
			bet = console.nextDouble();

		}
		return bet;
		
	}
	
	


	

	/**
	 * This is a main method in which user play the game. 
	 * @param args: a string array needed for the main method
	 */
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		check(console);
		Deck cardplay = new Deck(); //create a new deck

		while(usermoney > 0) {
			System.out.print("Do you want to play the game? Please enter Y of N: ");
			String playornot = console.next();
			if(playornot.equals("Y")) { //as long as user input is not Y, then end the game. 
				double bet = bet(console);
				System.out.println(game(cardplay, bet));
			}else {
				System.out.println("See you next time.");
				usermoney = -1;
			}
		}
		System.out.print("Game over!");
		console.close();

	}

}
