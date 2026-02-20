package sunshine;

/**
 * A task with only a description.
 */
public class ToDo extends Task {

    /**
     * Initialises a ToDo object.
     *
     * @param description Description of the task.
     * @throws ImproperFormatException If description is missing.
     */
    public ToDo(String description) throws ImproperFormatException {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
