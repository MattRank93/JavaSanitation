package com.example.javasanitation.routes;

import com.example.javasanitation.config.FirebaseService;
import com.example.javasanitation.controller.UserController;
import com.example.javasanitation.requestobjects.RunRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/algolia-data")
public class AlgoliaRoute {


    private final FirebaseService firebaseService;

    public AlgoliaRoute(UserController userController, FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }


    @PostMapping("/upload")
    public ResponseEntity<?> uploadDataFirebase(@RequestParam RunRequest runRequest) {
        System.out.println("____________________________________________________________________________________________");
        System.out.println(runRequest);
        System.out.println("____________________________________________________________________________________________");
        return firebaseService.saveWorkoutData(runRequest);
    }


}
