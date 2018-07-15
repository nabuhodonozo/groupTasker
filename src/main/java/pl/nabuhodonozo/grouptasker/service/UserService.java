package pl.nabuhodonozo.grouptasker.service;

import org.springframework.beans.factory.annotation.Autowired;
import pl.nabuhodonozo.grouptasker.entity.User;
import pl.nabuhodonozo.grouptasker.repository.UserRepository;

public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

        public User findByEmail(String email) {
            return userRepository.findByEmail(email).orElse(null);
        }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
