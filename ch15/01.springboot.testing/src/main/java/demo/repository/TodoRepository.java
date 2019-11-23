package demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.entity.Todo;


public interface TodoRepository extends JpaRepository<Todo, Integer>{

}
