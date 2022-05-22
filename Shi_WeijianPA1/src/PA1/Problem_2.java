/**
 * Weijian Shi 
 * weijianshi@brandeis.edu
 * Course: COSI_12B
 * February 4, 2022
 * PA1
 * Class description: this program prompts the user for a positive integer. then displays the digits one per line
 */
package PA1;

import java.util.*;
public class Problem_2 {
	/*
	 * This is the power method.
	 * @param base: this is an integer, the number that gets multiplied when using an exponent.
	 * @param power: this is an integer, a number says how many times to use the number in a multiplication.
	 * @output: this is an integer, the power of the base number.
	 */
	
	public static int power(int base, int power) {  
		if(power == 0) {
			return 1;
		}
		int result = base;
		while(power > 1) {
			result = result*base;
			power = power-1;
		}
		return result;
	}
	
	/*
	 * This is a method extract every digit of a positive number from left to right and print line by line
	 * @param num: positive integer inputed by user
	 */
	public static String problem2method(int num) {

		   
		   //check how many digits there is in a number
		   int count_digits = 0;
		   int digit = num;
		   while(digit > 0) {
			   digit = digit/10;
			   count_digits++;
		   }
		   
		   

		   //from left to right, extract every digit and print out line by line
		   String res = "";
		   while(count_digits >= 1) {
			   int temp = num/power(10, count_digits-1);
			   res += temp + "\n";
			   num = num%power(10, count_digits-1);
			   count_digits--;
			   
			   
		   }
		   return res;
		   
		
	}

	/*
	 * This is the main method runs the program.
	 */
	public static void main(String[] args) {

		   Scanner console = new Scanner(System.in);
		   System.out.print("Input a positive interger, please: ");
		   int num = console.nextInt();
			while(num<=0) {
				   System.out.print("Input a positive interger, please: ");
				   num = console.nextInt();
				   
			   }
			
		   System.out.println(problem2method(num));
		
	}
	
	

}




