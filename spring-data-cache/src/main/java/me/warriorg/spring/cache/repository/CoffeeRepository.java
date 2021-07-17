package me.warriorg.spring.redis.repository;

import me.warriorg.spring.redis.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
