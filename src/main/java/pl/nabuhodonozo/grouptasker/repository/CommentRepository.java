package pl.nabuhodonozo.grouptasker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.nabuhodonozo.grouptasker.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
}
