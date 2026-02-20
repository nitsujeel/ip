package sunshine;

/**
 * Class that deals with outputs shown to the user.
 */
public class Ui {
    private static final String ERROR_PREFIX = "BURUH!! ";

    /**
     * Prints an Exception message.
     *
     * @param description Explanation of what was trying to be executed.
     * @param e Exception thrown.
     */
    public String showException(String description, Exception e) {
        return ERROR_PREFIX + "Something went wrong " + description + ": " + e.getMessage();
    }

    /**
     * Prints a message for the successful loading of Tasks from local storage to memory.
     *
     * @param taskCount Number of tasks loaded.
     */
    public String showLoadSuccess(int taskCount) {
        return "Local file loaded!\nYou've been procrastinating " + taskCount + " task(s).";
    }

    /**
     * Prints a message for when no local save file is found, and a new one is created.
     */
    public String showLoadNew() {
        return "Seems like you don't have any saved tasks.\nCreated a new local save file for ya.";
    }

    /**
     * Prints welcome message.
     */
    public String showWelcome() {
        return "Hello! I'm Sunshine :)\nWhat can I do for you?\n(Say \"help\" if you need some ideas!)";
    }

    /**
     * Prints a list of Tasks.
     *
     * @param taskList TaskList to be printed.
     */
    public String showList(TaskList taskList) {
        return taskList.getTaskCount() == 0 ? "There are no tasks in your list."
                : "Here are the tasks in your list:\n" + taskList.toString();
    }

    /**
     * Prints a message for the successful marking of a Task.
     *
     * @param task Task marked.
     */
    public String showMarkSuccess(Task task) {
        return "Good job bubs! I've marked this task as done:\n" + task.toString();
    }

    /**
     * Prints a message when the task index is beyond the current task count.
     */
    public String showIndexOutofBounds() {
        return ERROR_PREFIX + "I need a valid task number. Stop gaslighting me!";
    }

    /**
     * Prints a message when the local file cannot be found.
     */
    public String showFileNotFound() {
        return ERROR_PREFIX + "I can't find the save file!!";
    }

    /**
     * Prints a message for the successful unmarking of a Task.
     *
     * @param task Task unmarked.
     */
    public String showUnmarkSuccess(Task task) {
        return "So you lied to me la. I've marked this task as not done yet:\n" + task.toString();
    }

    /**
     * Prints a message for the successful addition of a Task.
     *
     * @param task Task added.
     * @param taskCount Number of tasks after addition.
     */
    public String showAddTaskSuccess(Task task, int taskCount) {
        return "Got it. I've added this task:\n" + task.toString()
                + "\nNow you have " + taskCount + " task(s) in the list.";
    }

    /**
     * Prints a message for failed addition of ToDo to the list.
     */
    public String showToDoFormat() {
        return ERROR_PREFIX + "A ToDo must have a description.";
    }

    /**
     * Prints a message for failed addition of Deadline to the list.
     */
    public String showDeadlineFormat() {
        return ERROR_PREFIX + "A Deadline must have a description and a /by (DD/MM/YYYY).";
    }

    /**
     * Prints a message for failed addition of Event to the list.
     */
    public String showEventFormat() {
        return ERROR_PREFIX + "An Event must have a description, a /from (DD/MM/YYYY), and a /to (DD/MM/YYYY).";
    }

    /**
     * Prints a message for the successful deletion of a Task.
     *
     * @param task Task deleted.
     * @param taskCount Number of tasks after deletion.
     */
    public String showDeleteSuccess(Task task, int taskCount) {
        return "You bum. I've removed this task:\n" + task.toString()
                + "\nNow you have " + taskCount + " task(s) in the list.";
    }

    /**
     * Prints the results of the find feature.
     *
     * @param taskList List of Tasks found from the search.
     */
    public String showResults(TaskList taskList) {
        return taskList.getTaskCount() == 0 ? "I couldn't find anything like that..."
                : "Here's what I found, bubs:\n" + taskList.toString();
    }

    /**
     * Prints the default message when the command is not recognised.
     */
    public String showDefault() {
        return "Huh? I don't gets.";
    }

    /**
     * Prints a farewell message.
     */
    public String showFarewell() {
        return "Goodnight, rest well and sweet dreams.\nHope to see you again soon!";
    }

    /**
     * Prints a help message with commands and their functionalities.
     */
    public String showHelp() {
        return "You everything also don't know one.\n\n"
                + "Here's a list of commands you can use:\n\n"
                + "help\nShow this list of commands\n\n"
                + "list\nShow your current list of tasks\n\n"
                + "todo <description>\nAdd a ToDo Task to your list\n\n"
                + "deadline <description> /by <due (DD/MM/YYYY)>\nAdd a Deadline Task to your list\n\n"
                + "event <description> /from <start (DD/MM/YYYY)> /to <end (DD/MM/YYYY)>\n"
                + "Add an Event Task to your list\n\n"
                + "mark <index (1-based)>\nMark the indexed task as done\n\n"
                + "unmark <index (1-based)>\nUnmark the indexed task as yet to be done\n\n"
                + "delete <index (1-based)>\nDelete the indexed task from your list\n\n"
                + "find <keywords>\nSearch your list for tasks that match\n\n"
                + "bye\nSay goodbye to me!";
    }

    /**
     * Prints a message reminding user of the proper date format.
     */
    public String showDateTimeFormat() {
        return ERROR_PREFIX + "Dates must be of the form DD/MM/YYYY, and must be valid dates.";
    }

    /**
     * Prints a message reminding user that the end date of an event must be before its start date.
     */
    public String showEndBeforeStartException() {
        return ERROR_PREFIX + "An event's /to date cannot be before it /from date.";
    }
}
