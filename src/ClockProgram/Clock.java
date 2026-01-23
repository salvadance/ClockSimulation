package ClockProgram;

public abstract class Clock {
    // Initialized protected primitive and wrapper objects to ensure that only the classes and derived classes are to access them.
    protected String CLOCK_TYPE;
    protected String CLOCK_NAME;

    protected ClockTime initialTime;
    protected ClockTime newTime;
    protected ClockTime driftedTime;

    protected double driftPerSecond;
    protected double totalDrift;

    // The constructor that is called by clockTypes() and clockNames() that completely initializes all of them as part of the clock object class hierarchy.
    public Clock(String clockType, ClockTime time, double driftPerSecond, String name) {
        this.CLOCK_TYPE = clockType;
        this.CLOCK_NAME = name;

        this.initialTime = new ClockTime(time);
        this.newTime = new ClockTime(time);
        this.driftedTime = new ClockTime(time);

        this.driftPerSecond = driftPerSecond;
        totalDrift = 0.0;
    }

    // Returns the clock's name (example: Cuckoo Clock).
    public String getName() {
        return CLOCK_NAME;
    }

    // Returns the clock's type (example: Mechanical Clock)
    public String getClockType() {
        return CLOCK_TYPE;
    }

    // Returns the clock's drift per second (if applicable)
    public double getDriftPerSecond() {
        return driftPerSecond;
    }

    // All abstracts displayTime(), tick(parameter long), tickedDrifted(parameter long), reset(), and fullReset() are more depthly explained in clockNames().java.
    abstract void displayTime();

    abstract void tick(long specifiedSeconds);

    abstract void tickDrifted(long specifiedSeconds);

    abstract void reset();

    abstract void fullReset();
}