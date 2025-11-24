import java.io.*;
import java.util.*;

public class TaskRepository {

    private static final String FILE = "tasks.json";

    public TaskRepository() {
        File f = new File(FILE);
        if (!f.exists()) {
            try (FileWriter writer = new FileWriter(FILE)) {
                writer.write("[]");
            } catch (Exception e) {
                System.out.println("Cannot create file: " + e.getMessage());
            }
        }
    }

    public List<Task> load() {
        try {
            FileReader reader = new FileReader(FILE);
            BufferedReader br = new BufferedReader(reader);
            String json = br.readLine();
            br.close();

            return JsonUtils.jsonToTasks(json);

        } catch (Exception e) {
            System.out.println("Failed to load file");
            return new ArrayList<>();
        }
    }

    public void save(List<Task> tasks) {
        String json = JsonUtils.tasksToJson(tasks);
        try (FileWriter writer = new FileWriter(FILE)) {
            writer.write(json);
        } catch (Exception e) {
            System.out.println("Failed to save tasks");
        }
    }
}
