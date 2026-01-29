public class Parser {

    public static String[] parseInput(String input) {
        String[] parts = input.split("\s", 2);
        String cmd = parts[0];
        String arg = (parts.length == 2) ? parts[1] : "";
        return new String[] {cmd, arg};
    }

    public static int parseInt(String i) {
        return Integer.parseInt(i);
    }

    public static String[] parseDeadline(String arg) {
        return arg.split(" /by ");
    }

    public static String[] parseEvent(String arg) {
        String[] splits1 = arg.split(" /from ");
        String[] splits2 = splits1[1].split(" /to ");
        return new String[] {splits1[0], splits2[0], splits2[1]};
    }
}
