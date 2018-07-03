package polsl.pl.pm.model.additional;

        import polsl.pl.pm.model.Task;

        import java.util.List;

public class TaskTB {

    int userId;
    private List<Task> open;
    private List<Task> close;

    public TaskTB(int userId, List<Task> open, List<Task> close) {
        this.userId = userId;
        this.open = open;
        this.close = close;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
