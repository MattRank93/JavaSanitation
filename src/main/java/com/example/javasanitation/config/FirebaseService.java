package com.example.javasanitation.config;

import com.example.javasanitation.models.User;
import com.example.javasanitation.requestobjects.UserRequest;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FirebaseService {

    public ResponseEntity<?> saveUserDetails(UserRequest userRequest){
        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("users").document(userRequest.getEmail()).set(userRequest);
            return ResponseEntity.ok(collectionsApiFuture.get().getUpdateTime().toString());

        }catch(Exception e) {

            e.printStackTrace();
        return ResponseEntity.badRequest().body(e.toString());
        }
    }

}
