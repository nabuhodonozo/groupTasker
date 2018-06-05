package pl.nabuhodonozo.grouptasker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.nabuhodonozo.grouptasker.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Long>{
}
