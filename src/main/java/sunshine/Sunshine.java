package sunshine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Represents a chatbot task manager.
 */
public class Sunshine {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for a Sunshine object.
     */
    public Sunshine() {
        taskList = new TaskList();
        storage = new Storage();
        ui = new Ui();
    }

    /**
     * Loads tasks from local file into memory.
     *
     * @return String indicating the success or failure of the loading.
     */
    public String loadTasks() {
        try {
            storage.load(taskList);
            return ui.showLoadSuccess(taskList.getTaskCount());
        } catch (FileNotFoundException e) {
            try {
                storage.createNewFile();
                return ui.showLoadNew();
            } catch (IOException ex) {
                return ui.showException("creating a new save file", ex);
            }
        } catch (EmptyDescriptionException | IOException e) {
            return ui.showException("loading your saved tasks", e);
        }
    }

    /**
     * Gets the welcome message to be printed upon starting Sunshine.
     *
     * @return Welcome message.
     */
    public String getWelcomeMessage() {
        return ui.showWelcome();
    }

    /**
     * Generates a response to the user's chat message.
     *
     * @param input User's input.
     * @return Sunshine's response.
     */
    public String getResponse(String input) {
        String[] parsedInput = Parser.parseInput(input);
        String cmd = parsedInput[0];
        String arg = parsedInput[1];
        switch (cmd) {
        case "bye":
            return ui.showFarewell();
        case "list":
            return ui.showList(taskList);
        case "mark":
            return handleMark(arg, storage, taskList, ui);
        case "unmark":
            return handleUnmark(arg, storage, taskList, ui);
        case "todo":
            return handleToDo(arg, storage, taskList, ui);
        case "deadline":
            return handleDeadline(arg, storage, taskList, ui);
        case "event":
            return handleEvent(arg, storage, taskList, ui);
        case "delete":
            return handleDelete(arg, storage, taskList, ui);
        case "find":
            TaskList results = taskList.findTasks(arg);
            return ui.showResults(results);
        default:
            return ui.showDefault();
        }
    }

    private String handleMark(String arg, Storage storage, TaskList taskList, Ui ui) {
        try {
            int indexMark = Parser.parseInt(arg);
            storage.markEvent(indexMark, taskList.getTaskCount());
            Task markedTask = taskList.markTask(indexMark);
            return ui.showMarkSuccess(markedTask);
        } catch (NumberFormatException e) {
            return ui.showMissingIndex();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException | NoSuchElementException e) {
            return ui.showIndexOutofBounds();
        } catch (FileNotFoundException e) {
            return ui.showFileNotFound();
        } catch (IOException e) {
            return ui.showException("marking this task", e);
        }
    }

    private String handleUnmark(String arg, Storage storage, TaskList taskList, Ui ui) {
        try {
            int indexUnmark = Parser.parseInt(arg);
            storage.unmarkEvent(indexUnmark, taskList.getTaskCount());
            Task unmarkedTask = taskList.unmarkTask(indexUnmark);
            return ui.showUnmarkSuccess(unmarkedTask);
        } catch (NumberFormatException e) {
            return ui.showMissingIndex();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException | NoSuchElementException e) {
            return ui.showIndexOutofBounds();
        } catch (FileNotFoundException e) {
            return ui.showFileNotFound();
        } catch (IOException e) {
            return ui.showException("unmarking this task", e);
        }
    }

    private String handleToDo(String arg, Storage storage, TaskList taskList, Ui ui) {
        try {
            ToDo todo = new ToDo(arg);
            storage.addToDo(arg);
            taskList.addTask(todo);
            return ui.showAddTaskSuccess(todo, taskList.getTaskCount());
        } catch (EmptyDescriptionException e) {
            return ui.showToDoFormat();
        } catch (IOException e) {
            return ui.showException("saving this task", e);
        }
    }

    private String handleDeadline(String arg, Storage storage, TaskList taskList, Ui ui) {
        try {
            String[] dlSplits = Parser.parseDeadline(arg);
            Deadline dl = new Deadline(dlSplits[0], dlSplits[1]);
            storage.addDeadline(dlSplits[0], dlSplits[1]);
            taskList.addTask(dl);
            return ui.showAddTaskSuccess(dl, taskList.getTaskCount());
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.showDeadlineFormat();
        } catch (IOException e) {
            return ui.showException("saving this task", e);
        }
    }

    private String handleEvent(String arg, Storage storage, TaskList taskList, Ui ui) {
        try {
            String[] eSplits = Parser.parseEvent(arg);
            Event ev = new Event(eSplits[0], eSplits[1], eSplits[2]);
            storage.addEvent(eSplits[0], eSplits[1], eSplits[2]);
            taskList.addTask(ev);
            return ui.showAddTaskSuccess(ev, taskList.getTaskCount());
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.showEventFormat();
        } catch (IOException e) {
            return ui.showException("saving this task", e);
        }
    }

    private String handleDelete(String arg, Storage storage, TaskList taskList, Ui ui) {
        try {
            int indexDelete = Parser.parseInt(arg);
            storage.deleteEvent(indexDelete, taskList.getTaskCount());
            Task task = taskList.deleteTask(indexDelete);
            return ui.showDeleteSuccess(task, taskList.getTaskCount());
        } catch (NumberFormatException e) {
            return ui.showMissingIndex();
        } catch (FileNotFoundException e) {
            return ui.showFileNotFound();
        } catch (IOException e) {
            return ui.showException("deleting this task", e);
        } catch (IndexOutOfBoundsException | NoSuchElementException e) {
            return ui.showIndexOutofBounds();
        }
    }
}
