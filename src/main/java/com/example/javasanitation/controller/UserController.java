package com.example.javasanitation.controller;

import com.example.javasanitation.models.User;
import com.example.javasanitation.models.UserRepository;
import com.example.javasanitation.requestobjects.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserRepository userRepo;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }




    public ResponseEntity getAllUsers(){
        return new ResponseEntity(userRepo.findAll(), HttpStatus.FOUND);
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
