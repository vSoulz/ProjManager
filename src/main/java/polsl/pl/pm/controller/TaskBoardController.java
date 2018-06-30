package polsl.pl.pm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import polsl.pl.pm.model.Task;
import polsl.pl.pm.model.User;
import polsl.pl.pm.repository.TaskRepository;
import polsl.pl.pm.repository.UserRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


@RestController
public class TaskBoardController {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;


    //{ "userId": 1, "userName": "bill", "tasks": [ { "userId": 1, "open": [ { "taskName": "create good code", "taskId": 1 },
    // { "taskName": "do something", "taskId": 4 } ], "planed": [ { "taskName": "test", "taskId": 2 } ],
    // "inProgress": [ { "taskName": "test2", "taskId": 3 } ], "inTesting": [ { "taskName": "Testowane", "taskId": 5 } ],
    // "done": [ { "taskName": "Ukonczone", "taskId": 6 } ] } ] }

    @RequestMapping(value="/taskboard", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:4200")
    public String TaskBoardWeb(){
        String req = new String();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());
        List<Task> tasks = new ArrayList<>();
        req += user.toTaskString();
        //tasks.add(new Task("Projekt","Projekt na pp",false, "open", user));
        // tasks.add(new Task("Projekt2","Projekt na hd",false, "done", user));
        tasks = taskRepository.findByUserAndStatus(user,"open");
        if(tasks.size()>0) {
            req += ", \"open\":[";
            for (Task tsk : tasks
                    ) {
                req += tsk.toTaskString();
                if (tsk.getTaskId() != tasks.get(tasks.size() - 1).getTaskId()) req += ',';
            }
        req += "]";
        }
        tasks.clear();
        tasks = taskRepository.findByUserAndStatus(user,"done");
        if(tasks.size()>0) {
            req += ", \"done\":[";
            for (Task tsk : tasks
                    ) {
                req += tsk.toTaskString();
                if (tsk.getTaskId() != tasks.get(tasks.size() - 1).getTaskId()) req += ',';
            }
            req += "]";
        }
        req+=" } ] }\0";
        //taskRepository.save(tasks);
        //userRepository.save(user);
        return req;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String TaskBoardMove(@RequestBody String redirect){
        return "localhost:8080/" + redirect;
    }
/*
    @RequestMapping(method = RequestMethod.POST)
    public void TaskBoardAdd(@RequestBody Task task, String mail){
        task.setUser(userRepository.findByEmail(mail));
        taskRepository.save(task);
    }
*/


}
