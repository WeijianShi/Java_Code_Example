/**
 * Weijian Shi 
 * weijianshi@brandeis.edu
 * Course: COSI_12B
 * February 4, 2022
 * PA1
 * Class description: this program converts a positive number less than 4999 to Roman numerals.
 */
package PA1;
import java.util.*;
public class Problem_3 {
	
	/*
	 * This is a method converting a positive integer (smaller than 4999) to Roman numerals
	 * @param num: the positive integer inputed by user (smaller than 4999)
	 */
	public static String problem3method(int num) {
		    
		   //initialize the final result (a string).
		   String result = "";
		   
		   //start from the thousand digit, transfer to Roman numerals.
		   if(num >= 1000 && num <= 4999) {
			   int thousands_digit = num/1000;
			   for(int i=0; i < thousands_digit; i++) {
				   result = result + "M";
			   }
			   num = num%1000;
		   }
		   
		   //transfer the hundred digit to Roman numerals.
		   if(num >= 100 && num <= 999) {
			   int hundreds_digit = num/100;
			   if(hundreds_digit <= 3) {
				   for(int i=0; i < hundreds_digit; i++) {
					   result = result + "C";
			   }
		   }
			   if(hundreds_digit == 4) {
				   result = result + "CD";
        }
			   if(hundreds_digit >= 5 && hundreds_digit<9) {
				   result = result + "D";
				   if (hundreds_digit-5>0) {
					   for(int i = 0;i<hundreds_digit-5;i++) {
						   result = result + "C";
					   }   
				   }				   
        }
			   if(hundreds_digit==9) {
				   result = result + "CM";
			   }
			   num = num%100;
		   } 
		   
		   //transfer the tenth digit to Roman numerals.
		   if(num >= 10 && num <= 99) {
			   int tens_digit = num/10;
			   if(tens_digit <= 3) {
				   for(int i=0; i < tens_digit; i++) {
					   result = result + "X";
			   }
		   }
			   if(tens_digit == 4) {
				   result = result + "XL";
        }
			   if(tens_digit >= 5 && tens_digit<9) {
				   result = result + "L";
				   if (tens_digit-5>0) {
					   for(int i = 0;i<tens_digit-5;i++) {
						   result = result + "X";
					   }   
				   }				   
        }
			   if(tens_digit==9) {
				   result = result + "XC";
			   }
			   num = num%10;
		   }
		   
		   //transfer the ones digit to Roman numerals.
		   if(num >= 1 && num <= 9) {
			   int ones_digit = num/1;
			   if(ones_digit <= 3) {
				   for(int i=0; i < ones_digit; i++) {
					   result = result + "I";
			   }
		   }
			   if(ones_digit == 4) {
				   result = result + "IV";
        }
			   if(ones_digit >= 5 && ones_digit<9) {
				   result = result + "V";
				   if (ones_digit-5>0) {
					   for(int i = 0;i<ones_digit-5;i++) {
						   result = result + "I";
					   }   
				   }				   
        }
			   if(ones_digit==9) {
				   result = result + "IX";
			   }
			   num = num%1;
		   }
		   return result;
		
	}
	
	
	
	/*
	 * This is the main method runs the program.
	 */
	public static void main(String[] args) {
		   Scanner console = new Scanner(System.in);
		   System.out.print("Input a positive interger, please: ");
		   int num = console.nextInt();
		   //Prompt the user to input a integer between 1 and 4999.
		   while(num>4999 || num < 1) {
			   System.out.print("Input a positive interger not bigger than 4999, please: ");
			   num = console.nextInt();			   
		   } 
		   
		   
		   System.out.println(problem3method(num));

	}	
}




