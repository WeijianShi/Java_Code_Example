/**
* Weijian Shi
* weijianshi@brandeis.edu
* March 18th, 2022
* PA4
* This class is a test class for pitstop
*/
package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import main.RaceCar;
import main.FormulaOne;
import main.RaceTrack;
import main.Car;


/**
 * This is a test class for pitstop, checking if the cars enter the pitstop correctly
 * @author Weijian Shi
 */
public class mytest_for_PitStop {
	
	@Test
	void test1() {
		RaceCar testRaceCar = new RaceCar(40, 3); //create a racecar for test
		FormulaOne testFormulaOne = new FormulaOne(40, 5); //create a formulaone for test
		RaceTrack testRaceTrack = new RaceTrack(); //create a racetrack for test
		Car[] test = new Car[10]; //set the car type test to size 10 (maximum possible number of cars)
		test[0] = testRaceCar;
		test[1] = testFormulaOne;
		testRaceTrack.setCars(test);
		testRaceTrack.tick(); //proceed with a tick
		assertTrue(testRaceCar.getDamage()); //they should get damage after the first tick since they have the same speed
		
		assertEquals(testRaceCar.getspeed(), 25); //check the damaged racecar's new speed
		assertEquals(testFormulaOne.getspeed(), 15); //check the damaged formulaone's new speed
		testRaceTrack.tick();//proceed with a tick
		assertEquals(testRaceCar.getspeed(), 25);
		assertEquals(testFormulaOne.getspeed(), 15);
		testRaceTrack.tick();//proceed with a tick
		assertEquals(testRaceTrack.getPitStop().getpitstopgarage()[0], testRaceCar); //check whether they enter the pitstop
		
		
		
		
	}
	
}
