import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Sunshine {
    public static void main(String[] args) {

        String line = "\t____________________________________________________________\n";

        Task[] list = new Task[100];
        int taskCount = 0;
        String filePath = "data" + File.separator + "sunshine.txt";
        System.out.println(filePath);
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                switch (s.nextLine()) {
                case "T":
                    String tdDone = s.nextLine();
                    String tdDesc = s.nextLine();
                    System.out.println(tdDone + tdDesc);
                    ToDo td = new ToDo(tdDesc);
                    if (tdDone.equals("1")) {
                        td.mark();
                    }
                    list[taskCount++] = td;
                    break;
                case "D":
                    String dlDone = s.nextLine();
                    String dlDesc = s.nextLine();
                    String dlBy = s.nextLine();
                    Deadline dl = new Deadline(dlDesc, dlBy);
                    if (dlDone.equals("1")) {
                        dl.mark();
                    }
                    list[taskCount++] = dl;
                    break;
                case "E":
                    String evDone = s.nextLine();
                    String evDesc = s.nextLine();
                    String evFrom = s.nextLine();
                    String evTo = s.nextLine();
                    Event ev = new Event(evDesc, evFrom, evTo);
                    if (evDone.equals("1")) {
                        ev.mark();
                    }
                    list[taskCount++] = ev;
                    break;
                }
            }
            System.out.println(line +
                    "\t Found some saved tasks you've been procrastinating!\n" +
                    line);
        } catch (FileNotFoundException e) {
            System.out.println(line +
                    "\t Seems like you don't have any saved tasks...\n" +
                    "\t Creating a local list now...");
            File f = new File(filePath);
            try {
                f.createNewFile();
                System.out.println("\t Success!");
            } catch (IOException ex) {
                System.out.println("\t Something went wrong: " + e.getMessage());
            }
            System.out.println(line);
        } catch (EmptyDescriptionException e) {
            System.out.println(line +
                    "\t There was an issue loading your saved tasks: " + e.getMessage() +
                    line);
        }

        // Welcome
        String welcome = line +
                "\t Hello! I'm Sunshine :)\n" +
                "\t What can I do for you?\n" +
                line;
        System.out.println(welcome);

        // Main loop
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        do {
            String input = scanner.nextLine();
            String[] parts = input.split("\s", 2);
            String cmd = parts[0];
            String arg = (parts.length == 2) ? parts[1] : "";
            switch (cmd) {
            case "bye":
                exit = true;
                break;
            case "list":
                System.out.print(line);
                System.out.println("\t Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("\t " + (i + 1) + "." + list[i]);
                }
                System.out.println(line);
                break;
            case "mark":
                try {
                    int indexMark = Integer.parseInt(arg);
                    list[indexMark-1].mark();
                    System.out.println(line +
                            "\t Good job bubs! I've marked this task as done:\n\t   " +
                            list[indexMark-1] + "\n" +
                            line);
                    // TODO: mark in txt
                } catch (NumberFormatException e) {
                    System.out.println(line +
                            "\t Which one la?\n" +
                            line);
                } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                    System.out.println(line +
                            "\t You don't even have that many tasks, stop gaslighting me.\n" +
                            line);
                }
                break;
            case "unmark":
                try {
                    int indexUnmark = Integer.parseInt(arg);
                    list[indexUnmark-1].unmark();
                    System.out.println(line +
                            "\t So you lied to me la. I've marked this task as not done yet:\n\t   " +
                            list[indexUnmark-1] + "\n" +
                            line);
                    // TODO: unmark in txt
                } catch (NumberFormatException e) {
                    System.out.println(line +
                            "\t Which one la?\n" +
                            line);
                } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                    System.out.println(line +
                            "\t You don't even have that many tasks, stop gaslighting me.\n" +
                            line);
                }
                break;
            case "todo":
                ToDo todo;
                try {
                    todo = new ToDo(arg);
                    FileWriter fw = new FileWriter(filePath, true);
                    fw.write("T" + System.lineSeparator() + "0" + System.lineSeparator() + arg +
                            System.lineSeparator());
                    fw.close();
                } catch (EmptyDescriptionException e) {
                    System.out.println(line +
                            "\t " + e.getMessage() + "\n" +
                            line);
                    break;
                } catch (IOException e) {
                    System.out.println(line +
                            "\t BURUH!! Had some trouble saving this task.\n" +
                            line);
                    break;
                }
                list[taskCount] = todo;
                taskCount++;
                todo.addSuccess(taskCount);
                break;
            case "deadline":
                String[] dlSplit = arg.split(" /by ");
                Deadline dl;
                try {
                    dl = new Deadline(dlSplit[0], dlSplit[1]);
                    FileWriter fw = new FileWriter(filePath, true);
                    fw.write("D" + System.lineSeparator() + "0" + System.lineSeparator() + dlSplit[0] +
                            System.lineSeparator() + dlSplit[1] + System.lineSeparator());
                    fw.close();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(line +
                            "\t BURUH!! A deadline must have a description and a /by deadline.\n" +
                            line);
                    break;
                } catch (IOException e) {
                    System.out.println(line +
                            "\t BURUH!! Had some trouble saving this task.\n" +
                            line);
                    break;
                }
                list[taskCount] = dl;
                taskCount++;
                dl.addSuccess(taskCount);
                break;
            case "event":
                Event e;
                try {
                    String[] eSplit1 = arg.split(" /from ");
                    String[] eSplit2 = eSplit1[1].split(" /to ");
                    e = new Event(eSplit1[0], eSplit2[0], eSplit2[1]);
                    FileWriter fw = new FileWriter(filePath, true);
                    fw.write("E" + System.lineSeparator() + "0" + System.lineSeparator() + eSplit1[0] +
                            System.lineSeparator() + eSplit2[0] + System.lineSeparator() + eSplit2[1] +
                            System.lineSeparator());
                    fw.close();
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println(line +
                            "\t BURUH!! A event must have a description, a /from, and a /to.\n" +
                            line);
                    break;
                } catch (IOException ex) {
                    System.out.println(line +
                            "\t BURUH!! Had some trouble saving this task.\n" +
                            line);
                    break;
                }
                list[taskCount] = e;
                taskCount++;
                e.addSuccess(taskCount);
                break;
            case "delete":
                int indexDelete = Integer.parseInt(arg);
                // todo: delete from txt
                Task task = list[indexDelete-1];
                for (int i = indexDelete; i < taskCount; i++) {
                    list[i-1] = list[i];
                }
                list[taskCount-1] = null;
                taskCount--;
                System.out.println(line +
                        "\t You bum. I've removed this task:\n" +
                        "\t   " + task + "\n" +
                        "\t Now you have " + taskCount + " tasks in the list.\n" +
                        line);
                break;
            default:
                System.out.println(line +
                        "\t Huh? I don't gets.\n" +
                        line);
            }
        } while (!exit);

        // Farewell
        String farewell = line +
                "\t Goodnight, rest well and sweet dreams. Hope to see you again soon!\n" +
                line;
        System.out.println(farewell);
    }
}