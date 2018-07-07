package polsl.pl.pm.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import polsl.pl.pm.repository.UserRepository;

import javax.persistence.*;

@Entity
@Table(name="task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id")
    private int taskId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "finished", nullable = false)
    private boolean finished = false;

    @Column(name = "status", nullable = false)
    private String status;


    @Column(name = "userId", nullable = false)
    private int userId = 0;

    @Column(name = "projectId", nullable = true)
    private int projectId;

    @Column(name = "bugs", nullable = true)
    private String bugs;

    @Column(name = "source", nullable = true)
    private String source;

    @Column(name = "time", nullable = true)
    private String time;

    public Task(String title, String content, boolean finished, String status, int userId ,int projectId) {
        this.title = title;
        this.content = content;
        this.finished = finished;
        this.userId = userId;
        this.status = status;
        this.projectId = projectId;
    }

    public Task(){


    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBugs() {
        return bugs;
    }

    public void setBugs(String bugs) {
        this.bugs = bugs;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}