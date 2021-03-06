package pl.nabuhodonozo.grouptasker.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank
	@Column(unique = true)
	@Length(min=3, max=20)
	private String login;

	@NotBlank
	@Length(max=60) //FIXME 60 is required for hashed password, but I cant validate it now.
	private String password;

	@Email
	@Column(unique = true)
	private String email;

	private boolean enabled = true;

	@ManyToMany(cascade = CascadeType.ALL)
	private Collection<Role> roles = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.MERGE)
	private List<Group> group = new ArrayList<>();

	public User() {
	}

	public User(User user){

	}


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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, login=%s, password=%s, email=%s, group=%s]", id, login, password, email,
				group);
	}
}
