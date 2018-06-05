package pl.nabuhodonozo.grouptasker.controller;

import javax.servlet.http.HttpServletRequest;
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
import pl.nabuhodonozo.grouptasker.repository.GroupRepository;
import pl.nabuhodonozo.grouptasker.repository.UserRepository;


@Controller
@RequestMapping("/group")
public class Features {
	
	//needs filter and aka Admin management system to allow user to join group and admin acceptance 
	@GetMapping("manage/{groupName}") //have to be changed later
	@Transactional// this single line didnt make it work 
	public String group(Model model, @PathVariable String groupName) {
//		model.addAttribute("group", groupRepository.findGroupByName(groupName));
		model.addAttribute(groupRepository.findGroupByName(groupName));
		model.addAttribute("tasks", groupRepository.findGroupByName(groupName).getTasks()); //dirty fix
		System.out.println(groupRepository.findGroupByName(groupName).getTasks()); //O.o bez tego nie dziala
		model.addAttribute(new Task());
		return "group/group";
		//TODO: if doesnt exist ask if make one?
	}
	
	@Autowired
	UserRepository ur; //temp to delete later
	
	@PostMapping("manage/{groupName}") //have to be changed later
	@ResponseBody
	@Transactional
	public String addTask(@PathVariable String groupName, @ModelAttribute Task task) {
		task.setUser(ur.findOne(1l)); //FIXME user from session
		Group group = groupRepository.findGroupByName(groupName);
		group.addTask(task);
		groupRepository.save(group);
		return "YAY it's working";
	}
	
	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute(new Group());
		return "group/add";
	}
	
	@Autowired
	GroupRepository groupRepository;
	@PostMapping("add")
	public String add(@Valid Group group, BindingResult result) {
		if(result.hasErrors()) {
			return "group/add";
		}
		groupRepository.save(group);
		return ""; //add sumthing
	}
}