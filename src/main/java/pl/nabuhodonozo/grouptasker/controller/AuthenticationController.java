package pl.nabuhodonozo.grouptasker.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pl.nabuhodonozo.grouptasker.entity.Role;
import pl.nabuhodonozo.grouptasker.entity.User;
import pl.nabuhodonozo.grouptasker.model.UserLoginData;
import pl.nabuhodonozo.grouptasker.repository.RoleRepository;
import pl.nabuhodonozo.grouptasker.repository.UserRepository;

import java.util.Collection;
import java.util.Optional;


@Controller
public class AuthenticationController {

	@GetMapping("/login")
	public String allowLogin(Model model) {
		model.addAttribute(new UserLoginData());
		return "/auth/login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "/auth/login?logout";
	}

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	@GetMapping("/register")
	public String allowRegister(Model model) {
		model.addAttribute(new User());
		return "/auth/register";
	}

	@PostMapping("/register")
	public String procesRegister(@Valid User user, BindingResult result) {
		if(result.hasErrors()){
			return "/auth/register";
		}
		if(userRepository.findByLogin(user.getLogin()).orElse(null) != null){
			result.rejectValue("login", "error.userAlreadyExist", "This login is already used");
			return "/auth/register";
		}else if(userRepository.findByEmail(user.getEmail()).orElse(null) != null){
			result.rejectValue("email", "error.emailAlreadyExist", "This email is already used");
			return "/auth/register";
		}
//		User userFromDb = userRepository.findByLogin(user.getLogin());
//
//		if(userFromDb!=null){
//			result.rejectValue("login", "error.userAlreadyExist", "This login is already used");
//			return "/auth/register";
//		}else if(userFromDb != null){
//			result.rejectValue("email", "error.emailAlreadyExist", "This email is already used");
//			return "/auth/register";
//		}
		user.hashPassword();

		Role role = roleRepository.findByName("USER").orElse(null);
		if(role!=null){
			user.addRole(role);
		}else{
			role = new Role();
			role.setName("USER");
		}
		//Caused by: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry '1' for key 'PRIMARY'
		//	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method) ~[na:1.8.0_171]
		//	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62) ~[na:1.8.0_171]
		//	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45) ~[na:1.8.0_171]
		//	at java.lang.reflect.Constructor.newInstance(Constructor.java:423) ~[na:1.8.0_171]
		user.addRole(role); //not working


		//fixme need role to fully work with spring security
		userRepository.save(user);	
		return "/auth/index";
	}


	@GetMapping("/addadmin")
	public String addadmin() {
//		User user = new User();
//		user.setLogin("tomek");
//		user.setPassword("qwertyui");
//		user.hashPassword();
//		user.setEmail("q@q.q");
//		userRepository.save(user);
//		return "/auth/register";

		User user = new User();
		user.setLogin("bob");
		user.setPassword("qwertyui");
		user.hashPassword();
		user.setEmail("w@w.w");
		userRepository.save(user);
		return "/auth/register";
	}
}
