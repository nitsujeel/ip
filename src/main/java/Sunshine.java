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
        while (true) {
            String cmd = scanner.nextLine();
            if (cmd.equals("bye")) {
                break;
            } else if (cmd.equals("list")) {
                System.out.print(line);
                for (int i = 0; i < task_count; i++) {
                    System.out.println("\t " + (i+1) + "." + list[i]);
                }
                System.out.println(line);
            } else {
                list[task_count] = new Task(cmd);
                task_count++;
                System.out.println(line +
                        "\t added: " + cmd + "\n" +
                        line);
            }
        }

        // Farewell
        String farewell = line +
                "\t Bye. Hope to see you again soon!\n" +
                line;
        System.out.println(farewell);
    }
}
