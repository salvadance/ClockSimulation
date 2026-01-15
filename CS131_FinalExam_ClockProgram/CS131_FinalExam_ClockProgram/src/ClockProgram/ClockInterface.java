/* - PROGRAM DOCUMENTATION -
 * CLASS: CS 131 - DATA STRUCTURES.
 
 * PROJECT DEVELOPERS: Shelten Aguilar, Salvador Lugo
 
 * DATE: 11/20/2025 || NOVEMBER 20TH, 2025
 
 * DESCRIPTION: Write a program to simulate the simultaneous running of a collection of clocks for a period of one (1) week, where each drifts a specified amount
   of time per second.

* Old VERSION: v12.25

* New VERSION v13.25 Changes:
  - Added date functionality to ClockTime.java to keep track of day, month, and year.
    - Edited ClockTime.java to include constructors, fields and string formatted getters method to accommodate for date functionality.
    - Edited clockNames.java concrete classes displayTime methods to accommodate for date functionality.
    - Added date functionality in ClockInterface.java createCustomClocks() method to get user-defined day, month, and year.
  - Added javadoc comments to newly edited methods in clockNames.java and ClockInterface.java.
  - Edited clockNames.java methods reset() and fullReset() to include javadoc comments explaining their functionality.
  - Edited clockNames.java methods displayTime() to include javadoc comments explaining their functionality.
  - Edited ClockInterface.java methods createClocksMenu(), resetMenu(), printClocksInfoTable(), displayClocksTimes(), quitMessage(), initializeClockList(), createCustomClocks(), and isLeapYear() to include javadoc comments explaining their functionality.
  - No changes were made to the logic or functionality of the code, only documentation was added for clarity.
 
 * EXAM COMPLETION DATE: 12/2/2025 || DECEMBER 2ND, 2025
*/

package ClockProgram;
import java.time.*;
import java.util.*;
import java.math.*;
import static ClockProgram.consoleModifiers.*;

// This class contains most of the major functionality of the menu and handling of the inputs from user and outputting the desired information to the user.
public class ClockInterface{
    private static char userOption;
    private static ArrayList<Clock> clockList = new ArrayList<Clock>();

    /**************************************************************************************************							 
     * 								[Menu Methods]
     * These methods handle the main menu. 
     * One prints the options the other handles the selection. 
     *
     ***************************************************************************************************/
    
    // A method that prints the menu selection screen for the user.   
    public static void printMenu() {
        System.out.println(WHITE_BACKGROUND + BLACK + BOLD + "\nTICK-TOCK EXPLORE PAGE" + RESET);
        System.out.println(GREEN + BOLD + "[C]" + RESET + WHITE + ITALICS + " - Create New Clocks / Change Time" + RESET);
        System.out.println(GREEN + BOLD + "[V]" + RESET + WHITE + ITALICS + " - View Clocks' Information Table" + RESET);
        System.out.println(GREEN + BOLD + "[D]" + RESET + WHITE + ITALICS + " - Display Clocks' Times" + RESET);
        System.out.println(GREEN + BOLD + "[T]" + RESET + WHITE + ITALICS + " - Tick All Clocks" + RESET);
        System.out.println(GREEN + BOLD + "[R]" + RESET + WHITE + ITALICS + " - Reset All Clocks" + RESET);
        System.out.println(GREEN + BOLD + "[Q]" + RESET + WHITE + ITALICS + " - Quit Program" + RESET);
        System.out.println(WHITE_BACKGROUND + BLACK + BOLD + "\n" + RESET);
    }

