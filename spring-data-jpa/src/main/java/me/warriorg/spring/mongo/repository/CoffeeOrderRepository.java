package me.warriorg.spring.mongo.repository;

import java.util.List;
import me.warriorg.spring.mongo.model.CoffeeOrder;

public interface CoffeeOrderRepository extends BaseRepository<CoffeeOrder, Long> {
    List<CoffeeOrder> findByCustomerOrderById(String customer);

    List<CoffeeOrder> findByItems_Name(String name);
}
