package com.example.javasanitation.routes;

import com.example.javasanitation.config.FirebaseService;
import com.example.javasanitation.controller.UserController;
import com.example.javasanitation.requestobjects.RunRequest;
import com.example.javasanitation.requestobjects.UserRequest;
import com.example.javasanitation.responseobjects.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/fire-data")
public class CsvDataFirebaseRoute {


    private final FirebaseService firebaseService;

    public CsvDataFirebaseRoute( FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }


    @PostMapping("/upload")
    public ResponseEntity<?> uploadDataFirebase(@RequestParam RunRequest runRequest) {
        return firebaseService.saveWorkoutData(runRequest);
    }
}
