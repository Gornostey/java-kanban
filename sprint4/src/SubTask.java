public class SubTask extends Task {
    int epicID;

    public int getEpicID(){
        return epicID;
    }
    public void setEpicID(int epicID){
        if(epicID > 0 && TaskManager.epics.containsKey(epicID))
            this.epicID = epicID;
    }
}
