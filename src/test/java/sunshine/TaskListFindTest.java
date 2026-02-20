package sunshine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

// Test implemented by ChatGPT Codex
public class TaskListFindTest {

    @Test
    public void findTasks_keywordFiltersResults() {
        try {
            TaskList taskList = new TaskList();
            taskList.addTask(new ToDo("buy milk"));
            taskList.addTask(new Deadline("submit report", "05/05/2026"));
            taskList.addTask(new Event("buy groceries", "06/05/2026", "07/05/2026"));

            TaskList results = taskList.findTasks("buy");

            assertEquals(2, results.getTaskCount());
            assertEquals("1. [T][ ] buy milk\n2. [E][ ] buy groceries (from: 6 May 2026 to: 7 May 2026)",
                    results.toString());
        } catch (ImproperFormatException | EndBeforeStartException e) {
            fail();
        }
    }
}
