/**
 * Weijian Shi 
 * weijianshi@brandeis.edu
 * Course: COSI_12B
 * February 4, 2022
 * PA1
 * Class description: this program produces a Caesar cipher of a given message string.
 */
package PA1;

import java.util.Scanner;

public class Problem_4 {
	/*
	 * This is a method produces a Caesar cipher of a given message string.
	 * @param mes: a string inputed by user as the message
	 * @param num: a positive integer inputed by user as the encoding key
	 */
	public static String problem4method(String mes, double num) {
		 String result = "Your message: ";
		 
		 //method when key is greater or equal to 0
		 if (num >= 0) {
			   
			   //iterate all the characters in the message and perform the conversion
			   for (int i = 0;i < mes.length();i++ ) {
				   
				   //apply the ASCII table, to identify the characters
				   if (mes.charAt(i) >= 65 && mes.charAt(i) <= 90) {
					   if (mes.charAt(i) + num%26 >90) {
						   result += (char)(mes.charAt(i) + num%26 - 26);			   
					   }
					   else {
						   result += (char)(mes.charAt(i) + num%26);   
					   }

				   }
				   else {
					   result += mes.charAt(i);
				   }

			   }
			   return result;
		
		 }else {       //method when key is smaller than 0
			   
			   //iterate all the characters in the message and perform the conversion
			   for (int i = 0;i < mes.length();i++ ) {
				   
				   //apply the ASCII table, to identify the characters
				   if (mes.charAt(i) >= 65 && mes.charAt(i) <= 90) {
					   if (mes.charAt(i) + num%26 <65) {
						   result += (char)(mes.charAt(i) + num%26 + 26);			   
					   }
					   else {
						   result += (char)(mes.charAt(i) + num%26);   
					   }

				   }
				   else {
					   result += mes.charAt(i);
				   }

			   }
			   return result;
		 }

	}
	/*
	 * This is the main method runs the program.
	 */

	public static void main(String[] args) {
		   Scanner console = new Scanner(System.in);
		   //ask for a string input from user
		   System.out.print("Your message? ");
		   String mes = console.nextLine().toUpperCase();
		   //ask for a integer encoding key input from user
		   System.out.print("Encoding key? ");
		   double num = console.nextDouble();
		   System.out.println(problem4method(mes, num));;

	}

}
