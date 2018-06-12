package pl.nabuhodonozo.grouptasker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.nabuhodonozo.grouptasker.entity.Group;
import pl.nabuhodonozo.grouptasker.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByLogin(String login);
	User findByEmail(String email);
	List<User> findByGroup_Name(String name);
}
