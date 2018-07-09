package polsl.pl.pm.model.additional;

import polsl.pl.pm.model.Task;

import java.util.List;

public class TaskPB {

    int projectId;
    private List<Task> open;
    private List<Task> close;

    public TaskPB(int projectId, List<Task> open, List<Task> close) {
        this.projectId = projectId;
        this.open = open;
        this.close = close;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public List<Task> getOpen() {
        return open;
    }

    public void setOpen(List<Task> open) {
        this.open = open;
    }

    public List<Task> getClose() {
        return close;
    }

    public void setClose(List<Task> close) {
        this.close = close;
    }
}
