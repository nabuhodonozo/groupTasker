package pl.nabuhodonozo.grouptasker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.nabuhodonozo.grouptasker.entity.Role;
import pl.nabuhodonozo.grouptasker.entity.User;
import pl.nabuhodonozo.grouptasker.model.UserDto;
import pl.nabuhodonozo.grouptasker.repository.RoleRepository;
import pl.nabuhodonozo.grouptasker.repository.UserRepository;
import pl.nabuhodonozo.grouptasker.service.EmailService;
import pl.nabuhodonozo.grouptasker.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.UUID;

@Controller
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/login")
    public String allowLogin(Model model) {
        model.addAttribute(new UserDto());
        return "/auth/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "/auth/login?logout";
    }

    @GetMapping("/register")
    public String allowRegister(Model model) {
        model.addAttribute(new User());
        return "/auth/register";
    }

    @Autowired
    UserService userService;
    @Autowired
    EmailService emailService;


    @PostMapping("/register")
    public String procesRegister(@Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            return "/auth/register";
        }

        if (userRepository.findByLogin(user.getLogin()).orElse(null) != null) {
            result.rejectValue("login", "error.userAlreadyExist", "This login is already used");
            return "/auth/register";
        } else if (userRepository.findByEmail(user.getEmail()).orElse(null) != null) {
            result.rejectValue("email", "error.emailAlreadyExist", "This email is already used");
            return "/auth/register";
        }

        if (user.getPassword().length() < 12) {
            result.rejectValue("password", "error.passwordToShort", "Password is too short. Needs to be at least 12");
            return "/auth/register";
        }else{
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
        }

        Role role = roleRepository.findByName("USER").orElse(null);
        if (role != null) {
            user.addRole(role);
        } else {
            role = new Role();
            role.setName("USER");
        }



        // Generate random 36-character string token for confirmation link
        user.setConfirmationToken(UUID.randomUUID().toString());

        userService.saveUser(user);


        SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo(user.getEmail());
        registrationEmail.setSubject("Registration Confirmation");
        registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
                 + "/confirm?token=" + user.getConfirmationToken());
        registrationEmail.setFrom("noreply@domain.com");

        emailService.sendEmail(registrationEmail);



        user.addRole(role);
        userRepository.save(user);

        return "/auth/index";
    }
}
