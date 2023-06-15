package com.dockerImgB.util;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthserviceImpl implements Authservice {

	private final RestTemplate restTemplate = new RestTemplate();

	@Override
	public String validateAuth(String token) {
		String token1 = "http://localhost:8083/oauth/check_token?token=" + token;
		Root authValidation = restTemplate.getForObject(token1, Root.class);
		if (authValidation != null) {
			return "User Validate";
		} else {
			return "User Not Validate";
		}
	}

}
