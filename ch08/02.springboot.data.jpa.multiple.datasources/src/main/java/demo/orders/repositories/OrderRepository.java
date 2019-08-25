package demo.orders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.orders.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
