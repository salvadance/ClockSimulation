package ClockProgram;

// A combination of all clock types added into this program into a single class for ease of access and to improve organization.

public abstract class ClockTypes {

    /* INFORMATION ABOUT THE CLASSES AND ITS FUNCTIONS
    * All of these abstract class are derived from the superclass Clock which extends their functionality and allows it to be passed down to clockNames().java.
    * All call the constructor of the superclass Clock that initalizes the clockType, initialTime, driftsPerSecond, and clockName obtained from clockNames().java.
    * All abstracts displayTime(), tick(parameter long), tickedDrifted(parameter long), reset(), and fullReset() are more depthly explained in clockNames().java.
    */

    public static abstract class NaturalClock extends Clock{
        public NaturalClock(ClockTime initialTime, double driftsPerSecond, String name) {
            super("Natural Clock", initialTime, driftsPerSecond, name);
        }

        @Override
        abstract void displayTime();

        @Override
        abstract void tick(long specifiedSeconds);
      
        @Override
        abstract void tickDrifted(long specifiedSeconds);

        @Override
        abstract void reset();

        @Override
        abstract void fullReset();
    }

    public static abstract class MechanicalClock extends Clock{
        public MechanicalClock(ClockTime initialTime, double driftsPerSecond, String name) {
            super("Mechanical Clock", initialTime, driftsPerSecond, name);
        }

        @Override
        abstract void displayTime();

        @Override
        abstract void tick(long specifiedSeconds);

        @Override
        abstract void tickDrifted(long specifiedSeconds);

        @Override
        abstract void reset();

        @Override
        abstract void fullReset();
    }

    public static abstract class QuantumClock extends Clock{
        public QuantumClock(ClockTime initialTime, double driftsPerSecond, String name) {
            super("Quantum Clock", initialTime, driftsPerSecond, name);
        }

        @Override
        abstract void displayTime();

        @Override
        abstract void tick(long specifiedSeconds);

        @Override
        abstract void tickDrifted(long specifiedSeconds);

        @Override
        abstract void reset();

        @Override
        abstract void fullReset();
    }

    public static abstract class DigitalClock extends Clock{
        public DigitalClock(ClockTime initialTime, double driftsPerSecond, String name) {
            super("Digital Clock", initialTime, driftsPerSecond, name);
        }

        @Override
        abstract void displayTime();

        @Override
        abstract void tick(long specifiedSeconds);

        @Override
        abstract void tickDrifted(long specifiedSeconds);

        @Override
        abstract void reset();

        @Override
        abstract void fullReset();
    }
}
