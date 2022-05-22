package main;

public class FinishLine {
	
	
	/**
	 * this class has 4 fields
	 * carfinished: the type of car array storing the car who finished the race
	 * maxcarnumber: the maximum number of car
	 * finishcount: how many cars have finished
	 * totalcar: the total number of cars entered the race
	 */
	private Car[] carfinished;
	private int maxcarnumber = 10;
	private int finishcount = 0;
	private int totalcar;
	
	
	/**
	 * This is the constructor for the finishline. It can get the total number of cars in the finishline, which can be used
	 * in the future when we want to check whether all cars have finished the race
	 * @param garage: the field of this class, the type of car array which can hold the car finished the race
	 */
	public FinishLine(Car[] garage) {
		this.carfinished = new Car[maxcarnumber];
		for(int i = 0; i < garage.length; i++) {
			if(garage[i] != null) {
				totalcar++;
			}
		}
	}
	
	
	
	/**
	 * This is a method put the car into the finishline
	 * @param car: the car which should enter the finishline
	 */
	public void enterFinishLine(Car car) {
		carfinished[finishcount] = car;
		finishcount++;
	}
		

	/**
	 * This is a method check whether the race has been finished
	 * @return: a boolean variable, indicating whether the race has been finished
	 */
	public boolean finished() {
		return finishcount == totalcar;	
		
	}
	
	
	/**
	 * This is a get method which count how many cars have finished the race
	 * @return finishcount: the count of cars who have finished the race
	 */
	public int getFinishcount() {
		return finishcount;
	}

	
}

