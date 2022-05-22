/**
* Weijian Shi
* weijianshi@brandeis.edu
* April 2nd, 2022
* PA5
* This is an ArrayIntList class which have the functionality of the arraylist
* Known Bugs: NA
*/
package main;

/**
 * This is a class hold the functionality of the arraylist.
 * @author Weijian Shi
 */
public class ArrayIntList {
	
	/**
	 * This class has 3 fields
	 * dfcapacity: the capacity of the current list, which means the number of elements we can add to without adding new space
	 * 
	 * NOTE!!!!!!!!!
	 * This dfcapacity is different from capacity. For instance, if I have an default list constructed, the 
	 * capacity is 10. After I add 5 elements, like 1, 2, 3, 4, 5, then the list becomes [1, 2, 3, 4, 5, 0, 0, 0, 0, 0].
	 * Here, my dfcapacity is 5 rather than 10, becuase there is only 5 spots AVAILABLE (able to add new elements). 
	 * The capacity is 10, since there is 10 SPACES in total, either able or unable to add new element. 
	 * 
	 * 
	 * digitcount: the size of the list, which means how many elements are currently in the list
	 * dfarraylist: the arraylist built by array
	 */
	private int dfcapacity;
	private int digitcount;
	private int[] dfarraylist;
	
	
	
	
	/**
	 * This is a generic constructor 
	 */
	public ArrayIntList() {
		this.digitcount = 0; //initialize the digitcount(size) to 0 since no elements there yet
		this.dfcapacity = 10; //default capacity
		this.dfarraylist = new int[dfcapacity]; 
	}
	
	
	/**
	 * This is a  constructor setting an empty list with given capacity
	 */
	public ArrayIntList(int capacity) {
		checkCap(capacity); //check if the capacity is legal (>=0), if not, throw exception
		this.digitcount = 0;
		this.dfcapacity = capacity; //set the capacity to the inputed value
		this.dfarraylist = new int[dfcapacity];
			
		
	}
	
	
	/**
	 * This is a get method for dfcapacity
	 */
	public int getcap() {
		return dfcapacity;
	}
	
	/**
	 * This is a set method for dfcapacity
	 */
	public void setcap(int value) {
		this.dfcapacity = value;
	}
	
	/**
	 * This is a get method for digitcount
	 */
	public int size() {
		return this.digitcount;
	}
	
	/**
	 * This is a get method for digitcount
	 */
	public void setsize(int value) {
		this.digitcount = value;
	}
	
	/**
	 * This is a get method for dfarraylist
	 */
	public int[] getlist() {
		return dfarraylist;
	}
	
	
	/**
	 * This is a set method for the list
	 */
	public void setlist(int[] list) {
		this.dfarraylist = list;
	}
	
	/**
	 * This is an add method which append given value to end of list
	 */
	public void add(int value) {
		ensureCapacity(digitcount + 1);
		this.dfarraylist[digitcount] = value;
		this.digitcount += 1; //one more element added so the size grow by 1
		this.dfcapacity -= 1; //the increase decrease by 1 since the new element is added there
		
	
	}
	
	/**
	 * This is a insert method that inserts given value at given index, shifting subsequent values right
	 */
	public void add(int index, int value) {
		checkdigit(); //if there is no element, directly throw exception
		checkIndex(index, 0, digitcount-1); //check whether the inputed index is legal, if not, throw exception
		ensureCapacity(digitcount + 1); //the capacity should be enough to store original elements, and the new element. If sufficient, do nothing, If not, add one space. 
		for(int i = this.digitcount-1; i > index-1; i--) { //do the loop from the end of the current list
			this.dfarraylist[i+1] = this.dfarraylist[i]; //shift subsequent values right by 1 space
		}
		this.dfarraylist[index] = value; //put the value in the indexed position
		this.digitcount += 1;
		this.dfcapacity -= 1;
			
	}
	
	
	/**
	 * This is a get method returns the element at the given index
	 */
	public int get(int index) {
		checkdigit();
		checkIndex(index, 0, digitcount-1);
		return dfarraylist[index];
	}
	
