package com.example.javasanitation.routes;

import com.example.javasanitation.controller.UserController;
import com.example.javasanitation.requestobjects.UserRequest;
import com.example.javasanitation.responseobjects.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserRoute {

    private final UserController userController;
//    private final UserResponse userResponse;

    public UserRoute(UserController userController) {
        this.userController = userController;

    }


    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers(){
        return userController.getAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registeruser(@RequestBody UserRequest userRequest) {
         return userController.register(userRequest);
    }



}
