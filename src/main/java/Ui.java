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

    public void showException(String description, Exception e) {
        this.print(ERROR_PREFIX + "Something went wrong " + description + ":",
                e.getMessage());
    }

    public void showLoadSuccess(int taskCount) {
        this.print("Local file loaded!",
                "You've been procrastinating " + taskCount + " task(s).");
    }

    public void showLoadFail() {
        this.print("Seems like you don't have any saved tasks.");
    }

    public void showWelcome() {
        this.print("Hello! I'm Sunshine :)",
                "What can I do for you?");
    }

    public void showList(TaskList tasklist) {
        this.print("Here are the tasks in your list:",
                tasklist.toString());
    }

    public void showMarkSuccess(Task task) {
        this.print("Good job bubs! I've marked this task as done:",
                task.toString());
    }

    public void showMissingIndex() {
        this.print(ERROR_PREFIX + "Which one la?");
    }

    public void showIndexOutofBounds() {
        this.print(ERROR_PREFIX + "You don't even have that many tasks, stop gaslighting me.");
    }

    public void showFileNotFound() {
        this.print(ERROR_PREFIX + "I can't find the save file!!");
    }

    public void showUnmarkSuccess(Task task) {
        this.print("So you lied to me la. I've marked this task as not done yet:",
                task.toString());
    }

    public void showAddTaskSuccess(Task task, int taskCount) {
        this.print("Got it. I've added this task:",
                task.toString(),
                "Now you have " + taskCount + " task(s) in the list.");
    }

    public void showToDoFormat() {
        this.print(ERROR_PREFIX + "A ToDo must have a description.");
    }

    public void showDeadlineFormat() {
        this.print(ERROR_PREFIX + "A Deadline must have a description and a /by.");
    }

    public void showEventFormat() {
        this.print(ERROR_PREFIX + "An Event must have a description, a /from, and a /to.");
    }

    public void showDeleteSuccess(Task task, int taskCount) {
        this.print("You bum. I've removed this task:",
                task.toString(),
                "Now you have " + taskCount + " task(s) in the list.");
    }

    public void showDefault() {
        this.print("Huh? I don't gets.");
    }

    public void showFarewell() {
        this.print("Goodnight, rest well and sweet dreams.",
                "Hope to see you again soon!");
    }
}
