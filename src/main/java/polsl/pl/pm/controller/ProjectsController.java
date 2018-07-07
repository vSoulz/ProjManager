package polsl.pl.pm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import polsl.pl.pm.model.Project;
import polsl.pl.pm.model.additional.ProjectPB;
import polsl.pl.pm.model.additional.TaskTB;
import polsl.pl.pm.repository.ProjectRepository;
import polsl.pl.pm.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/project")
public class ProjectsController {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ProjectRepository projectRepository;

    @RequestMapping(method = RequestMethod.GET)
    List<ProjectPB> GetProjects()
    {
        List<ProjectPB> projectPBList = new ArrayList<>();
        List<Project> projects = projectRepository.findAll();
        for (Project project: projects) {
            projectPBList.add(new ProjectPB(project, new TaskTB(project.getProjectId(),
                    taskRepository.findByProjectIdAndStatus(project.getProjectId(),"open"),taskRepository.findByProjectIdAndStatus(project.getProjectId(),"done"))));
        }
        return projectPBList;
    }


}