    // A method that contains the logic for the selections that the user makes from the menu.
    public static void clockMenu(Scanner scnr) {
       do {
            printMenu();
            userOption = scnr.next().charAt(0);

            switch (userOption) {
                // This option, when selected, creates the new sets of clocks.
                case 'C':   
                    createClocksMenu(scnr);
                    break;

                // This option, when selected, allows the user to check all of the clocks' information tables.
                case 'V':   
                    printClocksInfoTable(scnr);
                    break;

                // This option, when selected, displays all of the clocks' times (initall, new, & drifted) 
                // and how much the drift depending on the specified time by user.
                case 'D':   
                    displayClocksTimes();
                    break;

                // This option, when selected, displays all of the clocks' times (initall, new, & drifted) 
                // and how much the drift depending on the specified time by user.
                case 'T':
                    tickSelection(scnr);
                    break; 
                
                // This option, when selected, allows the user to reset all clocks by either setting back to a previous time 
                // or performing a full reset back to 0 hour time.
                case 'R':
                    resetMenu(scnr);
                    break;

                // This option, when selected, terminates the program but before that it gives a warm holiday goodbye to the user.
                case 'Q':
                    quitMessage();
                    break;
            }

        } while (userOption != 'Q');
    }

    /**
     * This method prints the clocks' information table for viewing purposes.
     * The information is obtained from each diffent clock type in clockList.
     * The drift per second is formatted to show exact decimal places using BigDecimal.
     * @param scnr is a passed Scanner object.
     */
    private static void printClocksInfoTable(Scanner scr) {
        // If clockList() is empty, this must mean that the user hasn't created any clocks, therefore none can be viewed.
        if (clockList.isEmpty()) {
            System.out.println(RED + BOLD + UNDERLINE + "\nERROR: No clocks found to be viewed... You must first create a clock(s)" + RESET);
            delay(2750);
        }
        else {
            // If clockList() isn't empty, this prompts the clockName, clockType, clockDrift, and reportedTime to be printed out for viewing purposes.
            System.out.println(WHITE_BACKGROUND + BLACK + BOLD + "\n\t\t\t\t\t\t\t\tCLOCKS' INFORMATION TABLE" + RESET);
            
            for (Clock clocks : clockList) {
                BigDecimal formattedClockDrift = BigDecimal.valueOf(clocks.getDriftPerSecond()); //BigDecimal required to get exact decimal places for clock drift.

                String clockName = GREEN + "Clock's Name: " + BOLD + clocks.getName() + RESET;
                String clockType = BLUE + "\t\tClock's Type: " + BOLD + clocks.getClockType() + RESET;
                String reportedTime = WHITE + "\t\tReported Time: " + BOLD + clocks.driftedTime.toString() + " " + clocks.driftedTime.getAmPm() + RESET;
                String clockDrift = YELLOW + "\t\tClock's Drift (per Second): " + BOLD + formattedClockDrift + RESET;
                
                typeWrite(clockName, 8);
                typeWrite(clockType, 8);
                typeWrite(reportedTime, 8);
                typeWrite(clockDrift, 8);

                delay(500);

                System.out.println("\n");
            }
            System.out.print(WHITE_BACKGROUND + BLACK + BOLD + "\n\t\t\t\t\t\t\tEND OF DATA... HEADING BACK TO MAIN MENU" + RESET + "\n");
            delay(4500);
            
        }
    }

    /** 
     * This method displays all of the clocks' times (initial, new, & drifted) and how much the drift depending on the specified time by user.
     * Checks for empty clockList and prints an appropriate error message.
     */
    private static void displayClocksTimes() {
        // If clockList() is empty, that must mean that user hasn't created any clocks, therefore no clocks and their times can be displayed.
                if (clockList.isEmpty()) {
                    System.out.println("\n" + RED + BOLD + UNDERLINE + "ERROR: No clocks found to be displayed... You must first create a clock(s)." + RESET);
                    delay(2750);
                }
                else {
                    // If clockList() isn't empty, then all of the clocks' times and their drifts are displayed to the user for viewing purposes.
                    System.out.println(WHITE_BACKGROUND + BLACK + BOLD + "\n\t\t\t\t\t\t\t\tDISPLAYING CLOCKS' TIMES" + RESET);
                    for (Clock clocks : clockList) {
                        clocks.displayTime();
                        System.out.println();
                        delay(500); 
                    }
                    System.out.print(WHITE_BACKGROUND + BLACK + BOLD + "\n\t\t\t\t\t\t\tEND OF DATA... HEADING BACK TO MAIN MENU" + RESET + "\n");
                    delay(6500);
                }
    }
    
