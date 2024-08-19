package com.HarvestHUB.repo;


import com.HarvestHUB.collection.Token;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends MongoRepository<Token,String> {

    @Query(value = "{'token': ?0}")
    Optional<Token> findTokensByToken(String token);

    @Query(value = "{'userEmail': ?0}")
    List<Token> findTokensByUserEmailEquals(String email);
}
