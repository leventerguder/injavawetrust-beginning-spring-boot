package demo.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.security.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
