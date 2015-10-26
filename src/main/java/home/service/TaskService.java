package home.service;

import home.domain.Story;
import home.domain.StoryRepository;
import home.domain.Task;
import home.domain.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

import static java.util.Calendar.getInstance;

@Service
public class TaskService {

    @Autowired private TaskRepository taskRepository;
    @Autowired private StoryRepository storyRepository;
    //TODO WRITE TEST FOR THIS METHOD
    public void createOrUpdate(TaskCommand command) {
        Task task = taskRepository.getById(command.getId());
        if (task == null) {
            task = new Task();
        }
        task.setName(command.getName());
        task.setCreatedAt(getInstance());
        task.setDeadline(getInstance());
        Story story = storyRepository.getById(command.getStoryId());
        if (story == null) {
            story = storyRepository.getDefaultStory();
        }
        task.setStory(story);
        taskRepository.saveOrUpdate(task);
    }

    public TaskCommand getCommand(String taskId) {
        Task task = taskRepository.getById(taskId);
        TaskCommand command = new TaskCommand();
        command.setDeadline(task.getDeadline());
        command.setName(task.getName());
        command.setStoryId(task.getStory().getId());
        command.setId(taskId);
        command.setCreatedAt(task.getCreatedAt());
        return command;
    }

    public void delete(String taskId) {
        Task task = taskRepository.getById(taskId);
        if(task != null){
            taskRepository.delete(task);
        }

    }

    public void detachFromCurrentStory(String taskId) {
        Task task = taskRepository.getById(taskId);
        Story defaultStory = storyRepository.getDefaultStory();
        task.setStory(defaultStory);
        taskRepository.saveOrUpdate(task);
    }
}
