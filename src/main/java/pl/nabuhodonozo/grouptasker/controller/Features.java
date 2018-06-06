package pl.nabuhodonozo.grouptasker.controller;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.nabuhodonozo.grouptasker.entity.Group;
import pl.nabuhodonozo.grouptasker.entity.Task;
import pl.nabuhodonozo.grouptasker.entity.User;
import pl.nabuhodonozo.grouptasker.repository.GroupRepository;
import pl.nabuhodonozo.grouptasker.repository.UserRepository;


@Controller
@RequestMapping("/app/group")
public class Features {
	@GetMapping("/")
	public String group(Model model, HttpSession session) {
		Long id = Long.parseLong(session.getAttribute("user_id").toString());
		User user = userRepository.findOne(id);
		model.addAttribute("groups", user.getGroup());
		return "/app/group/userGroups";
		//TODO: if doesnt exist ask if make one?
	}
	 
	
	//needs filter and aka Admin management system to allow user to join group and admin acceptance 
	@GetMapping("manage/{groupName}") //have to be changed later
	@Transactional// this single line didnt make it work 
	public String group(Model model, @PathVariable String groupName) {
		Group group = groupRepository.findGroupByName(groupName);
		model.addAttribute(group);
		model.addAttribute(new Task());
		return "/app/group/group";
		//TODO: if doesnt exist ask if make one?
	}
	
	@Autowired
	UserRepository userRepository; //temp to delete later
	
	@PostMapping("manage/{groupName}") //have to be changed later
	@Transactional
	public String addTask(@PathVariable String groupName, @Valid Task task, HttpSession session) {
		Long id = Long.parseLong(session.getAttribute("user_id").toString());
		User user = userRepository.findOne(id);
		task.setUser(user); //FIXME user from session
		Group group = groupRepository.findGroupByName(groupName);
		group.addTask(task);
		groupRepository.save(group);
		return "redirect:/app/group/manage/"+groupName;
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute(new Group());
		return "/app/group/add";
	}
	
	@Autowired
	GroupRepository groupRepository;
	@PostMapping("/add")
	public String add(@Valid Group group, BindingResult result) {
		if(result.hasErrors()) {
			return "/app/group/add";
		}
		User user = findUserFromSession();
		user.addGroup(group);
		groupRepository.save(group);
		userRepository.save(user);
		return "/auth/index"; 
	}
	
	
	//test
	
	@Autowired
	HttpSession session;
	public User findUserFromSession() {
		Long id = Long.parseLong(session.getAttribute("user_id").toString());
		User user = userRepository.findOne(id); 
		return user;
	}	
}