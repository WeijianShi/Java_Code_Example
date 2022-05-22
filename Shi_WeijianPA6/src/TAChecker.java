/**
* Weijian Shi
* weijianshi@brandeis.edu
* April 27nd, 2022
* PA6
* This is an TAchecker class which will get the information for every single TA existed in the txt file, and check all the fraud
* Known Bugs: NA
*/
import java.io.*;
import java.util.*;

/**
 * This is an TAchecker class which will get the information for every single TA existed in the txt file, and check all the fraud
 * @author Weijian Shi
 */
public class TAChecker {
	
	
	/**
	 * we need 8 fields here:
	 * uniqueTA: all the TA's name in the file
	 * TAinfo: store all the info for every TA in the file
	 * allTAID: storing all the TA's ID associated to the sequence of start
	 * IDsubmitted: ID purely according to their submitted time in the file
	 * inputfile: the input file
	 * batchindicator: indicate whether the ID is in a batch. If no, it will be 0 but if yes, it will be the line number in which the batch is located
	 * line: recording the line number of those lines with "START", according to the original sequence.
	 * allFraud: list storing all the fraud
	 */
	private static Set<String> uniqueTA;
	private static Map<String, TARecord> TAinfo;
	private static int[] allTAID;
	private static ArrayList<Integer> IDsubmitted;
	private static String inputfile;
	private static ArrayList<Integer> batchindicator;
	private static ArrayList<Integer> line;
	private static ArrayList<String> allFraud;
	
	
	

	/**
	 * Method scans through the txt file and read all the ID purely according to their submitted time in the file
	 * THIS IS VERY DIFFERENT FROM ALLTAID!!!!!
	 * @throws FileNotFoundException: if the file is not found
	 */
	public static void IDsubmitted() throws FileNotFoundException {
		IDsubmitted = new ArrayList<Integer>();
		Scanner input=new Scanner(new File(inputfile));
		while(input.hasNextLine()) {
			
			String info = input.nextLine();
			if(!info.substring(semicomma(info)+1).equals("START")) {
				if(getCommaPos(info).size() == 0) {
					IDsubmitted.add(Integer.parseInt(info.substring(semicomma(info)+1)));
				}else {
					ArrayList<Integer> batchID = new ArrayList<Integer>();
					batchID.add(Integer.parseInt(info.substring(semicomma(info)+1, getCommaPos(info).get(0))));
					for(int i = 1; i < getCommaPos(info).size(); i++) {
						batchID.add(Integer.parseInt(info.substring(getCommaPos(info).get(i-1)+1,getCommaPos(info).get(i))));
					}
					batchID.add(Integer.parseInt(info.substring(getCommaPos(info).get(getCommaPos(info).size()-1)+1)));
					sort(batchID);
					for(int i = 0; i < getCommaPos(info).size()+1; i++) {
						IDsubmitted.add(batchID.get(i));
					}
				}
			}
		}
	}
	
	
	
	/**
	 * Method finding the ID breaking the ascending order in the file. Just based on the submitted time sequence, rather than based on 
	 * the sequence of their correponding start.
	 * @param IDsubmitted: ID purely according to their sequence in the file
	 * @return descendingID: the ID breaking the ascending order based on the sequence of ID apperance
	 */
	public static Set<Integer> descendingID(ArrayList<Integer> IDsubmitted){
		Set<Integer> descendingID  = new HashSet<Integer>();
		for(int i = 0; i < IDsubmitted.size(); i++) {
			for(int j = i+1; j < IDsubmitted.size(); j++) {
				if(IDsubmitted.get(j) < IDsubmitted.get(i)) {
					descendingID.add(IDsubmitted.get(j));
				}
			}
		}
		return descendingID;
	}
	
	
	
	
	
	
	/**
	 * Method scans through the .txt file and creates a TARecord for each unique TA
	 * @throws FileNotFoundException: if the file is not founded 
	 */
	public static void sortWorkLog() throws FileNotFoundException {
		TAinfo = new HashMap<String, TARecord>(); //create a hashmap to store the info for all TAs
		allFraud = new ArrayList<String>();
		Scanner input=new Scanner(new File(inputfile));
		while(input.hasNextLine()) {
			String firstTA=input.nextLine();
			firstTA = firstTA.substring(0, semicomma(firstTA));
			if(!TAinfo.containsKey(firstTA)) {
				TARecord TA=new TARecord(firstTA); 
				TA.TAname(inputfile); //generate the TAname list
				TA.TAlist(inputfile); //generate the TAlist containing the in
				for(int j = 0; j < (TA.getUnstartFraud()).size(); j++) {
					allFraud.add((TA.getUnstartFraud()).get(j)); 
				}
				TAinfo.put(firstTA, TA); //put the TA's info into the map
			}
		}
	}


