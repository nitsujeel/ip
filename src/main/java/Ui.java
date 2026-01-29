public class Ui {
    private static final String LINE = "\t____________________________________________________________\n";

    private void print(String... lines) {
        System.out.print(LINE);
        for (String line : lines) {
            System.out.println("\t " + line);
        }
        System.out.println(LINE);
    }

    public void showWelcome() {
        this.print("Hello! I'm Sunshine :)",
                "What can I do for you?");
    }

//    public void showList(TaskList tasklist) {
//
//    }

    public void showMarkSuccess(Task task) {
        this.print("Good job bubs! I've marked this task as done:",
                task.toString());
    }

    public void showUnmarkSuccess(Task task) {
        this.print("So you lied to me la. I've marked this task as not done yet:",
                task.toString());
    }

    public void showAddTaskSuccess(Task task, int taskCount) {
        this.print("Got it. I've added this task:",
                task.toString(),
                "Now you have " + taskCount + " tasks in the list.");
    }

    public void showDeleteSuccess(Task task, int taskCount) {
        this.print("You bum. I've removed this task:",
                task.toString(),
                "Now you have " + taskCount + " tasks in the list.");
    }

    public void showFarewell() {
        this.print("Goodnight, rest well and sweet dreams.",
                "Hope to see you again soon!");
    }
}
