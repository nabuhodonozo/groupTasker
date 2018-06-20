package pl.nabuhodonozo.grouptasker.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pl.nabuhodonozo.grouptasker.entity.User;
import pl.nabuhodonozo.grouptasker.model.UserLoginData;
import pl.nabuhodonozo.grouptasker.repository.UserRepository;


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
		if(userRepository.findByLogin(user.getLogin()) != null){
			result.rejectValue("login", "error.userAlreadyExist", "This login is already used");
			return "/auth/register";
		}else if(userRepository.findByEmail(user.getEmail()) != null){
			result.rejectValue("email", "error.emailAlreadyExist", "This email is already used");
			return "/auth/register";
		}
		user.hashPassword();
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
