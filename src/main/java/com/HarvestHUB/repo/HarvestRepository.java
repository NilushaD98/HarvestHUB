package com.HarvestHUB.repo;

import com.HarvestHUB.collection.Harvest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HarvestRepository extends MongoRepository<Harvest,String> {


    List<Harvest> findAllByFarmerIdEquals(String farmerId);

    List<Harvest> findAllByOrderByCreatedAtDesc();
}
