package pl.nabuhodonozo.grouptasker.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String description;
	@ManyToOne
	private User user; //just one user per task for now
	private boolean state = false; //done or not yet
	
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
	@Override
	public String toString() {
		return String.format("Task [id=%s, description=%s, state=%s]", id, description, state);
	}
	
}
