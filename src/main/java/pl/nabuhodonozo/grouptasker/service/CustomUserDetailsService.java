package pl.nabuhodonozo.grouptasker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.nabuhodonozo.grouptasker.entity.User;
import pl.nabuhodonozo.grouptasker.model.CustomUserDetails;
import pl.nabuhodonozo.grouptasker.repository.UserRepository;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> users = userRepository.findByLogin(login);
        users.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return users.map(CustomUserDetails::new).get();
    }
}
