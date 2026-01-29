package sunshine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Sunshine {
    public static void main(String[] args) {

        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Storage storage = new Storage();

        // Load locally saved data
        try {
            storage.load(taskList);
            ui.showLoadSuccess(taskList.getTaskCount());
        } catch (FileNotFoundException e) {
            ui.showLoadFail();
            try {
                storage.createNewFile();
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
            String[] parsedInput = Parser.parseInput(scanner.nextLine());
            String cmd = parsedInput[0];
            String arg = parsedInput[1];
            switch (cmd) {
            case "bye":
                exit = true;
                break;
            case "list":
                ui.showList(taskList);
                break;
            case "mark":
                try {
                    int indexMark = Parser.parseInt(arg);
                    storage.markEvent(indexMark, taskList.getTaskCount());
                    Task markedTask = taskList.markTask(indexMark);
                    ui.showMarkSuccess(markedTask);
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
                    int indexUnmark = Parser.parseInt(arg);
                    storage.unmarkEvent(indexUnmark, taskList.getTaskCount());
                    Task unmarkedTask = taskList.unmarkTask(indexUnmark);
                    ui.showUnmarkSuccess(unmarkedTask);
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
                    storage.addToDo(arg);
                } catch (EmptyDescriptionException e) {
                    ui.showToDoFormat();
                    break;
                } catch (IOException e) {
                    ui.showException("saving this task", e);
                    break;
                }
                taskList.addTask(todo);
                ui.showAddTaskSuccess(todo, taskList.getTaskCount());
                break;
            case "deadline":
                String[] dlSplits = Parser.parseDeadline(arg);
                Deadline dl;
                try {
                    dl = new Deadline(dlSplits[0], dlSplits[1]);
                    storage.addDeadline(dlSplits[0], dlSplits[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.showDeadlineFormat();
                    break;
                } catch (IOException e) {
                    ui.showException("saving this task", e);
                    break;
                }
                taskList.addTask(dl);
                ui.showAddTaskSuccess(dl, taskList.getTaskCount());
                break;
            case "event":
                Event ev;
                try {
                    String[] eSplits = Parser.parseEvent(arg);
                    ev = new Event(eSplits[0], eSplits[1], eSplits[2]);
                    storage.addEvent(eSplits[0], eSplits[1], eSplits[2]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.showEventFormat();
                    break;
                } catch (IOException e) {
                    ui.showException("saving this task", e);
                    break;
                }
                taskList.addTask(ev);
                ui.showAddTaskSuccess(ev, taskList.getTaskCount());
                break;
            case "delete":
                int indexDelete = Parser.parseInt(arg);
                try {
                    storage.deleteEvent(indexDelete, taskList.getTaskCount());
                } catch (FileNotFoundException e) {
                    ui.showFileNotFound();
                } catch (IOException e) {
                    ui.showException("deleting this task", e);
                }
                Task task = taskList.deleteTask(indexDelete);
                ui.showDeleteSuccess(task, taskList.getTaskCount());
                break;
            default:
                ui.showDefault();
            }
        } while (!exit);

        // Farewell
        ui.showFarewell();
    }
}