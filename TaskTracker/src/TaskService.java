import java.time.LocalDateTime;
import java.util.*;

public class TaskService {

    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public void add(String description) {
        List<Task> tasks = repo.load();
        int maxId = tasks.stream()
                .mapToInt(t -> t.id)
                .max()
                .orElse(0);

        int id = maxId + 1;

        LocalDateTime now = LocalDateTime.now();

        Task t = new Task(id, description, "todo", now.toString(), now.toString());
        tasks.add(t);
        repo.save(tasks);

        System.out.println("Added task #" + id);
    }

    public void update(int id, String newDesc) {
        List<Task> tasks = repo.load();

        for (Task t : tasks) {
            if (t.id == id) {
                t.description = newDesc;
                t.updatedAt = LocalDateTime.now().toString();
                repo.save(tasks);
                System.out.println("Updated task #" + id);
                return;
            }
        }
        System.out.println("Task not found");
    }

    public void delete(int id) {
        List<Task> tasks = repo.load();
        tasks.removeIf(t -> t.id == id);
        repo.save(tasks);
        System.out.println("Deleted task #" + id);
    }

    public void mark(int id, String status) {
        List<Task> tasks = repo.load();
        for (Task t : tasks) {
            if (t.id == id) {
                t.status = status;
                t.updatedAt = LocalDateTime.now().toString();
                repo.save(tasks);
                System.out.println("Marked task #" + id + " as " + status);
                return;
            }
        }
        System.out.println("Task not found");
    }

    public void list(String filter) {
        List<Task> tasks = repo.load();

        for (Task t : tasks) {
            if (filter.equals("all") || t.status.equals(filter)) {
                System.out.println("#" + t.id + " [" + t.status + "] " + t.description);
            }
        }
    }
}

