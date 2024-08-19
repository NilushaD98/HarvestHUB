package com.HarvestHUB.repo;

import com.HarvestHUB.collection.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {


    Optional<User> findUserByEmailEquals(String email);
}
