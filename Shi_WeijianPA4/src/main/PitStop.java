/**
* Weijian Shi
* weijianshi@brandeis.edu
* March 18th, 2022
* PA4
* This class defines an PitStop object
* Known Bugs: NA
*/
package main;



/**
 * This is a class defining the PitStop
 * @author Weijian Shi
 */
public class PitStop {

	/**
	 * this class has 3 fields
	 * pitstopsize: the size of the pitstop, which is 10. 
	 * pitstopgarage: the car type array which stores the cars enter the pitstop
	 * initialtick: store the tick of the car when it entered the pitstop
	 */
	private int pitstopsize = 10;
	private Car[] pitstopgarage;
	private int[] initialtick;
	
	
	
	
	/**
	 * This is a constructor for pitstop, set the garage to be size 10 car type array
	 * , and set up the array storing the initial tick when each car entered the pitstop
	 */
	public PitStop() {
		this.pitstopgarage = new Car[pitstopsize];
		this.initialtick = new int[pitstopsize];

	}
	
	/**
	 * This is a method let the car enter the pitstop.
	 * @param car: the car which should enter the pitstop
	 */
	public void enterPitStop(Car car) {
		for(int i = 0; i < pitstopsize; i++) {
			if(pitstopgarage[i] == null) { //find the first empty space for the car who enters the pitstop
				pitstopgarage[i] = car;
				initialtick[i] = car.gettick(); //store that cars' tick when it entered the pistop at the same place in initialtick array
				i = 100;
			}
		}
	}
	
	
	/**
	 * This is a method make the car exit the pitstop when it should do so. 
	 * @param tick: the current tick in the race
	 * @return exitcar: the car type array storing the cars who should exit the pitstop at certain tick
	 */
	public Car[] exitPitStop(int tick) {
		Car[] exitcar = new Car[pitstopsize];
		int carcount = 0;

		for(int j = 0; j < pitstopsize; j++) {
			if(pitstopgarage[j] != null) {
				//if a car's location before entering the pistop is between the pistop and the start of next loop, set it location to the pitstop when it exit the pitstop
				if(tick - 2 == initialtick[j]) {
					if(pitstopgarage[j].getLocation()%100 >= 75) { 
						pitstopgarage[j].setLocation(((int)pitstopgarage[j].getLocation()/100)*100 + 75);
					}else { //if a car's location before entering the pistop is after the start of next loop, set it location to the pitstop when it exit the pitstop
						pitstopgarage[j].setLocation((((int)pitstopgarage[j].getLocation()/100)-1)*100 + 75);
					}
					pitstopgarage[j].setspeed(pitstopgarage[j].getspeed() + pitstopgarage[j].getstrength()*5); //change the car (who leave the pitstop) speed back to its original speed
					pitstopgarage[j].setDamage(false);//set the damage condition to not damage when the car exit the pitstop

					//transfer the car who exit the pitstop from the pitstop to the exitcar array
					exitcar[carcount] = pitstopgarage[j]; 
					carcount++;
					pitstopgarage[j] = null; //make the car's place as null since it just exited the pitstop
					initialtick[j] = 0; //set the car's tick when it entered the pitstop to 0 since it just exited the pitstop
					
				}
			}
		}
		return exitcar;
		
	}
	
	
	/**
	 * This is a get method which return the car type array pitstopgarage
	 * @return pitstopgarage: the array store the car in pitstop
	 */
	public Car[] getpitstopgarage() {
		return pitstopgarage;
	}
	

	
	
	
}
