package vttp2023.batch3.ssf.frontcontroller.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@Service
public class AuthenticationService {

	// TODO: Task 2
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write the authentication method in here
	public void authenticate(String username, String password) throws Exception {

		String authUrl = "https://authservice-production-e8b2.up.railway.app/api/authenticate";

		JsonObject obj = Json.createObjectBuilder()
				.add("username", username)
				.add("password", password)
				.build();
		
		RequestEntity<String> req = RequestEntity.post(authUrl)
				.contentType(MediaType.APPLICATION_JSON)
				.header("Accept", "application/json")
				.body(obj.toString(), String.class);
				
		RestTemplate template = new RestTemplate();

		ResponseEntity<String> resp = template.exchange(req, String.class);
	}

	// TODO: Task 3
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to disable a user account for 30 mins
	public void disableUser(String username) {
	}

	// TODO: Task 5
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to check if a given user's login has been disabled
	public boolean isLocked(String username) {
		return false;
	}
}
