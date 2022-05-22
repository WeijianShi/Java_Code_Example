/**
* Weijian Shi
* weijianshi@brandeis.edu
* March 18th, 2022
* PA4
* This class is an abstact class representing a general race contestant
* Known Bugs: NA
*/
package main;

/**
 * This class is an abstact class representing a general race contestant 
 * @author Weijian Shi
 *
 */
public abstract class Car {
	
	/**
	 * this class has 6 fields
	 * speed: the speed of the car
	 * strength: the strength of the car
	 * tick: the time of the campaign
	 * location: the current location of the car
	 * isdamge: whether the car is damaged or not
	 * fixedspeed: the initial speed (which has not been affected by the damage yet)
	 */
	protected int speed;
	protected int strength;
	protected int tick;
	protected double location = 0;
	protected boolean isdamage = false;
	protected int fixedspeed;


	
	/**
	 * This is the constructor for the car which set the speed and strength to the inputed value, and record the initial speed
	 * @param speed: the inputed value for speed
	 * @param strength: the inputed value for strength
	 */
	protected Car(int speed, int strength) {
		this.speed = speed;
		this.strength = strength;
		fixedspeed = speed;

	}
	
	
	/**
	 * A generic constructor which set Car's speed and strength to 0
	 */
	protected Car() {
		this(0, 0);

	}
	
	/**
	 * The method get the car's current speed
	 * @return speed: return the current speed
	 */
	public int getspeed() {
		return this.speed;

	}
	
	/**
	 * The method set the car's speed to the inputed speed
	 * @param newspeed: the inputed value speed (interger)
	 */
	public void setspeed(int newspeed) {
		this.speed = newspeed;

	}
	
	/**
	 * The method get the car's current strength
	 * @return speed: return the current strength
	 */
	public int getstrength() {
		return this.strength;

	}
	
	/**
	 * The method set the car's strength to the inputed strength
	 * @param newstrength: the inputed value newstrength
	 */
	public void setstrength(int newstrength) {
		this.strength = newstrength;

	}
	
	/**
	 * The method get the current tick.
	 * @return tick: the current tick
	 */
	public int gettick() {
		return this.tick;

	}
	
	/**
	 * The method set the tick for the car
	 * @param newtick: set the tick to the newtick inputted
	 */
	public void settick(int newtick) {
		this.tick = newtick;

	}
	
	
	/**
	 * The method get the current location of the car
	 * @return location: the current location of the car
	 */
	public double getLocation() {
		return location;

	}
	
	/**
	 * The method set the location for the car
	 * @param loc: the new locaiton inputted
	 */
	public void setLocation(double loc) {
		this.location = loc;
	}
	
	/**
	 * The method get the info about whether the car is damager or not
	 * @return isdamage: whether the car is damaged now
	 */
	public boolean getDamage() {
		return isdamage;

	}
	
	/**
	 * The method set the car to damage or not damage
	 * @param damage: whether set the car to damage
	 */
	public void setDamage(boolean damage) {
		this.isdamage = damage;
	}
	
	/**
	 * The toString generic method return the infomation of the car
	 * @return: return the infomation of the car
	 */
	public String toString() {
		return("Car" + fixedspeed + "/" + strength);
	}
	
}
