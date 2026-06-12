package dev.warrior.spring.mvc.repository;

import dev.warrior.spring.mvc.model.Coffee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
    List<Coffee> findByNameInOrderById(List<String> list);
}
