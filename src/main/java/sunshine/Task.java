package sunshine;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Intitialises a Task object.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Marks the Task as completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the Task, resetting it to uncompleted.
     */
    public void unmark() {
        this.isDone = false;
    }
}
