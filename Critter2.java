/* CRITTERS Critter2.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * Haley Alexander
 * ha5722
 * 16215
 * Shariq Memon
 * skm2662
 * 16215
 * Slip days used: <0>
 * Spring 2017
 */
package assignment4;

public class Critter2 extends Critter{
	private int direction; 
	private int numSteps1; 
	private boolean moved; 
	
	/***
	 * Constructor/Initailized 
	 */
	public Critter2(){
		direction = Critter.getRandomInt(8); 
		numSteps1 = 0; 
		moved = false; 
	}
	
	
	/**
	 * Critter will be an H
	 */
	@Override
	public String toString(){
		return ("H"); 
	}
	
	/**
	 * Pick a random number 
	 * If divisible by 6 have a baby 
	 * If divisible by 2 walk 
	 * Else run 
	 */
	@Override
	public void doTimeStep() {
		moved = false; 
		if (Critter.getRandomInt(100) % 6 == 0){
			Critter2 kid = new Critter2(); 
			reproduce(kid, Critter.getRandomInt(8)); 
		}
		else if (Critter.getRandomInt(100) % 2 == 0){
			walk(direction); 
			moved = true; 
			numSteps1++; 
		}
		else{
			run(direction); 
			moved = true; 
			numSteps1 = numSteps1 + 2; 
		}
		
		direction = getEnergy() * Critter.getRandomInt(8) % 8; 
		
	}

	/**
	 * If didn't move, run 
	 * Else fight 
	 */
	public boolean fight(String oponent) { 
		if (moved == false){
			run(direction); 
			moved = true; 
			return false; 
		}
		
		else{ 
			return true; 
		}
	}
	
	/**
	 * Going to print number steps since it is random 
	 */
	public static void runStats(java.util.List<Critter> theCritter2){
		int total_steps = 0; 
		for(Object a : theCritter2){
			Critter2 critt = (Critter2) a; 
			total_steps = total_steps + critt.numSteps1; 
			}
			
		
		System.out.println("Total number of Critter2's: " + theCritter2.size()); 
		System.out.println("Total number of steps: " + total_steps); 
	}
}
