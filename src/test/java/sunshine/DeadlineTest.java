package sunshine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {

    @Test
    public void toString_localDate_formattedDate() {
        Deadline deadline = new Deadline("deadline description", "28/04/2026");
        assertEquals("[D][ ] deadline description (by: 28 Apr 2026)", deadline.toString());
    }

    @Test
    public void toString_nonLocalDate_copiedString() {
        Deadline deadline = new Deadline("deadline description", "non LocalDate string");
        assertEquals("[D][ ] deadline description (by: non LocalDate string)", deadline.toString());
    }
}
