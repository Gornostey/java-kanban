package test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import tracker.controllers.InMemoryTaskManager;
import tracker.controllers.Managers;
import tracker.controllers.TaskManager;
import tracker.controllers.model.Epic;
import tracker.controllers.model.SubTask;
import tracker.controllers.model.Task;
import tracker.controllers.model.util.Status;

import java.util.ArrayList;
import java.util.HashMap;
public class InMemoryTaskManagerTest {

    private TaskManager taskManager;
    private Task task1;
    private Task task2;
    private Epic epic1;
    private SubTask subTask11;
    private SubTask subTask12;
    private Epic epic2;
    private SubTask subTask21;
    private int idT1, idT2, idE1, idSt1, idSt2, idE2, idSt3;

    //Перед каждым тестом создать набор исходных данных, чтобы не дублировать код внутри тестов.
    @BeforeEach
    public void beforeEach() {
        taskManager = Managers.getDefault();
        task1 = new Task("Задача 1", "Действие таска 1", Status.NEW, 1);
        task2 = new Task("Задача 2", "Действие таска 2", Status.NEW, 2);
        epic1 = new Epic("Эпик 1", "Действие эпика 1", Status.NEW, 3);
        subTask11 = new SubTask("Подзадача 1", "Действие подтаска 1", Status.DONE, epic1.getId(), 4);
        subTask12 = new SubTask("Подзадача 2", "Действие подтаска 2", Status.NEW, epic1.getId(), 5);
        epic2 = new Epic("Эпик 2", "Действие эпика 2", Status.NEW, 6);
        subTask21 = new SubTask("Подзадача 1", "Действие", Status.DONE, epic2.getId(), 7);
        idT1 = taskManager.createTask(task1).getId();
        idT2 = taskManager.createTask(task2).getId();
        idE1 = taskManager.createEpic(epic1).getId();
        idSt1 = taskManager.createSubTask(subTask11).getId();
        idSt2 = taskManager.createSubTask(subTask12).getId();
        idE2 = taskManager.createEpic(epic2).getId();
        idSt3 = taskManager.createSubTask(subTask21).getId();
    }
    @AfterEach
    public void afterEach() {
        taskManager.deleteTasks();
        taskManager.deleteSubTasks();
        taskManager.deleteEpics();
    }

    @Test
    public void TaskManagerCanFindTasksById() {
        System.out.println(task1.getId());
        assertEquals(task1, taskManager.searchTask(idT1));
        assertEquals(task2, taskManager.searchTask(idT2));
        assertEquals(epic1, taskManager.searchEpic(idE1));
        assertEquals(subTask11, taskManager.searchSubTask(idSt1));
        assertEquals(subTask12, taskManager.searchSubTask(idSt2));
        assertEquals(epic2, taskManager.searchEpic(idE2));
        assertEquals(subTask21, taskManager.searchSubTask(idSt3));
    }

    @Test
    public void TaskImmutability() {
        Task oldtask = new Task("dd", "dd", Status.NEW, 10);
        Task newtask = taskManager.createTask(oldtask);
        assertEquals(oldtask, newtask);
    }

    @Test
    public void HistoryCheck() {
        taskManager.searchTask(task1.getId());
        assertNotNull(taskManager.getHistory());

    }
    @Test
    public void DeleteTaskTest(){
        taskManager.deleteTask(idT1);
        taskManager.deleteTask(idSt1);
        taskManager.deleteTask(idE1);
        assertNull(taskManager.searchTask(idT1));
        assertNull(taskManager.searchSubTask(idSt1));
        assertNull(taskManager.searchEpic(idE1));
    }
    @Test
    public void UpdateTaskTest(){
        Task updatedTask = new Task("1", "2", Status.IN_PROGRESS);
        updatedTask.setId(idT1);
        taskManager.updateTask(updatedTask);
        assertEquals("1", taskManager.searchTask(idT1).getName());
        assertEquals("2", taskManager.searchTask(idT1).getDescription());
        assertEquals(Status.IN_PROGRESS, taskManager.searchTask(idT1).getStatus());
    }
    @Test
    public void CheckStatus(){
        taskManager.deleteSubTask(idSt3);
        assertEquals(Status.NEW, taskManager.searchTask(idE2).getStatus());
    }
}


