package pl.nabuhodonozo.grouptasker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.nabuhodonozo.grouptasker.entity.Comment;
import pl.nabuhodonozo.grouptasker.entity.Task;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>{
}
