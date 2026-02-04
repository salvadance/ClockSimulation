package ClockProgram;

import java.time.*;


public class ClockTime {
    // Integers initalized to keep track of the hours, minutes, and seconds individually.
    private int hours, minutes, seconds;
    private int day, month, year;

    // This constructor is called if and only if the user has specified their own custom time in the create a clocks menu option.
    public ClockTime(int hours, int minutes, int seconds, int day, int month, int year) {
            this.hours = hours;
            this.minutes = minutes;
            this.seconds = seconds;
            this.day = day;
            this.month = month;
            this.year = year;
    }

    // This constructor is called to copy an existing ClockTime object.
    public ClockTime(ClockTime time) {
        this(time.hours, time.minutes, time.seconds, time.day, time.month, time.year);
    }

    // This constructor is called if and only if the user has specified their to take their system time in the create a clocks menu option.
    public ClockTime(LocalDateTime currentTime) {
        this.hours = currentTime.getHour();
        this.minutes = currentTime.getMinute();
        this.seconds = currentTime.getSecond();

        this.day = currentTime.getDayOfMonth();
        this.month = currentTime.getMonthValue();
        this.year = currentTime.getYear();

    }


    // Calculates the tick that is added to the clock's hour, minute, & second depending on the specified time (converted to secs) set by the user in the tick menu option.
    public void addTimeSec(long specifiedSeconds) {
        final long SECONDS_IN_A_DAY = 86_400L;

        // Total seconds is calculated by converting the current hours and minutes to seconds and adding the specified seconds to it.
        long totalSeconds = (hours * 3600L) + (minutes * 60L) + seconds + specifiedSeconds;

        long totalDays = totalSeconds / SECONDS_IN_A_DAY; // Calculate total days passed
        long secondsOfDay = totalSeconds % SECONDS_IN_A_DAY; // Seconds within the current day

        // Update hours, minutes, and seconds based on secondsOfDay
        hours = (int) (secondsOfDay / 3600L);
        minutes = (int) ((secondsOfDay % 3600L) / 60L);
        seconds = (int) (secondsOfDay % 60L);

        // Update the date based on totalDays passed
        changeDate(totalDays);        

    }

    /**
     * A private helper method that checks if the current year is a leap year.
     * @return true if the current year is a leap year, false otherwise.
     * @param year The year to check.
     */
    private boolean checkLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
    
    /**
     * A private helper method that changes the date of the clock based on the total number of days passed.
     * It correctly accounts for leap years and the varying number of days in each month.
     * To help optimize the process, it first handles complete 400-year cycles, then complete years, 
     * and finally the remaining days month by month.
     * @param totalDays The total number of days to add to the current date.
     */
    private void changeDate(long totalDays) {

        // If no days to add, return early.
       if (totalDays <= 0L) return;

       int daysInMonth;
       int remainingDaysInMonth;
       int daysInCurrentYear;
       long blocks400Years;
       final long DAYS_IN_400_YEARS = 146_097L; // 365*400 + 97 leap days

       // Handle complete 400-year cycles first for efficiency.
       blocks400Years = totalDays / DAYS_IN_400_YEARS;
       if (blocks400Years > 0L) {
           year += (int) (blocks400Years * 400L);
           totalDays -= blocks400Years * DAYS_IN_400_YEARS;
       }

       // Handle complete years next.
       // AI was used to fix the logic in this section.
       daysInCurrentYear = getDaysToSameDateNextYear();
       while (totalDays >= daysInCurrentYear) {
           totalDays -= daysInCurrentYear;
           year++;
           daysInCurrentYear = getDaysToSameDateNextYear();
        }

        // Finally, handle remaining days month by month.
        while (totalDays > 0L) {
            

            switch (month) { // Determine days in the current month
                case 1, 3, 5, 7, 8, 10, 12 -> daysInMonth = 31;
                case 4, 6, 9, 11 -> daysInMonth = 30;
                case 2 -> daysInMonth = checkLeapYear(year) ? 29 : 28;
                default -> throw new IllegalStateException("Unexpected month: " + month);
            }

            remainingDaysInMonth = daysInMonth - day; // Days left in the current month

            // If the remaining days in the current month can cover the totalDays, just add and return.
            if (totalDays <= remainingDaysInMonth) {
                day += (int) totalDays;
                return;
            } 
            // Otherwise, move to the next month.
            else {
                totalDays -= (long)remainingDaysInMonth + 1L;
                day = 1;
                month++;

                if (month > 12) {
                    month = 1;
                    year++;
                }
            }
            
        }
            
        
    }

    /**
     * A private helper method that calculates the number of days from the current date to the same date in the next year,
     * taking into account whether the current year or the next year is a leap year.
     * AI was used to help fix the logic in this section, because there are special cases to consider.
     * @return The number of days to the same date next year.
     */
    private int getDaysToSameDateNextYear() {

         // Special case: If current date is Feb 29 and a leap year, next year must be 366 days
       if (month == 2 && day == 29) {
           return 366;
       }

       // Special case: If current year is leap year and date is before or on Feb 28, return 366
       if (checkLeapYear(year) && (month < 2 || (month == 2 && day <= 28))) {
           return 366;
       }

       // Special case: If next year is a leap year and current date is after Feb, return 366
       if (checkLeapYear(year + 1) && month > 2) return 366; 
       
       // Otherwise, return 365
       return 365;
    }


    /**
     * Returns the time in "HH:MM:SS" format.
     * @return A string representing the time.
     */
    public String timeToString() {
        return String.format("%02d:%02d:%02d", this.hours, this.minutes, this.seconds);
    }

    /**
     * Returns the date in "MM-DD-YYYY" format.
     * @return A string representing the date.
     */
    public String dateToString() {
        return String.format("%02d-%02d-%04d", this.month, this.day, this.year);
    }
}