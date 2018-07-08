package polsl.pl.pm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import polsl.pl.pm.model.Project;
import polsl.pl.pm.model.User;
import polsl.pl.pm.model.additional.ProjectPB;
import polsl.pl.pm.model.additional.TaskTB;
import polsl.pl.pm.repository.ProjectRepository;
import polsl.pl.pm.repository.TaskRepository;
import polsl.pl.pm.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/project")
public class ProjectsController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ProjectRepository projectRepository;

    @RequestMapping(method = RequestMethod.GET)
    List<ProjectPB> GetProjects()
    {
        List<ProjectPB> projectPBList = new ArrayList<>();
        List<Project> projects = projectRepository.findAll();
        projectPBList.add(new ProjectPB(0, new Project("Unassigned"), new TaskTB(0,
                taskRepository.findByProjectIdAndStatus(0,"open"),taskRepository.findByProjectIdAndStatus(0,"done")
        )));
        for (Project project: projects) {
            projectPBList.add(new ProjectPB(project.getProjectId(), project, new TaskTB(project.getProjectId(),
                    taskRepository.findByProjectIdAndStatus(project.getProjectId(),"open"),taskRepository.findByProjectIdAndStatus(project.getProjectId(),"done")
            )));
        }
        return projectPBList;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addProject(@RequestBody Map<String, String> json)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());
        Project project = new Project();
        project.setContent(json.get("content"));
        project.setTitle(json.get("title"));
        project.setUserId(user.getId());
        projectRepository.save(project);
    }


}
