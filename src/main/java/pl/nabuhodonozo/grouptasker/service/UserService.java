package pl.nabuhodonozo.grouptasker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.nabuhodonozo.grouptasker.entity.User;
import pl.nabuhodonozo.grouptasker.repository.UserRepository;

@Service("userService")
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

    public User findByConfirmationToken(String confirmationToken) {
        return userRepository.findByConfirmationToken(confirmationToken);
    }

}
