package assignment4;
import java.util.Iterator;
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
	private boolean isAlive; 
	
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
			return (x_coord + movement); 
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
			return (y_coord + movement); 
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
	
	/**
	 * Makes a baby 
	 * Assign it as alive 
	 * Then uses walk code to give it a spot 
	 * @param offspring
	 * @param direction
	 */
	protected final void reproduce(Critter offspring, int direction) {
		if (this.energy < Params.min_reproduce_energy){
			return; 
		}
		//else babies
		offspring.energy = (this.energy / 2); 
		this.energy = (int)Math.ceil(this.energy/2); 
		//east/right
		if (direction == 0){
			offspring.x_coord = this.outOfBoundsX(1); 
			offspring.y_coord = this.y_coord; 
		}
		
		//northeast/up-right
		if (direction == 1){
			offspring.x_coord = this.outOfBoundsX(1); 
			offspring.y_coord = this.outOfBoundsY(-1); 
		}
		
		//north
		if (direction == 2){
			offspring.y_coord = this.outOfBoundsY(-1); 
			offspring.x_coord = this.x_coord; 
		}
		
		//northwest
		if (direction == 3){
			offspring.x_coord = this.outOfBoundsX(-1); 
			offspring.y_coord = this.outOfBoundsY(-1); 
		}
		
		//west
		if (direction == 4){
			offspring.x_coord = this.outOfBoundsX(-1); 
			offspring.y_coord = this.y_coord; 
		}
		
		//SW
		if (direction == 5){
			offspring.x_coord = this.outOfBoundsX(-1); 
			offspring.y_coord = this.outOfBoundsY(1); 
		}
		
		//S
		if (direction == 6){
			offspring.y_coord = this.outOfBoundsY(1); 
			offspring.x_coord = this.x_coord; 
		}
		
		//SE
		if (direction == 7){
			offspring.x_coord = this.outOfBoundsX(1); 
			offspring.y_coord = this.outOfBoundsY(1); 
		}
		//may need to be moved depending on rules 
		offspring.isAlive = true; 
		babies.add(offspring); 
	}

	// abstract so don't edit here
	public abstract void doTimeStep();
	
	// abstract so don't edit here
	public abstract boolean fight(String oponent);
	
	
	 
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
	
	//Needs to have an exception thing
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		//plug in error later in stage 3 
		Class<?> newCritterCons = null; 
		
		//need try catches for checking valid constructors
		try{
			newCritterCons = Class.forName(critter_class_name); 
			Constructor<?> build = null; 
			build = newCritterCons.getConstructor(); 
			// Critter Initialization
			//!!!!!!If we add traits, add it here too!!!!!!
			Object newCrit = build.newInstance(); 
			((Critter) newCrit).x_coord = Critter.getRandomInt(Params.world_width);
			((Critter) newCrit).y_coord = Critter.getRandomInt(Params.world_height);
			((Critter) newCrit).energy = Params.start_energy;
			((Critter) newCrit).isAlive = true; 
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
		
		
		//don't edit unless we add more varaibles
		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		//don't edit unless we add more varaibles
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
		List<Critter> population2 = new java.util.ArrayList<Critter>();
		//Iterator<Critter> it = population.iterator(); 
		for(Critter c: population){
			population2.add(c); 
		}
		population.removeAll(population2); 
	}


	// -------- DO WORLD TIME STEP()

	public static void worldTimeStep() {
		// Complete this method.
		//doTimeStep 
		for (Critter c: population){
			c.doTimeStep();
			c.move = false; 
			if (c.energy <= 0){
				c.isAlive = false; 
			}
		}
		for(Critter c: population){
			for(Critter d: population)
			{
				if(c == d)
				{
					continue;
				}
				else if(coordcheck(c,d))
				{
					fighting(c,d);
				}

			}


		}
		//move/run/walk/fight
		//update energy 
		
		//have babies
		for (Critter b : babies){
			b.isAlive = true; 
			population.add(b); 
		}
		babies.removeAll(babies); 
		
		
		//clean up 
		List<Critter> population2 = new java.util.ArrayList<Critter>();
		for (Critter c : population){
			if (!c.isAlive || c.energy <= 0){
				population2.add(c); 
			}
		}
		population.removeAll(population2); 
	}
	public static boolean coordcheck(Critter a, Critter b)
	{
		if((a.x_coord == b.x_coord)&& (a.y_coord == b.y_coord))
		{
			return true;
		}
		return false;
	}


	public static void fighting(Critter a , Critter b) {
		if (a.isAlive() && b.isAlive()) {
			int x = a.x_coord;
			int y = a.y_coord;
			boolean a_fight = a.fight(b.toString());
			if (a.energy <= 0) {
				a.isAlive = false;
			}
			if (a.isOccupied()) {
				a.x_coord = x;
				a.y_coord = y;
			}
			x = b.x_coord;
			y = b.y_coord;
			boolean b_fight = b.fight(a.toString());
			if (b.energy <= 0) {
				b.isAlive = false;
			}
			if (b.isOccupied()) {
				b.x_coord = x;
				b.y_coord = y;
			}
			// If running away, a or b may die, or a or b may get away.
			if (coordcheck(a, b)) {
				if (a.isAlive() && b.isAlive()) {
					// If there's still a conflict, time to duke it out.
					int a_roll, b_roll;
					if (a_fight) {
						a_roll = Critter.getRandomInt(a.energy);
					} else {
						a_roll = 0;
					}
					if (b_fight) {
						b_roll = Critter.getRandomInt(b.energy);
					} else {
						b_roll = 0;
					}

					if (a_roll > b_roll) {
						// A wins
						a.energy = a.energy + (b.energy) / 2;
						b.energy = 0;
						b.isAlive = false;
					} else if (b_roll > a_roll) {
						// B wins
						b.energy = b.energy + (a.energy) / 2;
						a.energy = 0;
						a.isAlive = false;
					}
					// Coin flip.
					else {
						int flip = Critter.getRandomInt(2);
						if (flip == 1) {
							// A wins
							a.energy = a.energy + (b.energy) / 2;
							b.energy = 0;
							b.isAlive = false;
						} else {
							// B wins
							b.energy = b.energy + (a.energy) / 2;
							a.energy = 0;
							a.isAlive = false;
						}
					}
				}
			}

		}
	}

	private boolean isAlive() {
		return isAlive;
	}
	private boolean isOccupied(){
		for(Critter a : population)
		{
			if(a == this){continue;}
			if(coordcheck(a,this))
			{
				return true;
			}
		}
		return false;
	}
	/** 
	 * Creates ASCII art to display the world as grid of symbols
	 */
	public static void displayWorld() {
		//2D Map of the world 
		String[][] world = new String[Params.world_height + 2][Params.world_width + 2]; 
		
		//for each row in the world, print line by line like 460M
		for (int i = 0; i < Params.world_height + 2; i++){
			//next go by pixel by pixel like 460M
			for (int j = 0; j < Params.world_width + 2; j++){
				
				//print border 
				if(i == 0 || i == Params.world_height + 1){
					//conner 
					if (j == 0 || j == Params.world_width + 1){
						world[i][j] = "+"; 
						continue;  
					}
					//top/bottom
					else{
						world[i][j] = "-"; 
					}
				}
				
				//Sides
				else if (j == 0 || j == Params.world_width + 1){
					world[i][j] = "|"; 
				}
				
				//space and reservations for critters
				else{
					world[i][j] = " "; 
				}
			}
		}
		
		//fill in the spaces/reservations with critters
		for  (Critter c: population){
			//update map with critter with offset
			world[c.y_coord + 1][c.x_coord + 1] = c.toString(); 
		}
		
		//print row by row
		for (int h = 0; h < Params.world_height + 2; h ++){
			//print column like column like making the map above
			for (int k = 0; k < Params.world_width + 2; k++){
				System.out.print(world[h][k]);
			}
			//seperate rows
			System.out.print("\n");
		}
			
			
		
	}
}
