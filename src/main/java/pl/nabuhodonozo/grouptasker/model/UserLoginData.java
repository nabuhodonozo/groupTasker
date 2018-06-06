package pl.nabuhodonozo.grouptasker.model;

import javax.persistence.Column;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class UserLoginData {
	@NotBlank
	@Column(unique = true)
	@Length(min=4, max=12)
	private String login;
	@NotBlank
	@Length(min=8, max=20)
	private String password;
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
