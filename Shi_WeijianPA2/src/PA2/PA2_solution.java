/**
* Weijian Shi
* weijianshi@brandeis.edu
* Feb 10, 2022
* PA2
* This class produce relevant information about the DNA
* Known Bugs: NA 
**/
package PA2;

import java.io.*;
import java.util.*;

public class PA2_solution {
	
	//first class constant: minimum number of codons
	public static final int minimum_number_of_codons = 5;

	//second class constant: valid percentage
	public static final int valid_percentage = 30;
	
	//third class constant: unique nucleotides
	public static final int unique_nucleotides = 4;
	
	//fourth class constant: nucleotides per codon
	public static final int nucleotides_per_codon = 3;
	


	/**
	 * This is method that can read the file line by line
	 * the 1st, 3rd, 5th...line is the region name
	 * the 2nd, 4th, 6th...line is the Nucleotides
	 * @param inputfile: the input file name given by the user
	 * @return result: the final result for the file after all the processing 
	 * @throws FileNotFoundException: if the file name is not founded
	 */
	public static String print_protein_and_DNA_info(String inputfile) throws FileNotFoundException{  //the print method required 
		Scanner input  = new Scanner(new File(inputfile));
		String result = "";
		while (input.hasNextLine()) {
			String protein = input.nextLine();
			result += "Region Name: " + protein + "\n";
			String Nucleotides = input.nextLine().toUpperCase();
			result += "Nucleotides: " + Nucleotides + "\n";
			result += "Nuc. Counts: " + Arrays.toString(countACGT(Nucleotides)) + "\n";
			result += "Total Mass%: " + Arrays.toString(percentageACGT(countACGT(Nucleotides), Nucleotides)) + " of " + Math.round(totalweight(countACGT(Nucleotides), Nucleotides)*10.0)/10.0 + "\n";
			result += "Codons List: " + Arrays.toString(codonlist(getpurecodon(Nucleotides))) + "\n";
			result += "Is Protein?: " + predictprotein(Nucleotides) + "\n";
			result += "\n";		
		}
		return result;	
	}
	
	
	

	/**
	 * This is a method that counts A, C, G, T in the nucleotides and put it into an array
	 * @param nuc: the Nucleotides extracted from the file (file name is inputed by the user) 
	 * @return ACGTnumber: return an size 4 array storing the number of A, C, G, T in the nucleotides
	 */
	
	public static int[] countACGT(String nuc){
		int countA = 0;
		int countC = 0;
		int countG = 0;
		int countT = 0;
		int[] ACGTnumber = new int[unique_nucleotides];
		for(int i = 0; i < nuc.length(); i++) {
			if(nuc.charAt(i) == 'A') {
				countA += 1;
			}
			if(nuc.charAt(i)== 'C'){
				countC += 1;
			}
			if(nuc.charAt(i)== 'G'){
				countG += 1;
			}
			if(nuc.charAt(i)== 'T'){
				countT += 1;
			}
		}
		ACGTnumber[0] = countA;
		ACGTnumber[1] = countC;
		ACGTnumber[2] = countG;
		ACGTnumber[3] = countT;
		
		return ACGTnumber;
	}
	
	
	
	
	
	/**
	 * This is a method that get the sum of a given array
	 * @param array: any array with integer numbers 
	 * @return sum: return the summation of all the values in the array
	 */
	
	public static int summation(int[] array){
		int sum = 0;
		for(int i = 0; i<array.length; i++) {
			sum += array[i];
		}
		return sum;
	}
	
	
	
	

	/**
	 * This is a method that get total mass of the nucleotides
	 * @param array: the array storing the number of A, C, G, T
	 * @param nuc: the Nucleotides extracted from the file (file name is inputed by the user)
	 * @return totalweight: return the total mass rounded to one digit after decimal
	 */
	public static double totalweight(int[] array, String nuc){
		double[] masses = {135.128, 111.103, 151.128, 125.107}; //an array including the mass of A, C, G, T
		double totalweight = (nuc.length() - summation(array))*100.000 + masses[0]*array[0] 
				+ masses[1]*array[1] + masses[2]*array[2] + masses[3]*array[3]; //get the total weight, including A, C, G, T, -
		return totalweight; //return the total mass rounded to one digit after decimal
	}
	
	
	
	

