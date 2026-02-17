package sunshine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

// Test implemented by ChatGPT Codex
public class EventTest {

    @Test
    public void toString_newEvent_formatsCorrectly() {
        Event event = new Event("team sync", "Mon 9am", "Mon 10am");
        assertEquals("[E][ ] team sync (from: Mon 9am to: Mon 10am)", event.toString());
    }

    @Test
    public void mark_setsDoneInString() {
        Event event = new Event("demo day", "Fri 2pm", "Fri 4pm");
        event.mark();
        assertEquals("[E][X] demo day (from: Fri 2pm to: Fri 4pm)", event.toString());
    }
}
