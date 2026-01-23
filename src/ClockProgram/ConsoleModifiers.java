package ClockProgram;

import java.util.*;

public class ConsoleModifiers {

    // All of these public static final strings contain special codes that allows text/string and their colors (text and backdrop) and fonts to be modified by programmer.
    public static final String RESET = "\u001B[0m"; // Returns back to standard format of string outputs in terminal.

    public static final String BOLD = "\u001B[1m";
    public static final String ITALICS = "\033[3m";
    public static final String UNDERLINE = "\033[4m";

    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String BRIGHT_PINK = "\u001B[95m";
    public static final String ORANGE = "\u001B[38;2;255;165;0m";
    public static final String BROWN = "\u001B[38;2;150;75;0m";
    public static final String GOLD = "\u001B[38;2;255;215;0m";
    public static final String SILVER = "\u001B[38;2;192;192;192m";
    //MAGENTA = "\u001B[35m"

    public static final String BLACK_BACKGROUND = "\u001B[40m";
    public static final String RED_BACKGROUND = "\u001B[41m";
    public static final String GREEN_BACKGROUND = "\u001B[42m";
    public static final String YELLOW_BACKGROUND = "\u001B[43m";
    public static final String BLUE_BACKGROUND = "\u001B[44m";
    public static final String PURPLE_BACKGROUND = "\u001B[45m";
    public static final String CYAN_BACKGROUND = "\u001B[46m";
    public static final String WHITE_BACKGROUND = "\u001B[47m";

    // A public function method when accessed by programmer, it halts (or delays) for a decided amount of milliseconds until the next set of statement are executed.
    public static void delay(long milliseconds) {
        // A try and catch block to ensure when a delay is prompted, it is not interrupted by user or programer.
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException interruptedSleep) {
            interruptedSleep.printStackTrace();
        }
    }

    // A public function method when accessed by programmer, it outputs the string simulating as if were it was a typewriter spelling it out with milliseconds specifying the delay between each char.
    public static void typeWrite(String text, long milliseconds) {
        int i;

        // A for-loop that repeats until the text's maximum length has been reached, the printf and %c is what allows the format to be outputted letter by letter to terminal.
        for (i = 0; i < text.length(); i++) {
            System.out.printf("%c", text.charAt(i));
            delay(milliseconds);
        }
    }

    // A public function method when accessed by programmer, it imitates a loading progress bar as if it was rendering, calculating, or allocating objects, problems, or memory.
    public static void load(String type) {
        // Random class is created and assigns integer milisecond with a number range of 100 - 150 to simulate as if different options require various loadings to complete.
        Random randgen = new Random();
        int miliseconds;
        miliseconds = randgen.nextInt(50) + 101;

        // A for loop that repeats until the progress bar has reached 100% (complete), with the \r allowing the ability of single-line animation in the terminal.
        for (int i = 0; i <= 100; i++) {
            System.out.print("\r" + type + ": [" + GREEN + "|".repeat(i) + RESET + "] " + i + "%");
            delay(miliseconds); // Delay to simulate processing
            --miliseconds; // Makes it as if it rendering faster
        }
    }
}
