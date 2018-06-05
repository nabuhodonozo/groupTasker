package pl.nabuhodonozo.grouptasker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	@Column(unique = true)
	@Length(min=4, max=12)
	private String login;
	@NotBlank
	@Length(min=8, max=20)
	private String password;
	@Email
	@NotBlank
	@Column(unique = true)
	private String email;
//	@OneToMany
//	private List<Group> group = new ArrayList<>();

	public void setPassword(String password) {
		this.password = password;
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

//	public List<Group> getGroup() {
//		return group;
//	}
//
//	public void setGroup(List<Group> group) {
//		this.group = group;
//	}
}
