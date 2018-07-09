package polsl.pl.pm.controller;


        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.security.core.Authentication;
        import org.springframework.security.core.context.SecurityContextHolder;
        import org.springframework.web.bind.annotation.*;
        import polsl.pl.pm.model.Project;
        import polsl.pl.pm.model.User;
        import polsl.pl.pm.model.Task;
        import polsl.pl.pm.model.additional.ProjectPB;
        import polsl.pl.pm.model.additional.TaskPB;
        import polsl.pl.pm.model.additional.TaskTB;
        import polsl.pl.pm.repository.ProjectRepository;
        import polsl.pl.pm.repository.TaskRepository;
        import polsl.pl.pm.repository.UserRepository;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/backlog")
public class BackLogController {


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
//        projectPBList.add(new ProjectPB(0, new Project("Unassigned"), new TaskPB(
//                0, taskRepository.findByStatusAndProject("open", 0),taskRepository.findByStatusAndProject("done", 0)
//        )));
        for (Project project: projects) {
            projectPBList.add(new ProjectPB(project.getProjectId(), project, new TaskPB(
                    project.getProjectId(), taskRepository.findByStatusAndProject("open", project.getProjectId()),taskRepository.findByStatusAndProject("done",project.getProjectId())
            )));
        }
        return projectPBList;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ProjectPB GetProject(@PathVariable("id") int id)
    {
        System.out.println("\n\nBACKLOG\n\n");
        String bugs = new String();
        TaskPB taskPB = new TaskPB(id,
                taskRepository.findByStatusAndProject("open", id),taskRepository.findByStatusAndProject("done",id)
        );
        for(Task tasks: taskPB.getOpen())
        {
            bugs += tasks.getTitle() +" bugs: " + tasks.getBugs()+'\n';
        }
        for(Task tasks: taskPB.getClose())
        {
            bugs += tasks.getTitle() +" bugs: " + tasks.getBugs()+'\n';
        }
        ProjectPB projectPB = new ProjectPB();
        projectPB.setProjectId(id);
        projectPB.setBugs(bugs);
        projectPB.setTaskPB(taskPB);
        projectPB.setProject(projectRepository.findOne(id));
        return projectPB;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addProject(@RequestBody Map<String, String> json)
    {
        Project project = new Project();
        project.setContent(json.get("content"));
        project.setTitle(json.get("title"));
        projectRepository.save(project);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateProject(@PathVariable("id") int id, @RequestBody Map<String, String> json)
    {
        Project project = projectRepository.findOne(id);
        project.setContent(json.get("content"));
        project.setTitle(json.get("title"));
    }


    @RequestMapping(method = RequestMethod.PUT)
    public void UpdateTask(@RequestBody Map<String, String> json)
    {
        Task upTask = new Task();
        Task task = taskRepository.findByTaskId(Integer.parseInt(json.get("taskId")));
        if(task!=null)
        {
            upTask.setUserId(Integer.parseInt(json.get("userId")));
            upTask.setProject(Integer.parseInt(json.get("newProjectId")));
            upTask.setStatus(json.get("newStatus"));
            upTask.setContent(task.getContent());
            upTask.setFinished(false);
            upTask.setTitle(task.getTitle());
            upTask.setSource(task.getSource());
            upTask.setBugs(task.getBugs());
            upTask.setTime(task.getTime());
            upTask.setTaskId(task.getTaskId());
            taskRepository.save(upTask);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteProject(@PathVariable("id") int id)
    {
        Project project = projectRepository.findOne(id);
        List<Task> openTasks = taskRepository.findByStatusAndProject("open", id);
        List<Task> doneTasks = taskRepository.findByStatusAndProject("done", id);
        for(Task task: openTasks)
        {
            task.setProject(0);
            taskRepository.save(task);
        }
        for(Task task: doneTasks)
        {
            task.setProject(0);
            taskRepository.save(task);
        }
        projectRepository.delete(project);
    }

}
