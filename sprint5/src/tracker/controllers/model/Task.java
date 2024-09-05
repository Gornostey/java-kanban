package tracker.controllers.model;
import java.util.Objects;
import tracker.controllers.model.util.Status;
public class Task {
    private String name;
    private String description;
    private int id = 0;
    private Status status;

    public Task(String name, String description, Status status){
        this.name = name;
        this.description = description;
        this.status = status;
    }
    public Task(String name, String description, Status status, int id){
        this.name = name;
        this.description = description;
        this.status = status;
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public int getId(){
        return id;
    }
    public Status getStatus(){
        return status;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setStatus(Status status){
        this.status = status;
    }

    public void setId(int id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

