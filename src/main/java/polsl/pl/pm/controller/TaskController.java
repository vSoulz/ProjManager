package polsl.pl.pm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import polsl.pl.pm.model.Task;
import polsl.pl.pm.repository.TaskRepository;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Task showTask(@PathVariable("id") int id)
    {
        return taskRepository.findByTaskId(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Task updateTask(@RequestBody Task task, @PathVariable("id") int id)
    {
        Task upTask = taskRepository.findByTaskId(id);
        upTask.setTaskId(task.getTaskId());
        upTask.setStatus(task.getStatus());
        upTask.setBugs(task.getBugs());
        upTask.setContent(task.getContent());
        upTask.setFinished(task.isFinished());
        upTask.setSource(task.getSource());
        upTask.setUserId(task.getUserId());
        upTask.setTime(task.getTime());
        upTask.setTitle(task.getTitle());
        taskRepository.save(upTask);
        return upTask;
    }


}
