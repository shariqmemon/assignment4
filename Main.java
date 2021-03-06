package assignment4;
/* CRITTERS Main.java

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
        
        //Scanner user = new Scanner(System.in); 
        //String command = user.nextLine(); 
        boolean go = true; 
        
        //while user wants to type
        while (go){
        	String command = kb.nextLine(); 
        	
        	//throw away arguments that are too big
        	if (command.split(" ").length > 3){
        		System.out.println("error processing: " + command);
        	}
        	
        	//leave by changing flag
        	else if(command.equals("quit")){
            	go = false; 
            	kb.close(); 
            	break; 
            }
            
            //call display b/c show
            //working
        	else if(command.contains("show")){
        		if (command.equals("show")){
        			Critter.displayWorld();
        		}
        		else{
        			System.out.println("error processing: " + command);
        		}
            	
            }
            
            //get number and perform number of steps
            //working
            //doesn't do anything unless make was called earlier
        	else if(command.contains("step")){
            	String[] command1 = command.split(" "); 
            	
            	//only takes 1-2 arguements 
            	if (command1.length > 2){
            		System.out.println("error processing: " + command);
            	}
            	
            	//1 argument
            	if(command1.length == 1){
            		Critter.worldTimeStep();
            	}
            	
            	//2 arguments 
            	else{
            		try{
            			int num = Integer.parseInt(command1[1]); 
                    	for (int i = 0; i < num; i++){
                    		Critter.worldTimeStep();
                    	}
            		}
            		catch (IllegalArgumentException e){
            			System.out.println("error processing: " + command);
            			continue; 
            		}
            		
            	}
            }
            
            //set the given seed
            //working
        	else if(command.contains("seed")){
            	String[] command1 = command.split(" "); 
            	if (command1.length > 2){
            		System.out.println("error processing: "  + command); 
            	}
            	else{
            		int num = Integer.parseInt(command1[1]); 
                	Critter.setSeed(num);
            	}
            }
            
            //for each number call the constructor of that class
        	//reason why we need the expection 
            //working
        	else if(command.contains("make")){
            	String[] command1 = command.split(" "); 
            	
            	//0 arguments
            	if  (command1.length == 1){
            		System.out.println("error processing: " + command);
            	}
            	
            	//1 argument 
            	else if (command1.length == 2){
            		String name = command1[1]; 
            		try{
            			String full = myPackage; 
            			
            			full = full + "." + name;  
            			Critter.makeCritter(full);
            		}
            		//check name exists
            		catch(InvalidCritterException e){
            			System.out.println("" + name + " is not a valid critter class.");
            		}
            	}
            	
            	//2 arguments
            	else{
            		String name = command1[1]; 
            		try{
            			//see if integers exists
            			int num = Integer.parseInt(command1[2]); 
                		String full = myPackage; 
            			full = full + "." + name;
                    	for (int i = 0; i < num; i++){
                    		//see if critter name exists 
                    		try{
                    			Critter.makeCritter(full);
                    		}
                    		catch(InvalidCritterException e){
                    			System.out.println("" + name + " is not a valid critter class.");
                    			break; 
                    		}
                    	}
            		}
            		catch (IllegalArgumentException e){
            			System.out.println("error processing: " + command);
            			continue; 
            		}
            		
            	}
            	
            }
            
            //call getInstances and runStats
            //check later
        	else if(command.contains("stats")){
        		
            	String[] command1 = command.split(" "); 
            	if (command1.length > 2){
            		System.out.println("invalid command: " + command );
            	}
            	else{
                	String name = command1[1];
                	List<Critter> toStat = null; 
                	String full = myPackage; 
        			full = full + "." + name;
        			//see if critter name exists
                	try{
                		toStat = Critter.getInstances(full); 
                	}
                	catch (InvalidCritterException e){
                		e.printStackTrace();
                	}
                	/*if (toStat.size() <= 0){
                		System.out.println("No instances of " + name + " are alive");
                	}*/ 
                	
                	//reflection and qualified magic 
                	Class<?> crit = null; 
                	Class [] Paraman = new Class[1]; 
                	Paraman[0] = java.util.List.class; 
                	
                	//see if critter exists and runStats exists 
                	try{
                		crit = Class.forName(full); 
                		java.lang.reflect.Method runStats = crit.getMethod("runStats", Paraman); 
                		runStats.invoke(crit, toStat); 
                	}
                	catch(Exception e){
                		System.out.println("error processing: " + command); 
                	}
            	}
            }
            //else an invalid command
            else{
            	System.out.println("invalid command: " + command );
            }
            
        }
       
        
        //////////////////////Testing Block//////////////////////////////
        ////////////////////////////////////////////////////////////////

        /* Write your code above */
        System.out.flush();

    }
}
