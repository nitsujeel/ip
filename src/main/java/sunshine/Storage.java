package sunshine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class that deals with reading and writing to the local file.
 */
public class Storage {

    private static final String FILE_PATH = "data" + File.separator + "sunshine.txt";

    /**
     * Loads saved Tasks from a local file into memory.
     *
     * @param taskList Destination to load Tasks into.
     * @throws FileNotFoundException If local file does not exist.
     * @throws EmptyDescriptionException If ToDo tasks are missing a description.
     */
    public void load(TaskList taskList) throws IOException, EmptyDescriptionException {
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            switch (scanner.nextLine()) {
            case "T":
                handleToDoLoad(scanner, taskList);
                break;
            case "D":
                handleDeadlineLoad(scanner, taskList);
                break;
            case "E":
                handleEventLoad(scanner, taskList);
                break;
            default:
                throw new IOException();
            }
        }
        scanner.close();
    }

    private void handleToDoLoad(Scanner scanner, TaskList taskList) throws EmptyDescriptionException {
        String tdDone = scanner.nextLine();
        String tdDesc = scanner.nextLine();
        ToDo td = new ToDo(tdDesc);
        if (tdDone.equals("1")) {
            td.mark();
        }
        taskList.addTask(td);
    }

    private void handleDeadlineLoad(Scanner scanner, TaskList taskList) {
        String dlDone = scanner.nextLine();
        String dlDesc = scanner.nextLine();
        String dlBy = scanner.nextLine();
        Deadline dl = new Deadline(dlDesc, dlBy);
        if (dlDone.equals("1")) {
            dl.mark();
        }
        taskList.addTask(dl);
    }

    private void handleEventLoad(Scanner scanner, TaskList taskList) {
        String evDone = scanner.nextLine();
        String evDesc = scanner.nextLine();
        String evFrom = scanner.nextLine();
        String evTo = scanner.nextLine();
        Event ev = new Event(evDesc, evFrom, evTo);
        if (evDone.equals("1")) {
            ev.mark();
        }
        taskList.addTask(ev);
    }


    /**
     * Creates a new file at FILE_PATH.
     *
     * @throws IOException If file creation is interrupted.
     */
    public void createNewFile() throws IOException {
        File file = new File(FILE_PATH);
        file.createNewFile();
    }

    /**
     * Adds the representation of a ToDo to local file.
     *
     * @param description Description of the task.
     * @throws IOException If file modification is interrupted.
     */
    public void addToDo(String description) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        fw.write("T" + System.lineSeparator() + "0" + System.lineSeparator() + description
                + System.lineSeparator());
        fw.close();
    }

    /**
     * Adds the representation of a Deadline to local file.
     *
     * @param description Description of the task.
     * @param deadline Deadline of the task.
     * @throws IOException If file modification is interrupted.
     */
    public void addDeadline(String description, String deadline) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        fw.write("D" + System.lineSeparator() + "0" + System.lineSeparator() + description
                + System.lineSeparator() + deadline + System.lineSeparator());
        fw.close();
    }

    /**
     * Adds the representation of an Event to local file.
     *
     * @param description Description of the task.
     * @param from Start time of the task.
     * @param to End time of the task.
     * @throws IOException If file modification is interrupted.
     */
    public void addEvent(String description, String from, String to) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        fw.write("E" + System.lineSeparator() + "0" + System.lineSeparator() + description
                + System.lineSeparator() + from + System.lineSeparator() + to
                + System.lineSeparator());
        fw.close();
    }

    /**
     * Removes the representation of a Task from local file.
     *
     * @param index Index of the task, starting from 1.
     * @param taskCount Current number of tasks before deletion.
     * @throws IOException If file modification is interrupted.
     */
    public void deleteEvent(int index, int taskCount) throws IOException {
        File inputFile = new File(FILE_PATH);
        File tempFile = new File("data" + File.separator + "temp.txt");
        tempFile.createNewFile();

        Scanner scanner = new Scanner(inputFile);
        FileWriter writer = new FileWriter(tempFile);

        copyTasks(scanner, writer, index - 1);
        switch (scanner.nextLine()) {
        case "T":
            skipLines(scanner, 2);
            break;
        case "D":
            skipLines(scanner, 3);
            break;
        case "E":
            skipLines(scanner, 4);
            break;
        default:
            break;
        }
        copyTasks(scanner, writer, taskCount - index);

        writer.close();
        scanner.close();
        tempFile.renameTo(inputFile);
    }

    private void skipLines(Scanner scanner, int numberOfLines) {
        for (int i = 0; i < numberOfLines; i++) {
            scanner.nextLine();
        }
    }

    /**
     * Marks a Task as done in local file.
     *
     * @param index Index of the task, starting from 1.
     * @param taskCount Current number of tasks.
     * @throws IOException If file modification is interrupted.
     */
    public void markEvent(int index, int taskCount) throws IOException {
        File inputFile = new File(FILE_PATH);
        File tempFile = new File("data" + File.separator + "temp.txt");
        tempFile.createNewFile();

        Scanner scanner = new Scanner(inputFile);
        FileWriter writer = new FileWriter(tempFile);

        copyTasks(scanner, writer, index - 1);
        switch (scanner.nextLine()) {
        case "T":
            handleToDoMark(writer, scanner);
            break;
        case "D":
            handleDeadlineMark(writer, scanner);
            break;
        case "E":
            handleEventMark(writer, scanner);
            break;
        default:
            break;
        }
        copyTasks(scanner, writer, taskCount - index);

        writer.close();
        scanner.close();
        tempFile.renameTo(inputFile);
    }

    private void handleToDoMark(FileWriter writer, Scanner scanner) throws IOException {
        writer.write("T\n1\n");
        scanner.nextLine();
        writer.write(scanner.nextLine() + "\n");
    }

    private void handleDeadlineMark(FileWriter writer, Scanner scanner) throws IOException {
        writer.write("D\n1\n");
        scanner.nextLine();
        for (int j = 0; j < 2; j++) {
            writer.write(scanner.nextLine() + "\n");
        }
    }

    private void handleEventMark(FileWriter writer, Scanner scanner) throws IOException {
        writer.write("E\n1\n");
        scanner.nextLine();
        for (int j = 0; j < 3; j++) {
            writer.write(scanner.nextLine() + "\n");
        }
    }

    /**
     * Unmarks a Task as done in local file.
     *
     * @param index Index of the task, starting from 1.
     * @param taskCount Current number of tasks.
     * @throws IOException If file modification is interrupted.
     */
    public void unmarkEvent(int index, int taskCount) throws IOException {
        File inputFile = new File(FILE_PATH);
        File tempFile = new File("data" + File.separator + "temp.txt");
        tempFile.createNewFile();

        Scanner scanner = new Scanner(inputFile);
        FileWriter writer = new FileWriter(tempFile);

        copyTasks(scanner, writer, index - 1);
        switch (scanner.nextLine()) {
        case "T":
            handleToDoUnmark(writer, scanner);
            break;
        case "D":
            handleDeadlineUnmark(writer, scanner);
            break;
        case "E":
            handleEventUnmark(writer, scanner);
            break;
        default:
            break;
        }
        copyTasks(scanner, writer, taskCount - index);

        writer.close();
        scanner.close();
        tempFile.renameTo(inputFile);
    }

    private void handleToDoUnmark(FileWriter writer, Scanner scanner) throws IOException {
        writer.write("T\n0\n");
        scanner.nextLine();
        writer.write(scanner.nextLine() + "\n");
    }

    private void handleDeadlineUnmark(FileWriter writer, Scanner scanner) throws IOException {
        writer.write("D\n0\n");
        scanner.nextLine();
        for (int j = 0; j < 2; j++) {
            writer.write(scanner.nextLine() + "\n");
        }
    }

    private void handleEventUnmark(FileWriter writer, Scanner scanner) throws IOException {
        writer.write("E\n0\n");
        scanner.nextLine();
        for (int j = 0; j < 3; j++) {
            writer.write(scanner.nextLine() + "\n");
        }
    }

    private void copyTasks(Scanner scanner, FileWriter writer, int numberOfLines) throws IOException {
        for (int i = 0; i < numberOfLines; i++) {
            switch (scanner.nextLine()) {
            case "T":
                writer.write("T\n");
                for (int j = 0; j < 2; j++) {
                    writer.write(scanner.nextLine() + "\n");
                }
                break;
            case "D":
                writer.write("D\n");
                for (int j = 0; j < 3; j++) {
                    writer.write(scanner.nextLine() + "\n");
                }
                break;
            case "E":
                writer.write("E\n");
                for (int j = 0; j < 4; j++) {
                    writer.write(scanner.nextLine() + "\n");
                }
                break;
            default:
                break;
            }
        }
    }
}
