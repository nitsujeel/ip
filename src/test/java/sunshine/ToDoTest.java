package sunshine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

// Test implemented by ChatGPT Codex
public class ToDoTest {

    @Test
    public void constructor_emptyDescription_throwsException() {
        assertThrows(EmptyDescriptionException.class, () -> new ToDo(""));
    }

    @Test
    public void toString_newTask_showsTodoPrefix() throws EmptyDescriptionException {
        ToDo todo = new ToDo("buy milk");
        assertEquals("[T][ ] buy milk", todo.toString());
    }
}
