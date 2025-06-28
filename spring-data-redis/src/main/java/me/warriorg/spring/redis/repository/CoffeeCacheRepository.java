package me.warriorg.spring.redis.repository;

import java.util.Optional;
import me.warriorg.spring.redis.model.CoffeeCache;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeCacheRepository extends CrudRepository<CoffeeCache, Long> {

    Optional<CoffeeCache> findOneByName(String name);
}
