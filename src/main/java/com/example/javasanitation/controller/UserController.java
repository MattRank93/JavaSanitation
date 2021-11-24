package com.example.javasanitation.controller;
import com.example.javasanitation.middleware.RanksSanitizers;
import com.example.javasanitation.models.User;
import com.example.javasanitation.models.UserRepository;
import com.example.javasanitation.requestobjects.UserRequest;
import com.example.javasanitation.responseobjects.SanitizerResponse;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;


@Controller
public class UserController {

    private final UserRepository userRepo;
    private final RanksSanitizers rankSani;




    @Autowired
    public UserController(UserRepository userRepo, RanksSanitizers rankSani) {
        this.userRepo = userRepo;
        this.rankSani = rankSani;
    }

    MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
    MongoDatabase database = mongoClient.getDatabase("javaSanitation");
    MongoCollection<Document> collection = database.getCollection("SpringUser");

    public ResponseEntity<?> getAllUsers(){
        try{
            List<User> users = userRepo.findAll();
             return ResponseEntity.ok(users);
        } catch(Exception e){
             return ResponseEntity.badRequest().body("Something donked up");
        }
    }

    public User getOneUserById(@PathVariable("id") UserRequest userRequest) {
        UUID id = userRequest.getId();
        User user = userRepo.findOneById(id);
        System.out.println(user + " : " + id);
        return user;
    }


    public User getOneUserByName(@PathVariable("username") UserRequest userRequest){
            String username = userRequest.getUsername();
            User user = userRepo.findOneByName(username);
            System.out.println(user + " : " + username);
            return user;
    }


    /**
     *
     * @param userRequest
     * @return
     */
    public SanitizerResponse enterSanitation(@PathVariable("username") UserRequest userRequest){
        boolean badPass = true;
        try {
            String username = userRequest.getUsername();
            System.out.println("the unsanitized string: " + username);
            if(!rankSani.MongoInputBool(username)){
                badPass = false;
                Document document = new Document();
                document.append("username", username);
                collection.insertOne(document);
                System.out.println(document);
            }
            return new SanitizerResponse(username, badPass);
        } catch(Exception e){
            return new SanitizerResponse(e.toString(),badPass);
        }
    }

    public SanitizerResponse testSanitation(@PathVariable("username") UserRequest userRequest){
        boolean badPass = false;

        String username = userRequest.getUsername();
        String sanitizedName = rankSani.MongoInput(username);
        System.out.println("the unsanitized string: " + username);
        System.out.println("the sanitized string: " + sanitizedName);

        if(!(username.equals(sanitizedName))){
            badPass = true;
        }

        return new SanitizerResponse(sanitizedName,badPass);
    }


    public ResponseEntity<User> register(UserRequest userRequest){
        User user = new User(
                userRequest.getUsername(),
                userRequest.getEmail() ,
                userRequest.getPassword(),
                userRequest.getFirstname(),
                userRequest.getLastname(),
                userRequest.getPhone(),
                "user"
        );
        userRepo.save(user);
        return new ResponseEntity(user, HttpStatus.CREATED);
    }


}