    /** 
     * This method handles the reset menu and its options.
     * If clockList is empty, an error message is displayed.
     * If not, the user is prompted to either soft reset (back to initial time set by user) or a hard reset (back to 00:00:00).
     * There are two options for resetting all clocks in clockList.
        1. Soft Reset - calls the reset() method for each clock in clockList.
        2. Hard Reset - calls the fullReset() method for each clock in clockList.
     * @param scnr is a passed Scanner object.
    */
    private static void resetMenu(Scanner scnr) {
        char userOption = ' ';

        // If clockList() is empty, that must mean that the user haven't created any clocks, therefore no clocks can be reset.
        if (clockList.isEmpty()) {
            System.out.println(RED + BOLD + UNDERLINE + "\nERROR: No clocks found to be reset... You must first create a clock(s)." + RESET);
            delay(2750);
        }
        else {
            // If clockList() isn't empty, the user is prompted to either soft reset (back to initial time set by user) or a hard reset (back to 00:00:00).
            System.out.println(WHITE_BACKGROUND + BLACK + BOLD + "\nRESET ALL CLOCKS" + RESET);
            System.out.println(GREEN + BOLD + "[1]" + RESET + WHITE + ITALICS + "- Soft Reset (Back to Initial Time)" + RESET);
            System.out.println(GREEN + BOLD + "[2]" + RESET + WHITE + ITALICS + "- Hard Reset (Back to 00:00:00)" + RESET);
            System.out.println(GREEN + BOLD + "[ANY KEY]" + RESET + WHITE + ITALICS + " - To return to main menu" + RESET);
            userOption = scnr.next().charAt(0);

            // Selection choice for soft reset().
            if (userOption == '1') {
                for (Clock clocks : clockList) {
                    clocks.reset();
                }
            }

            // Selection choice for hard fullReset().
            else if (userOption == '2') {
                for (Clock clocks : clockList) {
                    clocks.fullReset(); 
                }  
            }
            
            // Validates to prompt a progress bar if and only if a user has selected from the two options.
            if (userOption == '1' || userOption == '2') {
                load("RESETTING");
                System.out.print(WHITE_BACKGROUND + BLACK + BOLD + "\n\t\t\t\t\t\tALL CLOCKS SUCESSFULLY RESTARTED... HEADING BACK TO MAIN MENU" + RESET + "\n");
                delay(2500);
            }
        }
    }

    /** 
     * This method displays a colorful goodbye message when the user decides to quit the program.
    */
    private static void quitMessage() {
        // Good bye message variable initialization.
        String goodByeMessage = "\nGoodbye! Thank you for using the Tick-Tock Clock Program!";
        // Get current date to determine if there is a special holiday message to display.
        LocalDateTime currentTimeDate = LocalDateTime.now();
        

        //String [] colorsUE, GREEN} = {WHITE, RED}; // Array of color codes for alternating characters.
        String[] colors = new String[]{RED, ORANGE, YELLOW, GREEN, CYAN, BLUE, PURPLE}; // Rainbow colors for general goodbye message.
        StringBuilder result = new StringBuilder(); // Allows for advanced customization of the string [effects].

        switch (currentTimeDate.getMonth()) {
            case JANUARY -> {
                if (currentTimeDate.getDayOfMonth() == 1){ // New Year's Day
                    goodByeMessage = "\nHappy New Year!";
                    colors = new String[]{SILVER, GOLD};
                }
            }
            case FEBRUARY -> {
                if (currentTimeDate.getDayOfMonth() == 14) { // Valentine's Day
                    goodByeMessage = "\nHappy Valentine's Day!";
                    colors = new String[]{BRIGHT_PINK, RED};
                }
            }
            case JULY -> {
                if (currentTimeDate.getDayOfMonth() == 4) { // Independence Day
                    goodByeMessage = "\nHappy 4th of July!";
                    colors = new String[]{RED, WHITE, BLUE};
                }
            }
            case OCTOBER -> {
                if  (currentTimeDate.getDayOfMonth() == 31) {  // Halloween
                    goodByeMessage = "\nHappy Halloween!";
                    colors = new String[]{ORANGE, BLACK};
                }
            }
            case NOVEMBER -> {
                goodByeMessage = "\nHappy Thanksgiving!"; // General Thanksgiving message for the month of November
                colors = new String[]{BROWN, ORANGE};
            }
            case DECEMBER -> {
                goodByeMessage = "\nHappy Holidays!"; // General Holiday message for the month of December
                colors = new String[]{RED, GREEN, WHITE};
            }
            default -> {
            }
        }

        
        for (int i = 0; i < goodByeMessage.length(); i++) {
            result.append(colors[i % colors.length]).append(goodByeMessage.charAt(i));
        }

        System.out.println(result.append("\u001B[0m"));
    }

