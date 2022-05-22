/**
* Weijian Shi
* weijianshi@brandeis.edu
* April 2nd, 2022
* PA5
* This class is an SortedIntList which is the variation of the ArrayIntList
* Known Bugs: NA
*/
package main;

import java.util.*;

/**
 * This is a class hold the functionality of the SortedIntList.
 * @author Weijian Shi
 */
public class SortedIntList extends ArrayIntList {
	
	/**
	 * This class only have one field, which is the switch for whether duplicate is allowed
	 */
	private boolean unique;

	/**
	 * This is a default constructor that allow duplicates, with capacity 10.
	 */
	public SortedIntList() {
		super();
		this.unique = false;
	}
	
	
	/**
	 * This is a method initialize a list of default capacity (10) with uniqueness set to the given value.
	 */
	public SortedIntList(boolean unique) {
		super();
		this.unique = unique;
	}
	
	/**
	 * This constructor should initialize a list with the given capacity and with uniqueness set to false
	 */
	public SortedIntList(int capacity) {
		super(capacity);
		this.unique = false;
		
	}
	
	/**
	 * This constructor should initialize a list with the given capacity and with the given setting for 
	 * whether or not to limit the list to unique values
	 */
	public SortedIntList(boolean unique, int capacity) {
		super(capacity);
		this.unique = unique;
	}
	
	
	/**
	 * This is a get method that return the current uniqueness setting
	 */
	public boolean getUnique() {
		return this.unique;
	}

	
	/**
	 * This method lets client decide whether to allow duplicates in the list 
	 */
	public void setUnique(boolean unique) {
		this.unique = unique;
		if(unique) {
			removeDup(super.getlist());
		}
		
	}
	
	
	/**
	 * The single argument add method. (override the add method in ArrayIntList
	 */
	public void add(int value) {
		int position = Arrays.binarySearch(super.getlist(), 0, super.size(), value);
		if(!this.unique) { //allow for duplicates
			if(super.size() == 0) {
				super.add(value);
			}else {
				if(position < 0) { //if there is not such element in the list
					/*
					 * this if condition is super important!!!!!!!!!!!
					 * In arrayintlist, we don't allow using add(index, value) to append
					 * element at the end of list. But here we need this function so we directly use add
					 * method to append
					 */
					if(-position-1 == super.size()) { 
						super.add(value);
					}else {
						super.add(-position-1, value); //insert the element in the correct place
					}
				}else {
					if(position == super.size()) {
						super.add(value);
					}else {
						super.add(position, value);
					}
				}
			}

		}else { //no duplicates
			if(super.size() == 0) {
				super.add(value);
			}else {
				if(position < 0) { //only add the value when its position returned by binary search is negative (which means no such element in the list-no duplicates)
					if(-position-1 == super.size()) {
						super.add(value);
					}else {
						super.add(-position-1, value);
						
					}
				}	
			}
		}
	}
	
	
	/**
	 * This is a method add a value to the certain index. It should not be here so we override it
	 */
	public void add(int index, int value) {
		throw new UnsupportedOperationException();
	}
	

	
	
	/**
	 * This is a bubble sort method
	 */
	public void sort(int[] list) {
		int didswap = 1, tmp = 0;
		while(didswap == 1) {
			didswap = 0;
			for(int i = 1; i < super.size(); i++) {
				if(list[i-1] > list[i]) {
					tmp = list[i-1];
					list[i-1] = list[i];
					list[i] = tmp;
					didswap = 1;
				}
			}
		}
	}
	
	
	/**
	 * This is a method that will remove the duplicates in a sorted array
	 */
	public void removeDup(int[] dfarraylist) {
		sort(dfarraylist); //sort the list first
		int[] temp = new int[dfarraylist.length]; //create a temporary array store the unique values by sequence. 
		int current = 0;
		temp[0] = dfarraylist[current]; //put the first element in the original list to the new array
		for(int i = 1; i < super.size(); i++) { //go over every element
			if(dfarraylist[i] != temp[current]) { //only add the unique values in the new array
				current += 1;
				temp[current] = dfarraylist[i]; //assign the new array back to the original list
				
			}
		}
		super.setsize(current + 1); //how many elements in the new array after removing the duplicates, +1 because it starts from 0
		super.setcap(dfarraylist.length - super.size()); //how many capacity is left after removing the duplicates
		super.setlist(temp);
		
	}
	
	
	
	/**
	 * This method return the largest element in the list
	 */
	public int max() {
		if(super.size() == 0) { //edge case, no element at all
			throw new NoSuchElementException();
		}else { //sort first then return the last element
			sort(super.getlist());
			return super.getlist()[super.size()-1]; //the list has been sorted and the last element is the max	
		}
	}
	
	
	/**
	 * This method return the smallest element in the list
	 */
	public int min() {
		if(super.size() == 0) {
			throw new NoSuchElementException();
		}else {
			sort(super.getlist());
			return super.getlist()[0]; //the list has been sorted and the first element is the min	
		}
	}
	
	
	
	/**
	 * This method is a to string method
	 */
	public String toString() {
		String sortresult = "";
		if(!unique) {
			sortresult += "S:";
			sortresult += super.toString();	
		}else {
			sortresult += super.toString();
			sortresult += "U";
		}
		return sortresult;
	}
	
	
	
	/**
	 * This is a indexOf method that use binary search to find the element's position
	 */
	public int indexof(int value) {
		return Arrays.binarySearch(super.getlist(), 0, super.size(), value);
	}
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
