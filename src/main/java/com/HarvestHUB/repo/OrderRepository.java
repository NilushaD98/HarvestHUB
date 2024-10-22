package com.HarvestHUB.repo;

import com.HarvestHUB.collection.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Order,String> {
    Optional<Order> findByHarvestIDEquals(String harvestID);

    List<Order> findByWholeSellerIDEquals(String wholeSellerID);
    List<Order> findByFarmerIDEquals(String farmerID);
}
