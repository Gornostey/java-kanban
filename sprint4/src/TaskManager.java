import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    static int id = 0;
    static HashMap<int, Task> tasks = new HashMap<int, Task>();
    static HashMap<int, SubTask> subTasks = new HashMap<int, SubTask>();
    static HashMap<int, Epic> epics = new HashMap<int, Epic>();

    public Task createTask(Task newTask){
        id++;
        newTask.setId(id);
        tasks.put(newTask.getId(), newTask);
        return newTask;
    }
    public Task updeatTask(Task newTask){
        Task oldTask = tasks.get(newTask.getId());
        tasks.put(newTask.getId(), oldTask);
        return newTask;
    }
    public Task deleteTask(int id){
        Task task = tasks.get(id);
        tasks.remove(id);
        return task;
    }
    public Task searchTask(int id){
        return tasks.get(id);
    }
    public void scanTasks(){
        for (Task task : tasks.values()){
            System.out.println(task);
        }
    }
    public SubTask createSubTask(SubTask newSubTask){
        id++;
        newSubTask.setId(id);
        subTasks.put(newSubTask.getId(), newSubTask);
        epics.get(newSubTask.epicID).listSubTasks.add(newSubTask);
        epics.get(newSubTask.epicID).status = Status.NEW;
        return newSubTask;
    }
    public SubTask updeatSubTask(SubTask newSubTask){
        SubTask oldSubTask = subTasks.get(newSubTask.getId());
        subTasks.put(newSubTask.getId(), oldSubTask);
        if(newSubTask.status.equals(Status.IN_PROGRESS)) {
            epics.get(newSubTask.epicID).status = Status.IN_PROGRESS;
        }else if (newSubTask.status.equals(Status.DONE) && epics.get(newSubTask.epicID).listSubTasks.size() > 1) {
            int flagProgress = 0;
            int flagNew = 0;
            for(SubTask subTask : epics.get(newSubTask.epicID).listSubTasks){
                if (subTask.status.equals(Status.IN_PROGRESS)) {
                    flagProgress++;
                }else if(subTask.status.equals(Status.NEW)) {
                    flagNew++;
            }
            if (flagProgress > 0){
                epics.get(newSubTask.epicID).status = Status.IN_PROGRESS;
            }else if(flagNew > 0) {
                epics.get(newSubTask.epicID).status = Status.NEW;
            }else epics.get(newSubTask.epicID).status = Status.DONE;
            }
        }else epics.get(newSubTask.epicID).status = Status.NEW;
        return newSubTask;
    }
    public SubTask deleteSubTask(int id){
        SubTask subTask = subTasks.get(id);
        subTasks.remove(id);
        epics.get(id).listSubTasks.remove(subTask.epicID);
        if(!epics.get(subTask.epicID).listSubTasks.isEmpty()) {
            int flagProgress = 0;
            int flagDone = 0;
            int flagNew = 0;
            for (SubTask subTask1 : epics.get(subTask.epicID).listSubTasks) {
                if (subTask1.status.equals(Status.IN_PROGRESS)) {
                    flagProgress++;
                } else if (subTask1.status.equals(Status.DONE)) {
                    flagDone++;
                }else flagNew++;
            }
            if (flagProgress > 0){
                epics.get(subTask.epicID).status = Status.IN_PROGRESS;
            } else if (flagDone > 0 && flagNew == 0) {
                epics.get(subTask.epicID).status = Status.DONE;
            }
        }else epics.get(subTask.epicID).status = Status.NEW;
        return subTask;
    }
    public SubTask searchSubTask(int id){
        return subTasks.get(id);
    }
    public void scanSubTasks(){
        for (SubTask subTask : subTasks.values()){
            System.out.println(subTask);
        }
    }
    public Epic createEpic(Epic newEpic){
        id++;
        newEpic.setId(id);
        epics.put(newEpic.getId(), newEpic);
        return newEpic;
    }
    public Epic updeatEpic(Epic newEpic){
        Epic oldEpic = epics.get(newEpic.getId());
        epics.put(newEpic.getId(), oldEpic);
        return newEpic;
    }
    public Epic deleteEpic(int id){
        Epic epic = epics.get(id);
        for(SubTask subTask : epics.get(id).listSubTasks){
            subTasks.remove(subTask.id);
        }
        epics.remove(id);
        return epic;
    }
    public Epic searchEpic(int id){
        return epics.get(id);
    }
    public void scanEpics(){
        for (Epic epic : epics.values()){
            System.out.println(epic);
        }
    }

}
