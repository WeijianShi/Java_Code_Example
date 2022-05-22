/**
* Weijian Shi
* weijianshi@brandeis.edu
* March 18th, 2022
* PA4
* This class defines an RaceCar object
* Known Bugs: NA
*/
package main;

/**
 * This class defines an RaceCar object.
 * @author Weijian Shi
 */
public class RaceCar extends Car {

	/**
	 * This is a constructor of RaceCar setting the speed and strength
	 * @param speed: the speed of the RaceCar
	 * @param strength: the strength of the RaceCar
	 */
	public RaceCar(int speed, int strength) {
		boolean speedchange = false; //track if the speed inputed is out of bound
		boolean strengthchange = false; //track if the strength inputed is out of bound
		

		if(speed > 55){ //if the speed entered is above 70, then it's closer to 70 rather than to 30, so the speed should be 70
			this.speed = 55;
			speedchange = true;
		}else if(speed < 30) {//set the speed to its closest bound when the inputed value is not within the range
			this.speed = 30;
			speedchange = true;
		}
		if(strength > 4) { //if the strength entered is above 5, then it's closer to 5 rather than to 3, so the speed should be 5
			this.strength = 4;
			strengthchange = true;
		}else if(strength < 2) {//set the strength to its closest bound when the inputed value is not within the range
			this.strength = 2;
			strengthchange = true;
		}
		if(!speedchange) { //set the speed to its inputed value when such value is within the range
			this.speed = speed;
		}
		if(!strengthchange) { //set the strength to its inputed value when such value is within the range
			this.strength = strength;
		}
		fixedspeed = this.speed;
	}

	
	/**
	 * This is a generic constructor which set the speed to 40 and strength to 3
	 */
	public RaceCar() {
		super(40, 3);

	}
	
	
	/**
	 * This is a toString method which gives the infomation of RaceCar
	 */
	public String toString(){
		return("RaceCar" + this.fixedspeed + "/" + this.strength);
		
	}
	
	
	
	
	
}
