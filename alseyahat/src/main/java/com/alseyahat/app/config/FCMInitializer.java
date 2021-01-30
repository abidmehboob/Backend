package com.alseyahat.app.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

//@Slf4j
//@Component
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class FCMInitializer {

//    @Value("${app.firebase-configuration-file}")
//    String firebaseConfigurationPath;
//
//    @Value("${app.firebase-database-url}")
//    String firebaseDatabaseUrl;
//
//    @PostConstruct
//    public void initialize() throws IOException {
//        FirebaseOptions firebaseOptions = new FirebaseOptions
//                .Builder()
//                .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseConfigurationPath).getInputStream()))
//                .setDatabaseUrl(firebaseDatabaseUrl)
//                .build();
//
//        if (FirebaseApp.getApps().isEmpty()) {
//            FirebaseApp.initializeApp(firebaseOptions);
//        }
//    }
}
