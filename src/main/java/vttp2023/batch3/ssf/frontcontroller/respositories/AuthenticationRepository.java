package vttp2023.batch3.ssf.frontcontroller.respositories;

import java.time.Duration;
import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthenticationRepository {

	private RedisTemplate<String, String> template;

	public AuthenticationRepository(RedisTemplate<String, String> template) {
		this.template = template;
	}

	// TODO Task 5
	// Use this class to implement CRUD operations on Redis
	public void disableUser(String username) {
		template.opsForValue().set(username, username, Duration.ofMinutes(30));
	}

	public boolean isLocked(String username) {
		Optional<String> opt = Optional.ofNullable(template.opsForValue().get(username));

		if (opt.isPresent())
			return true;
		
		return false;
	}
}	
