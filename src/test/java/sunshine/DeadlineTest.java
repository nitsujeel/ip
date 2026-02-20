package sunshine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void toString_localDate_formattedDate() {
        try {
            Deadline deadline = new Deadline("deadline description", "28/04/2026");
            assertEquals("[D][ ] deadline description (by: 28 Apr 2026)", deadline.toString());
        } catch (ImproperFormatException e) {
            fail();
        }
    }

    @Test
    public void constructor_invalidFormat_throwsImproperFormatException() {
        assertThrows(ImproperFormatException.class, (
                ) -> new Deadline("deadline description", "2026-04-28"));
    }
}
