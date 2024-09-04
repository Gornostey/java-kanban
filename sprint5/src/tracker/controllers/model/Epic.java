package tracker.controllers.model;
import tracker.controllers.model.util.Status;
import java.util.ArrayList;
public class Epic extends Task {
    private ArrayList<SubTask> listSubTasks = new ArrayList<>();

    public Epic(String name, String description){
        super(name, description, Status.NEW);
    }
    public Epic(String name, String description, Status status, int id){
        super(name, description, Status.NEW);
        setId(id);
    }

    public ArrayList<SubTask> getListSubTasks() {
        ArrayList<SubTask> listSubTasks1 = listSubTasks;
        return listSubTasks1;
    }
    public void setListSubTasks(ArrayList<SubTask> listSubTasks){
        this.listSubTasks = listSubTasks;
    }
}
