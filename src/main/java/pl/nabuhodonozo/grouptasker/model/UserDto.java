package pl.nabuhodonozo.grouptasker.model;

import org.hibernate.validator.constraints.Length;

public class UserDto {
    @Length(min = 3, max = 20)
	private String login;
    @Length(min=3,max=20)
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
