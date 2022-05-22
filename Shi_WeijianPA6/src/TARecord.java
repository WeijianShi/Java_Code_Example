/**
* Weijian Shi
* weijianshi@brandeis.edu
* April 27nd, 2022
* PA6
* This is an TARecord class which will get the information for a certain TA
* Known Bugs: NA
*/
import java.io.*;
import java.util.*;


/**
 * This is a class which will get the information for a certain TA
 * @author Weijian Shi
 */
public class TARecord {
	
	/**
	 * This class have 4 fields:
	 * name: the name of a certain TA (we will only focus on getting one guy's info per time)
	 * TAname: by sequence of name existence, record the names of TA
	 * ID: record all the ID in the file, by sequence
	 * unstartfraud: record the unstarted job, prepare to be combine with other fraud in the TAChecker, and put all these fraud into a list
	 */
	private String name;
	private ArrayList<String> TAname;
	private ArrayList<Integer> ID;
	private ArrayList<String> unstartfraud;

	/**
	 * This method get the list of TAname according to the sequence of their start time
	 * @param inputfile: the name of the input file
	 * @throws FileNotFoundException: if the file is not founded
	 */
	public void TAname(String inputfile) throws FileNotFoundException{
		this.TAname = new ArrayList<String>();
		Scanner input  = new Scanner(new File(inputfile));
		while (input.hasNextLine()) {
			String record = input.nextLine();
			if((record.substring(record.length()-5)).equals("START")) { //check if the TA start
				this.TAname.add(record.substring(0, record.length()-6)); //add TA's name to the list
			}
		}
	}
	
	
	
	/**
	 * A method search for the specific TA's name in the file and then record relative infomation. 
	 * @param inputfile: the name of the input file
	 * @throws FileNotFoundException: if the file is not founded
	 */
	public void TAlist(String inputfile) throws FileNotFoundException{
		this.ID = new ArrayList<Integer>(); //initialize the ID list with -1. We will replace the value when we read the ID.
		for(int i = 0; i < TAname.size(); i++) {
			this.ID.add(-1);
		}
		this.unstartfraud = new ArrayList<String>();
		/*
		 * Initialize the counter for line, ID, start, and all the ID associated with the name provided.
		 */
		int linecount = 0;
		int IDcount = 0;
		int Startcount = 0;
		ArrayList<Integer> IDsorted = new ArrayList<Integer>();
		Scanner input = new Scanner(new File(inputfile));

		while (input.hasNextLine()) {
			linecount++;
			String record = input.nextLine();
			if(record.substring(semicomma(record)+1).equals("START") && record.substring(0, semicomma(record)).equals(this.name)) {
				Startcount++; //count the number of times this TA start helping student
			}
			if(!record.substring(semicomma(record)+1).equals("START") && record.substring(0, semicomma(record)).equals(this.name)) {
				IDcount += countcomma(record) + 1; //count the number of IDs return by this TA
				
				/*
				 * only add the ID to IDlist when the number of start is greater than ID, which is avoiding
				 * adding the unstarted job ID into the IDlist. In another word, only add the ID after the start,
				 * and the number of ID added must be the same with the recorded start's number, after identifying and deleting the unstarted job
				 */
				if(IDcount <= Startcount) {
					if(getCommaPosition(record).size() == 0) { //one number in a line
						IDsorted.add(Integer.parseInt(record.substring(semicomma(record)+1)));
					}else { //multiple number in a line
						/*
						 * Based on the semicomma and comma, add all the ID in a single line into the IDsorted list
						 */
						IDsorted.add(Integer.parseInt(record.substring(semicomma(record)+1, getCommaPosition(record).get(0))));
						for(int i = 1; i < getCommaPosition(record).size(); i++) {
							IDsorted.add(Integer.parseInt(record.substring(getCommaPosition(record).get(i-1)+1,getCommaPosition(record).get(i))));
						}
						IDsorted.add(Integer.parseInt(record.substring(getCommaPosition(record).get(getCommaPosition(record).size()-1)+1)));
						
					}
					/*
					 * For those lines with batch, we need to add all the ID into the ID list from the IDsorted list. To make processing ID list 
					 * easier, we first sort the numbers in IDsorted list, then add them to ID list. 
					 */
					sort(IDsorted); 
					for(int j = 0; j < IDsorted.size(); j++) {
						for(int i = 0; i < TAname.size(); i++) {
							if(TAname.get(i).equals(record.substring(0, semicomma(record))) && ID.get(i) == -1) { //find the first case: name matching this TA's name and the corresponding ID has not been updated yet.  
								ID.set(i, IDsorted.get(j));
								i = TAname.size() + 1; //stop the loop
							}
						}
					}
				/*
				 * when the number of ID is greated than number of START, report unstarted job
				 */
				}else {
					IDcount--; //if it's an unstarted job, should not add such ID into the list so the IDcount will drop by 1. 
					String unstarted = "";
					unstarted += linecount;
					unstarted += ";";
					unstarted += this.name;
					unstarted += ";";
					unstarted += "UNSTARTED_JOB";
					this.unstartfraud.add(unstarted); //put the unstarted into the list
					System.out.println(unstarted);
				}
			}
	
		}

	}
	

	/**
	 * This is a method find the index of ';'
	 * @param input: the string contains ';'
	 * @return: the index of first ';'
	 */
	public int semicomma(String input) {
		for(int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == ';') {
				return i;
			}
		}
		return -1; //of course, there will absolutely a ;, but we still need to write this to make the program work
	}

	

	/**
	 * This is a count method counting the comma in a string
	 * @param input: string to be analyzed
	 * @return: number of comma
	 */
	public int countcomma(String input) {
		int countcomma = 0;
		for (int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == ',') {
				countcomma++;
			}
		}
		return countcomma;
	}
	

	/**
	 *This is a method get the positions of comma in a string
	 * @param input: TA's string 
	 * @return: list of comma's position
	 */
	public ArrayList<Integer> getCommaPosition(String input) {
		ArrayList<Integer> comma = new ArrayList<Integer>();
		int count = 0;
		for(int i = 0; i < input.length(); i++) {
			
			if(input.charAt(i) == ','){
				comma.add(count);
				
			}
			count++;
		}
		return comma;
	}
	
	
	/**
	 * This is a bubble sort method for arraylist since we did not learn .sort
	 * @param list: the list we would like to sort
	 * @return: the sorted list
	 */
	public ArrayList<Integer> sort(ArrayList<Integer> list) {
		int didswap = 1, tmp = 0;
		while(didswap == 1) {
			didswap = 0;
			for(int i = 1; i < list.size(); i++) {
				if(list.get(i-1) > list.get(i)) {
					tmp = list.get(i-1);
					list.set(i-1,list.get(i));
					list.set(i, tmp);
					didswap = 1;
				}
			}
		}
		return list;
	}
	

	
	/**
	 * This is a get method for TAname.
	 * @return: TAname arraylist
	 */
	public ArrayList<String> getTAname(){
		return this.TAname;
	}
	
	/**
	 * This is a get method for ID.
	 * @return: ID arraylist
	 */
	public ArrayList<Integer> getID(){
		return this.ID;
	}
	
	
	/**
	 * This is a get method for unstartedfraud.
	 * @return: unstarted fraud arraylist
	 */
	public ArrayList<String> getUnstartFraud(){
		return this.unstartfraud;
	}


	/**
	 * This is a constructor for the TARecord
	 * @param name: the name of the TA we would like to collect info
	 */
	public TARecord (String name) {
		this.name = name;
	}
	
	
	
	

}
	
	


	


