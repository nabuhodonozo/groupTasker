package pl.nabuhodonozo.grouptasker.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "\"group\"") //dunno if it's safe, but mysql wont allow to make it regular way
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	@Column(unique=true)
	private String name; //TODO: make names unique or give them number...
	@OneToMany(cascade = CascadeType.ALL)
	private List<Task> tasks = new ArrayList<>();
	//public or private group ??? // future note
	//group admins // future note
	
	public Group() {}
	
	public Group(String name) {
		this.name = name;
	}
 	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void addTask(Task task) {
		this.tasks.add(task);
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		return String.format("Group [id=%s, name=%s, tasks=%s]", id, name, tasks);
	}
	
	
}
