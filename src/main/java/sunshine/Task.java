package sunshine;

/**
 * Representation of a task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Intitialises a Task object.
     *
     * @param description Description of the task.
     */
    public Task(String description) throws ImproperFormatException {
        this.description = description;
        if (description.isBlank()) {
            throw new ImproperFormatException();
        }
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    private String getStatusIcon() {
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
