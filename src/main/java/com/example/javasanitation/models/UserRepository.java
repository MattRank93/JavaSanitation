package com.example.javasanitation.models;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

//@Repository
public interface UserRepository extends MongoRepository<User, String> {


    @Query("{name:'?0'}")
    User findItemByName(String name);


    @Query("{id:'?0'}")
    User findOneById(UUID id);


    @Query("{username:'?0'}")
    User findOneByName(String username);



    @Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    List<User> findAll(String category);

    public long count();

}