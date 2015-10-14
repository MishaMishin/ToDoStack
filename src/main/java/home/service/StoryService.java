package home.service;

import home.domain.Story;
import home.domain.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoryService {

    @Autowired private StoryRepository storyRepository;

    public void createOrUpdate(StoryCommand command) {
        Story story = storyRepository.getById(command.getId());
        if (story == null) {
            story = new Story();
        }
        story.setName(command.getName());
        storyRepository.saveOrUpdate(story);
    }
}
