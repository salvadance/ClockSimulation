package ClockProgram;
import static ClockProgram.ConsoleModifiers.*;

// A combination of all clock names added into this program into a single class for ease of access and to improve organization.

public class ClockNames extends ClockTypes {

    /* INFORMATION ABOUT THE CLASSES AND ITS FUNCTIONS
    * All of these pubic static classes extends their functionality from their derived clockTypes classes.
    * All contain a constructor that initializes with their correct clockType, with the only parameter accessed is the current time to create the necessary class.
    * All contain a tick() function that calculates the new time depending on the specified seconds set by user from the tick menu option.
    * All contain a tickdrifted() function that calculate the new time with drift added depending on the clock and specified seconds set by user from the tick menu option.
    * All contain a reset() function that resets the clock back to the initial time set by user in the create a clock menu option.
    * All contain a fullReset() function that resets the clock back to 00:00:00 regardless of what user had put in the initial time in the menu option. 
    * All contain a displayTime() function that displays the clock's name, clock's initial time, clock's new time (w/o drift), clock's drifted time, & the total drift.
    */

    public static class SundialClock extends NaturalClock {

        // Constructor that initializes the SundialClock with the initial time.
        public SundialClock(ClockTime initialTime) {
            super(initialTime, 0.0, "Sundial Clock");
        }
        
        /**
         * Ticks the clock by the specified number of seconds without drift.
         * Using long to prevent overflow for large values.
         * @param specifiedSeconds The number of seconds to tick the clock.
         */
        @Override
        public void tick(long specifiedSeconds) {
            newTime.addTimeSec(specifiedSeconds);
        }

        /**
         * Entry method to tick the clock by the specified number of seconds with drift.
         * Drifted seconds is calculated by multiplying the specified seconds by the drift per second.
         * Seconds drifted is calculated by rounding up the drifted seconds to the nearest whole number.
         * The drifted time is updated by calling the addTimeSec method with the specified seconds plus the seconds drifted as arguments.
         * total drift is updated to reflect the total drift in seconds.
         * Using long to prevent overflow for large values.
         * @param specifiedSeconds The number of seconds to tick the clock.
         */
        @Override
        public void tickDrifted(long specifiedSeconds) {
            double driftedSeconds = specifiedSeconds * driftPerSecond;
            long secondsDrifted = (long) Math.ceil(driftedSeconds);
            driftedTime.addTimeSec(specifiedSeconds + secondsDrifted);
            totalDrift = driftedSeconds; 
        }

        /**
         * Resets the clock to its initial time. (Soft reset)
         */
        @Override
        public void reset() {
            newTime = new ClockTime(initialTime);
            driftedTime = new ClockTime(initialTime);
            totalDrift = 0.0;
        }

        /**
         * Fully resets the clock to 00:00:00 regardless of initial time set by user. (Hard Reset)
         */
        @Override
        public void fullReset() {
            initialTime = new ClockTime(0, 0, 0, 0, 0, 0);
            newTime = new ClockTime(0, 0, 0, 0, 0, 0);
            driftedTime = new ClockTime(0, 0, 0, 0, 0, 0);
            totalDrift = 0.0;
        }