 /************************************************************************************
  * 				[Create clocks menu methods]
  * These methods create clocks by either user input or system clock.
  * Uses military time.
  * Validates data to shorten code.
  * 
  * *********************************************************************************
  */

    /**
     * This method handles the create clocks menu and its options.
     * If clockList is empty, an error message is displayed.
     * There are two options for creating clocks in clockList.
        1. System Time - calls the initializeClockList() method with system time as argument.
        2. Custom Time - calls the createCustomClocks() method to get user-defined time and then calls initializeClockList() method with user-defined time as arguments.
     * @param scnr is a passed Scanner object.
    */
    private static void createClocksMenu(Scanner scnr) {
        char userOption = '0';
        
        // Prompts the user to either create clocks based on their system time or their own custom time.
        System.out.println(WHITE_BACKGROUND + BLACK + BOLD + "\nCREATE NEW CLOCKS" + RESET);
        System.out.println(GREEN + BOLD + "[1]" + RESET + WHITE + ITALICS + " - Create using your System Time" + RESET);
        System.out.println(GREEN + BOLD + "[2]" + RESET + WHITE + ITALICS + " - Create using your own Custom Time" + RESET);
        System.out.println(GREEN + BOLD + "[ANY KEY]" + RESET + WHITE + ITALICS + " - To return to main menu" + RESET);
        userOption = scnr.next().charAt(0);
        

        // This option, when selected, creates the clocks based on the user's system time.
        if (userOption == '1') {
            LocalDateTime currentTimeDate = LocalDateTime.now();
            initializeClockList(currentTimeDate);
        }
        // This option, when selected, launches the createCustomClocks() function and creates the clocks based on the user's specified hour, minute, and second.
        else if (userOption == '2') {
            createCustomClocks(scnr);
        }

        // Validates to prompt a progress bar if and only if a user has selected from the two options.
        if (userOption == '1' || userOption == '2') {
            //load("CREATING");
            System.out.print(WHITE_BACKGROUND + BLACK + BOLD + "\n\t\t\t\t\t\t\tALL CLOCKS SUCESSFULLY CREATED... HEADING BACK TO MAIN MENU" + RESET + "\n");
            //delay(2500);
        }
    }
 
    /** 
     * Initializes clockList clocks with system's current  time as arguments
     * @param LocalDateTime is a  object that contains value of System time.
     */
    public static void initializeClockList(LocalDateTime currentTimeDate) {
        clockList.clear();
        
        clockList.add(new clockNames.SundialClock(new ClockTime(currentTimeDate)));
        clockList.add(new clockNames.CuckooClock(new ClockTime(currentTimeDate)));
        clockList.add(new clockNames.GrandfatherClock(new ClockTime(currentTimeDate)));
        clockList.add(new clockNames.WristClock(new ClockTime(currentTimeDate)));
        clockList.add(new clockNames.AtomicClock(new ClockTime(currentTimeDate)));
    }

