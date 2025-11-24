import java.util.*;

public class JsonUtils {

    public static List<Task> jsonToTasks(String json) {
        List<Task> tasks = new ArrayList<>();

        if (json == null || json.trim().equals("[]")) return tasks;

        json = json.substring(1, json.length() - 1).trim();

        if (json.isEmpty()) return tasks;

        String[] objects = json.split("\\},\\{");

        for (String obj : objects) {
            obj = obj.replace("{", "").replace("}", "");
            String[] lines = obj.split(",");

            Map<String, String> map = new HashMap<>();
            for (String line : lines) {
                String[] kv = line.split(":");
                String key = kv[0].replace("\"", "").trim();
                String val = kv[1].replace("\"", "").trim();
                map.put(key, val);
            }

            tasks.add(new Task(
                    Integer.parseInt(map.get("id")),
                    map.get("description"),
                    map.get("status"),
                    map.get("createdAt"),
                    map.get("updatedAt")
            ));
        }

        return tasks;
    }

    public static String tasksToJson(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            sb.append("{")
                    .append("\"id\":\"" + t.id + "\",")
                    .append("\"description\":\"" + t.description + "\",")
                    .append("\"status\":\"" + t.status + "\",")
                    .append("\"createdAt\":\"" + t.createdAt + "\",")
                    .append("\"updatedAt\":\"" + t.updatedAt + "\"")
                    .append("}");
            if (i < tasks.size() - 1) sb.append(",");
        }

        sb.append("]");
        return sb.toString();
    }
}

