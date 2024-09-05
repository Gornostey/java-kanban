package tracker.controllers.model;
import tracker.controllers.InMemoryTaskManager;
import tracker.controllers.model.util.Status;
import tracker.controllers.TaskManager;

public class SubTask extends Task {
    private int epicID;

    public SubTask(String name, String description, Status status, int epicID){
        super(name, description, status);
        this.epicID = epicID;

    }
    public SubTask(String name, String description, Status status, int epicID, int id){
        super(name, description, status);
        this.epicID = epicID;
        setId(id);

    }
    public int getEpicID(){
        return epicID;
    }
    public void setEpicID(int epicID){
        if(epicID > 0 && InMemoryTaskManager.getInMemoryTaskManager().getEpics().containsKey(epicID))
            this.epicID = epicID;
    }
}
