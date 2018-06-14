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
    private User user; //just one user per task for now
    private boolean state = false; //done or not yet
    @OneToMany
    private List<Comment> comment = new ArrayList<>();


    @CreationTimestamp
    private Date date;

    /*TODO
     * creation date
     * expiration date
     *
     * Below smthing like fridge controll :D
     * number of products bought (that also need more complex implementation)
     * products used or make additional method for removing number of products
     * products used per day
     */
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

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
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


    public void changeState() {
        if(this.state){
            this.state = false;
        }else{
            this.state = true;
        }
    }

}
