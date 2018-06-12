package pl.nabuhodonozo.grouptasker.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	@Column(unique = true)
	@Length(min=3, max=12)
	private String login;
	@NotBlank
	@Length(min=8, max=20)
	private String password;
	@Email
	@NotBlank
	@Column(unique = true)
	private String email;
	@ManyToMany(cascade = CascadeType.MERGE) // Casdade type this one needs change cuz I ll never create user with task already just for testing purpose
	private List<Group> group = new ArrayList<>();

	public void setPassword(String password) {
		this.password = password;
	}

	public void hashPassword() {
		this.password = BCrypt.hashpw(this.password, BCrypt.gensalt(12));
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	} 

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<Group> getGroup() {
		return group;
	}

	public void setGroup(List<Group> group) {
		this.group = group;
	}
	
	public void addGroup(Group group) {
		this.group.add(group);
	}

	
	
	@Override
	public String toString() {
		return String.format("User [id=%s, login=%s, password=%s, email=%s, group=%s]", id, login, password, email,
				group);
	}
}
