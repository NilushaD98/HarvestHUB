package com.HarvestHUB.repo;

import com.HarvestHUB.collection.Harvest;
import com.HarvestHUB.enums.AvailableStatus;

import java.util.List;

public interface HarvestRepositoryCustom {
    List<Harvest> searchHarvests(String type, String location, Double minPrice, Double maxPrice, AvailableStatus availableStatus);
}
