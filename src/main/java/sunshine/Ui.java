package sunshine;

public class Ui {
    private static final String LINE = "\t____________________________________________________________\n";
    private static final String ERROR_PREFIX = "BURUH!! ";

    private void print(String... lines) {
        System.out.print(LINE);
        for (String line : lines) {
            System.out.println("\t " + line);
        }
        System.out.println(LINE);
    }

    /**
     * Prints an Exception message.
     *
     * @param description Explanation of what was trying to be executed.
     * @param e Exception thrown.
     */
    public void showException(String description, Exception e) {
        this.print(ERROR_PREFIX + "Something went wrong " + description + ":",
                e.getMessage());
    }

    /**
     * Prints a message for the successful loading of Tasks from local storage to memory.
     *
     * @param taskCount Number of tasks loaded.
     */
    public void showLoadSuccess(int taskCount) {
        this.print("Local file loaded!",
                "You've been procrastinating " + taskCount + " task(s).");
    }

    /**
     * Prints a message for when there are no saved tasks.
     */
    public void showLoadFail() {
        this.print("Seems like you don't have any saved tasks.");
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        this.print("Hello! I'm Sunshine :)",
                "What can I do for you?");
    }

    /**
     * Prints a list of Tasks.
     *
     * @param taskList TaskList to be printed.
     */
    public void showList(TaskList taskList) {
        this.print("Here are the tasks in your list:",
                taskList.toString());
    }

    /**
     * Prints a message for the successful marking of a Task.
     *
     * @param task Task marked.
     */
    public void showMarkSuccess(Task task) {
        this.print("Good job bubs! I've marked this task as done:",
                task.toString());
    }

    /**
     * Prints a message when the task index is not specified.
     */
    public void showMissingIndex() {
        this.print(ERROR_PREFIX + "Which one la?");
    }

    /**
     * Prints a message when the task index is beyond the current task count.
     */
    public void showIndexOutofBounds() {
        this.print(ERROR_PREFIX + "You don't even have that many tasks, stop gaslighting me.");
    }

    /**
     * Prints a message when the local file cannot be found.
     */
    public void showFileNotFound() {
        this.print(ERROR_PREFIX + "I can't find the save file!!");
    }

    /**
     * Prints a message for the successful unmarking of a Task.
     *
     * @param task Task unmarked.
     */
    public void showUnmarkSuccess(Task task) {
        this.print("So you lied to me la. I've marked this task as not done yet:",
                task.toString());
    }

    /**
     * Prints a message for the successful addition of a Task.
     *
     * @param task Task added.
     * @param taskCount Number of tasks after addition.
     */
    public void showAddTaskSuccess(Task task, int taskCount) {
        this.print("Got it. I've added this task:",
                task.toString(),
                "Now you have " + taskCount + " task(s) in the list.");
    }

    /**
     * Prints a message for failed addition of ToDo to the list.
     */
    public void showToDoFormat() {
        this.print(ERROR_PREFIX + "A ToDo must have a description.");
    }

    /**
     * Prints a message for failed addition of Deadline to the list.
     */
    public void showDeadlineFormat() {
        this.print(ERROR_PREFIX + "A Deadline must have a description and a /by.");
    }

    /**
     * Prints a message for failed addition of Event to the list.
     */
    public void showEventFormat() {
        this.print(ERROR_PREFIX + "An Event must have a description, a /from, and a /to.");
    }

    /**
     * Prints a message for the successful deletion of a Task.
     *
     * @param task Task deleted.
     * @param taskCount Number of tasks after deletion.
     */
    public void showDeleteSuccess(Task task, int taskCount) {
        this.print("You bum. I've removed this task:",
                task.toString(),
                "Now you have " + taskCount + " task(s) in the list.");
    }

    /**
     * Prints the default message when the command is not recognised.
     */
    public void showDefault() {
        this.print("Huh? I don't gets.");
    }

    /**
     * Prints a farewell message.
     */
    public void showFarewell() {
        this.print("Goodnight, rest well and sweet dreams.",
                "Hope to see you again soon!");
    }
}
