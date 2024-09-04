package tracker.controllers;

import tracker.controllers.model.Task;

import java.util.ArrayList;
public class InMemoryHistoryManager implements HistoryManager{
    public ArrayList<Task> history = new ArrayList<>();

    @Override
    public void add(Task task){
        history.add(task);
        if(history.size() > 10){
            history.remove(0);
        }
    }

    @Override
    public ArrayList<Task> getHistory() {
        return new ArrayList<Task>(history);
    }
}