    /**
     * Initializes clockList clocks with user specified values as arguments
     * @param hour an int for hour for concrete clock type class constructor
     * @param minute an int for minute for concrete clock type class constructor
     * @param second an int for second for concrete clock type class constructor
     * @param day an int for day for concrete clock type class constructor
     * @param month an int for month for concrete clock type class constructor
     * @param year an int for year for concrete clock type class constructor
     */
    public static void initializeClockList(int hour, int minute, int second, int day, int month, int year) {
        clockList.clear();
        
        clockList.add(new clockNames.SundialClock(new ClockTime(hour, minute, second, day, month, year)));
        clockList.add(new clockNames.CuckooClock(new ClockTime(hour, minute, second, day, month, year)));
        clockList.add(new clockNames.GrandfatherClock(new ClockTime(hour, minute, second, day, month, year)));
        clockList.add(new clockNames.WristClock(new ClockTime(hour, minute, second, day, month, year)));
        clockList.add(new clockNames.AtomicClock(new ClockTime(hour, minute, second, day, month, year)));
    }

    /**
     * Method that gets user-defined values for clocks' time
     * @param Scanner object is passed
     */
    public static void createCustomClocks(Scanner scnr) {
        int userHours = -1;
        int userMinutes = -1;
        int userSeconds = -1;
        int userMonth = -1;
        int userDay = -1;
        int userYear = -1;
        String message = "None";
        String errorMessage = "None";

        clockList.clear();
        
        //Initialize messages and call helper method to get valid data for hours
        message = "\nWhat hour do you want to set it at?";
        errorMessage = "\nEnter valid military time hours: (0 - 23)";
        userHours = getValidCreateClocksData(scnr, message, errorMessage, 0, 23); // Call helper method
        
        //Initialize messages and call helper method to get valid data for minutes
        message = "\nWhat minute do you want to set it at?";
        errorMessage = "\nEnter valid minutes: (0 - 59)";
        userMinutes = getValidCreateClocksData(scnr, message, errorMessage, 0, 59); // Call helper method
        
        //Initialize messages and call helper method to get valid data for seconds
        message = "\nWhat seconds do you want to set it at?";
        errorMessage = "\nEnter valid seconds: (0 - 59)";
        userSeconds = getValidCreateClocksData(scnr, message, errorMessage, 0, 59); // Call helper method

        //Initialize message and call helper method to get valid data for year
        message = "\nWhat year do you want to set it at?";
        errorMessage = "\nEnter valid year: (e.g., 2023, 1- 9999)";
        userYear = getValidCreateClocksData(scnr, message, errorMessage, 1, 9999); // Call helper method

        //Initialize message and call helper method to get valid data for month
        message = "\nWhat month do you want to set it at?";
        errorMessage = "\nEnter valid month: (1 - 12)";
        userMonth = getValidCreateClocksData(scnr, message, errorMessage, 1, 12); // Call helper method
        
        //Switch statement to check for valid days in month
        switch (userMonth) {

            case 2 -> {
                // February check for leap year
                message = "\nWhat day do you want to set it at?";
                if (isLeapYear(userYear)) {
                    errorMessage = "\nEnter valid day: (1 - 29)";
                    userDay = getValidCreateClocksData(scnr, message, errorMessage, 1, 29); // Call helper method
                }
                else {
                    errorMessage = "\nEnter valid day: (1 - 28)";
                    userDay = getValidCreateClocksData(scnr, message, errorMessage, 1, 28); // Call helper method
                }
            }

            case 4, 6, 9, 11 -> {
                // April, June, September, November check
                message = "\nWhat day do you want to set it at?";
                errorMessage = "\nEnter valid day: (1 - 30)";
                userDay = getValidCreateClocksData(scnr, message, errorMessage, 1, 30); // Call helper method
            }
                
            case 1, 3, 5, 7, 8, 10, 12 -> {
                // January, March, May, July, August, October, December check
                message = "\nWhat day do you want to set it at?";
                errorMessage = "\nEnter valid day: (1 - 31)";
                userDay = getValidCreateClocksData(scnr, message, errorMessage, 1, 31); // Call helper method
            }
        }

        initializeClockList(userHours, userMinutes, userSeconds, userDay, userMonth, userYear); // Call initializeClockList with user-defined valuesC
        
    }


