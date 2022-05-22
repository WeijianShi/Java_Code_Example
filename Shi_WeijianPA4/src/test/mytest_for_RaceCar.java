/**
* Weijian Shi
* weijianshi@brandeis.edu
* March 18th, 2022
* PA4
* This class is a test class for racecar
* Known Bugs: NA
*/
package test;

import static org.junit.jupiter.api.Assertions.*;
import main.RaceCar;


import org.junit.jupiter.api.Test;

/**
 * This is a test class testing the racecar
 * @author Weijian Shi
 */
class mytest_for_RaceCar {

	@Test
	void test() {
		
		//create a racecar for test
		RaceCar testcase1 = new RaceCar(); 
		//test the racecar's speed and strength
		assertEquals(testcase1.getspeed(), 40);
		assertEquals(testcase1.getstrength(), 3);
		
		//create a racecar for test
		RaceCar testcase2 = new RaceCar(1000, 400);
		//test the racecar's speed and strength
		assertEquals(testcase2.getspeed(), 55);
		assertEquals(testcase2.getstrength(), 4);
		
		//create a racecar for test
		RaceCar testcase3 = new RaceCar(10, 7);
		//test the racecar's speed and strength
		assertEquals(testcase3.getspeed(), 30);
		assertEquals(testcase3.getstrength(), 4);
		
		//create a racecar for test
		RaceCar testcase4 = new RaceCar(43, 3);
		//test the racecar's speed and strength
		assertEquals(testcase4.getspeed(), 43);
		assertEquals(testcase4.getstrength(), 3);
		
		

	}

}
