package tracker.controllers;

import tracker.controllers.model.Epic;
import tracker.controllers.model.SubTask;
import tracker.controllers.model.Task;
import tracker.controllers.model.util.Status;

import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {
    private int id = 0;
    private HashMap<Integer, Task> tasks = new HashMap<Integer, Task>();
    private HashMap<Integer, SubTask> subTasks = new HashMap<Integer, SubTask>();
    private HashMap<Integer, Epic> epics = new HashMap<Integer, Epic>();
    private HistoryManager historyManager = Managers.getDefaultHistory();

    @Override
    public HashMap<Integer, Epic> getEpics(){
        HashMap<Integer, Epic> epics1 = new HashMap<Integer, Epic>(epics);
        return epics1;
    }
    @Override
    public ArrayList<Task> getListTasks(){
        ArrayList<Task> tasks1 = new ArrayList<>(tasks.values());
        return tasks1;
    }
    @Override
    public ArrayList<SubTask> getListSubTasks(){
        ArrayList<SubTask> subTasks1 = new ArrayList<>(subTasks.values());
        return subTasks1;
    }
    @Override
    public ArrayList<Epic> getListEpics(){
        ArrayList<Epic> epics1 = new ArrayList<>(epics.values());
        return epics1;
    }
    @Override
    public void setEpics(HashMap<Integer, Epic> epics){
        this.epics = epics;
    }
    public static InMemoryTaskManager getInMemoryTaskManager(){
        return new InMemoryTaskManager();
    }
    @Override
    public Task createTask(Task newTask){
        id++;
        newTask.setId(id);
        tasks.put(newTask.getId(), newTask);
        return newTask;
    }
    @Override
    public void updateTask(Task newTask){
        boolean flag = false;
        for (Task task : tasks.values()){
            if (task.getId() == newTask.getId()) {
                flag = true;
                tasks.put(newTask.getId(), newTask);
                break;
            }
        }
        if (!flag)
            System.out.println("задача, которую нужно обновить, не найдена.");

    }
    @Override
    public Task deleteTask(int id){
        Task task = tasks.get(id);
        tasks.remove(id);
        return task;
    }
    @Override
    public Task searchTask(int id){
        historyManager.add(tasks.get(id));
        return tasks.get(id);
    }
    @Override
    public SubTask createSubTask(SubTask newSubTask){
        id++;
        newSubTask.setId(id);
        subTasks.put(newSubTask.getId(), newSubTask);
        epics.get(newSubTask.getEpicID()).getListSubTasks().add(newSubTask);
        checkStatusEpic(epics.get(newSubTask.getEpicID()));
        return newSubTask;
    }
    @Override
    public void updateSubTask(SubTask newSubTask){
        boolean flag = false;
        for (SubTask subTask : subTasks.values()){
            if (subTask.getId() == newSubTask.getId()) {
                flag = true;
                subTasks.put(newSubTask.getId(), newSubTask);
                for(int i = 0; i < epics.get(newSubTask.getEpicID()).getListSubTasks().size(); i++){
                    SubTask task1 = epics.get(newSubTask.getEpicID()).getListSubTasks().get(i);
                    if (task1.getId() == newSubTask.getId()) {
                        ArrayList<SubTask> subTasks1 = epics.get(newSubTask.getEpicID()).getListSubTasks();
                        subTasks1.set(i, newSubTask);
                        epics.get(newSubTask.getEpicID()).setListSubTasks(subTasks1);
                    }
                }
                break;
            }
        }
        if (!flag)
            System.out.println("задача, которую нужно обновить, не найдена.");

    }
    @Override
    public void deleteSubTask(int id){
        SubTask subTask = subTasks.get(id);
        Epic epic = epics.get(subTask.getEpicID());
        ArrayList<SubTask> subTasks1 = epic.getListSubTasks();
        subTasks1.remove(subTask);
        epic.setListSubTasks(subTasks1);
        subTasks.remove(id);
        checkStatusEpic(epic);
    }
    @Override
    public SubTask searchSubTask(int id){
        historyManager.add(subTasks.get(id));
        return subTasks.get(id);
    }
    @Override
    public Epic createEpic(Epic newEpic){
        id++;
        newEpic.setId(id);
        epics.put(newEpic.getId(), newEpic);
        return newEpic;
    }
    @Override
    public void updateEpic(Epic newEpic){
        boolean flag = false;
        for (Epic epic : epics.values()){
            if (epic.getId() == newEpic.getId()) {
                flag = true;
                epics.put(newEpic.getId(), newEpic);
                break;
            }
        }
        if (!flag)
            System.out.println("задача, которую нужно обновить, не найдена.");
    }
    @Override
    public Epic deleteEpic(int id){
        Epic epic = epics.get(id);
        for(SubTask subTask : epics.get(id).getListSubTasks()){
            subTasks.remove(subTask.getId());
        }
        epics.remove(id);
        return epic;
    }
    @Override
    public Epic searchEpic(int id){
        historyManager.add(epics.get(id));
        return epics.get(id);
    }
    private void checkStatusEpic(Epic epic){
        if(epic.getListSubTasks().isEmpty()) {
            int flagProgress = 0;
            int flagDone = 0;
            int flagNew = 0;
            for (SubTask subTask : epic.getListSubTasks()) {
                if (subTask.getStatus().equals(Status.IN_PROGRESS)) {
                    flagProgress++;
                } else if (subTask.getStatus().equals(Status.DONE)) {
                    flagDone++;
                }else flagNew++;
            }
            if (flagProgress > 0){
                epic.setStatus(Status.IN_PROGRESS);
            } else if (flagDone > 0 && flagNew == 0) {
                epic.setStatus(Status.DONE);
            }
        }else epic.setStatus(Status.NEW);
    }
    @Override
    public ArrayList<SubTask> getEpicSubtasks(int epicID){
        return epics.get(epicID).getListSubTasks();
    }
    @Override
    public void deleteTasks(){
        tasks.clear();
    }
    @Override
    public void deleteSubTasks(){
        for(Epic epic : epics.values()){
            epic.getListSubTasks().clear();
            epic.setListSubTasks(epic.getListSubTasks());
            epic.setStatus(Status.NEW);
        }
        subTasks.clear();
    }
    @Override
    public void deleteEpics(){
        epics.clear();
        subTasks.clear();
    }
    @Override
    public ArrayList<Task> getHistory(){
        return historyManager.getHistory();
    }

}
