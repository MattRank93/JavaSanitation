package com.example.javasanitation.config;

import com.example.javasanitation.models.RunData;
import com.example.javasanitation.models.User;
import com.example.javasanitation.requestobjects.RunRequest;
import com.example.javasanitation.requestobjects.UserRequest;
import com.example.javasanitation.responseobjects.UserResponse;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class FirebaseService {

    private final Firestore firestore;

    public FirebaseService(Firestore firestore) {
        this.firestore = firestore;
    }

    public ResponseEntity<?> saveUserDetails(UserRequest userRequest){
        try {
            User user = new User(
                    userRequest.getUsername(),
                    userRequest.getEmail() ,
                    userRequest.getPassword(),
                    userRequest.getFirstname(),
                    userRequest.getLastname(),
                    userRequest.getPhone(),
                    "user"
            );
            ApiFuture<WriteResult> collectionsApiFuture = firestore.collection("users").document(user.getEmail()).set(user);
            return ResponseEntity.ok(collectionsApiFuture.get().getUpdateTime().toString());

        }catch(Exception e) {
            e.printStackTrace();
        return ResponseEntity.badRequest().body(e.toString());
        }
    }

    /**
     * Saves the workoutdata to the firestore
     * @param runRequest
     * @return
     */
    public ResponseEntity<?> saveWorkoutData(RunRequest runRequest){
        try {

            RunData runData = new RunData(
                    runRequest.getId(),
                    runRequest.getUsername(),
                    runRequest.getDistance(),
                    runRequest.getTotalTime()
            );
            ApiFuture<WriteResult> collectionsApiFuture = firestore.collection("app-upload-data").document(runData.getId()).set(runData);
            return ResponseEntity.ok(collectionsApiFuture.get().getUpdateTime().toString());

        }catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    public Optional<UserResponse> getUserDetails(UserRequest userRequest){
        try {
            DocumentSnapshot document = firestore.collection("users").document(userRequest.getEmail()).get().get();
            UserResponse userResponse = null;
            if (document.exists()) {
                userResponse = new UserResponse(
                        document.getString("username"),
                        document.getString("email"),
                        document.getString("firstname"),
                        document.getString("lastname"),
                        document.getString("phone")
                );
                return Optional.of(userResponse);
            } else {
                return Optional.empty();
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return Optional.empty();
    }



    public String updatePatientDetails(User user) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("user").document(user.getEmail()).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deletePatient(User user) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("user").document(user.getEmail()).delete();
        return "Document with Patient ID "+user.getUsername()+" has been deleted";
    }


}
