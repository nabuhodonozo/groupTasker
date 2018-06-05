package pl.nabuhodonozo.grouptasker.entity;

import java.util.List;

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
	private String name;
	@OneToMany
	private List<Task> task;
	
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

	@Override
	public String toString() {
		return String.format("Group [id=%s, name=%s, task=%s]", id, name, task);
	}
	
}
