package sunshine;

public class ToDo extends Task {

    /**
     * Initialises a ToDo object.
     *
     * @param description Description of the task.
     * @throws EmptyDescriptionException If description is missing.
     */
    public ToDo(String description) throws EmptyDescriptionException {
        super(description);
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("BURUH!! A todo must have a description.");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