	/**
	 * A method creating an arraylist for storing all the TA's ID according to the sequence of start.
	 * @param TAinfo: the hashmap storing all the TAs' info
	 */
	public static void allTAID(Map<String, TARecord> TAinfo) {
		uniqueTA = TAinfo.keySet(); //the key set of TAinfo hashmap is all TAs' names, without duplication.
		Iterator<String> itr = uniqueTA.iterator();
		if(itr.hasNext()) {  
			TARecord rec = TAinfo.get(itr.next()); //use a TA's name to construct an TARecord that contains the ID list
			/*
			 * the size of ID list equals to the total number of ID. Initialize the allTAID array having enough spaces 
			 * to store all IDs. 
			 * NOTE!!!!!!!!! the shortened job's ID won't be stored. Because they will be reported as an error and then ignored 
			 */
			allTAID = new int[rec.getID().size()]; 

		}
		itr = uniqueTA.iterator();
		while(itr.hasNext()) { //iterate through the hashmap and put all the ID according to its original sequence (sequence in the txt file)
			ArrayList<Integer> individualID = TAinfo.get(itr.next()).getID();
			for(int i = 0; i < individualID.size(); i++) {
				if(individualID.get(i) != -1) {
					allTAID[i] = individualID.get(i); //copy each TA's ID into the allTAID list, at the same location as they were in the ID list
				}
			}
		}
		
		
	}
	/**
	 * Create a arraylist recording the line number of those lines with "START", according to the original sequence.
	 * @throws FileNotFoundException: if the file is not found 
	 */
	public static void line() throws FileNotFoundException {
		line = new ArrayList<Integer>();
		Scanner input=new Scanner(new File(inputfile));
		int linecount = 0;
		while(input.hasNextLine()) {
			linecount++;
			String startline=input.nextLine();
			if(startline.substring(semicomma(startline)+1).equals("START")){
				line.add(linecount);
			}	
		}	
	}
	
	

	/**
	 * This is a method create a batch indicator, indicating which ID is from a batch (two or more ID in a line)
	 * @throws FileNotFoundException: if the file is not found
	 */
	public static void batchindicate() throws FileNotFoundException {
		
		batchindicator = new ArrayList<Integer>(); 
		for(int i = 0; i < allTAID.length; i++) {
			batchindicator.add(0); //initialize the list which has the same length with allTAID
		}
		Scanner input=new Scanner(new File(inputfile));
		int linecount = 0;
		while(input.hasNextLine()) {
			ArrayList<Integer> batch = new ArrayList<Integer>();
			linecount++;
			String TAinfo=input.nextLine();
			/*
			 * get the ID if it's in a batch
			 */
			if(getCommaPos(TAinfo).size() != 0) {
				batch.add(Integer.parseInt(TAinfo.substring(semicomma(TAinfo)+1, getCommaPos(TAinfo).get(0))));
				for(int i = 1; i < getCommaPos(TAinfo).size(); i++) {
					batch.add(Integer.parseInt(TAinfo.substring(getCommaPos(TAinfo).get(i-1)+1,getCommaPos(TAinfo).get(i))));
				}
				batch.add(Integer.parseInt(TAinfo.substring(getCommaPos(TAinfo).get(getCommaPos(TAinfo).size()-1)+1)));
				/*
				 * indicate the ID is batch with the line number in which the ID is located,
				 * the indicator's location in the batchindicator will be the same with the batch ID's location in the allTAID
				 */
				for(int i = 0; i < batch.size(); i++) {
					if(indexof(allTAID, batch.get(i)) != -1) {
						batchindicator.set(indexof(allTAID, batch.get(i)), linecount); 
					}
				}
			}	
		}
	}
	

