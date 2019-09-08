package demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
