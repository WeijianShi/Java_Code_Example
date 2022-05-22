/**
* Weijian Shi
* weijianshi@brandeis.edu
* March 18th, 2022
* PA4
* This class defines an RaceTrack object
* Known Bugs: NA
*/
package main;

/**
 * This is a class hold your cars and racetrack components.
 * @author Weijian Shi
 */
public class RaceTrack {
	
	/**
	 * this class has 9 fields
	 * logger: the TrackLoggerC logger used to print result
	 * tick: the time of the campaign
	 * maxcar: the maximum number of cars in a race
	 * garage: the car type array holding the cars participating the race
	 * pitstop: the PitStop
	 * finishline: the FinishLine
	 * RaceCarCount: the number of racecar participating the race
	 * FormulaOneCount: the number of FormulaOne participating the race
	 * SportsCarCount: the number of SportsCar participating the race
	 */
	private int tick = 0;
	private TrackLoggerC logger;
	private int maxcar = 10;
	private Car[] garage;
	private PitStop pitstop;
	private FinishLine finishline;
	private int RaceCarCount = 0;
	private int FormulaOneCount = 0;
	private int SportsCarCount = 0;
	
	/**
	 * This is a constructor creating a new RaceTrack object, new a garage and pitstop
	 */
	public RaceTrack() {
		logger = new TrackLoggerC(); // DO NOT REMOVE THIS LINE
		this.garage = new Car[maxcar];
		this.pitstop = new PitStop();

		
		
	}
	
	/**
	 * This is a method put the cars in the garage
	 * @param cars: array storing the infomation for the cars participating the race. 
	 */
	public void setCars(Car[] cars) {
		for(int i = 0; i < cars.length; i++) {
			garage[i] = cars[i];
			//count the racecar, formulaone, sportscar seperately
			if(garage[i] instanceof RaceCar) {
				RaceCarCount ++;
			}else if(garage[i] instanceof FormulaOne) {
				FormulaOneCount ++;
			}else if(garage[i] instanceof SportsCar) {
				SportsCarCount ++;
			
			}
			
			
		}
		//create the finishline
		this.finishline = new FinishLine(garage);
		
	}
	
	public void tick() {
		tick++; //run a tick
		logger.logNewTick();
		Car[] checkexit = pitstop.exitPitStop(tick); //create an array to check if cars in pitstop can exit
		for(int j = 0; j < checkexit.length; j++) { //push the car who can exit the pistop back to the campaign (which is the garage)
			if(checkexit[j] != null) {
				logger.logExitPit(checkexit[j]);
				for(int i = 0; i < garage.length; i++) {
					if(garage[i] == null) {
						garage[i] = checkexit[j];
						i = 100; //once find the null to fill the exit car in, jump out the loop. 
					}
				}
			}
		}
		
		/*
		 * let the car satisfying the conditions (damage, pass the pitstop) enter the pitstop. 
		 */
		for(int i = 0; i < garage.length; i++) {
			if(garage[i] != null) {
				garage[i].setLocation(garage[i].getLocation() + garage[i].getspeed());
				garage[i].settick(tick);
				/*
				 * if the car is damaged before the pitstop and in the next tick it passed/reached the pitstop (he is still within the current loop)
				 */
				if((int)garage[i].getLocation()%100 >= 75 && garage[i].getDamage() == true) {
					pitstop.enterPitStop(garage[i]);
					logger.logEnterPit(garage[i]);
					garage[i] = null;
				/*
				 * this is very important!!!!!!!! Because it's a edge case: if a car is damaged before pitstop, and in the next tick he enters next lap.
				 * Here we use 3 conditions to make sure it enters the pitstop correctly 
				 * 1. before this tick start, it has not reached pitstop yet. 
				 * 2. after this tick, it enterd a new lap. 
				 * 3. It's damaged before the tick.
				 */
				}else if((int)(garage[i].getLocation() - garage[i].getspeed())%100 <75 && (int)garage[i].getLocation()/100 - 1 == (int)(garage[i].getLocation() - garage[i].getspeed())/100 && garage[i].getDamage() == true) { //105 cases
					pitstop.enterPitStop(garage[i]);
					logger.logEnterPit(garage[i]);
					garage[i] = null;
					
				}
				
			}
		}
	
		
		
		/*
		 * if a car has passed the finishline, let it enter the finishline and exclude it from the campaign (garage)
		 */
		for(int i = 0; i < garage.length; i++) {
			if(garage[i] != null) {
				if(garage[i].getLocation() >= 1000) {
					finishline.enterFinishLine(garage[i]);
					logger.logFinish(garage[i], finishline.getFinishcount());
					garage[i] = null;
				}			
			}		
		}
		
		checkCollision(); //check if there is collision after each tick
		
	}
	
	/**
	 * This is a check colission method
	 */
	public void checkCollision() {
		//use double for loop to check whether two cars have damaged. 
		for(int i = 0; i < garage.length; i++) {
			for(int j = i+1; j < garage.length; j++) {
				if(garage[i] != null && garage[j] != null) { //make sure the two cars checked are not null
					if((int)garage[i].getLocation()%100 == (int)garage[j].getLocation()%100) { //if they are at the same location at the end of a tick
						if(garage[i].getDamage() == false && garage[j].getDamage() == false) { //make sure only damage the cars who are not damaged previously
							logger.logDamaged(garage[i]);
							logger.logDamaged(garage[j]);
							//set the car to damage
							garage[i].setDamage(true);
							garage[j].setDamage(true);
							//change the speed
							garage[i].setspeed(garage[i].getspeed()-garage[i].getstrength()*5);
							garage[j].setspeed(garage[j].getspeed()-garage[j].getstrength()*5);
							//the condition for only one of the two cars are previously not damaged, and only trun it to damaged
						}else if(garage[i].getDamage() == true && garage[j].getDamage() == false) {
							logger.logDamaged(garage[j]);
							garage[j].setDamage(true);
							garage[j].setspeed(garage[j].getspeed()-garage[j].getstrength()*5);
						}else if(garage[i].getDamage() == false && garage[j].getDamage() == true) {
							logger.logDamaged(garage[i]);
							garage[i].setDamage(true);
							garage[i].setspeed(garage[i].getspeed()-garage[i].getstrength()*5);
						}
					}
				}
			}
		}
		

	}
	

	
	
	
	/**
	 * This is a method processing all the ticks until the race ends
	 */
	public void run() {
		while(!finishline.finished()) {
			tick();
		}
		calculatorScore(tick); //calculate the race's score
		

	}
	
	/**
	 * This is a method calculating the score of the race
	 * @param ticks: the time of the race
	 */
	public void calculatorScore(int ticks) {
		int initialscore = 1000;
		initialscore -= ticks*20; //for each tick, reduce 20 scores
		initialscore += RaceCarCount*150 + FormulaOneCount*100 + SportsCarCount*200;
		logger.logScore(initialscore);
		
	}
	
	/**
	 * This is a get method return the pitstop. 
	 * @return pitstop: the pitstop
	 */
	public PitStop getPitStop() {
		return pitstop;
	}
	
	/**
	 * This method returns the logger instance used by this RaceTrack. You <b>SHOULD NOT</b> be using this method. 
	 * @return logger with this track's events 
	 */
	public TrackLoggerC getLogger() {
		return logger;
	}

}
