/**
 * Weijian Shi 
 * weijianshi@brandeis.edu
 * Course: COSI_12B
 * February 4, 2022
 * PA1
 * Class description: this program create a new string that contains the full name in pig latin with the first letter capitalized for the first and last name.
 */
package PA1;

import java.util.Scanner;

public class Problem_5 {
	/*
	 * This is the method for converting the name through pig latin rule. 
	 * @param first_name: first name inputed by user as a string
	 * @param last_name: last name inputed by user as a string 
	 */
	public static String problem5method(String first_name, String last_name) {
		   //perform the conversion required in the question
		   String result = first_name.substring(1, first_name.length()) + first_name.charAt(0) + "ay" + " "+last_name.substring(1, last_name.length()) + last_name.charAt(0) + "ay";
		   
		   //find the position of the space
		   int space_position = 0;
		   for(int i=0; i<result.length(); i++) {
			   if(result.charAt(i) != ' ') {
				   space_position += 1;		   
			   }
			   else {
				   i = result.length() + 1000000; //end the loop
			   }		   
			   
		   }
		   
        //based on the space position, convert the first character of first name and last name to upper case.
		   result = result.substring(0, 1).toUpperCase()+result.substring(1, space_position+1)+result.substring(space_position+1, space_position+2).toUpperCase()+result.substring(space_position+2, result.length()) ;

		   return result;
		
		
	}
	/*
	 * This is the main method runs the program.
	 */
	public static void main(String[] args) {
		   Scanner console = new Scanner(System.in);
		   //ask for the first name from user
		   System.out.print("Your first name? ");
		   String first_name = console.nextLine().toLowerCase();
		   //ask for the last name from user
		   System.out.print("Your last name? ");
		   String last_name = console.nextLine().toLowerCase();
		   System.out.println(problem5method(first_name, last_name));;
		   
	}

}
