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
        int taskCount = 0;
        boolean exit = false;
        do {
            String input = scanner.nextLine();
            String[] parts = input.split("\s", 2);
            String cmd = parts[0];
            String arg = (parts.length == 2) ? parts[1] : "";
            switch (cmd) {
                case ("bye"):
                    exit = true;
                    break;
                case ("list"):
                    System.out.print(line);
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println("\t " + (i + 1) + "." + list[i]);
                    }
                    System.out.println(line);
                    break;
                case ("mark"):
                    int indexMark = Integer.parseInt(arg);
                    list[indexMark-1].mark();
                    System.out.println(line +
                            "\t Nice! I've marked this task as done:\n\t   " +
                            list[indexMark-1] + "\n" +
                            line);
                    break;
                case ("unmark"):
                    int indexUnmark = Integer.parseInt(arg);
                    list[indexUnmark-1].unmark();
                    System.out.println(line +
                            "\t OK, I've marked this task as not done yet:\n\t   " +
                            list[indexUnmark-1] + "\n" +
                            line);
                    break;
                case ("todo"):
                    ToDo todo = new ToDo(arg);
                    list[taskCount] = todo;
                    taskCount++;
                    System.out.println(line +
                            "\t Got it. I've added this task:\n " +
                            "\t   " + todo + "\n" +
                            "\t Now you have " + taskCount + " tasks in the list.\n" +
                            line);
                    break;
                case ("deadline"):
                    String[] dlSplit = arg.split(" /by ");
                    Deadline dl = new Deadline(dlSplit[0], dlSplit[1]);
                    list[taskCount] = dl;
                    taskCount++;
                    System.out.println(line +
                            "\t Got it. I've added this task:\n " +
                            "\t   " + dl + "\n" +
                            "\t Now you have " + taskCount + " tasks in the list.\n" +
                            line);
                    break;
                case ("event"):
                    String[] eSplit1 = arg.split(" /from ");
                    String[] eSplit2 = eSplit1[1].split(" /to ");
                    Event e = new Event(eSplit1[0], eSplit2[0], eSplit2[1]);
                    list[taskCount] = e;
                    taskCount++;
                    System.out.println(line +
                            "\t Got it. I've added this task:\n " +
                            "\t   " + e + "\n" +
                            "\t Now you have " + taskCount + " tasks in the list.\n" +
                            line);
                    break;
                default:
                    String taskName = cmd + " " + arg;
                    list[taskCount] = new Task(taskName);
                    taskCount++;
                    System.out.println(line +
                            "\t added: " + taskName + "\n" +
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