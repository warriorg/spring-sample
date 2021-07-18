package dev.warrior.spring.mvc.repository;

import dev.warrior.spring.mvc.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