        /**
         * Displays the clock's name, initial time, new time, drifted time, and total drift in a formatted manner.
         * Uses console modifiers for colored and bold text.
         * Initial time and date are displayed in cyan, new time and date in purple, drifted time and date in red, and total drift in yellow.
         */
        @Override
        public void displayTime() {

            // Display clock name in bold green text
            System.out.printf(GREEN + BOLD + "%-20s " + RESET, CLOCK_NAME); // Display clock name in bold green text

            // Display initial date in bold cyan text
            System.out.printf(CYAN + "\tInitial Date: " + BOLD + "[%s]" + RESET, initialTime.dateToString());

            // Display initial time in bold cyan text
            System.out.printf(CYAN + "\tInitial Time: " + BOLD + "[%s]" + RESET, initialTime.timeToString());

            // Display new date in bold purple text
            System.out.printf(PURPLE + "\tNew Date: " + BOLD + "[%s]" + RESET, newTime.dateToString());

            // Display new time in bold purple text
            System.out.printf(PURPLE + "\tNew Time: " + BOLD + "[%s]" + RESET + "%n", newTime.timeToString());

            // Display drifted date in bold red text
            System.out.printf("%-20s" + RED + "\tDrifted Date: " + BOLD + "[%s]" + RESET, "", driftedTime.dateToString());

            // Display drifted time in bold red text
            System.out.printf(RED + "\tDrifted Time: " + BOLD + "[%s]" + RESET, driftedTime.timeToString());

            // Display total drift in bold yellow text
            System.out.printf(YELLOW + "\tTotal Drift = " + BOLD + "%.6f seconds%n" + RESET, totalDrift);

            /* 
            System.out.printf(GREEN + BOLD + "%-20s " + RESET + // Display clock name in bold green text
            CYAN + "\tInitial Date: " + BOLD + "[%s]" + RESET + // Display initial date in bold cyan text
            CYAN + "\tInitial Time: " + BOLD + "[%s " + initialTime.getAmPm() + "]" + RESET + // Display initial time in bold cyan text
            PURPLE + "\tNew Date: " + BOLD + "[%s]" + RESET + // Display new date in bold purple text
            PURPLE + "\tNew Time: " + BOLD + "[%s " + newTime.getAmPm() + "]" + RESET + "%n" + // Display new time in bold purple text
            RED + "Drifted Time: " + BOLD + "[%s " + driftedTime.getAmPm() + "]" + RESET + // Display drifted time in bold red text
            YELLOW + "\tTotal Drift = " + BOLD + "%.6f seconds%n" + RESET, // Display total drift in bold yellow text
            CLOCK_NAME, initialTime.dateToString(), initialTime.timeToString(), newTime.dateToString(), 
            newTime.timeToString(), driftedTime.timeToString(), totalDrift);
            */
        }
    }

    public static class CuckooClock extends MechanicalClock{

        // Constructor that initializes the Cuckoo Clock with the initial time.
        public CuckooClock(ClockTime initialTime) {
            super(initialTime, 0.000694444, "Cuckoo Clock");
        }

        /**
         * Ticks the clock by the specified number of seconds without drift.
         * Using long to prevent overflow for large values.
         * @param specifiedSeconds The number of seconds to tick the clock.
         */
        @Override
        public void tick(long specifiedSeconds) {
            newTime.addTimeSec(specifiedSeconds);
        }

        /**
         * Entry method to tick the clock by the specified number of seconds with drift.
         * Drifted seconds is calculated by multiplying the specified seconds by the drift per second.
         * Seconds drifted is calculated by rounding up the drifted seconds to the nearest whole number.
         * The drifted time is updated by calling the addTimeSec method with the specified seconds plus the seconds drifted as arguments.
         * total drift is updated to reflect the total drift in seconds.
         * Using long to prevent overflow for large values.
         * @param specifiedSeconds The number of seconds to tick the clock.
         */
        @Override
        public void tickDrifted(long specifiedSeconds) {
            double driftedSeconds = specifiedSeconds * driftPerSecond;
            long secondsDrifted = (long) Math.ceil(driftedSeconds);
            driftedTime.addTimeSec(specifiedSeconds + secondsDrifted);
            totalDrift = driftedSeconds; 
        }

        /**
         * Resets the clock to its initial time. (Soft reset)
         */
        @Override
        public void reset() {
            newTime = new ClockTime(initialTime);
            driftedTime = new ClockTime(initialTime);
            totalDrift = 0.0;
        }

        /**
         * Fully resets the clock to 00:00:00 regardless of initial time set by user. (Hard Reset)
         */
        @Override
        public void fullReset() {
            initialTime = new ClockTime(0, 0, 0, 0, 0, 0);
            newTime = new ClockTime(0, 0, 0, 0, 0, 0);
            driftedTime = new ClockTime(0, 0, 0, 0, 0, 0);
            totalDrift = 0.0;
        }

