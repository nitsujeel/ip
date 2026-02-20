package sunshine;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A task with a start and end date.
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Initialises an Event object.
     *
     * @param description Description of the task.
     * @param from Start time of the event.
     * @param to End time of the event.
     */
    public Event(String description, String from, String to)
            throws ImproperFormatException, DateTimeParseException, EndBeforeStartException {
        super(description);
        try {
            String[] fromDates = from.split("/");
            this.from = LocalDate.parse(fromDates[2] + "-" + fromDates[1] + "-" + fromDates[0]);
            String[] toDates = to.split("/");
            this.to = LocalDate.parse(toDates[2] + "-" + toDates[1] + "-" + toDates[0]);
            if (this.to.isBefore(this.from)) {
                throw new EndBeforeStartException();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ImproperFormatException();
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
