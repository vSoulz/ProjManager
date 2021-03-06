package polsl.pl.pm.model;

import org.hibernate.annotations.Type;
import org.hibernate.type.MaterializedClobType;

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

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = true)
    @Lob @Type(type="org.hibernate.type.MaterializedClobType")
    private String content;

    public Project(){}

    public Project(String title) {
        this.title = title;
    }

    public Project(String title, String description) {
        this.title = title;
        this.content = description;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}