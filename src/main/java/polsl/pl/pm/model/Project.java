package polsl.pl.pm.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "project_id")
    private int projectId;

    private String title;

    private String description;

/*  @ManyToMany(cascade=CascadeType.ALL,mappedBy = "projects")
    private List<UserController> users = new ArrayList<>();*/

    @OneToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "project_tasks",
            joinColumns = { @JoinColumn(name = "project_id") },
            inverseJoinColumns = { @JoinColumn(name = "task_id") }
    )
    private  List<Task> tasks = new ArrayList<>();



/*    @ManyToOne
    private Administrator supervisor;*/

    public Project(){

    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

/*    public List<UserController> getUsers() {
        return users;
    }*/

/*
    public void setUsers(List<UserController> users) {
        this.users = users;
    }
*/

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

/*
    public Administrator getSupervisor() {
        return supervisor;
    }
*/

/*    public void setSupervisor(Administrator supervisor) {
        this.supervisor = supervisor;
    }*/
}