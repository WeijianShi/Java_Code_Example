package main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SimulationDriverC {
	
	/**
	 * Holds the String that a user should enter to specify a RaceCar type car. 
	 */
	public static String TYPE_RACE_CAR = "R";
	
	/**
	 * Holds the String that a user should enter to specify a FormulaOne type car.
	 */
	public static String TYPE_FORMULA_ONE = "F";
	
	/**
	 * Holds the String that a user should enter to specify a SportsCar type car.
	 */
	public static String TYPE_SPORTS_CAR = "S";

	/**
	 * Value to signify a default stat value.
	 */
	public static final int DEFAULT_STAT_VAL = 0;
		
	public static void main(String[] args) {
		RaceTrack track = new RaceTrack();
		track.setCars(getSomeCars());
		System.out.println("The race has started!");
		track.run();
	}
		
	/**
	 * Runs a short program that allows the user to set up the simulation for part B. The user will be prompted to 
	 * set up some number of Cars using the format outlined on the PDF.
	 * @return an array of Cars constructed by the user
	 */
	public static Car[] getSomeCars() {
		System.out.print("Welcome to the Need for Speed Simulator!\n\nFor each Car: enter the speed, strength, and car type separated by a space.\nTo create a RaceCar use " + TYPE_RACE_CAR + ", to create a FormulaOne use " + TYPE_FORMULA_ONE + " or to create a SportsCar use " + TYPE_SPORTS_CAR +  ".\nIf you want to construct a default Car of some type, enter " + DEFAULT_STAT_VAL + " for the speed and strength.\n");
		Scanner consoleRdr = new Scanner(System.in);
		boolean waitingForValidIn = true;
		int numCars = 0;
		do {
			System.out.print("How many Cars would you like to enter in the race? ");
			try {
				numCars = consoleRdr.nextInt();
			}
			catch (InputMismatchException e) {
				System.out.print("Non-integer values are not allowed.");
				consoleRdr.nextLine();
			}
			if (numCars >= 0) {
				waitingForValidIn = false;
			}
			else {
				System.out.println("Number of cars being created must be a nonnegative number.");
			}
		} while (waitingForValidIn);
		waitingForValidIn = true;
		Car[] cars = new Car[numCars];
		
		// all inputs will have only 3 characters, no more no less! 
		for (int i = 0; i < numCars; i++) {
			do {
				System.out.print("Car #" + (i + 1) + ": ");
				try {
					int speed = consoleRdr.nextInt();
					int strength = consoleRdr.nextInt();
					String carType = consoleRdr.next();
					if (speed == DEFAULT_STAT_VAL && strength == DEFAULT_STAT_VAL) {
						if (carType.equals(TYPE_SPORTS_CAR)) {
							cars[i] = new SportsCar();
							waitingForValidIn = false;
						}
						else if (carType.equals(TYPE_FORMULA_ONE)) {
							cars[i] = new FormulaOne();
							waitingForValidIn = false;
						}
						else if (carType.equals(TYPE_RACE_CAR)) {
							cars[i] = new RaceCar();
							waitingForValidIn = false;
						}
						else {
							System.out.print("Invalid car type: " + carType);
						}
					}
					else if (speed > DEFAULT_STAT_VAL && strength > DEFAULT_STAT_VAL) {
						if (carType.equals(TYPE_SPORTS_CAR)) {
							cars[i] = new SportsCar(speed, strength);
							waitingForValidIn = false;
						}
						else if (carType.equals(TYPE_FORMULA_ONE)) {
							cars[i] = new FormulaOne(speed, strength);
							waitingForValidIn = false;
						}
						else if (carType.equals(TYPE_RACE_CAR)) {
							cars[i] = new RaceCar(speed, strength);
							waitingForValidIn = false;
						}
						else {
							System.out.print("Invalid car type: " + carType);
						}
					}
					else {
						System.out.println("Speed and strength stats must either both be " + DEFAULT_STAT_VAL + " or both positive values.");
					}
				}
				catch (InputMismatchException e) {
					System.out.print("Non-integer values are not allowed.");
					consoleRdr.nextLine();
				}
			} while (waitingForValidIn);
			waitingForValidIn = true;
		}
		consoleRdr.close();
		System.out.println();
		return cars;
	}
	
	 
}
