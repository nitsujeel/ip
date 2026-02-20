package sunshine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

// Test implemented by ChatGPT Codex
public class EventTest {

    @Test
    public void toString_newEvent_formatsCorrectly() {
        try {
            Event event = new Event("team sync", "01/05/2026", "02/05/2026");
            assertEquals("[E][ ] team sync (from: 1 May 2026 to: 2 May 2026)", event.toString());
        } catch (ImproperFormatException | EndBeforeStartException e) {
            fail();
        }
    }

    @Test
    public void mark_setsDoneInString() {
        try {
            Event event = new Event("demo day", "10/07/2026", "11/07/2026");
            event.mark();
            assertEquals("[E][X] demo day (from: 10 Jul 2026 to: 11 Jul 2026)", event.toString());
        } catch (ImproperFormatException | EndBeforeStartException e) {
            fail();
        }
    }

    @Test
    public void constructor_endBeforeStart_throwsException() {
        assertThrows(EndBeforeStartException.class,
                () -> new Event("invalid", "10/07/2026", "09/07/2026"));
    }
}
