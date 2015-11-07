package home.service;

import home.domain.Story;
import home.domain.StoryRepository;
import home.domain.Task;
import home.domain.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;

import static java.util.Calendar.getInstance;
import static org.junit.Assert.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    @InjectMocks TaskService taskService;
    @Mock TaskRepository taskRepository;
    @Mock StoryRepository storyRepository;

    @Test
    public void createOrUpdate_TaskIsNotNullInRepository_RunRepositorySaveOrUpdateMethod() {
        TaskCommand command = new TaskCommand();
        Task task = new Task();
        command.setName("test");
        Story testStory = new Story();
        when(taskRepository.getById(command.getId())).thenReturn(task);
        when(storyRepository.getById(command.getStoryId())).thenReturn(testStory);
        taskService.createOrUpdate(command);
        verify(taskRepository).saveOrUpdate(task);
    }


    @Test
    public void createOrUpdate_CheackArgumentForTaskRepositoryGetById(){

        TaskCommand command = new TaskCommand();
        Task task = new Task();
        command.setName("test");
        command.setId("15");
        Story testStory = new Story();

        taskService.createOrUpdate(command);

    }


    @Test
    public void createOrUpdate_InvocationTaskRepository(){
        TaskCommand command = new TaskCommand();
        command.setId("15");
        command.setName("Test");
        Story story = new Story();
        when(storyRepository.getById(command.getStoryId())).thenReturn(story);
        taskService.createOrUpdate(command);
        verify(taskRepository).getById(command.getId());
    }

    @Test
    public void createOrUpdate_IfStoryNull_RunStoryRepositoryGetDefaultStory(){
        TaskCommand command = new TaskCommand();
        command.setName("Test");
        when(storyRepository.getById(command.getStoryId())).thenReturn(null);
        when(storyRepository.getDefaultStory()).thenReturn(new Story());

        taskService.createOrUpdate(command);
        verify(storyRepository).getDefaultStory();
    }




    @Test
    public void createOrUpdate_TaskIsNullInRepository_DoNotRunRepositorySaveOrUpdateMethod() {
        TaskCommand command = new TaskCommand();
        Task task = null;
        command.setName("test");
        Story testStory = new Story();
        when(taskRepository.getById(command.getId())).thenReturn(task);
        when(storyRepository.getById(command.getStoryId())).thenReturn(testStory);
        taskService.createOrUpdate(command);
        verify(taskRepository, never()).saveOrUpdate(task);
    }

    @Test
    public void createOrUpdate_TaskIsNullInRepository_CheckTaskNameAndCommandName() {
        TaskCommand command = new TaskCommand();
        ArgumentCaptor<Task> newName = ArgumentCaptor.forClass(Task.class);
        Task task = new Task();
        command.setName("test");
        task.setName(command.getName());
        task.setCreatedAt(getInstance());
        task.setDeadline(getInstance());
        Story testStory = new Story();
        when(taskRepository.getById(command.getId())).thenReturn(task);
        when(storyRepository.getById(command.getStoryId())).thenReturn(testStory);

        taskService.createOrUpdate(command);
        verify(taskRepository).saveOrUpdate(newName.capture());

        Task capturedStory = newName.getValue();
        Assert.assertEquals(command.getName(), capturedStory.getName());

    }

    @Test
    public void testDeleteTest1() {

        Task testTask = new Task("asdsa", Calendar.getInstance());
        Story story = new Story("mystory");
        testTask.setPriority(1);
        testTask.setStory(story);
        testTask.setDeadline(Calendar.getInstance());
        String testTaskId = testTask.getId();
        when(taskRepository.getById(testTaskId)).thenReturn(testTask);
        taskService.delete(testTaskId);
        verify(taskRepository).delete(testTask);
    }


    @Test
    public void delete_TaskIdIsNotNull_RunRepositoryDeleteMethod() {
        String taskId = "blabla";
        Task task = new Task();
        when(taskRepository.getById(taskId)).thenReturn(task);
        taskService.delete(taskId);
        verify(taskRepository).delete(task);
    }

    @Test
    public void delete_TaskIsNullInRepository_DoNotRunRepositoryDeleteMethod(){
        String taskId = "Test";
        Task task = null;
        when(taskRepository.getById(taskId)).thenReturn(null);
        taskService.delete(taskId);
        verify(taskRepository, never()).delete(task);
    }

    @Test
    public void getCommand_TaskIsNotNullInRepository_RunRepositoryGetByIdMethod(){

        Task task = new Task("gfhfg",Calendar.getInstance());
        when(taskRepository.getById(task.getId())).thenReturn(task);
        Story story = new Story();
        task.setStory(story);
        TaskCommand command = new TaskCommand();
        command.setDeadline(task.getDeadline());
        command.setName(task.getName());
        command.setStoryId(task.getStory().getId());
        command.setId(task.getId());
        command.setCreatedAt(task.getCreatedAt());

        taskService.getCommand(task.getId());
        verify(taskRepository).getById(task.getId());
    }




}