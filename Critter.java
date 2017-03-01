package assignment4;
/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */
//There a 3 premade critters with different fight trends
//We just have to fill in the stuff here 
import java.util.List;
import java.lang.reflect.Constructor;
/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	////////////Traits of the Critter////////////
	private int x_coord;
	private int y_coord;
	private boolean move; 
	
	/**
	 * Makes the bugs go around the world like 312
	 * @param movement
	 * @return
	 */
	private int outOfBoundsX(int movement){
		if (x_coord + movement < 0){
			return (Params.world_width - movement);
		}
		if ((x_coord + movement) > (Params.world_width - 1)){
			return (movement - 1); 
		}
		else{
			return (x_coord += movement); 
		}
	}
	
	/**
	 * Makes the bugs go around the world like in 312
	 * @param movement
	 * @return
	 */
	private int outOfBoundsY(int movement){
		if (y_coord + movement < 0){
			return (Params.world_height - movement);
		}
		if ((y_coord + movement) > (Params.world_height - 1)){
			return (movement - 1); 
		}
		else{
			return (y_coord = y_coord + movement); 
		}
	}
	
	/***
	 * Move bug  1 square based on compass given in PDF
	 * lower the y the closer to the top
	 * lower the x the closer to the left
	 * Each direction checks if outofBounds to see if goes to different edge
	 * @param direction
	 */
	protected final void walk(int direction) {
		//check if critter has moved 
		if (this.move == true){
			return; 
		}
		//else move the critter
		//east/right
		if (direction == 0){
			x_coord = outOfBoundsX(1); 
		}
		
		//northeast/up-right
		if (direction == 1){
			x_coord = outOfBoundsX(1); 
			y_coord = outOfBoundsY(-1); 
		}
		
		//north
		if (direction == 2){
			y_coord = outOfBoundsY(-1); 
		}
		
		//northwest
		if (direction == 3){
			x_coord = outOfBoundsX(-1); 
			y_coord = outOfBoundsY(-1); 
		}
		
		//west
		if (direction == 4){
			x_coord = outOfBoundsX(-1); 
		}
		
		//SW
		if (direction == 5){
			x_coord = outOfBoundsX(-1); 
			y_coord = outOfBoundsY(1); 
		}
		
		//S
		if (direction == 6){
			y_coord = outOfBoundsY(1); 
		}
		
		//SE
		if (direction == 7){
			x_coord = outOfBoundsX(1); 
			y_coord = outOfBoundsY(1); 
		}
		//update info
		energy = energy - Params.walk_energy_cost; 
		this.move = true; 
	}
	
	/**
	 * Same as walk but 2 squares
	 * @param direction
	 */
	protected final void run(int direction) {
		//check if critter has moved 
		if (this.move == true){
			return; 
		}
		//else move the critter
		//east/right
		if (direction == 0){
			x_coord = outOfBoundsX(2); 
		}
		
		//northeast/up-right
		if (direction == 1){
			x_coord = outOfBoundsX(2); 
			y_coord = outOfBoundsY(-2); 
		}
		
		//north
		if (direction == 2){
			y_coord = outOfBoundsY(-2); 
		}
		
		//northwest
		if (direction == 3){
			x_coord = outOfBoundsX(-2); 
			y_coord = outOfBoundsY(-2); 
		}
		
		//west
		if (direction == 4){
			x_coord = outOfBoundsX(-2); 
		}
		
		//SW
		if (direction == 5){
			x_coord = outOfBoundsX(-2); 
			y_coord = outOfBoundsY(2); 
		}
		
		//S
		if (direction == 6){
			y_coord = outOfBoundsY(2); 
		}
		
		//SE
		if (direction == 7){
			x_coord = outOfBoundsX(2); 
			y_coord = outOfBoundsY(2); 
		}
		//update info
		energy = energy - Params.walk_energy_cost; 
		this.move = true; 
	}
	
	/* to do*/ 
	protected final void reproduce(Critter offspring, int direction) {
	}

	// abstract so don't edit here
	public abstract void doTimeStep();
	
	// abstract so don't edit here
	public abstract boolean fight(String oponent);
	
	
	
	/* to do*/ 
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	
	
	//Needs to be tested 
	//Needs to have an exception thing
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		//plug in error later in stage 3 
		Class<?> newCritterCons = null; 
		
		//need try catches for checking valid constructors
		try{
			Constructor build = null; 
			build = newCritterCons.getConstructor(); 
			// Critter Initialization
			//!!!!!!If we add traits, add it here too!!!!!!
			Object newCrit = build.newInstance(); 
			((Critter) newCrit).x_coord = Critter.getRandomInt(Params.world_width);
			((Critter) newCrit).y_coord = Critter.getRandomInt(Params.world_height);
			((Critter) newCrit).energy = Params.start_energy;
			population.add((Critter) newCrit);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/* Needs to Be Tested */ 
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		//given
		List<Critter> result = new java.util.ArrayList<Critter>();
		
		//stackover and git says to do this 
		Class<?> crit = null;
		
		//try to get class of critter from string
		try {
			crit = Class.forName(critter_class_name);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//check instances of that class in the population 
		for (Critter a : population) {
			if (crit.isInstance(a)) {
				result.add(a);
			}
		}
		return result;
	}
	
	
	/**
	 * Given
	 * Don't edit
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 * 
	 *  >>ADD functions if we add private traits/varaibles <<<<<
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		
		
		/* to do */ 
		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		
		//?????
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}
	
	//end of test_critter

	/**
	 * Clear the world of all critters, dead and alive
	 * These functions are part of abstract class
	 */
	public static void clearWorld() {
		// Complete this method.
	}
	
	public static void worldTimeStep() {
		// Complete this method.
		//doTimeStep 
		//move/run/walk/fight
		//update energy 
		//have babies?
	}
	
	public static void displayWorld() {
		// Complete this method.
		//does the ASCII Art 
		
	}
}
