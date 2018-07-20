package pl.nabuhodonozo.grouptasker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.nabuhodonozo.grouptasker.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByLogin(String login);
	Optional<User> findByEmail(String email);
	List<User> findByGroup_Name(String name);
	User findByConfirmationToken(String confirmationToken);
}
