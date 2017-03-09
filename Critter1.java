package assignment4;

public class Critter1 extends Critter {
	
	
	private int direction; 
	private int numFight; 
	private int babies1;
	private boolean moved; 
	
	/***
	 * Constructor/Initailized 
	 */
	public Critter1(){
		direction = Critter.getRandomInt(8); 
		moved = false; 
		numFight = 0; 
		babies1 = 0; 
	}
	
	/**
	 * Critter will be an !
	 */
	@Override
	public String toString(){
		return ("!"); 
	}
	
	/**
	 * Walk if around 1/4 energy 
	 * Babies if around 1/2 energy 
	 * Else run
	 */
	@Override
	public void doTimeStep() {
		//too hungry to run
		moved = false; 
		if (getEnergy() < (Params.start_energy / 4)){
			walk(direction); 
			moved = true; 
		}
		//baby
		else if (getEnergy() < (Params.start_energy / 2)){
			Critter1 kid = new Critter1(); 
			reproduce(kid, Critter.getRandomInt(8)); 
			babies1++; 
		}
		//run
		else{
			run(direction); 
			moved = true; 
		}
		direction = getEnergy() * Critter.getRandomInt(8) % 8; 
		
	}

	/**
	 * Going to ditch baby because only time it didn't move
	 * Else fight 
	 * Thought this would be kind of funny or different 
	 */
	public boolean fight(String oponent) {
		//ditch baby 
		if (moved == false){
			walk(direction); 
			moved = true; 
			return false; 
		}
		
		//fight 
		else{
			numFight++; 
			return true; 
		}
	}
	
	/**
	 * Stat function so I don't get an error in main or terminal 
	 * Print number of critters, number of fights, number of babies
	 */
	public static void runStats(java.util.List<Critter> theCritter1){
		int total_fights = 0; 
		int total_babies = 0; 
		for(Object a : theCritter1){
			Critter1 critt = (Critter1) a; 
			total_fights = total_fights + critt.numFight; 
			total_babies = total_babies + critt.babies1; 
		}
		System.out.println("Total number of Crtiter1's: " + theCritter1.size()); 
		System.out.println("Total number of fights: " + total_fights);
		System.out.println("Total number of babies: " + total_babies); 
	}

}
