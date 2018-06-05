package pl.nabuhodonozo.grouptasker.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.nabuhodonozo.grouptasker.entity.Group;
import pl.nabuhodonozo.grouptasker.entity.User;
import pl.nabuhodonozo.grouptasker.repository.UserRepository;


@Controller
public class HomeController {
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@Autowired
	UserRepository userRepository;
	@GetMapping("/i")//initialize
	@ResponseBody
	public String initialize() {
		User user1 = new User();
		user1.setLogin("Maniek");
		user1.setPassword("Maniek123");
		user1.setEmail("q@q.q");
		user1.addGroup(new Group("Family"));
		userRepository.save(user1);
		
		User user2 = new User();
		user2.setLogin("Jasiu");
		user2.setPassword("Jasiu123");
		user2.setEmail("e@e.e");
		user2.addGroup(new Group("Family"));
		user2.addGroup(new Group("School"));
		user2.addGroup(new Group("Job"));
		
		userRepository.save(user2);
		
		User user3 = new User();
		user3.setLogin("Michal");
		user3.setPassword("Michal123");
		user3.setEmail("w@w.w");
		user3.addGroup(new Group("School"));
		
		userRepository.save(user3);
		
		return "Dodano";
	}
}