        /**
         * Displays the clock's name, initial time, new time, drifted time, and total drift in a formatted manner.
         * Uses console modifiers for colored and bold text.
         * Initial time and date are displayed in cyan, new time and date in purple, drifted time and date in red, and total drift in yellow.
         */
        @Override
        public void displayTime() {

            // Display clock name in bold green text
            System.out.printf(GREEN + BOLD + "%-20s " + RESET, CLOCK_NAME); // Display clock name in bold green text

            // Display initial date in bold cyan text
            System.out.printf(CYAN + "\tInitial Date: " + BOLD + "[%s]" + RESET, initialTime.dateToString());

            // Display initial time in bold cyan text
            System.out.printf(CYAN + "\tInitial Time: " + BOLD + "[%s]" + RESET, initialTime.timeToString());

            // Display new date in bold purple text
            System.out.printf(PURPLE + "\tNew Date: " + BOLD + "[%s]" + RESET, newTime.dateToString());

            // Display new time in bold purple text
            System.out.printf(PURPLE + "\tNew Time: " + BOLD + "[%s]" + RESET + "%n", newTime.timeToString());

            // Display drifted date in bold red text
            System.out.printf("%-20s" + RED + "\tDrifted Date: " + BOLD + "[%s]" + RESET, "", driftedTime.dateToString());

            // Display drifted time in bold red text
            System.out.printf(RED + "\tDrifted Time: " + BOLD + "[%s]" + RESET, driftedTime.timeToString());
            
            // Display total drift in bold yellow text
            System.out.printf(YELLOW + "\tTotal Drift = " + BOLD + "%.6f seconds%n" + RESET, totalDrift);
            
        }
    }

    public static class GrandfatherClock extends MechanicalClock{

        // Constructor that initializes the Grandfather Clock with the initial time.
        public GrandfatherClock(ClockTime initialTime) {
            super(initialTime, 0.000347222, "Grandfather Clock");
        }

        /**
         * Ticks the clock by the specified number of seconds without drift.
         * Using long to prevent overflow for large values.
         * @param specifiedSeconds The number of seconds to tick the clock.
         */
        @Override
        public void tick(long specifiedSeconds) {
            newTime.addTimeSec(specifiedSeconds);
        }

        /**
         * Entry method to tick the clock by the specified number of seconds with drift.
         * Drifted seconds is calculated by multiplying the specified seconds by the drift per second.
         * Seconds drifted is calculated by rounding up the drifted seconds to the nearest whole number.
         * The drifted time is updated by calling the addTimeSec method with the specified seconds plus the seconds drifted as arguments.
         * total drift is updated to reflect the total drift in seconds.
         * Using long to prevent overflow for large values.
         * @param specifiedSeconds The number of seconds to tick the clock.
         */
        @Override
        public void tickDrifted(long specifiedSeconds) {
            double driftedSeconds = specifiedSeconds * driftPerSecond;
            long secondsDrifted = (long) Math.ceil(driftedSeconds);
            driftedTime.addTimeSec(specifiedSeconds + secondsDrifted);
            totalDrift = driftedSeconds; 
        }

        /**
         * Resets the clock to its initial time. (Soft reset)
         */
        @Override
        public void reset() {
            newTime = new ClockTime(initialTime);
            driftedTime = new ClockTime(initialTime);
            totalDrift = 0.0;
        }

        /**
         * Fully resets the clock to 00:00:00 regardless of initial time set by user. (Hard Reset)
         */
        @Override
        public void fullReset() {
            initialTime = new ClockTime(0, 0, 0, 0, 0, 0);
            newTime = new ClockTime(0, 0, 0, 0, 0, 0);
            driftedTime = new ClockTime(0, 0, 0, 0, 0, 0);
            totalDrift = 0.0;
        }

