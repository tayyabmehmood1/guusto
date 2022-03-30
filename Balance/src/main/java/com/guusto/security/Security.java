package com.guusto.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class Security {

	@Value("${authorization.token}")
	private String authorizationToken;
	
	public boolean isTokenValidated(HttpHeaders headers) {
		List headerAuthorizationList = headers.get("Authorization");
    	if (headers.get("Authorization") == null || headers.get("Authorization").size() == 0) {
    		return false;
    	}
    	String token = (String) headerAuthorizationList.get(0);
    	if (authorizationToken.equals(token))
    		return true;
		return false;
	}
}
