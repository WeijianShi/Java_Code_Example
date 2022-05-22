/**
 * Weijian Shi 
 * weijianshi@brandeis.edu
 * Course: COSI_12B
 * February 4, 2022
 * PA1
 * Class description: this program halve the value if it's even, multiply 3 and add 1 if it's odd, repeat the process until the value is 1, printing out each value, then finally
 *                    print out how many of these operations you performed.
 */
package PA1;

import java.util.*;
public class Problem_1 {
	/*
	 * This is a method which halve the value if it's even, multiply 3 and add 1 if it's odd, repeat the process until the value is 1, printing out each value
	 * @param number: this is a integer inputed by user
	 */
	
	public static String problem1method(int number) {
		String result = "";
		   if(number < 1) {
			   result = "Error, please enter a positive number greater or equal to 1";//repeat the prompt message if what user input is not a positive integer
			   return result;
		   }else {
			   result += "Initial value is: "+ number + "\n";
			   int count = 0;  //initialize the count for how many operations performed
			   while(number >= 1) {
				   if(number == 1) {
					   number = -1; //when number is 1, end the loop
					   result += "Final value 1, number of operations performed " + count;				  
				   }else {
					   if(number%2 == 0) {  //identify a even number
						   number = number/2;
						   result += "Next value is: " + number + "\n";
						   count++;						   
					   }else {              //identify an odd number
						   number = number*3 + 1; 
						   result += "Next value is: " + number + "\n";
						   count++;			   
					   }					   
				   }				   
			   }
			   
		   }
		   return result;
		
	}
	
	/*
	 * This is the main method runs the program.
	 */
	
	
	public static void main(String[] args) {
		   Scanner console = new Scanner(System.in);
		   System.out.print("Input a positive interger, please: ");
		   int number = console.nextInt();  //ask for a positive integer from user
           System.out.println(problem1method(number));
	}
}




