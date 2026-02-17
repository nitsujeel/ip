package sunshine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

// Test implemented by ChatGPT Codex
public class TaskListFindTest {

    @Test
    public void findTasks_keywordFiltersResults() throws EmptyDescriptionException {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("buy milk"));
        taskList.addTask(new Deadline("submit report", "tomorrow"));
        taskList.addTask(new Event("buy groceries", "today", "today"));

        TaskList results = taskList.findTasks("buy");

        assertEquals(2, results.getTaskCount());
        assertEquals("1. [T][ ] buy milk\n2. [E][ ] buy groceries (from: today to: today)",
                results.toString());
    }
}
