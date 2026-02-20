package sunshine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void deleteTask_noExceptions_success() {
        try {
            TaskList taskList = new TaskList();
            taskList.addTask(new Deadline("deadline name", "01/01/2025"));
            taskList.addTask(new Event("event name", "02/01/2025", "03/01/2025"));
            Task taskToBeDeleted = new Deadline("delete this", "04/01/2025");
            taskList.addTask(taskToBeDeleted);
            assertEquals(taskToBeDeleted, taskList.deleteTask(3));
        } catch (ImproperFormatException | EndBeforeStartException e) {
            fail();
        }
    }

    @Test
    public void deleteTask_noTasks_exceptionThrown() {
        TaskList taskList = new TaskList();
        try {
            taskList.deleteTask(1);
            fail();
        } catch (Exception e) {
            assertEquals("Index is out of bounds", e.getMessage());
        }
    }

    @Test
    public void deleteTask_indexInvalid_exceptionThrown() {
        try {
            TaskList taskList = new TaskList();
            Task taskToBeDeleted = new Deadline("delete this", "01/01/2025");
            taskList.addTask(taskToBeDeleted);
            try {
                assertEquals(taskToBeDeleted, taskList.deleteTask(2));
                fail();
            } catch (Exception e) {
                assertEquals("Index is out of bounds", e.getMessage());
            }
        } catch (ImproperFormatException e) {
            fail();
        }
    }
}
