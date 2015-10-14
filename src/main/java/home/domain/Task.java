package home.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Calendar;
import java.util.UUID;

import static home.utils.assertions.Assert.assertNotBlank;
import static home.utils.assertions.Assert.assertNotNull;

@Entity
@Table(name = "task")
public class Task {

    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private Calendar createdAt;
    private Calendar deadline;

    @ManyToOne
    private Story story;
    private int priority;

    public Task() {}

    public Task(String name, Calendar createdAt) {
        assertNotBlank(name, "name", this.getClass());
        assertNotNull(createdAt, "createdAt", this.getClass());
        this.name = name;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        assertNotNull(story, "story", this.getClass());
        this.story = story;
        story.addTask(this);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }

        Task anotherTask = (Task) object;

        return this.name.equals(anotherTask.getName()) &&
               this.createdAt.equals(anotherTask.getCreatedAt()) &&
               this.story.equals(anotherTask.getStory());
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Calendar getDeadline() {
        return deadline;
    }

    public void setDeadline(Calendar deadline) {
        this.deadline = deadline;
    }

    public void setName(String name) {
        assertNotBlank(name, "name", Task.class);
        this.name = name;
    }

    public void setCreatedAt(Calendar createdAt) {
        assertNotNull(createdAt, "createdAt", Task.class);
        this.createdAt = createdAt;
    }
}
