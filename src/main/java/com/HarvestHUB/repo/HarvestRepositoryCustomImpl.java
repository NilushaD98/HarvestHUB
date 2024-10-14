package com.HarvestHUB.repo;

import com.HarvestHUB.collection.Harvest;
import com.HarvestHUB.enums.AvailableStatus;
import com.mongodb.client.MongoClients;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HarvestRepositoryCustomImpl implements HarvestRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Harvest> searchHarvests(String type, String location, Double minPrice, Double maxPrice, AvailableStatus availableStatus) {
        Query query = new Query();

        Criteria criteria = new Criteria();
        List<Criteria> criteriaList = new ArrayList<>();

        if (type != null && !type.isEmpty()) {
            criteriaList.add(Criteria.where("type").is(type));
        }
        if (location != null && !location.isEmpty()) {
            criteriaList.add(Criteria.where("location").regex(location, "i"));
        }
        if(availableStatus != null && availableStatus.name()!= ""){
            criteriaList.add(Criteria.where("availability").is(availableStatus));
        }
        if (minPrice != null && maxPrice != null && minPrice !=0 && maxPrice != 0) {
            criteriaList.add(Criteria.where("price").gte(minPrice).lte(maxPrice));
        } else if (minPrice != null && minPrice !=0) {
            criteriaList.add(Criteria.where("price").gte(minPrice));
        } else if (maxPrice != null && maxPrice != 0) {
            criteriaList.add(Criteria.where("price").lte(maxPrice));
        }
        criteria.andOperator(criteriaList.toArray(new Criteria[0]));
        query.addCriteria(criteria);

        return mongoTemplate.find(query, Harvest.class);
    }
}
