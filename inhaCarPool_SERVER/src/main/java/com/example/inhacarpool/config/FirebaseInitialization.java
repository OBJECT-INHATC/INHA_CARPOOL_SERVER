package com.example.inhacarpool.config;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class FirebaseInitialization {

	@Primary
	@Bean
	public void initializeFirebaseApp() {
		try {
			Resource resource = new ClassPathResource("serviceAccountKey.json");
			// 파이어스토어에 접근하기 위한 인증
			InputStream serviceAccount = resource.getInputStream();

			FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();

			if (FirebaseApp.getApps().isEmpty()) {
				FirebaseApp.initializeApp(options);
				log.info("===========Firebase initialization is success=========");
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.info("===========Firebase initialization is failed=========");
		}
	}
}
