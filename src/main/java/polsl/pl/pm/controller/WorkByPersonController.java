package polsl.pl.pm.controller;


import org.springframework.beans.factory.annotation.Autowired;
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


@RestController
@RequestMapping(value="/workbyperson")
public class WorkByPersonController {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    //Lista taskow od uzytkownikow
    @RequestMapping(method = RequestMethod.GET)
    public List<UserTB> WorkByPersonWeb(){
        Iterable<User> users = userRepository.findAll();
        List<UserTB> userTBList = new ArrayList<>();
        userTBList.add(new UserTB(0, "Unassigned", new TaskTB( 0,
                taskRepository.findByUserIdAndStatus(0, "open"), taskRepository.findByUserIdAndStatus(0,"done"))));
        for (User user: users)
        {
            List<Task> tasks = new ArrayList<>();
            tasks.add(new Task("Projekt","Projekt na pp",false, "open", user.getId()));
            tasks.add(new Task("Projekt2","Projekt na hd",false, "done", user.getId()));
            userTBList.add(new UserTB(user.getId(), user.getUsername(), new TaskTB(user.getId(),
                    taskRepository.findByUserIdAndStatus(user.getId(),"open"), taskRepository.findByUserIdAndStatus(user.getId(),"done"))));
        }
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
        Task task = taskRepository.findByTaskId(Integer.parseInt(json.get("taskId")));
        if(task!=null)
        {
            task.setUserId(Integer.parseInt(json.get("newUserId")));
            task.setStatus(json.get("newStatus"));
            taskRepository.save(task);
        }
    }
}
