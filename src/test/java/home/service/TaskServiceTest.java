package home.service;

import home.domain.Story;
import home.domain.StoryRepository;
import home.domain.Task;
import home.domain.TaskRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Calendar;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class TaskServiceTest {

    @InjectMocks TaskService taskService;
    @Mock TaskRepository taskRepository;
    @Mock StoryRepository storyRepository;


}