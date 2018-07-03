package polsl.pl.pm.model.additional;



public class UserTB {

    private int userId;
    private String userName;
    private TaskTB tasks;


    public UserTB(int userId, String userName, TaskTB tasks) {
        this.userId = userId;
        this.userName = userName;
        this.tasks = tasks;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public TaskTB getTasks() {
        return tasks;
    }

    public void setTasks(TaskTB tasks) {
        this.tasks = tasks;
    }
}