	/**
	 * This is a method that get the percentage of A, C, G, T's mass (over total mass), then store it into an array
	 * @param array: the array storing the number of A, C, G, T
	 * @param nuc: the Nucleotides extracted from the file (file name is inputed by the user)
	 * @return percentage: return an size 4 array storing the mass percentage of A, C, G, T in the nucleotides
	 */
	public static double[] percentageACGT(int[] array, String nuc){ //take in two parameter: array, which stores the number of A, C, G, T; String nuc, which is the nucleotides excluding -
		double[] percentage = new double[unique_nucleotides];
		double[] masses = {135.128, 111.103, 151.128, 125.107};
		for(int i = 0; i < array.length; i++) {
			double num = masses[i]*array[i]/totalweight(array, nuc);
			percentage[i] = Math.round(num*100*10.0)/10.0;
			
		}
		return percentage;
	}
	


	

	/**
	 * This is a method that return Nucleotides without '-', prepare for getting the codon list
	 * @param nuc: the Nucleotides extracted from the file (file name is inputed by the user)
	 * @return newnuc: eliminate '-' in Nucleotides, prepare for getting the codon list
	 */
	public static String getpurecodon(String nuc) {
		// eliminate the "-" in the nucleotides because will be ignored in codon list
		String newnuc = "";
		for(int i = 0; i < nuc.length(); i++) {
			if(nuc.charAt(i) != '-'){
				newnuc += nuc.charAt(i);
			}	
		}
		return newnuc;	
	}
	
	
	
	

	/**
	 * This is a method get the codon list given the nucleotides excluding '-'
	 * @param newnuc: the Nucleotides extracted from the file (file name is inputed by the user), but excluding the '-' inside it。 Which means, there are only A, C, G, T in the newnuc
	 * @return codonlist: return the codon list given the nucleotides excluding '-'
	 */
	public static String[] codonlist(String newnuc) {
		String[] codonlist = new String[newnuc.length()/nucleotides_per_codon]; //new an array with the size equal to the number of codons
		int count = 0;
		for(int i = 0; i<newnuc.length()-2; i += nucleotides_per_codon) { //get 3 nucleotides (no -)per time
			
			String A = "";
			A += newnuc.charAt(i);
			A += newnuc.charAt(i+1);
			A += newnuc.charAt(i+2);
			codonlist[count] = A;
			count += 1;
		}
		return codonlist;	
	}
	
	
	

	/**
	 * This is method predict whether or not the sequence is a protein-coding gene based on 4 rubric
	 * @param nuc: the Nucleotides extracted from the file (file name is inputed by the user)
	 * @return: only return YES when satisfy all 4 requirements, otherwise return NO
	 */
	public static String predictprotein(String nuc) {
		if(nuc.substring(0,nucleotides_per_codon).equals("ATG") //begins with a valid start codon (ATG)
			&& codonlist(getpurecodon(nuc)).length >= minimum_number_of_codons  //contains at least 5 total codons
			&& percentageACGT(countACGT(nuc), nuc)[1]+ percentageACGT(countACGT(nuc), nuc)[2] >= valid_percentage  //C and G combined account for at least 30% of its total mass
			&& (nuc.substring(nuc.length() - nucleotides_per_codon).equals("TAA")  //ends with a valid stop codon (one of the following: TAA, TAG, or TGA)
			||nuc.substring(nuc.length()-nucleotides_per_codon).equals("TAG")
			||nuc.substring(nuc.length()-nucleotides_per_codon).equals("TGA"))){	
			
			return "YES";
		}
		else { //not satisfy all 4 conditions simultaneously
			
			return "NO";
		}

	}
	
	
	
	
    /**
     * This is the main method
     * @param args: this is a string array needed for main method
     * @throws FileNotFoundException: if the file name is not founded
     */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner console = new Scanner(System.in);
		System.out.println("This program reports information about DNA\nnucleotide sequences that may encode proteins.");
		System.out.print("Input file name? ");
		String inputfile = console.next();
		System.out.print("Output file name? ");
		String outputfile = console.next();
		String result = print_protein_and_DNA_info(inputfile); //apply all other methods in print method
		PrintStream out = new PrintStream(new File(outputfile)); //print everything in output file
		out.print(result);
		console.close();
	}

}





