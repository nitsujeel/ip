package sunshine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

// Test implemented by ChatGPT Codex
public class ToDoTest {

    @Test
    public void constructor_emptyDescription_throwsException() {
        assertThrows(ImproperFormatException.class, () -> new ToDo(""));
    }

    @Test
    public void toString_newTask_showsTodoPrefix() {
        try {
            ToDo todo = new ToDo("buy milk");
            assertEquals("[T][ ] buy milk", todo.toString());
        } catch (ImproperFormatException e) {
            fail();
        }
    }
}
