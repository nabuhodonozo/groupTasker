package pl.nabuhodonozo.grouptasker.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "groups")
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	@Column(unique=true)
	private String name; //TODO: make names unique or give them number...
//	@OneToOne    			not needed now
//	private User admin;
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


//	public User getAdmin() {
//		return admin;
//	}
//
//	public void setAdmin(User admin) {
//		this.admin = admin;
//	}
}