	/**
	 * Method check the validity of the billing info, telling all the error and print them out
	 * @throws FileNotFoundException
	 */
	public static void checkValidity() throws FileNotFoundException {
		ArrayList<Integer> b = batchptf(potentialfraud(allTAID));
		ArrayList<Integer> l = lineptf(potentialfraud(allTAID));
		for(int i = 0; i < potentialfraud(allTAID).size(); i++) {
			String result = "";
			if(b.get(i) == 0) {
				result += l.get(i);
				result += ";";
				result += getname(l.get(i));
				result += ";";
				result += "SHORTENED_JOB";
				allFraud.add(result);
				System.out.println(result);
			}else {
				if(countbatchptf(b.get(i), b) == countbatch(b.get(i))) { //every ID is shortened in a certain batch
					for(int j = 0; j < countbatchptf(b.get(i), b); j++) {
						result += l.get(i + j);
						result += ";";
						result += getname(l.get(i + j));
						result += ";";
						result += "SHORTENED_JOB";
						allFraud.add(result);
						System.out.println(result);
						result  = "";
						 
					}
					i += countbatchptf(b.get(i), b)-1; 
				}else {
					result += b.get(i);
					result += ";";
					result += getname(b.get(i));
					result += ";";
					result += "SUSPICIOUS_BATCH";
					allFraud.add(result);
					System.out.println(result);
					result  = "";
				}
				i += countbatchptf(b.get(i), b)-1; 
			}
		}
	}
	
	/**
	 * Method get the name in a certain line number. 
	 * @param allTAID: all the TA's ID, according to the sequence in the file
	 * @return: name in that line
	 * @throws FileNotFoundException: if the file is not found
	 */
	public static String getname(int linenumber) throws FileNotFoundException {
		Scanner input=new Scanner(new File(inputfile));
		int linecount = 0;
		while(input.hasNextLine()) {
			linecount++;
			String TAinfo=input.nextLine();
			if(linecount == linenumber) {
				return TAinfo.substring(0, semicomma(TAinfo));
			}
		}
		return "";
	}
	
	
	/**
	 * The method return an arraylist containing the ID in allTAID which break the ascending order.
	 * @param allTAID: all TAs' ID
	 * @return: the list containing the ID which break the ascendeing order
	 */
	public static ArrayList<Integer> potentialfraud(int[] allTAID){
		ArrayList<Integer> ptf = new ArrayList<Integer>();
		/*
		 * use double for loop to identify which ID is breaking the increasing pattern. But!!!!!!!
		 * This is super important!!!!!!!
		 * Breaking ascending order is not enough. It may not be abot to handle this case: 
		 * Wes;START
		 * William;START
		 * Wes;START
		 * William;4
		 * Wes;5, 6
		 * In this case, nothing is submitted before 4 so there should not be a problem. To handle this,
		 * we need to make sure the ID added into the potentialfraud list is also breaking ascending order on the 
		 * aspect of "submitted time"
		 */
		
		for(int i = 0; i < allTAID.length; i++) {
			for(int j = i+1; j < allTAID.length; j++) {
				if(allTAID[j] < allTAID[i] && !ptf.contains(allTAID[j]) && descendingID(IDsubmitted).contains(allTAID[j])) {
					ptf.add(allTAID[j]);
				}
			}
		}
		return ptf;
	}
	
	
	/**
	 * Method return the batch indicator for all the ID in the ptf
	 * @param ptf: the list containing the ID which break the ascendeing order
	 * @return: the batch indicator for all the ID in the ptf
	 */
	public static ArrayList<Integer> batchptf(ArrayList<Integer> ptf){
		ArrayList<Integer> batchptf = new ArrayList<Integer>();
		for(int i = 0; i < ptf.size(); i++) {
			for(int j = 0; j < allTAID.length; j++) {
				if(allTAID[j] == ptf.get(i)) {
					batchptf.add(batchindicator.get(j));
				}
			}
		}
		return batchptf;
	}
	

