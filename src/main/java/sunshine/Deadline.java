package sunshine;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String byString;
    protected LocalDate byDate;

    /**
     * Initialises a Deadline object.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.byString = by;
        try {
            String[] dates = by.split("/");
            this.byDate = LocalDate.parse(dates[2] + "-" + dates[1] + "-" + dates[0]);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            this.byDate = null;
        }
    }

    @Override
    public String toString() {
        if (this.byDate == null) {
            return "[D]" + super.toString() + " (by: " + byString + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + this.byDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
        }
    }
}
