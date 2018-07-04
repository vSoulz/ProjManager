package polsl.pl.pm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import polsl.pl.pm.model.Task;
import polsl.pl.pm.model.User;
import polsl.pl.pm.model.additional.TaskTB;
import polsl.pl.pm.model.additional.UserTB;
import polsl.pl.pm.repository.TaskRepository;
import polsl.pl.pm.repository.UserRepository;

import java.lang.reflect.Array;
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


    //{ "userId": 1, "userName": "bill", "tasks": [ { "userId": 1, "open": [ { "taskName": "create good code", "taskId": 1 },
    // { "taskName": "do something", "taskId": 4 } ], "planed": [ { "taskName": "test", "taskId": 2 } ],
    // "inProgress": [ { "taskName": "test2", "taskId": 3 } ], "inTesting": [ { "taskName": "Testowane", "taskId": 5 } ],
    // "done": [ { "taskName": "Ukonczone", "taskId": 6 } ] } ] }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<UserTB> TaskBoardWeb() {
        System.out.println("przed sprawdzeniem");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Projekt","Projekt na pp",false, "open", 1, null));
        tasks.add(new Task("Projekt2","Projekt na hd",false, "done", 1,null));
        taskRepository.save(tasks);
        UserTB userTaskBoard = new UserTB(user.getId(), user.getUsername(), new TaskTB(user.getId(),
                taskRepository.findByUserIdAndStatus(user.getId(),"open"), taskRepository.findByUserIdAndStatus(user.getId(),"done")));
        System.out.println(userTaskBoard);
        return ResponseEntity.ok(userTaskBoard);
    }


    @RequestMapping(method = RequestMethod.POST)
    public void TaskAdd(@RequestBody Task task)
    {
        //System.out.println("Content="+task.getContent()+"\nTitle="+task.getTitle()+"\nStatus="+task.ge);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());
        task.setUserId(user.getId());
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

    @RequestMapping(method = RequestMethod.DELETE)
    public void DeleteTask(@RequestBody Map<String, String> json)
    {
        taskRepository.delete(Integer.parseInt(json.get("taskId")));
    }

}
