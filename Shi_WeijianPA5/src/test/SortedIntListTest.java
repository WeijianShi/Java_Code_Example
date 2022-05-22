/**
* Weijian Shi
* weijianshi@brandeis.edu
* April 8th, 2022
* PA5
* This class is a test class for SortedIntList
* Known Bugs: NA
*/
package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.SortedIntList;



class SortedIntListTest {
	@Test
	void test1() {
		SortedIntList sortlist = new SortedIntList(4);
		sortlist.setUnique(false);
		sortlist.add(0);
		sortlist.add(3);
		assertEquals(sortlist.toString(), "S:[0, 3]");
	
		sortlist.add(5);
		sortlist.add(3);
		sortlist.add(35);
		sortlist.add(7);
		sortlist.add(0);
		assertEquals(sortlist.toString(), "S:[0, 0, 3, 3, 5, 7, 35]");
		sortlist.setUnique(true); //trun the uniqueness to true, then the duplicates should be eliminted. 
		assertEquals(sortlist.toString(), "[0, 3, 5, 7, 35]U");
		sortlist.remove(4); //remove the element at index of 4
		assertEquals(sortlist.toString(), "[0, 3, 5, 7]U");
		assertThrows(UnsupportedOperationException.class, () -> sortlist.add(5, 2)); //such method will throw UnsupportedOperationException
		assertThrows(IndexOutOfBoundsException.class, () -> sortlist.get(4)); //such method will throw UnsupportedOperationException
		

		
	}

	@Test
	void test2() {
		SortedIntList sortlist = new SortedIntList(0);
		assertEquals(sortlist.toString(), "S:[]");
		sortlist.add(0); //append 0 to the list
		assertEquals(sortlist.toString(), "S:[0]");
		SortedIntList sortlist2 = new SortedIntList(true, 5); //uniqueness switch on
		assertEquals(sortlist2.toString(), "[]U");
		sortlist2.add(1);
		assertEquals(sortlist2.toString(), "[1]U");
		sortlist2.add(0);
		sortlist2.add(0); //won't be added since there should not be any duplicates
		sortlist2.add(1); //won't be added since there should not be any duplicates
		assertEquals(sortlist2.toString(), "[0, 1]U");
		
		
		
	}
	
	
	
	@Test
	void test3() {
		SortedIntList sortlist3 = new SortedIntList(true, 3);
		sortlist3.add(3);
		sortlist3.add(0);
		sortlist3.add(1);
		sortlist3.add(0);
		sortlist3.add(10);
		sortlist3.add(1);
		sortlist3.add(0);
		sortlist3.add(0);
		sortlist3.add(3);
		assertEquals(sortlist3.toString(), "[0, 1, 3, 10]U");
		sortlist3.ensureCapacity(4); //there is currently 4 spaces, so no need to add space
		assertEquals(sortlist3.getcap(), 0);
		sortlist3.ensureCapacity(5); //still need one more space
		assertEquals(sortlist3.getcap(), 1);
		sortlist3.setUnique(false);
		assertEquals(sortlist3.toString(), "S:[0, 1, 3, 10]");
		sortlist3.add(10); //can be added due to allowing duplicates 
		sortlist3.add(2);
		assertEquals(sortlist3.toString(), "S:[0, 1, 2, 3, 10, 10]");
		sortlist3.setUnique(true); //no duplicates
		assertEquals(sortlist3.toString(), "[0, 1, 2, 3, 10]U"); //10 is deleted due to duplicates. 
		
		
		
		
		
		
	}
	

}
