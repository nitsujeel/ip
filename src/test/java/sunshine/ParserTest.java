package sunshine;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

// Test implemented by ChatGPT Codex
public class ParserTest {

    @Test
    public void parseInput_noArgs_returnsEmptyArgument() {
        String[] parsed = Parser.parseInput("list");
        assertEquals("list", parsed[0]);
        assertEquals("", parsed[1]);
    }

    @Test
    public void parseInput_withArgs_splitsOnce() {
        String[] parsed = Parser.parseInput("deadline submit report by tonight");
        assertEquals("deadline", parsed[0]);
        assertEquals("submit report by tonight", parsed[1]);
    }

    @Test
    public void parseDeadline_splitsDescriptionAndBy() {
        String[] parsed = Parser.parseDeadline("read book /by tomorrow");
        assertArrayEquals(new String[] {"read book", "tomorrow"}, parsed);
    }

    @Test
    public void parseEvent_splitsDescriptionFromTo() {
        String[] parsed = Parser.parseEvent("project meeting /from 2pm /to 4pm");
        assertArrayEquals(new String[] {"project meeting", "2pm", "4pm"}, parsed);
    }
}
