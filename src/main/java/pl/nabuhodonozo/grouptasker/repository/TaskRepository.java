package pl.nabuhodonozo.grouptasker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.nabuhodonozo.grouptasker.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	List<Task> findAllByUser_Login(String login);
//	List<Task> findAllByUser_LoginAndUser_Group_Name(String login, String name); doesnt work
}
