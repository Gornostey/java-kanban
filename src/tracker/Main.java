package tracker;
import test.InMemoryTaskManagerTest;
import tracker.controllers.InMemoryTaskManager;
import tracker.controllers.Managers;
import tracker.controllers.model.*;
import tracker.controllers.TaskManager;
import tracker.controllers.model.util.Status;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        TaskManager inMemoryTaskManager = Managers.getDefault();
        Task task1 = new Task("1", "1", Status.NEW);
        Task task2 = new Task("2", "2", Status.IN_PROGRESS);
        inMemoryTaskManager.createTask(task1);
        inMemoryTaskManager.createTask(task2);
        Epic epic1 = new Epic("3", "1.1");
        Epic epic2 = new Epic("4", "2.1");
        inMemoryTaskManager.createEpic(epic1);
        inMemoryTaskManager.createEpic(epic2);
        SubTask subTask1 = new SubTask("5", "2.2", Status.NEW, 3);
        SubTask subTask2 = new SubTask("6", "2.4", Status.IN_PROGRESS, 3);
        inMemoryTaskManager.createSubTask(subTask1);
        inMemoryTaskManager.createSubTask(subTask2);
        SubTask subTask3 = new SubTask("7", "3.4", Status.NEW, 4);
        inMemoryTaskManager.createSubTask(subTask3);
        System.out.println(inMemoryTaskManager.getListTasks());
        System.out.println(inMemoryTaskManager.getListSubTasks());
        System.out.println(inMemoryTaskManager.getListEpics());
        SubTask subTask4 = new SubTask("111", "1111", Status.IN_PROGRESS, 4, 7);
        System.out.println(inMemoryTaskManager.getEpicSubtasks(4));
        inMemoryTaskManager.updateSubTask(subTask4);
        System.out.println(inMemoryTaskManager.getEpicSubtasks(4));
        System.out.println(inMemoryTaskManager.getListSubTasks());
        inMemoryTaskManager.deleteEpic(4);
        inMemoryTaskManager.deleteSubTask(5);
        System.out.println(inMemoryTaskManager.getEpicSubtasks(3));
        System.out.println(inMemoryTaskManager.getEpics());
        System.out.println(inMemoryTaskManager.searchEpic(3));
        System.out.println(inMemoryTaskManager.searchEpic(3));
        System.out.println(inMemoryTaskManager.getListSubTasks());
        System.out.println(inMemoryTaskManager.getHistory());
        }
}
