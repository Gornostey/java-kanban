package tracker.controllers.model;
import tracker.controllers.model.util.Status;
import java.util.ArrayList;
public class Epic extends Task {
    private ArrayList<SubTask> listSubTasks = new ArrayList<>();

    public Epic(String name, String description){
        super(name, description, Status.NEW);
    }

    public ArrayList<SubTask> getListSubTasks() {
        ArrayList<SubTask> listSubTasks1 = listSubTasks;
        return listSubTasks1;
    }
    public void setListSubTasks(ArrayList<SubTask> listSubTasks){
        this.listSubTasks = listSubTasks;
    }
}
