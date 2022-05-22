/**
* Weijian Shi
* weijianshi@brandeis.edu
* March 18th, 2022
* PA4
* This class defines an FormulaOne object
* Known Bugs: NA
*/
package main;

/**
 * This class defines an FormulaOne object.
 * @author Weijian Shi
 */
public class FormulaOne extends Car {

	/**
	 * This is a constructor of FormulaOne setting the speed and strength
	 * @param speed: the speed of the FormulaOne
	 * @param strength: the strength of the FormulaOne
	 */
	public FormulaOne(int speed, int strength) {
		boolean speedchange = false; //track if the speed inputed is out of bound
		boolean strengthchange = false; //track if the strength inputed is out of bound
		

		if(speed > 70){ //if the speed entered is above 70, then it's closer to 70 rather than to 30, so the speed should be 70
			this.speed = 70;
			speedchange = true;
		}else if(speed < 30) { //set the speed to its closest bound when the inputed value is not within the range
			this.speed = 30;
			speedchange = true;
		}
		if(strength > 5) { //if the strength entered is above 5, then it's closer to 5 rather than to 3, so the speed should be 5
			this.strength = 5;
			strengthchange = true;
		}else if(strength < 3) { //set the strength to its closest bound when the inputed value is not within the range
			this.strength = 3;
			strengthchange = true;
		}
		if(!speedchange) {
			this.speed = speed;
		}
		if(!strengthchange) {
			this.strength = strength;
		}
		fixedspeed = this.speed;
	
	}
	
	/**
	 * This is a generic constructor which set the speed to 50 and strength to 4
	 */
	public FormulaOne() {
		super(50, 4);
		
	}
	
	
	/**
	 * This is a toString method which gives the infomation of FormulaOne
	 */
	public String toString() {
		return("FormulaOne" + this.fixedspeed + "/" + this.strength);
		
	}
	
	
}
