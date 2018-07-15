package pl.nabuhodonozo.grouptasker.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;

    @ManyToOne
    private User user;

    private boolean completed = false;

    @OneToMany(mappedBy = "task", cascade = {CascadeType.REMOVE })
    private List<Comment> comment = new ArrayList<>();

    @CreationTimestamp
    private Date date;

    @ManyToOne
    private Group group;


    public void toggleCompleted() {
        this.completed = !this.completed;
    }

    public boolean isCompleted() {
        return completed;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public void addComment(Comment comment) {
        this.comment.add(comment);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
