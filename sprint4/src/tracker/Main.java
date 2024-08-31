package tracker;
import tracker.controllers.model.*;
import tracker.controllers.TaskManager;
import tracker.controllers.model.util.Status;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Task task1 = new Task("1", "1", Status.NEW);
        Task task2 = new Task("2", "2", Status.IN_PROGRESS);
        taskManager.createTask(task1);
        taskManager.createTask(task2);
        Epic epic1 = new Epic("3", "1.1");
        Epic epic2 = new Epic("4", "2.1");
        taskManager.createEpic(epic1);
        taskManager.createEpic(epic2);
        SubTask subTask1 = new SubTask("5", "2.2", Status.NEW, 3);
        SubTask subTask2 = new SubTask("6", "2.4", Status.IN_PROGRESS, 3);
        taskManager.createSubTask(subTask1);
        taskManager.createSubTask(subTask2);
        SubTask subTask3 = new SubTask("7", "3.4", Status.NEW, 4);
        taskManager.createSubTask(subTask3);
        System.out.println(taskManager.getListTasks());
        System.out.println(taskManager.getListSubTasks());
        System.out.println(taskManager.getListEpics());
        SubTask subTask4 = new SubTask("111", "1111", Status.IN_PROGRESS, 4, 7);
        System.out.println(taskManager.getEpicSubtasks(4));
        taskManager.updeatSubTask(subTask4);
        System.out.println(taskManager.getEpicSubtasks(4));
        System.out.println(taskManager.getListSubTasks());
        taskManager.deleteEpic(4);
        taskManager.deleteSubTask(5);
        System.out.println(taskManager.getEpicSubtasks(3));
        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getListSubTasks());
        }
}
