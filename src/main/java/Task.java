public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        if (description.isEmpty()) {
            throw new IllegalArgumentException("BURUH!! A task must come with a description.");
        }
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public void addSuccess(int taskCount) {

        String line = "\t____________________________________________________________\n";

        System.out.println(line +
                "\t Got it. I've added this task:\n " +
                "\t   " + this.toString() + "\n" +
                "\t Now you have " + taskCount + " tasks in the list.\n" +
                line);
    }
}
