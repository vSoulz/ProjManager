package polsl.pl.pm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import polsl.pl.pm.model.Task;
import polsl.pl.pm.model.User;
import polsl.pl.pm.model.additional.TaskTB;
import polsl.pl.pm.model.additional.UserTB;
import polsl.pl.pm.repository.TaskRepository;
import polsl.pl.pm.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="/taskboard")
public class TaskBoardController {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;


    @RequestMapping(method = RequestMethod.GET)
    public List<UserTB> TaskBoardWeb() {
        List<UserTB> userTBList = new ArrayList<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());
        UserTB userTaskBoard = new UserTB(user.getId(), user.getUsername(), new TaskTB(user.getId(),
                taskRepository.findByUserIdAndStatus(user.getId(),"open"), taskRepository.findByUserIdAndStatus(user.getId(),"done")));
        userTBList.add(userTaskBoard);
        return userTBList;
    }




    @RequestMapping(method = RequestMethod.POST)
    public void TaskAdd(@RequestBody Task task)
    {
        taskRepository.save(task);
    }




    @RequestMapping(method = RequestMethod.PUT)
    public void UpdateTask(@RequestBody Map<String, String> json)
    {
        System.out.println("\n\nTAKSBOARD\n\n");
        Task upTask = new Task();
        Task task = taskRepository.findOne(Integer.parseInt(json.get("taskId")));
        System.out.println("\n\nID ="+json.get("newUserId"));
        if(task!=null)
        {
            upTask.setUserId(Integer.parseInt(json.get("newUserId")));
            System.out.println("Project Id="+json.get("projectId"));
            upTask.setProject(Integer.parseInt(json.get("projectId")));
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

    @RequestMapping(method = RequestMethod.DELETE)
    public void DeleteTask(@RequestBody Map<String, String> json)
    {
        taskRepository.delete(Integer.parseInt(json.get("taskId")));
    }

}