        /**
         * Displays the clock's name, initial time, new time, drifted time, and total drift in a formatted manner.
         * Uses console modifiers for colored and bold text.
         * Initial time and date are displayed in cyan, new time and date in purple, drifted time and date in red, and total drift in yellow.
         */
        @Override
        public void displayTime() {

            // Display clock name in bold green text
            System.out.printf(GREEN + BOLD + "%-20s " + RESET, CLOCK_NAME); // Display clock name in bold green text

            // Display initial date in bold cyan text
            System.out.printf(CYAN + "\tInitial Date: " + BOLD + "[%s]" + RESET, initialTime.dateToString());

            // Display initial time in bold cyan text
            System.out.printf(CYAN + "\tInitial Time: " + BOLD + "[%s]" + RESET, initialTime.timeToString());

            // Display new date in bold purple text
            System.out.printf(PURPLE + "\tNew Date: " + BOLD + "[%s]" + RESET, newTime.dateToString());

            // Display new time in bold purple text
            System.out.printf(PURPLE + "\tNew Time: " + BOLD + "[%s]" + RESET + "%n", newTime.timeToString());

            // Display drifted date in bold red text
            System.out.printf("%-20s" + RED + "\tDrifted Date: " + BOLD + "[%s]" + RESET, "", driftedTime.dateToString());

            // Display drifted time in bold red text
            System.out.printf(RED + "\tDrifted Time: " + BOLD + "[%s]" + RESET, driftedTime.timeToString());
            
            // Display total drift in bold yellow text
            System.out.printf(YELLOW + "\tTotal Drift = " + BOLD + "%.6f seconds%n" + RESET, totalDrift);
           
        }
    }

    public static class WristClock extends DigitalClock{

        // Constructor that initializes the Digital Clock with the initial time.
        public WristClock(ClockTime initialTime) {
            super(initialTime, 0.000034722, "Wrist Clock");
        }

        /**
         * Ticks the clock by the specified number of seconds without drift.
         * Using long to prevent overflow for large values.
         * @param specifiedSeconds The number of seconds to tick the clock.
         */
        @Override
        public void tick(long specifiedSeconds) {
            newTime.addTimeSec(specifiedSeconds);
        }

        /**
         * Entry method to tick the clock by the specified number of seconds with drift.
         * Drifted seconds is calculated by multiplying the specified seconds by the drift per second.
         * Seconds drifted is calculated by rounding up the drifted seconds to the nearest whole number.
         * The drifted time is updated by calling the addTimeSec method with the specified seconds plus the seconds drifted as arguments.
         * total drift is updated to reflect the total drift in seconds.
         * Using long to prevent overflow for large values.
         * @param specifiedSeconds The number of seconds to tick the clock.
         */
        @Override
        public void tickDrifted(long specifiedSeconds) {
            double driftedSeconds = specifiedSeconds * driftPerSecond;
            long secondsDrifted = (long) Math.ceil(driftedSeconds);
            driftedTime.addTimeSec(specifiedSeconds + secondsDrifted);
            totalDrift = driftedSeconds; 
        }

        /**
         * Resets the clock to its initial time. (Soft reset)
         */
        @Override
        public void reset() {
            newTime = new ClockTime(initialTime);
            driftedTime = new ClockTime(initialTime);
            totalDrift = 0.0;
        }

        /**
         * Fully resets the clock to 00:00:00 regardless of initial time set by user. (Hard Reset)
         */
        @Override
        public void fullReset() {
            initialTime = new ClockTime(0, 0, 0, 0, 0, 0);
            newTime = new ClockTime(0, 0, 0, 0, 0, 0);
            driftedTime = new ClockTime(0, 0, 0, 0, 0, 0);
            totalDrift = 0.0;
        }

        /**
         * Displays the clock's name, initial time, new time, drifted time, and total drift in a formatted manner.
         * Uses console modifiers for colored and bold text.
         * Initial time and date are displayed in cyan, new time and date in purple, drifted time and date in red, and total drift in yellow.
         */
        @Override
        public void displayTime() {

            // Display clock name in bold green text
            System.out.printf(GREEN + BOLD + "%-20s " + RESET, CLOCK_NAME); // Display clock name in bold green text

            // Display initial date in bold cyan text
            System.out.printf(CYAN + "\tInitial Date: " + BOLD + "[%s]" + RESET, initialTime.dateToString());

            // Display initial time in bold cyan text
            System.out.printf(CYAN + "\tInitial Time: " + BOLD + "[%s]" + RESET, initialTime.timeToString());

            // Display new date in bold purple text
            System.out.printf(PURPLE + "\tNew Date: " + BOLD + "[%s]" + RESET, newTime.dateToString());

            // Display new time in bold purple text
            System.out.printf(PURPLE + "\tNew Time: " + BOLD + "[%s]" + RESET + "%n", newTime.timeToString());

            // Display drifted date in bold red text
            System.out.printf("%-20s" + RED + "\tDrifted Date: " + BOLD + "[%s]" + RESET, "", driftedTime.dateToString());

            // Display drifted time in bold red text
            System.out.printf(RED + "\tDrifted Time: " + BOLD + "[%s]" + RESET, driftedTime.timeToString());
            
            // Display total drift in bold yellow text
            System.out.printf(YELLOW + "\tTotal Drift = " + BOLD + "%.6f seconds%n" + RESET, totalDrift);

            
        }
    }

