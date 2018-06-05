package pl.nabuhodonozo.grouptasker.controller;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.nabuhodonozo.grouptasker.entity.Group;
import pl.nabuhodonozo.grouptasker.entity.Task;
import pl.nabuhodonozo.grouptasker.entity.User;
import pl.nabuhodonozo.grouptasker.repository.GroupRepository;
import pl.nabuhodonozo.grouptasker.repository.UserRepository;


@Controller
public class HomeController {
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	GroupRepository groupRepository;
	@GetMapping("/i")//initialize
	@Transactional
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
		user2.addGroup(groupRepository.findGroupByName("Family"));
		user2.addGroup(new Group("School"));
		user2.addGroup(new Group("Job"));
		
		userRepository.save(user2);
		
		User user3 = new User();
		user3.setLogin("Michal");
		user3.setPassword("Michal123");
		user3.setEmail("w@w.w");
		user3.addGroup(groupRepository.findGroupByName("School"));
		 
		userRepository.save(user3);
		
		///
		
		Task task1 = new Task();
		
		task1.setDescription("Buy smthing");
		task1.setState(false);
		task1.setUser(user1);
		
		
		return "Dodano";
	}
	
	@GetMapping("/f")
	@Transactional //fix for laziness to get Task list
	@ResponseBody
	public String test() {
		return groupRepository.findGroupByName("School").toString();
	}
}
