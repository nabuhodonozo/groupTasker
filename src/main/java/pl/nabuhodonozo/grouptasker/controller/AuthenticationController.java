package pl.nabuhodonozo.grouptasker.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.nabuhodonozo.grouptasker.entity.User;
import pl.nabuhodonozo.grouptasker.model.UserLoginData;
import pl.nabuhodonozo.grouptasker.repository.UserRepository;


@Controller
@RequestMapping("/auth")
public class AuthenticationController {

	@GetMapping("/login")
	public String allowLogin(Model model) {
		model.addAttribute(new UserLoginData());
		return "/auth/login";
	}

	@Autowired
	UserRepository userRepository;

	
	@PostMapping("/login")
	public String procesLogin(@ModelAttribute UserLoginData userLoginData, HttpSession session, BindingResult result) {
		User user = userRepository.findByLogin(userLoginData.getLogin());
		if (user == null) {
			result.rejectValue("login", "error.UserDoesntExist", "User doesnt exist");
			return "/auth/login";
		} else if (BCrypt.checkpw(userLoginData.getPassword(), user.getPassword())) {
			session.setAttribute("user_id", user.getId());
			session.setAttribute("user_name", user.getLogin());
			return "/auth/index";
		} else {
			result.rejectValue("password", "error.WrongPassword", "WrongPassword");
			return "/auth/login";
		}
	}

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
		userRepository.save(user);	
		return "/auth/index";
	}
}
