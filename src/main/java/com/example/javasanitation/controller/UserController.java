package com.example.javasanitation.controller;

import com.example.javasanitation.models.User;
import com.example.javasanitation.models.UserRepository;
import com.example.javasanitation.requestobjects.UserRequest;
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
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }




    public ResponseEntity<?> getAllUsers(){

        try{
            List<User> users = userRepo.findAll();
             return ResponseEntity.ok(users);
        } catch(Exception e){
             return ResponseEntity.badRequest().body("Something donked up");
        }
    }

    public ResponseEntity<?> getOneUser(@PathVariable("id") UserRequest userRequest){
        try{
            UUID id = userRequest.getId();
            User user = userRepo.findOneById(id);
            return ResponseEntity.ok(user);
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Something donked up");
        }
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
