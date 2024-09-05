package tracker.controllers;

public class Managers {
    private static TaskManager defaultManager;
    private Managers() {}

    public static TaskManager getDefault() {
        if (defaultManager == null) {
            defaultManager = new InMemoryTaskManager();
        }
        return defaultManager;
    }

    public static void setDefault(TaskManager manager) {
        defaultManager = manager;
    }
    public static HistoryManager getDefaultHistory(){
        return new InMemoryHistoryManager();
    }


}
