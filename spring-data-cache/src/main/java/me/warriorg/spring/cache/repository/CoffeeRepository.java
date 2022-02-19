package me.warriorg.spring.cache.repository;

import me.warriorg.spring.cache.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
