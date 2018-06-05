package pl.nabuhodonozo.grouptasker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.nabuhodonozo.grouptasker.entity.User;

public interface TaskRepository extends JpaRepository<User, Long>{
}
