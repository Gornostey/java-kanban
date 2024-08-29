public class Task {
    String name;
    String description;
    int id;
    Status status;

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
}

