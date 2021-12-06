package com.example.javasanitation.routes;

        import com.example.javasanitation.config.FirebaseService;
        import com.example.javasanitation.controller.UserController;
        import com.example.javasanitation.models.User;
        import com.example.javasanitation.requestobjects.AppUploadRequest;
        import com.example.javasanitation.requestobjects.UserRequest;
        import com.example.javasanitation.responseobjects.UserResponse;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/fire-user")
public class UserFirebaseRoute {

    private final UserController userController;
    //    private final UserResponse userResponse;
    private final FirebaseService firebaseService;

    public UserFirebaseRoute(UserController userController, FirebaseService firebaseService) {
        this.userController = userController;
        this.firebaseService = firebaseService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> registeruserinfirebase(@RequestBody UserRequest userRequest) {
        return firebaseService.saveUserDetails(userRequest);
    }

    @PostMapping("/app-upload-data")
    public ResponseEntity<?> uploadAppAUploadData(@RequestBody AppUploadRequest uploadRequest) {
        return firebaseService.saveAppUploadData(uploadRequest);
    }


    @GetMapping("/byemail")
    public ResponseEntity<?> getOneUserEmail(@RequestBody UserRequest userRequest){
        try{
            Optional<UserResponse> oneUser = firebaseService.getUserDetails(userRequest);
//            System.out.println(oneUser);
            return ResponseEntity.ok(oneUser);
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Something donked up");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateOneUserEmail(@RequestBody UserRequest userRequest){
        try{
            Optional<UserResponse> oneUser = firebaseService.getUserDetails(userRequest);
            return ResponseEntity.ok(oneUser);
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Something donked up");
        }
    }

}