    /**
     * Helper method to check if year is leap year
     * A leap year is divsible by 4 but not by 100, unless it is also divisible by 400
     * @param year an int for year to be checked
     * @return true if leap year, false if not
     */
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /**
	 * Method to prompt user for value based on string message passed. The string
	 * errorMessage tells the user if out of bounds
	 * @param Scanner object is passed
	 * @param message is a string message to prompt user for value and type of value being obtained.
	 * @param errorMessage is a string message to tell user if value is out of bounds from lowerLimit to higherLimit 
	 * @param lowerLimit is the smallest integer the value can be
	 * @param higherLimit is the highest it can be.
	 * Loops until valid value is obtained and catching exceptions.
	 * @return a valid value
	 */
    private static int getValidCreateClocksData(Scanner scnr, String message, String errorMessage, int lowerLimit, int higherLimit) {
    	// initialize variables
    	int timeValue = 0;
    	boolean isValidData = false;
    	
    	while(!isValidData) { // While loop until isValidData is true
    		
    		//Try-catch to catch InputMismatchException and programmer defined exception
            try {
            	//Print message to prompt user for value and get value
                System.out.println(BOLD + ITALICS + message + RESET); 
                timeValue = scnr.nextInt();

                if (timeValue < lowerLimit || timeValue > higherLimit) { // Throw exception if value is out of bounds
                    throw new Exception(RED + BOLD + UNDERLINE + errorMessage + RESET); //Use errorMessage from arguments as String message for Exception 
                }

                isValidData = true; // If this statement is reached set isValidData to true to end while loop
            }
            catch (InputMismatchException excpt) { // Catch block for InputMismatchException, value is considered invalid
                System.out.println(RED + BOLD + UNDERLINE + "\nInvalid input: Try again" + RESET);
                //isValidData = false;
                scnr.next();
                delay(1000);
            }
            catch (Exception excpt) { // Catch block for programmer-defined exception 
                System.out.println(excpt.getMessage());
                delay(1000);
                //isValidData = false;
            }
        }

        return timeValue; // Return valid value
    }

    /* ******************************************************************************************************************
     * 													[Tick]
     *                                      Methods for tick clock selection
     ******************************************************************************************************************/
    
    /** 
     * This method handles the tick selection menu and its options.
     * If clockList is empty, an error message is displayed.
     * If not, the tickClocks() function is called.
     * @param scnr is a passed Scanner object.
    */
    private static void tickSelection(Scanner scnr) {
        // If clockList() is empty, that must mean that the user haven't created any clocks, therefore no clocks can be ticked.
        if (clockList.isEmpty()) {
            System.out.println(RED + BOLD + UNDERLINE + "\nERROR: No clocks found to be ticked... You must first create a clock(s)." + RESET);
            delay(2750);
        }
        else {
            // If clockList() isn't empty, the tickClocks() function is called, which askes the user to specify the tick amount, afterwards prompts loading.
            tickClocks(scnr);
            //load("TICKING");
            System.out.print(WHITE_BACKGROUND + BLACK + BOLD + "\n\t\t\t\t\t\tALL CLOCKS SUCESSFULLY TICKED... HEADING BACK TO MAIN MENU" + RESET + "\n");
            //C
            // delay(2500);
        }
    }

