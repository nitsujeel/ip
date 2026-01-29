import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Sunshine {
    public static void main(String[] args) {

        String line = "\t____________________________________________________________\n";

        Ui ui = new Ui();

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
            ui.showLoadSuccess(taskCount);
        } catch (FileNotFoundException e) {
            ui.showLoadFail();
            File f = new File(filePath);
            try {
                f.createNewFile();
            } catch (IOException ex) {
                ui.showException("creating a new save file", ex);
            }
        } catch (EmptyDescriptionException e) {
            ui.showException("loading your saved tasks", e);
        }

        // Welcome
        ui.showWelcome();

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

                    File inputFile = new File(filePath);
                    File tempFile = new File("data" + File.separator + "temp.txt");
                    tempFile.createNewFile();

                    Scanner deleteScanner = new Scanner(inputFile);
                    FileWriter deleteWriter = new FileWriter(tempFile);

                    for (int i = 1; i <= taskCount; i++) {
                        if (i == indexMark) {
                            switch (deleteScanner.nextLine()) {
                            case "T":
                                deleteWriter.write("T\n1\n");
                                deleteScanner.nextLine();
                                deleteWriter.write(deleteScanner.nextLine() + "\n");
                                break;
                            case "D":
                                deleteWriter.write("D\n1\n");
                                deleteScanner.nextLine();
                                for (int j = 0; j < 2; j++) {
                                    deleteWriter.write(deleteScanner.nextLine() + "\n");
                                }
                                break;
                            case "E":
                                deleteWriter.write("E\n1\n");
                                deleteScanner.nextLine();
                                for (int j = 0; j < 3; j++) {
                                    deleteWriter.write(deleteScanner.nextLine() + "\n");
                                }
                                break;
                            }
                        } else {
                            switch (deleteScanner.nextLine()) {
                            case "T":
                                deleteWriter.write("T\n");
                                for (int j = 0; j < 2; j++) {
                                    deleteWriter.write(deleteScanner.nextLine() + "\n");
                                }
                                break;
                            case "D":
                                deleteWriter.write("D\n");
                                for (int j = 0; j < 3; j++) {
                                    deleteWriter.write(deleteScanner.nextLine() + "\n");
                                }
                                break;
                            case "E":
                                deleteWriter.write("E\n");
                                for (int j = 0; j < 4; j++) {
                                    deleteWriter.write(deleteScanner.nextLine() + "\n");
                                }
                                break;
                            }
                        }
                    }

                    deleteWriter.close();
                    deleteScanner.close();
                    tempFile.renameTo(inputFile);

                    Task task = list[indexMark-1];
                    task.mark();
                    ui.showMarkSuccess(task);
                } catch (NumberFormatException e) {
                    ui.showMissingIndex();
                } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                    ui.showIndexOutofBounds();
                } catch (FileNotFoundException e) {
                    ui.showFileNotFound();
                } catch (IOException e) {
                    ui.showException("marking this task", e);
                }
                break;
            case "unmark":
                try {
                    int indexUnmark = Integer.parseInt(arg);

                    File inputFile = new File(filePath);
                    File tempFile = new File("data" + File.separator + "temp.txt");
                    tempFile.createNewFile();

                    Scanner deleteScanner = new Scanner(inputFile);
                    FileWriter deleteWriter = new FileWriter(tempFile);

                    for (int i = 1; i <= taskCount; i++) {
                        if (i == indexUnmark) {
                            switch (deleteScanner.nextLine()) {
                            case "T":
                                deleteWriter.write("T\n0\n");
                                deleteScanner.nextLine();
                                deleteWriter.write(deleteScanner.nextLine() + "\n");
                                break;
                            case "D":
                                deleteWriter.write("D\n0\n");
                                deleteScanner.nextLine();
                                for (int j = 0; j < 2; j++) {
                                    deleteWriter.write(deleteScanner.nextLine() + "\n");
                                }
                                break;
                            case "E":
                                deleteWriter.write("E\n0\n");
                                deleteScanner.nextLine();
                                for (int j = 0; j < 3; j++) {
                                    deleteWriter.write(deleteScanner.nextLine() + "\n");
                                }
                                break;
                            }
                        } else {
                            switch (deleteScanner.nextLine()) {
                            case "T":
                                deleteWriter.write("T\n");
                                for (int j = 0; j < 2; j++) {
                                    deleteWriter.write(deleteScanner.nextLine() + "\n");
                                }
                                break;
                            case "D":
                                deleteWriter.write("D\n");
                                for (int j = 0; j < 3; j++) {
                                    deleteWriter.write(deleteScanner.nextLine() + "\n");
                                }
                                break;
                            case "E":
                                deleteWriter.write("E\n");
                                for (int j = 0; j < 4; j++) {
                                    deleteWriter.write(deleteScanner.nextLine() + "\n");
                                }
                                break;
                            }
                        }
                    }

                    deleteWriter.close();
                    deleteScanner.close();
                    tempFile.renameTo(inputFile);

                    Task task = list[indexUnmark-1];
                    task.unmark();
                    ui.showUnmarkSuccess(task);
                } catch (NumberFormatException e) {
                    ui.showMissingIndex();
                } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                    ui.showIndexOutofBounds();
                } catch (FileNotFoundException e) {
                    ui.showFileNotFound();
                } catch (IOException e) {
                    ui.showException("unmarking this task", e);
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
                    ui.showToDoFormat();
                    break;
                } catch (IOException e) {
                    ui.showException("saving this task", e);
                    break;
                }
                list[taskCount] = todo;
                taskCount++;
                ui.showAddTaskSuccess(todo, taskCount);
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
                    ui.showDeadlineFormat();
                    break;
                } catch (IOException e) {
                    ui.showException("saving this task", e);
                    break;
                }
                list[taskCount] = dl;
                taskCount++;
                ui.showAddTaskSuccess(dl, taskCount);
                break;
            case "event":
                Event ev;
                try {
                    String[] eSplit1 = arg.split(" /from ");
                    String[] eSplit2 = eSplit1[1].split(" /to ");
                    ev = new Event(eSplit1[0], eSplit2[0], eSplit2[1]);
                    FileWriter fw = new FileWriter(filePath, true);
                    fw.write("E" + System.lineSeparator() + "0" + System.lineSeparator() + eSplit1[0] +
                            System.lineSeparator() + eSplit2[0] + System.lineSeparator() + eSplit2[1] +
                            System.lineSeparator());
                    fw.close();
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.showEventFormat();
                    break;
                } catch (IOException e) {
                    ui.showException("saving this task", e);
                    break;
                }
                list[taskCount] = ev;
                taskCount++;
                ui.showAddTaskSuccess(ev, taskCount);
                break;
            case "delete":
                int indexDelete = Integer.parseInt(arg);
                try {
                    File inputFile = new File(filePath);
                    File tempFile = new File("data" + File.separator + "temp.txt");
                    tempFile.createNewFile();

                    Scanner deleteScanner = new Scanner(inputFile);
                    FileWriter deleteWriter = new FileWriter(tempFile);

                    for (int i = 1; i <= taskCount; i++) {
                        if (i == indexDelete) {
                            switch (deleteScanner.nextLine()) {
                            case "T":
                                for (int j = 0; j < 2; j++) {
                                    deleteScanner.nextLine();
                                }
                                break;
                            case "D":
                                for (int j = 0; j < 3; j++) {
                                    deleteScanner.nextLine();
                                }
                                break;
                            case "E":
                                for (int j = 0; j < 4; j++) {
                                    deleteScanner.nextLine();
                                }
                                break;
                            }
                        } else {
                            switch (deleteScanner.nextLine()) {
                            case "T":
                                deleteWriter.write("T\n");
                                for (int j = 0; j < 2; j++) {
                                    deleteWriter.write(deleteScanner.nextLine() + "\n");
                                }
                                break;
                            case "D":
                                deleteWriter.write("D\n");
                                for (int j = 0; j < 3; j++) {
                                    deleteWriter.write(deleteScanner.nextLine() + "\n");
                                }
                                break;
                            case "E":
                                deleteWriter.write("E\n");
                                for (int j = 0; j < 4; j++) {
                                    deleteWriter.write(deleteScanner.nextLine() + "\n");
                                }
                                break;
                            }
                        }
                    }

                    deleteWriter.close();
                    deleteScanner.close();
                    tempFile.renameTo(inputFile);

                } catch (FileNotFoundException e) {
                    ui.showFileNotFound();
                } catch (IOException e) {
                    ui.showException("deleting this task", e);
                }
                Task task = list[indexDelete-1];
                for (int i = indexDelete; i < taskCount; i++) {
                    list[i-1] = list[i];
                }
                list[taskCount-1] = null;
                taskCount--;
                ui.showDeleteSuccess(task, taskCount);
                break;
            default:
                ui.showDefault();
            }
        } while (!exit);

        // Farewell
        ui.showFarewell();
    }
}