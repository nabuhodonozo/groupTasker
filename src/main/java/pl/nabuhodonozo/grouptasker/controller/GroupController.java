package pl.nabuhodonozo.grouptasker.controller;

import java.security.Principal;
import java.util.*;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.nabuhodonozo.grouptasker.entity.Comment;
import pl.nabuhodonozo.grouptasker.entity.Group;
import pl.nabuhodonozo.grouptasker.entity.Task;
import pl.nabuhodonozo.grouptasker.entity.User;
import pl.nabuhodonozo.grouptasker.repository.CommentRepository;
import pl.nabuhodonozo.grouptasker.repository.GroupRepository;
import pl.nabuhodonozo.grouptasker.repository.TaskRepository;
import pl.nabuhodonozo.grouptasker.repository.UserRepository;


@Controller
@RequestMapping("/group")
public class GroupController {
	@GetMapping("/")
	public String group(Principal principal, HttpSession session, Model model) {
		User user = findUserFromSession(principal);
		model.addAttribute("groups", user.getGroup());
		model.addAttribute("group", new Group());
        return "/group/userGroups";
	}
	

	@GetMapping("{groupName}")
	public String group(Model model, @PathVariable String groupName, Principal principal) {

		if(!findUserFromSession(principal).getGroup().contains(groupRepository.findByName(groupName))) {
			return "error/accessDenied";
		}

		Group group = groupRepository.findByName(groupName);


		model.addAttribute(group);
		model.addAttribute(new Task());
		model.addAttribute(new Comment());
		model.addAttribute("groupTasks", taskRepository.findAllByGroup_Name(groupName));

		//FIXME: Probably there is one querry for all of this code
		List<User> userList = userRepository.findAll();
		List<User> usersToInvite = new ArrayList<>();
		for (User user : userList) {
			for (Group groupLookedFor : user.getGroup()) {
				int tempVar=0;
				if(!groupName.equals(groupLookedFor.getName())) {
					tempVar++;
				}
				if(tempVar==user.getGroup().size()) {
					usersToInvite.add(user);
				}
			}
			if(user.getGroup().isEmpty()) {
				usersToInvite.add(user);
			}
		}
		
		model.addAttribute("usersInGroup", userRepository.findByGroup_Name(groupName));
		model.addAttribute("userList", usersToInvite); //FIXME: only users not present already in group
		return "/group/group";
	}
	
	
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	TaskRepository taskRepository;
	
	@PostMapping("{groupName}/{taskId}")
	public String group(@Valid Comment comment, @PathVariable String groupName, @PathVariable Long taskId, Principal principal) {
		comment.setUser(findUserFromSession(principal));
		commentRepository.save(comment);
		Task task = taskRepository.findById(taskId).orElse(null);
		comment.setTask(task);
		commentRepository.save(comment);
		
		
		return "redirect:/group/"+groupName;
	}
	
	@Autowired
	UserRepository userRepository; //temp to delete later
	
	@PostMapping("{groupName}") //have to be changed later
	@Transactional
	public String addTask(@PathVariable String groupName, @Valid Task task, Principal principal) {
		User user =  findUserFromSession(principal);
		task.setUser(user); //FIXME user from session
		Group group = groupRepository.findByName(groupName);
		task.setGroup(group);
		taskRepository.save(task);
		return "redirect:/group/"+groupName;
	}

	
	@Autowired
	GroupRepository groupRepository;
	@PostMapping("/")
	public String add(@Valid Group group, BindingResult result, Principal principal) {
		if(result.hasErrors()) {
			return "/group/userGroups";
		}
		if(groupRepository.findByName(group.getName())==null){
			User user = findUserFromSession(principal);
			user.addGroup(group);
			groupRepository.save(group);
			userRepository.save(user);
			return "/auth/index";
		}else {
			result.rejectValue("name", "error.GroupAlreadyExist", "Group already exist");
			return "/group/userGroups";
		}
	}
	
	
	@PostMapping("{groupName}/addUser")
	public String addUser(@PathVariable String groupName, @RequestParam String user_name ) {
		User foundUser = userRepository.findByLogin(user_name).orElse(null);
		foundUser.addGroup(groupRepository.findByName(groupName));
		userRepository.save(foundUser);
		return "redirect:/group/"+groupName;
	}
	
	
	@PostMapping("{groupName}/delTask")
	public String delTask(@PathVariable String groupName, @RequestParam Long taskId ) {
		taskRepository.deleteById(taskId);
		return "redirect:/group/"+groupName;
	}


	@PostMapping("{groupName}/switchState")
	public String changeTaskState(@PathVariable String groupName, @RequestParam Long taskId ) {
		Task task = taskRepository.findById(taskId).orElse(null);
		task.toggleCompleted();
		taskRepository.save(task);
		return "redirect:/group/"+groupName;
	}


	@PostMapping("{groupName}/userTasks")
	public String userTasks(@PathVariable String groupName, @RequestParam String user_name, Model model ) {
		model.addAttribute("tasks",taskRepository.findAllByUser_LoginAndGroup_Name(user_name, groupName));
		return "/group/userTasks";
	}


	@GetMapping("/mytasks")
	public String mytasks(Model model, Principal principal ) {
		User user = findUserFromSession(principal);
		model.addAttribute("tasks",taskRepository.findAllByUser_Login(user.getLogin()));
		return "/group/userTasks";
	}
	
	public User findUserFromSession(Principal principal) {
		return userRepository.findByLogin(principal.getName()).orElse(null);
	}	
}