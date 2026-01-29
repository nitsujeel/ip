import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private static final String FILE_PATH = "data" + File.separator + "sunshine.txt";

    public void load(TaskList taskList) throws FileNotFoundException, EmptyDescriptionException {
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            switch (scanner.nextLine()) {
            case "T":
                String tdDone = scanner.nextLine();
                String tdDesc = scanner.nextLine();
                ToDo td = new ToDo(tdDesc);
                if (tdDone.equals("1")) {
                    td.mark();
                }
                taskList.addTask(td);
                break;
            case "D":
                String dlDone = scanner.nextLine();
                String dlDesc = scanner.nextLine();
                String dlBy = scanner.nextLine();
                Deadline dl = new Deadline(dlDesc, dlBy);
                if (dlDone.equals("1")) {
                    dl.mark();
                }
                taskList.addTask(dl);
                break;
            case "E":
                String evDone = scanner.nextLine();
                String evDesc = scanner.nextLine();
                String evFrom = scanner.nextLine();
                String evTo = scanner.nextLine();
                Event ev = new Event(evDesc, evFrom, evTo);
                if (evDone.equals("1")) {
                    ev.mark();
                }
                taskList.addTask(ev);
                break;
            }
        }
        scanner.close();
    }

    public void createNewFile() throws IOException {
        File file = new File(FILE_PATH);
        file.createNewFile();
    }

    public void addToDo(String description) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        fw.write("T" + System.lineSeparator() + "0" + System.lineSeparator() + description +
                System.lineSeparator());
        fw.close();
    }

    public void addDeadline(String description, String deadline) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        fw.write("D" + System.lineSeparator() + "0" + System.lineSeparator() + description +
                System.lineSeparator() + deadline + System.lineSeparator());
        fw.close();
    }

    public void addEvent(String description, String from, String to) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        fw.write("E" + System.lineSeparator() + "0" + System.lineSeparator() + description +
                System.lineSeparator() + from + System.lineSeparator() + to +
                System.lineSeparator());
        fw.close();
    }

    public void deleteEvent(int index, int taskCount) throws IOException {
        File inputFile = new File(FILE_PATH);
        File tempFile = new File("data" + File.separator + "temp.txt");
        tempFile.createNewFile();

        Scanner scanner = new Scanner(inputFile);
        FileWriter writer = new FileWriter(tempFile);

        copyLines(scanner, writer, index - 1);
        switch (scanner.nextLine()) {
        case "T":
            for (int j = 0; j < 2; j++) {
                scanner.nextLine();
            }
            break;
        case "D":
            for (int j = 0; j < 3; j++) {
                scanner.nextLine();
            }
            break;
        case "E":
            for (int j = 0; j < 4; j++) {
                scanner.nextLine();
            }
            break;
        }
        copyLines(scanner, writer, taskCount - index);

        writer.close();
        scanner.close();
        tempFile.renameTo(inputFile);
    }

    public void markEvent(int index, int taskCount) throws IOException {
        File inputFile = new File(FILE_PATH);
        File tempFile = new File("data" + File.separator + "temp.txt");
        tempFile.createNewFile();

        Scanner scanner = new Scanner(inputFile);
        FileWriter writer = new FileWriter(tempFile);

        copyLines(scanner, writer, index - 1);
        switch (scanner.nextLine()) {
        case "T":
            writer.write("T\n1\n");
            scanner.nextLine();
            writer.write(scanner.nextLine() + "\n");
            break;
        case "D":
            writer.write("D\n1\n");
            scanner.nextLine();
            for (int j = 0; j < 2; j++) {
                writer.write(scanner.nextLine() + "\n");
            }
            break;
        case "E":
            writer.write("E\n1\n");
            scanner.nextLine();
            for (int j = 0; j < 3; j++) {
                writer.write(scanner.nextLine() + "\n");
            }
            break;
        }
        copyLines(scanner, writer, taskCount - index);

        writer.close();
        scanner.close();
        tempFile.renameTo(inputFile);
    }

    public void unmarkEvent(int index, int taskCount) throws IOException {
        File inputFile = new File(FILE_PATH);
        File tempFile = new File("data" + File.separator + "temp.txt");
        tempFile.createNewFile();

        Scanner scanner = new Scanner(inputFile);
        FileWriter writer = new FileWriter(tempFile);

        copyLines(scanner, writer, index - 1);
        switch (scanner.nextLine()) {
        case "T":
            writer.write("T\n0\n");
            scanner.nextLine();
            writer.write(scanner.nextLine() + "\n");
            break;
        case "D":
            writer.write("D\n0\n");
            scanner.nextLine();
            for (int j = 0; j < 2; j++) {
                writer.write(scanner.nextLine() + "\n");
            }
            break;
        case "E":
            writer.write("E\n0\n");
            scanner.nextLine();
            for (int j = 0; j < 3; j++) {
                writer.write(scanner.nextLine() + "\n");
            }
            break;
        }
        copyLines(scanner, writer, taskCount - index);

        writer.close();
        scanner.close();
        tempFile.renameTo(inputFile);
    }

    private void copyLines(Scanner scanner, FileWriter writer, int numberOfLines) throws IOException {
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
            }
        }
    }

}
