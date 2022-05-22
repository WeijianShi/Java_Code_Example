/**
* Weijian Shi
* weijianshi@brandeis.edu
* April 8th, 2022
* PA5
* This class is a test class for ArrayIntList
* Known Bugs: NA
*/

package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.ArrayIntList;



class ArrayIntListTest {
	@Test
	void test1() {
		ArrayIntList arraylist = new ArrayIntList(); //default constructor, create an array with space 10
		arraylist.add(0);
		arraylist.add(1);
		arraylist.add(5);
		arraylist.add(28);
		arraylist.add(35);
		arraylist.add(15);
		arraylist.add(9);
		arraylist.add(10);
		arraylist.add(7);
		arraylist.add(0);
		assertEquals(arraylist.toString(), "[0, 1, 5, 28, 35, 15, 9, 10, 7, 0]");
		arraylist.add(7); //here the space is not enough, so use addspace
		assertEquals(arraylist.toString(), "[0, 1, 5, 28, 35, 15, 9, 10, 7, 0, 7]");
		assertEquals(arraylist.getcap(), 0); //there is no available space
		arraylist.add(5, 15); //add 15 at the index 5
		assertEquals(arraylist.toString(), "[0, 1, 5, 28, 35, 15, 15, 9, 10, 7, 0, 7]");
		arraylist.add(0, 1); //add 1 at the beginning of the list
		
		assertEquals(arraylist.toString(), "[1, 0, 1, 5, 28, 35, 15, 15, 9, 10, 7, 0, 7]");
		assertEquals(arraylist.get(3), 5);
		assertEquals(arraylist.indexOf(7), 10);
		assertEquals(arraylist.indexOf(3), -1); //no 3 in the list, so return -1
		arraylist.remove(3); //remove the value at index 3
		assertEquals(arraylist.toString(), "[1, 0, 1, 28, 35, 15, 15, 9, 10, 7, 0, 7]");
		assertEquals(arraylist.size(), 12);
		assertEquals(arraylist.contains(28), true);
		assertEquals(arraylist.contains(100), false);
		/*
		 * Test ensureCapacity, with getdfcapacity
		 */
		assertEquals(arraylist.getcap(), 1);
		arraylist.ensureCapacity(10); //currently 13 spaces in total so 10<13, no extra space needed
		assertEquals(arraylist.getcap(), 1);
		assertFalse(arraylist.isEmpty());
		arraylist.clear(); //remove all the elements
		assertEquals(arraylist.toString(), "[]");
		assertThrows(IndexOutOfBoundsException.class, () -> arraylist.checkIndex(-1, 0, 1));
		assertThrows(IndexOutOfBoundsException.class, () -> arraylist.add(arraylist.size(), 1)); //add 1 at the end of the list
		arraylist.add(0);
		assertEquals(arraylist.toString(), "[0]");
		
		
	}

	@Test
	void test2() {
		ArrayIntList arraylist1 = new ArrayIntList(7); //create an empty list with 7 available space
		arraylist1.add(3);
		arraylist1.add(0);
		arraylist1.add(3);
		assertEquals(arraylist1.toString(), "[3, 0, 3]");
		arraylist1.add(10);
		arraylist1.add(0);
		assertEquals(arraylist1.toString(), "[3, 0, 3, 10, 0]");
		assertEquals(arraylist1.getcap(), 2);
		assertEquals(arraylist1.size(), 5);
		assertEquals(arraylist1.get(0), 3);
		assertEquals(arraylist1.indexOf(0), 1);
		assertEquals(arraylist1.indexOf(5), -1);
		arraylist1.remove(4);
		assertEquals(arraylist1.getcap(), 3);
		assertEquals(arraylist1.toString(), "[3, 0, 3, 10]");
		assertEquals(arraylist1.size(), 4);
		assertEquals(arraylist1.contains(3), true);
		assertEquals(arraylist1.contains(1), false);
		/*
		 * Test ensureCapacity, with getdfcapacity
		 */
		arraylist1.ensureCapacity(12);
		assertEquals(arraylist1.getcap(), 8);
		arraylist1.remove(0); //remove the first element
		assertEquals(arraylist1.toString(), "[0, 3, 10]");
		assertFalse(arraylist1.isEmpty());
		arraylist1.clear();
		assertEquals(arraylist1.toString(), "[]");
		assertThrows(IndexOutOfBoundsException.class, () -> arraylist1.get(5));
	}
	
	
	
	@Test
	void test3() {
		ArrayIntList arraylist2 = new ArrayIntList(0); //create an empty list with 7 available space
		assertEquals(arraylist2.toString(), "[]");
		assertThrows(IndexOutOfBoundsException.class, () -> arraylist2.get(0)); //exception since there is no index 0 in an empty list
		assertThrows(IndexOutOfBoundsException.class, () -> arraylist2.add(0, 2)); //out of bound 
		arraylist2.add(0); //add 0 at index 0
		assertEquals(arraylist2.toString(), "[0]");
		arraylist2.add(0, 13);
		assertEquals(arraylist2.toString(), "[13, 0]");
		assertThrows(IndexOutOfBoundsException.class, () -> arraylist2.add(2, 2));

	}
	


}


