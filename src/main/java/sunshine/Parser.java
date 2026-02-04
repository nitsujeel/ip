package sunshine;

/**
 * Class to deal with parsing the user's input.
 */
public class Parser {

    /**
     * Separates an input String into its first word and its remaining words.
     *
     * @param input Input to parse.
     * @return List containing first word as the first element and remaining words as the second element.
     */
    public static String[] parseInput(String input) {
        String[] parts = input.split("\s", 2);
        String cmd = parts[0];
        String arg = (parts.length == 2) ? parts[1] : "";
        return new String[] {cmd, arg};
    }

    /**
     * Converts a String of integers into an Integer.
     *
     * @param i String of integers.
     * @return Integer form of input String.
     */
    public static int parseInt(String i) {
        return Integer.parseInt(i);
    }

    /**
     * Parses the input for Deadline creation.
     *
     * @param arg String argument for Deadline creation.
     * @return List with description as first element and deadline as second.
     */
    public static String[] parseDeadline(String arg) {
        return arg.split(" /by ");
    }

    /**
     * Parses the input for Event creation.
     *
     * @param arg String argument for Event creation.
     * @return List with description as first element, start time as second, and end time as third.
     */
    public static String[] parseEvent(String arg) {
        String[] splits1 = arg.split(" /from ");
        String[] splits2 = splits1[1].split(" /to ");
        return new String[] {splits1[0], splits2[0], splits2[1]};
    }
}
