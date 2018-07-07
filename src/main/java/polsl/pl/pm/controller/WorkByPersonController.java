package polsl.pl.pm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import polsl.pl.pm.model.Task;
import polsl.pl.pm.model.User;
import polsl.pl.pm.model.additional.TaskTB;
import polsl.pl.pm.model.additional.UserTB;
import polsl.pl.pm.repository.TaskRepository;
import polsl.pl.pm.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;


@RestController
public class WorkByPersonController {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    //Lista taskow od uzytkownikow
    @RequestMapping(value="/workbyperson", method = RequestMethod.GET)
    public List<UserTB> WorkByPersonWeb(){
        Iterable<User> users = userRepository.findAll();
        List<UserTB> userTBList = new ArrayList<>();
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
}
