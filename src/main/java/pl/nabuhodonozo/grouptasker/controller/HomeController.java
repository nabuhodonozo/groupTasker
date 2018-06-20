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
		return "auth/index";
	}
}
