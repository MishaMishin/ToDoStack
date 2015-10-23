package home.service;

import home.domain.Story;
import home.domain.StoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class StoryServiceTest {


    /*в этот обьект вставить моки*/
    @InjectMocks StoryService storyService;
    /*фальшивые обьекты вместо зависимостей в тестируемом классе*/
    @Mock StoryRepository storyRepository;

    @Test
    public void createOrUpdate() {
        /*это просто создали для метода createService*/
        StoryCommand storyCommand = new StoryCommand();
        /*просто задали параметры*/
        storyCommand.setName("NewStoryName");
        Story story = new Story("MyStory");
        /*это макито команды..... Заменяем вызов настоящего обьекта фальшивым*/
        when(storyRepository.getById(storyCommand.getId())).thenReturn(story);


        storyService.createOrUpdate(storyCommand);
        /*Проверяем вызывается ли метод saveOrUpdate  для обьекта StoryRepository*/
        verify(storyRepository).saveOrUpdate(story);
    }


    @Test
    public void createOrUpdateStory() {
        /*это просто создали для метода createService*/
        StoryCommand storyCommand = new StoryCommand();
        ArgumentCaptor<Story> newName = ArgumentCaptor.forClass(Story.class);

        /*просто задали параметры*/
        storyCommand.setName("NewStoryName");
        Story story = new Story("MyStory");

        /*это макито команды..... Заменяем вызов настоящего обьекта фальшивым*/
        when(storyRepository.getById(storyCommand.getId())).thenReturn(story);
        storyService.createOrUpdate(storyCommand);


        /*Проверяем вызывается ли метод saveOrUpdate  для обьекта StoryRepository*/
        verify(storyRepository).saveOrUpdate(newName.capture());

        Story capturedStory = newName.getValue();
        Assert.assertEquals(storyCommand.getName(), capturedStory.getName());
    }


}