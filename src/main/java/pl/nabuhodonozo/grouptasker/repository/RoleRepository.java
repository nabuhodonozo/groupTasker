package pl.nabuhodonozo.grouptasker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.nabuhodonozo.grouptasker.entity.Role;
import pl.nabuhodonozo.grouptasker.entity.User;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByName(String name);
}
