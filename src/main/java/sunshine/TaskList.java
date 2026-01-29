package sunshine;

public class TaskList {

    private Task[] list;
    private int taskCount;

    /**
     * Initialises a TaskList object.
     */
    public TaskList() {
        this.list = new Task[100];
        this.taskCount = 0;
    }

    /**
     * Adds a Task to the list.
     *
     * @param task Task to add to the list.
     */
    public void addTask(Task task) {
        this.list[taskCount++] = task;
    }

    public int getTaskCount() {
        return this.taskCount;
    }

    /**
     * Marks the Task at index as completed.
     *
     * @param index Index of Task to be marked.
     * @return Task marked as completed.
     */
    public Task markTask(int index) {
        Task task = this.list[index - 1];
        task.mark();
        return task;
    }

    /**
     * Unmarks the Task at index, resetting it to uncompleted.
     *
     * @param index Index of Task to be unmarked.
     * @return Task unmarked.
     */
    public Task unmarkTask(int index) {
        Task task = this.list[index - 1];
        task.unmark();
        return task;
    }

    /**
     * Deletes a Task from the list.
     *
     * @param index Index of Task to be deleted.
     * @return Task that was deleted.
     */
    public Task deleteTask(int index) {
        if (index > taskCount) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Task task = this.list[index - 1];
        for (int i = index; i < this.taskCount; i++) {
            this.list[i - 1] = this.list[i];
        }
        this.list[this.taskCount - 1] = null;
        this.taskCount--;
        return task;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.taskCount; i++) {
            result += "\t " + (i + 1) + ". " + list[i] + "\n";
        }
        return result.trim();
    }

    /**
     * Searches list for Tasks containing keyword.
     *
     * @param keyword Keyword to search for.
     * @return A TaskList of tasks that contain the keyword in their descriptions.
     */
    public TaskList findTasks(String keyword) {
        TaskList result = new TaskList();
        for (int i = 0; i < this.taskCount; i++) {
            Task t = this.list[i];
            if (t.getDescription().contains(keyword)) {
                result.addTask(t);
            }
        }
        return result;
    }
}
