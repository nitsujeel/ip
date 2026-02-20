package sunshine;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A task with a due date.
 */
public class Deadline extends Task {

    protected LocalDate byDate;

    /**
     * Initialises a Deadline object.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) throws ImproperFormatException, DateTimeParseException {
        super(description);
        try {
            String[] dates = by.split("/");
            this.byDate = LocalDate.parse(dates[2] + "-" + dates[1] + "-" + dates[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ImproperFormatException();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.byDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