    /**
     * This helper method is for getting a valid value for the user from tick method
     * Catches InputMismatchException
     * Has dataIsValid initialized to false before while loop
     * Will stay in while loop is InputMismatchException and user inputs a negative value 
     * @param scnr a passed Scanner object
     * @param prompt a message to prompt user for type of value obtained
     * @return returns a valid tick value
     */
    private static int checkValidTickValue(Scanner scnr, String prompt) {
        int tickValue = -1; //initialize tickValue
        
        boolean isValidData = false; //initialize isValidData

        do {
            // Try-catch for InputMismatch and programmer-defined Exception
            try {
                //Prompt user for value and set it to tickValue
                System.out.println("\n" + BOLD + ITALICS + prompt + RESET);
                tickValue = scnr.nextInt(); // will throw inputmismatch exception

                if (tickValue < 0) { //If statement to throw exception is value is negative
                    throw new Exception("\n" + RED + BOLD + UNDERLINE + "ERROR: Non-negative values only!" + RESET);
                }

                isValidData = true;
            }
            catch (InputMismatchException excpt) { // catch block for InputMistmatch Exception 
                System.out.println("\n" + RED + BOLD + UNDERLINE + "ERROR: Invalid Input. Integers Only!" + RESET);
                // delay(1000);
                scnr.next();
            }
            catch (Exception excpt) { // Catch block for programmer-defined exception
                System.out.println(excpt.getMessage());
                // delay(1000);
            }
        } while (!isValidData);

        return tickValue; //return tickValue
    }

    /**
	* Method for option for Tick for ticking clocks.
	* Checks for empty clockList.
	* Uses helper methods to validate user input.
	* @param scnr is a passed Scanner object.
	*/
    public static void tickClocks(Scanner scnr) {
        final long  SECONDS_IN_MINUTE = 60;
        final long SECONDS_IN_HOUR = SECONDS_IN_MINUTE * 60;
        final long SECONDS_IN_DAY = SECONDS_IN_HOUR * 24;
	    final long SECONDS_IN_WEEK = SECONDS_IN_DAY * 7;
        final long SECONDS_IN_MONTH = SECONDS_IN_DAY * 30;
	    final long SECONDS_IN_YEAR = SECONDS_IN_DAY * 365;
        
        int userSeconds = -1;
        int userMinutes = -1;
        int userHours = -1;
        int userDays = -1;
        int userWeeks = -1;
        int userMonths = -1;
        int userYears = -1;

        int totalTicks = 0;

    	//Set returned values to corresponding variables
        //Passes a Scanner object and a String
        userSeconds = checkValidTickValue(scnr, "How many second(s) long to tick for?");
        userMinutes = checkValidTickValue(scnr, "How many minutes(s) long to tick for?");
        userHours = checkValidTickValue(scnr, "How many hour(s) long to tick for?");
        userDays = checkValidTickValue(scnr, "How many day(s) long to tick for?");
        userWeeks = checkValidTickValue(scnr, "How many week(s) long to tick for?");
        userMonths = checkValidTickValue(scnr, "How many month(s) long to tick for? (Assuming 30 days per month)");
        userYears = checkValidTickValue(scnr, "How many year(s) long to tick for? (Assuming 365 days per year)");

        //Calculate total ticks
        // Use long to prevent overflow for large tick values
        totalTicks = userSeconds; // Add seconds directly
        totalTicks += ((long)userMinutes * SECONDS_IN_MINUTE); // Convert minutes to seconds and add
        totalTicks += ((long)userHours * SECONDS_IN_HOUR); // Convert hours to seconds and add
        totalTicks += ((long)userDays * SECONDS_IN_DAY); // Convert days to seconds and add
        totalTicks += ((long)userWeeks * SECONDS_IN_WEEK); // Convert weeks to seconds and add
        totalTicks += ((long)userMonths * SECONDS_IN_MONTH); // Convert months to seconds and add, assuming 30 days per month
        totalTicks += ((long)userYears * SECONDS_IN_YEAR); // Convert years to seconds and add, assuming 365 days per year

        // Calculates the new time (w/o drift included).
        for (Clock clock : clockList) {
            clock.tick(totalTicks);
        }

        // Calculates the new time (w/ drift included).
        for (Clock clock : clockList) {
            clock.tickDrifted(totalTicks);
        }
    }
    
    // In main, clockInterface.clockMenu() as the Scanner scnr object passed as the parameter, which allows for the main program to be promptly started.
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        ClockInterface.clockMenu(scnr);

        scnr.close();
    }
}