	/**
	 * Method that show the line number (for the start) associated with the ID in ptf
	 * @param ptf: the list containing the ID which break the ascendeing order
	 * @return: the line number of start associated with the ID in ptf
	 */
	public static ArrayList<Integer> lineptf(ArrayList<Integer> ptf){
		ArrayList<Integer> lineptf = new ArrayList<Integer>();
		for(int i = 0; i < ptf.size(); i++) {
			for(int j = 0; j < allTAID.length; j++) {
				if(allTAID[j] == ptf.get(i)) {
					lineptf.add(line.get(j)); //add the line number
				}
			}
		}
		return lineptf;
	}
	

	/**
	 * The method count the number of certain indicator (presented by the line number of batch) in batchindicator
	 * @param target: the indicator we want to count
	 * @return: the number of such indicator
	 */
	public static int countbatch(int target) {
		int count = 0;
		for(int i = 0; i < batchindicator.size(); i++) {
			if(batchindicator.get(i) == target) {
				count++;
			}
		}
		return count;
	}
	
	

	/**
	 * Method that count the number of certain indicator (presented by the line number of batch) in batchptf
	 * @param target: the indicator we want to count
	 * @param batchptf: the list contain the batch indicator for all the ID in ptf
	 * @return: the number of such indicator
	 */
	public static int countbatchptf(int target, ArrayList<Integer> batchptf) {
		int countptf = 0;
		for(int i = 0; i < batchptf.size(); i++) {
			if(batchptf.get(i) == target) {
				countptf++;
			}
		}
		return countptf;
	}
	


	/**
	 * Method locate the comma in a string
	 * @param input: the string 
	 * @return: the list of position of comma in a string
	 */
	public static ArrayList<Integer> getCommaPos(String input) {
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
	 * This is a method find the index of ';'
	 * @param input: the string contains ';'
	 * @return: the index of ';'
	 */
	public static int semicomma(String input) {
		for(int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == ';') {
				return i;
			}
		}
		return -1;
	}
	

	/**
	 * Method that find the index of a certain ID
	 * @param source: the array contains such certain ID
	 * @param target: the ID we want to find
	 * @return: the index of such unique ID 
	 */
	public static int indexof(int[] source, int target) {
		for(int i = 0; i < source.length; i++) {
			if(source[i] == target) {
				return i;
			}
		}
		return -1;
	}
	
	
	
	/**
	 * This is a bubble sort method for arraylist since we did not learn .sort
	 * @param list: the list we would like to sort
	 * @return: the sorted list
	 */
	public static ArrayList<Integer> sort(ArrayList<Integer> list) {
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
     * This is the main method
     * @param args: this is a string array needed for main method
     * @throws FileNotFoundException: if the file name is not founded
	 */
	public static void main(String [] args) throws FileNotFoundException {
		Scanner console = new Scanner(System.in);
		System.out.println("Enter a work log: ");
		inputfile = console.next(); //get the name of the inputfile from user
		IDsubmitted();
		sortWorkLog(); 
		allTAID(TAinfo);
		line();
		batchindicate();
		checkValidity(); //check all the errors
	}
}
