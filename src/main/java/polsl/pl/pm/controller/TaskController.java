package polsl.pl.pm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import polsl.pl.pm.model.Task;
import polsl.pl.pm.repository.TaskRepository;

import java.util.Map;

@RestController
@RequestMapping(value = "/task")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Task showTask(@PathVariable("id") int id)
    {
        return taskRepository.findByTaskId(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void TaskAdd(@RequestBody Map<String, String> json)
    {
        Task upTask = new Task();
        upTask.setStatus(json.get("status"));
        upTask.setContent(json.get("content"));
        upTask.setFinished(Boolean.parseBoolean(json.get("finished")));
        upTask.setSource(json.get("source"));
        upTask.setUserId(Integer.parseInt(json.get("userId")));
        upTask.setTitle(json.get("title"));
        upTask.setProjectId(0);
        taskRepository.save(upTask);
    }

    @RequestMapping( method = RequestMethod.PUT)
    public Task updateTask(@RequestBody Map<String, String> json)
    {
        Task upTask = new Task();
        upTask.setTaskId(Integer.parseInt(json.get("taskId")));
        upTask.setStatus(json.get("status"));
        upTask.setBugs(json.get("bugs"));
        upTask.setContent(json.get("content"));
        upTask.setFinished(Boolean.parseBoolean(json.get("finished")));
        upTask.setSource(json.get("source"));
        upTask.setUserId(Integer.parseInt(json.get("userId")));
        upTask.setTime(json.get("time"));
        upTask.setTitle(json.get("title"));
        upTask.setProjectId(Integer.parseInt(json.get("projectId")));
        taskRepository.save(upTask);
        return upTask;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void DeleteTask(@RequestBody Map<String, String> json)
    {
        taskRepository.delete(Integer.parseInt(json.get("taskId")));
    }


}
