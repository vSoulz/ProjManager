package polsl.pl.pm.model.additional;

import polsl.pl.pm.model.Project;

import java.util.List;

public class ProjectPB {

    Integer projectId;
    Project project;

    TaskTB taskTB;

    public ProjectPB() {
    }

    public ProjectPB(Integer projectId, Project project, TaskTB taskTB) {
        this.projectId = projectId;
        this.project = project;
        this.taskTB = taskTB;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public TaskTB getTasks() {
        return taskTB;
    }

    public void setTasks(TaskTB taskTB) {
        this.taskTB = taskTB;
    }
}
