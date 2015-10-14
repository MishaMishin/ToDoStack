package home.domain;

import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static home.utils.assertions.Assert.assertNotBlank;
import static home.utils.assertions.Assert.assertNotNull;

@Entity
@Table(name = "story")
public class Story {

    @Id
    private String id = UUID.randomUUID().toString();

    @OneToMany(mappedBy = "story")
    @OrderBy("createdAt DESC")
    private List<Task> tasks = new ArrayList<>();
    private String name;
    private int priority;

    public Story() {}

    public Story(String name) {
        assertNotBlank(name, "name", this.getClass());
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void addTask(Task task) {
        assertNotNull(task, "task", this.getClass());
        if (this.tasks.contains(task)) {
            throw new IllegalStateException("Story already contains given Task");
        }
        this.tasks.add(task);
    }

    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setName(String name) {
        assertNotBlank(name, "name", Story.class);
        this.name = name;
    }
}