    public static class AtomicClock extends QuantumClock {

        // Constructor that initializes the Atomic Clock with the initial time.
        public AtomicClock(ClockTime initialTime) {
            super(initialTime, 0.0, "Atomic Clock");
        }

        /**
         * Ticks the clock by the specified number of seconds without drift.
         * Using long to prevent overflow for large values.
         * @param specifiedSeconds The number of seconds to tick the clock.
         */
        @Override
        public void tick(long specifiedSeconds) {
            newTime.addTimeSec(specifiedSeconds);
        }

        /**
         * Entry method to tick the clock by the specified number of seconds with drift.
         * Drifted seconds is calculated by multiplying the specified seconds by the drift per second.
         * Seconds drifted is calculated by rounding up the drifted seconds to the nearest whole number.
         * The drifted time is updated by calling the addTimeSec method with the specified seconds plus the seconds drifted as arguments.
         * total drift is updated to reflect the total drift in seconds.
         * Using long to prevent overflow for large values.
         * @param specifiedSeconds The number of seconds to tick the clock.
         */
        @Override
        public void tickDrifted(long specifiedSeconds) {
            double driftedSeconds = specifiedSeconds * driftPerSecond;
            long secondsDrifted = (long) Math.ceil(driftedSeconds);
            driftedTime.addTimeSec(specifiedSeconds + secondsDrifted);
            totalDrift = driftedSeconds; 
        }

        /**
         * Resets the clock to its initial time. (Soft reset)
         */
        @Override
        public void reset() {
            newTime = new ClockTime(initialTime);
            driftedTime = new ClockTime(initialTime);
            totalDrift = 0.0;
        }

        /**
         * Fully resets the clock to 00:00:00 regardless of initial time set by user. (Hard Reset)
         */
        @Override
        public void fullReset() {
            initialTime = new ClockTime(0, 0, 0, 0, 0, 0);
            newTime = new ClockTime(0, 0, 0, 0, 0, 0);
            driftedTime = new ClockTime(0, 0, 0, 0, 0, 0);
            totalDrift = 0.0;
        }

        /**
         * Displays the clock's name, initial time, new time, drifted time, and total drift in a formatted manner.
         * Uses console modifiers for colored and bold text.
         * Initial time and date are displayed in cyan, new time and date in purple, drifted time and date in red, and total drift in yellow.
         */
        @Override
        public void displayTime() {

            // Display clock name in bold green text
            System.out.printf(GREEN + BOLD + "%-20s " + RESET, CLOCK_NAME); // Display clock name in bold green text

            // Display initial date in bold cyan text
            System.out.printf(CYAN + "\tInitial Date: " + BOLD + "[%s]" + RESET, initialTime.dateToString());

            // Display initial time in bold cyan text
            System.out.printf(CYAN + "\tInitial Time: " + BOLD + "[%s]" + RESET, initialTime.timeToString());

            // Display new date in bold purple text
            System.out.printf(PURPLE + "\tNew Date: " + BOLD + "[%s]" + RESET, newTime.dateToString());

            // Display new time in bold purple text
            System.out.printf(PURPLE + "\tNew Time: " + BOLD + "[%s]" + RESET + "%n", newTime.timeToString());

            // Display drifted date in bold red text
            System.out.printf("%-20s" + RED + "\tDrifted Date: " + BOLD + "[%s]" + RESET, "", driftedTime.dateToString());

            // Display drifted time in bold red text
            System.out.printf(RED + "\tDrifted Time: " + BOLD + "[%s]" + RESET, driftedTime.timeToString());
            
            // Display total drift in bold yellow text
            System.out.printf(YELLOW + "\tTotal Drift = " + BOLD + "%.6f seconds%n" + RESET, totalDrift);


        }
    }
}
