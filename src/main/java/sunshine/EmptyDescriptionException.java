package sunshine;

/**
 * Thrown when a task is attempted to be constructed with no description.
 */
public class EmptyDescriptionException extends Exception {

    public EmptyDescriptionException(String s) {
        super(s);
    }
}