	/**
	 * This is a indexof method that return the index of first occurence of given value
	 */
	public int indexOf(int value) {
		for(int i = 0; i < digitcount; i++) { //search for the value
			if(dfarraylist[i] == value) { 
				return i;//return its index if found
			}
		}
		return -1; //if no such value, return -1
	}
	
	
	/**
	 * This is a remove method that remove the value at given index, shifting subsequent values left.
	 */
	public void remove(int index) {
		checkdigit();
		checkIndex(index, 0, digitcount-1);
		for(int i = index+1; i < digitcount; i++) { //shifting subsequent values left by 1 space
			dfarraylist[i-1] = dfarraylist[i];
		}
		digitcount -= 1;
		dfcapacity += 1;
	}

	
	
	/**
	 * This is a toString method that returns a string version of the list
	 */
	public String toString() {
		if(this.digitcount == 0) {
			return "[]"; 
		}else {
			String result = "[";
			for(int i = 0; i < digitcount-1; i++) {//if there is n elements, we will only append fist n-1 of them first, since we don't want there is "," between the last element and "]"
				result += dfarraylist[i];
				result += ", ";
			}
			result += dfarraylist[digitcount-1]; //append the last element, without "," this time
			result += "]";
			return result;
			
		}

	}
	
	/**
	 * This is a clear method that removes all elements from this list
	 */
	public void clear() {
		for(int i = 0; i < digitcount; i++) {
			dfarraylist[i] = 0; //remove all the elements from the list
		}
		this.dfcapacity += digitcount; //capacity will increase by the size of the list
		this.digitcount = 0; //no elements anymore so the size is 0
	}
	

	
	
	/**
	 * This is a contais method that returns true if the given value is contained in this list, else false
	 */
	public boolean contains(int value) {
		for(int i = 0; i < digitcount; i++) {
			if(dfarraylist[i] == value) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * This is method ensure the capacity of the list is large enough to store at least the given nunmber of elements
	 */
	public void ensureCapacity(int capacity) {
		if(dfarraylist.length < capacity) { 
			addspace(capacity - dfarraylist.length);	//if the space needed is bigger than the current size of list and the rest of available spaces, add the needed space
		}
	}
	
	
	/**
	 * This is a check empty method
	 */
	public boolean isEmpty() {
		return this.digitcount == 0;
	}
	
	
	/**
	 * This is a check index method 
	 */
	public void checkIndex(int index, int min, int max) {
		if(digitcount == 0 && index > 0) { //if the list is empty, if the index is greated than 0, it is illegal
			throw new IndexOutOfBoundsException();
		}else if(index < min || index > max) {
			throw new IndexOutOfBoundsException();
		}

	}
	
	/**
	 * This is a check capacity method 
	 */
	public void checkCap(int capacity) {
		if(capacity < 0 ) {
			throw new IllegalArgumentException();	 
		}
	}
	
	
	/**
	 * This is a check digitcount method, if the digitcount is 0, then any index will be out of bound 
	 */
	public void checkdigit() {
		if(this.digitcount == 0 ) {
			throw new IndexOutOfBoundsException();	 
		}
	}
	
	
	/**
	 * This is a method adding certain number of spaces at the end of array. 
	 */
	public void addspace(int spacesnum) {
		this.dfcapacity += spacesnum;
		int[] temparraylist = new int[dfcapacity + digitcount]; //buils an array who have more space as required (but it's empty)
		/*
		 * copy and paste the original list to the new list, with each element at its original position
		 */
		for(int i = 0; i < digitcount; i++) {
			temparraylist[i] = this.dfarraylist[i];
		}
		this.dfarraylist = temparraylist;
	}
	
	

	
	
	
}
