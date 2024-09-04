package tracker.controllers;

import tracker.controllers.model.Epic;
import tracker.controllers.model.SubTask;
import tracker.controllers.model.Task;

import java.util.ArrayList;
import java.util.HashMap;

public interface TaskManager {
    int id = 0;
    HashMap<Integer, Task> tasks = new HashMap<Integer, Task>();
    HashMap<Integer, SubTask> subTasks = new HashMap<Integer, SubTask>();
    HashMap<Integer, Epic> epics = new HashMap<Integer, Epic>();

    HashMap<Integer, Epic> getEpics();

    ArrayList<Task> getListTasks();

    ArrayList<SubTask> getListSubTasks();

    ArrayList<Epic> getListEpics();

    void setEpics(HashMap<Integer, Epic> epics);

    Task createTask(Task newTask);

    void updateTask(Task newTask);

    Task deleteTask(int id);

    Task searchTask(int id);

    SubTask createSubTask(SubTask newSubTask);

    void updateSubTask(SubTask newSubTask);

    void deleteSubTask(int id);

    SubTask searchSubTask(int id);

    Epic createEpic(Epic newEpic);

    void updateEpic(Epic newEpic);

    Epic deleteEpic(int id);
    Epic searchEpic(int id);

    ArrayList<SubTask> getEpicSubtasks(int epicID);

    void deleteTasks();

    void deleteSubTasks();

    void deleteEpics();

    ArrayList<Task> getHistory();
}
