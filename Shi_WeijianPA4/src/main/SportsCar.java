/**
* Weijian Shi
* weijianshi@brandeis.edu
* March 18th, 2022
* PA4
* This class defines an Sportscar object
* Known Bugs: NA
*/
package main;

/**
 * This class defines an Sportscar object.
 * @author Weijian Shi
 */
public class SportsCar extends Car{
	
	/**
	 * This is a constructor of sportscar setting the speed and strength
	 * @param speed: the speed of the sportscar
	 * @param strength: the strength of the sportscar
	 */
	public SportsCar(int speed, int strength) {
		boolean speedchange = false; //track if the speed inputed is out of bound
		boolean strengthchange = false; //track if the strength inputed is out of bound
		

		if(speed > 45){ //if the speed entered is above 45, then it's closer to 45 rather than to 20, so the speed should be 45
			this.speed = 45;
			speedchange = true;
		}else if(speed < 20) { //set the speed to its closest bound when the inputed value is not within the range
			this.speed = 20;
			speedchange = true;
		}
		if(strength > 3) { //if the strength entered is above 3, then it's closer to 3 rather than to 1, so the speed should be 3
			this.strength = 3;
			strengthchange = true;
		}else if(strength < 1) { //set the strength to its closest bound when the inputed value is not within the range
			this.strength = 1;
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
	 * This is a generic constructor which set the speed to 30 and strength to 2
	 */
	public SportsCar() { 
		super(30, 2);
		
	}
	
	/**
	 * This is a toString method which gives the infomation of sportscar
	 */
	public String toString() {
		return("SportsCar" + this.fixedspeed + "/" + this.strength);
		
	}
	
}


