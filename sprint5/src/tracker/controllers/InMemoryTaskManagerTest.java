package tracker.controllers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import tracker.controllers.model.Epic;
import tracker.controllers.model.SubTask;
import tracker.controllers.model.Task;
import tracker.controllers.model.util.Status;

import java.util.ArrayList;
import java.util.HashMap;
public class InMemoryTaskManagerTest {

    private TaskManager taskManager = InMemoryTaskManager.getInMemoryTaskManager();
    @Test
    public void testTaskId() {
        Task task1 = new Task("Test", "Description", Status.NEW, 1);
        Task task2 = new Task("Test", "Test", Status.IN_PROGRESS, 1);
        assertEquals(task1, task2);
    }
    @Test
    public void testSubTaskId() {
        Task subTask1 = new SubTask("Test", "Test", Status.NEW, 3);
        Task subTask2 = new SubTask("Test", "Test1", Status.IN_PROGRESS, 3, subTask1.getId());
        assertEquals(subTask1, subTask2);
    } // не понял как сделать другие тесты, особенно проверку на добавление epic в epic

}


