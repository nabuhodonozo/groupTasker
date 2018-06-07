package pl.nabuhodonozo.grouptasker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.nabuhodonozo.grouptasker.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	
}
