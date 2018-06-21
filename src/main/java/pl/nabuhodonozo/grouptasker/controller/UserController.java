package pl.nabuhodonozo.grouptasker.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.nabuhodonozo.grouptasker.entity.User;
import pl.nabuhodonozo.grouptasker.repository.UserRepository;



@Controller
public class UserController {
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/delete")
	@ResponseBody
	public String delete(HttpSession session) {
		Long id = Long.parseLong( session.getAttribute("user_id").toString());
		User entity = userRepository.findById(id).orElse(null);

		userRepository.delete(entity);
		return "usuniteto" + entity.toString();
	}
}
