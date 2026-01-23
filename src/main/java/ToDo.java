public class ToDo extends Task {
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
