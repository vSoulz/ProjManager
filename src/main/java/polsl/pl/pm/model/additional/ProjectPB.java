package polsl.pl.pm.model.additional;

import polsl.pl.pm.model.Project;

import java.util.List;

public class ProjectPB {

    Integer projectId;
    Project project;
    TaskPB taskPB;
    String bugs;

    public ProjectPB() {
    }

    public ProjectPB(Integer projectId, Project project, TaskPB taskPB) {
        this.projectId = projectId;
        this.project = project;
        this.taskPB = taskPB;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public TaskPB getTaskPB() {
        return taskPB;
    }

    public void setTaskPB(TaskPB taskPB) {
        this.taskPB = taskPB;
    }

    public String getBugs() {
        return bugs;
    }

    public void setBugs(String bugs) {
        this.bugs = bugs;
    }
}
