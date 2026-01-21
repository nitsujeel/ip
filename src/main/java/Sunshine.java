import java.util.Scanner;

public class Sunshine {
    public static void main(String[] args) {
        String welcome = "    ____________________________________________________________\n" +
                "    Hello! I'm Sunshine\n" +
                "    What can I do for you?\n" +
                "    ____________________________________________________________\n";

        System.out.println(welcome);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.equals("bye")) {
                break;
            }
            System.out.println("    ____________________________________________________________\n" +
                    "    " + cmd + "\n" +
                    "    ____________________________________________________________\n");
        }

        String farewell = "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n";
        System.out.println(farewell);
    }
}
