package demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer>
{

}
