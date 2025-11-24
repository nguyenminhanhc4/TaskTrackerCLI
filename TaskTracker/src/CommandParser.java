public class CommandParser {

    private final TaskService service;

    public CommandParser(TaskService service) {
        this.service = service;
    }

    public void handle(String[] args) {
        if (args.length == 0) {
            printHelp();
            return;
        }

        String cmd = args[0];

        switch (cmd) {
            case "add":
                service.add(args[1]);
                break;

            case "update":
                service.update(Integer.parseInt(args[1]), args[2]);
                break;

            case "delete":
                service.delete(Integer.parseInt(args[1]));
                break;

            case "mark-in-progress":
                service.mark(Integer.parseInt(args[1]), "in-progress");
                break;

            case "mark-done":
                service.mark(Integer.parseInt(args[1]), "done");
                break;

            case "list":
                String filter = args.length == 1 ? "all" : args[1];
                service.list(filter);
                break;

            default:
                printHelp();
        }
    }

    private void printHelp() {
        System.out.println("Commands:");
        System.out.println(" add \"description\"");
        System.out.println(" update <id> \"new desc\"");
        System.out.println(" delete <id>");
        System.out.println(" mark-in-progress <id>");
        System.out.println(" mark-done <id>");
        System.out.println(" list [all|todo|in-progress|done]");
    }
}

