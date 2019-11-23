package demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
}
