package assignment4;
/* CRITTERS Main.java
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

import java.util.List;
import java.util.Scanner;
import java.io.*;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) { 
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        
        ///////////////////////////////////////////////////////////////
        /* Write your code below. */
        
        Scanner user = new Scanner(System.in); 
        String command = user.nextLine(); 
        boolean go = true; 
        
        //leave by changing flag
        if(command.equals("quit")){
        	go = false; 
        }
        
        //call display b/c show
        //working
        if(command.equals("show")){
        	Critter.displayWorld();
        }
        
        //get number and perform number of steps
        //working
        //doesn't do anything unless make was called earlier
        if(command.contains("step")){
        	String[] command1 = command.split(" "); 
        	//empty steps
        	if(command1.length == 1){
        		Critter.worldTimeStep();
        	}
        	else{
        		int num = Integer.parseInt(command1[1]); 
            	for (int i = 0; i < num; i++){
            		Critter.worldTimeStep();
            	}
        	}
        }
        
        //set the given seed
        //working
        if(command.contains("seed")){
        	String[] command1 = command.split(" "); 
        	int num = Integer.parseInt(command1[1]); 
        	Critter.setSeed(num);
        }
        
        //for each number call the constructor of that class
    	//reason why we need the expection 
        //working
        if(command.contains("make")){
        	String[] command1 = command.split(" "); 
        	String name = command1[1];
        	//no number
        	if (command1.length == 2){
        		try{
        			//check piazza before continuing 
        			String full = myPackage; 
        			full = full + "." + name;  
        			//System.out.println(full);
        			Critter.makeCritter(full);
        		}
        		catch(InvalidCritterException e){
        			System.out.println("" + name + " is not a valid critter class.");
        		}
        	}
        	//number
        	else{
        		int num = Integer.parseInt(command1[2]);
        		String full = myPackage; 
    			full = full + "." + name;
            	for (int i = 0; i < num; i++){
            		try{
            			Critter.makeCritter(full);
            		}
            		catch(InvalidCritterException e){
            			System.out.println("" + name + " is not a valid critter class.");
            			break; 
            		}
            	}
        	}
        	
        }
        
        //call getInstances and runStats
        //check later
        if(command.contains("stat")){
        	String[] command1 = command.split(" "); 
        	String name = command1[1];
        	List<Critter> toStat = null; 
        	String full = myPackage; 
			full = full + "." + name;
        	try{
        		toStat = Critter.getInstances(full); 
        	}
        	catch (InvalidCritterException e){
        		e.printStackTrace();
        	}
        	if (toStat.size() <= 0){
        		System.out.println("No instances of " + name + " are alive");
        	}
        	
        	//Java magic from stack over flow about making it qualified
        	Class<?> crit = null; 
        	Class [] Paraman = new Class[1]; 
        	Paraman[0] = java.util.List.class; 
        	
        	try{
        		crit = Class.forName(name); 
        		java.lang.reflect.Method runStats = crit.getMethod("runStats", Paraman); 
        		runStats.invoke(crit, toStat); 
        	}
        	catch(Exception e){
        		e.printStackTrace(); 
        	}
        }
        
        //////////////////////Testing Block//////////////////////////////
        //Running 
        System.out.println("GLHF");
        //constructor - changed craig to test babies
        try{
        	Critter.makeCritter("assignment4.Craig");
        }
        catch (InvalidCritterException e){
        	System.out.println("No Craig");
        }
        //view
        Critter.displayWorld();
        //walk/time step/babies 
        Craig.worldTimeStep();
        Critter.displayWorld(); 
        //delete/clear world 
        Critter.clearWorld(); 
        Critter.displayWorld();
        int x = 10; 
        x ++; 
        ////////////////////////////////////////////////////////////////
        
      
        
      
        
        
        /* Write your code above */
        System.out.flush();

    }
}
