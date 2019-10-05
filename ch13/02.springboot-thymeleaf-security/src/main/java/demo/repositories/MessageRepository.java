package demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

}
