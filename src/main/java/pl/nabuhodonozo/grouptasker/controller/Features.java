package pl.nabuhodonozo.grouptasker.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.nabuhodonozo.grouptasker.entity.Group;
import pl.nabuhodonozo.grouptasker.repository.GroupRepository;


@Controller
@RequestMapping("/group")
public class Features {
	
	//needs filter and aka Admin management system to allow user to join group and admin acceptance 
	@GetMapping("magement/{GroupName}") //have to be changed later
	public String group() {
		return "group/add";
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