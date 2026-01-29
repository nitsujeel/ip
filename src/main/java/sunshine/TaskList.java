package sunshine;

public class TaskList {

    private Task[] list;
    private int taskCount;

    public TaskList() {
        this.list = new Task[100];
        this.taskCount = 0;
    }

    public void addTask(Task task) {
        this.list[taskCount++] = task;
    }

    public int getTaskCount() {
        return this.taskCount;
    }

    public Task markTask(int index) {
        Task task = this.list[index - 1];
        task.mark();
        return task;
    }

    public Task unmarkTask(int index) {
        Task task = this.list[index - 1];
        task.unmark();
        return task;
    }

    public Task deleteTask(int index) {
        Task task = this.list[index - 1];
        for (int i = index; i < this.taskCount; i++) {
            this.list[i - 1] = this.list[i];
        }
        this.list[this.taskCount - 1] = null;
        this.taskCount--;
        return task;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < this.taskCount; i++) {
            result += "\t " + (i + 1) + ". " + list[i] + "\n";
        }
        return result.trim();
    }
}
