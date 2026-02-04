package sunshine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void deleteTask_noExceptions_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Deadline("deadline name", "deadline by"));
        taskList.addTask(new Event("event name", "event from", "event to"));
        Task taskToBeDeleted = new Deadline("delete this", "now");
        taskList.addTask(taskToBeDeleted);
        assertEquals(taskToBeDeleted, taskList.deleteTask(3));
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
        TaskList taskList = new TaskList();
        Task taskToBeDeleted = new Deadline("delete this", "now");
        taskList.addTask(taskToBeDeleted);
        try {
            assertEquals(taskToBeDeleted, taskList.deleteTask(2));
            fail();
        } catch (Exception e) {
            assertEquals("Index is out of bounds", e.getMessage());
        }
    }
}
