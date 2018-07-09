package polsl.pl.pm.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import polsl.pl.pm.model.Task;
import polsl.pl.pm.model.User;
import polsl.pl.pm.model.UserDto;
import polsl.pl.pm.repository.TaskRepository;
import polsl.pl.pm.repository.UserRepository;
import polsl.pl.pm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<User> listUser(){
        return userService.findAll();
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User getOne(@PathVariable(value = "id") Long id){
        return userService.findById(id);
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public User saveUser(@RequestBody UserDto user){
        return userService.save(user);
    }

    @RequestMapping(value="/user", method = RequestMethod.GET)
    public User sendUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());
        return user;
    }

    @RequestMapping(value="/user", method = RequestMethod.PUT)
    public void updateUser(@RequestBody Map<String, String> json)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());
        user.setEmail(json.get("email"));
        user.setLastName(json.get("lastname"));
        userRepository.save(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteProject(@PathVariable("id") int id)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());
        List<Task> openTasks = taskRepository.findByUserIdAndStatus(user.getId(), "open");
        List<Task> doneTasks = taskRepository.findByUserIdAndStatus(user.getId(),"done");
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
        userRepository.delete(user);
    }

}
