package com.HarvestHUB.repo;

import com.HarvestHUB.collection.Harvest;
import com.HarvestHUB.enums.AvailableStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HarvestRepository extends MongoRepository<Harvest,String> ,HarvestRepositoryCustom{


    List<Harvest> findAllByFarmerIdEquals(String farmerId);

    List<Harvest> findAllByOrderByCreatedAtDesc();

    Optional<Harvest> findByHarvestIdEqualsAndAvailabilityEquals(String harvestID, AvailableStatus availableStatus);
}
