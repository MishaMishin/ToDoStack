package home.ui;

import home.domain.Story;
import home.domain.StoryRepository;
import home.domain.Task;
import home.domain.TaskRepository;
import home.service.StoryCommand;
import home.service.StoryService;
import home.service.TaskCommand;
import home.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class TaskController {

    static final String TASKS_VIEW = "/tasks";
    static final String EDIT_TASK_VIEW = "/editTask";
    static final String STORY_VIEW = "/story";
    static final String REDIRECT_TO = "redirect:";
    static final String EDIT_STORY_VIEW = "/editStory";

    @Autowired private TaskRepository taskRepository;
    @Autowired private TaskService taskService;
    @Autowired private StoryRepository storyRepository;
    @Autowired private StoryService storyService;

    @RequestMapping(value = "/tasks", method = GET)
    public String showTaskList(Model model) {
        List<Task> tasks = taskRepository.getAll();
        sortByCreationDate(tasks);
        model.addAttribute("tasks", tasks);
        return TASKS_VIEW;
    }

    private void sortByCreationDate(List<Task> tasks) {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task task, Task anotherTask) {
                return anotherTask.getCreatedAt().compareTo(task.getCreatedAt());
            }
        });
    }

    @RequestMapping(value = "/tasks/sort", method = GET)
    public String sortTasks(@RequestParam(value = "sortParam") String sortParam,
                            @RequestParam(value = "sortOrder") String sortOrder,
                            Model model) {
        List<Task> tasks = taskRepository.getAll();
        sortByDeadline(tasks, sortOrder);
        // TODO: implement sort by 'createdAt'
        model.addAttribute("tasks", tasks);
        return TASKS_VIEW;
    }

    private void sortByDeadline(List<Task> tasks, String sortOrder) {
        final int sortOrderRate = sortOrder.equals("asc") ? 1 : -1;
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task task, Task anotherTask) {
                return sortOrderRate * task.getDeadline().compareTo(anotherTask.getDeadline());
            }
        });
    }

    @RequestMapping(value = "/story/{storyId}", method = GET)
    public String showStory(@PathVariable("storyId") String storyId, Model model) {
        Story story = storyRepository.getById(storyId);
        model.addAttribute("story", story);
        return STORY_VIEW;
    }

    @RequestMapping(value = "/story/create", method = GET)
    public String createStory(Model model) {
        model.addAttribute("storyCommand", new StoryCommand());
        return EDIT_STORY_VIEW;
    }

    @RequestMapping(value = "/story/save", method = POST)
    public String saveStory(@ModelAttribute("storyCommand") StoryCommand command) {
        storyService.createOrUpdate(command);
        return REDIRECT_TO + TASKS_VIEW;
    }

    @RequestMapping(value = "/task/create", method = GET)
    public String createTask(Model model,
                             @RequestParam(value = "storyId", required = false) String storyId) {
        TaskCommand taskCommand = new TaskCommand();
        taskCommand.setStoryId(storyId);
        model.addAttribute("taskCommand", taskCommand);
        model.addAttribute("stories", storyRepository.getAll());
        return EDIT_TASK_VIEW;
    }

    @RequestMapping(value = "/task/{taskId}/edit", method = GET)
    public String editTask(@PathVariable("taskId") String taskId,
                           Model model) {

        model.addAttribute("taskCommand", taskService.getCommand(taskId));
        model.addAttribute("stories", storyRepository.getAll());
        return EDIT_TASK_VIEW;
    }

    @RequestMapping(value = "/task/{taskId}/detach", method = GET)
    public String detachTask(@PathVariable("taskId") String taskId) {
        taskService.detachFromCurrentStory(taskId);
        return REDIRECT_TO + TASKS_VIEW;
    }

    @RequestMapping(value = "/task/save", method = POST)
    public String saveTask(@ModelAttribute("taskCommand") TaskCommand command) {
        taskService.createOrUpdate(command);
        return REDIRECT_TO + TASKS_VIEW;
    }

    @RequestMapping(value = "/task/{taskId}/delete", method = GET)
    public String deleteTask(@PathVariable("taskId") String taskId) {
        taskService.delete(taskId);
        return REDIRECT_TO + TASKS_VIEW;
    }

}
