package com.example.inhacarpool.user.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

@RestController
public class AuthController {

	@PostMapping("/verifyToken")
	public ResponseEntity<String> verifyToken(@RequestHeader("Authorization") String token) {
		if (verifyFirebaseToken(token)) {
			return ResponseEntity.ok("Authorized");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
		}
	}

	public boolean verifyFirebaseToken(String token) {
		try {
			FirebaseAuth auth = FirebaseAuth.getInstance();
			FirebaseToken decodedToken = auth.verifyIdToken(token);

			if (decodedToken == null) {
				return false;
			}
			return true; // 토큰이 유효한 경우
		} catch (Exception e) {
			e.printStackTrace();
			return false; // 토큰이 유효하지 않은 경우
		}
	}
}