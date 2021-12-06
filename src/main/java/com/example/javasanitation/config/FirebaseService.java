package com.example.javasanitation.config;

import com.example.javasanitation.models.AppUpload;
import com.example.javasanitation.models.BreadCrumb;
import com.example.javasanitation.models.User;
import com.example.javasanitation.requestobjects.AppUploadRequest;
import com.example.javasanitation.requestobjects.BreadCrumbRequest;
import com.example.javasanitation.requestobjects.UserRequest;
import com.example.javasanitation.responseobjects.UserResponse;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

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

    public ResponseEntity<?> saveAppUploadData(AppUploadRequest uploadRequest){
        try {
            AppUpload appUpload = new AppUpload(
                    uploadRequest.getDeviceId(),
                    uploadRequest.getFileId() ,
                    uploadRequest.getFileSize(),
                    uploadRequest.getFileType(),
                    uploadRequest.getFirmwareVersion(),
                    UUID.randomUUID().toString()
            );
            ApiFuture<WriteResult> collectionsApiFuture = firestore.collection("app-upload-data").document().set(appUpload);
            return ResponseEntity.ok(collectionsApiFuture.get().getUpdateTime().toString());

        }catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

//    /**
//     * Saves the workoutdata to the firestore
//     * @param runRequest
//     * @return
//     */
//    public ResponseEntity<?> saveWorkoutData(BreadCrumbRequest runRequest){
//        try {
//
//            BreadCrumb runData = new BreadCrumb(
//                    runRequest.getLat(),
//                    runRequest.getLongi(),
//                    runRequest.getHeight(),
//                    runRequest.getGSpeed()
//            );
//            System.out.println(runData);
//            ApiFuture<WriteResult> collectionsApiFuture = firestore.collection("test-firebase-array-upload").document(runData.g).set(runData);
//            return ResponseEntity.ok(collectionsApiFuture.get().getUpdateTime().toString());
//
//        }catch(Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest().body(e.toString());
//        }
//    }

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
