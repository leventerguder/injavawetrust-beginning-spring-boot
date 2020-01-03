package demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
