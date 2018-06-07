package pl.nabuhodonozo.grouptasker.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import pl.nabuhodonozo.grouptasker.entity.Group;
import pl.nabuhodonozo.grouptasker.entity.Task;

public interface GroupRepository extends JpaRepository<Group, Long>{
	Group findByName(String name);
	Group findByTasks_Id(Long id);
}
