import java.util.Scanner;

public class Sunshine {
    public static void main(String[] args) {

        // Welcome
        String line = "\t____________________________________________________________\n";
        String welcome = line +
                "\t Hello! I'm Sunshine\n" +
                "\t What can I do for you?\n" +
                line;
        System.out.println(welcome);

        // Main loop
        Scanner scanner = new Scanner(System.in);
        Task[] list = new Task[100];
        int task_count = 0;
        boolean exit = false;
        do {
            String input = scanner.nextLine();
            String[] parts = input.split("\\s+", 2);
            String cmd = parts[0];
            String arg = (parts.length == 2) ? parts[1] : "";
            switch (cmd) {
                case ("bye"):
                    exit = true;
                    break;
                case ("list"):
                    System.out.print(line);
                    for (int i = 0; i < task_count; i++) {
                        System.out.println("\t " + (i + 1) + "." + list[i]);
                    }
                    System.out.println(line);
                    break;
                default:
                    list[task_count] = new Task(cmd);
                    task_count++;
                    System.out.println(line +
                            "\t added: " + cmd + "\n" +
                            line);
            }
        } while (!exit);

            // Farewell
            String farewell = line +
                    "\t Bye. Hope to see you again soon!\n" +
                    line;
            System.out.println(farewell);
    }
}