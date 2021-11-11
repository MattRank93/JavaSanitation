package com.example.javasanitation.routes;

import com.example.javasanitation.config.FirebaseService;
import com.example.javasanitation.controller.UserController;
import com.example.javasanitation.models.User;
import com.example.javasanitation.requestobjects.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserRoute {

    private final UserController userController;
//    private final UserResponse userResponse;
    private final FirebaseService firebaseService;

    public UserRoute(UserController userController, FirebaseService firebaseService) {
        this.userController = userController;
        this.firebaseService = firebaseService;
    }


    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers(){
        return userController.getAllUsers();
    }

    @GetMapping("/byid")
    public ResponseEntity<?> getOneUserById(@RequestBody UserRequest userRequest){
        try{
            User oneUser = userController.getOneUserById(userRequest);
            System.out.println(oneUser);
            return ResponseEntity.ok(oneUser);
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Something donked up");
        }
    }

    @GetMapping("/byname")
    public ResponseEntity<?> getOneUserByName(@RequestBody UserRequest userRequest){
        try{
            User oneUser = userController.getOneUserByName(userRequest);
            System.out.println(oneUser);
            return ResponseEntity.ok(oneUser);
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Something donked up");
        }
    }

    @GetMapping("/testsani")
    public Object testSanitation(@RequestBody UserRequest userRequest){
        try{
            return userController.testSanitation(userRequest);
        } catch(Exception e){
            return "Failed";
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registeruser(@RequestBody UserRequest userRequest) {
         return userController.register(userRequest);
    }

    @PostMapping("/registerinfirebase")
    public ResponseEntity<?> registeruserinfirebase(@RequestBody UserRequest userRequest) {
        return firebaseService.saveUserDetails(userRequest);
    }



}
