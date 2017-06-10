package com.scm.security;

import org.springframework.security.core.userdetails.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class TokenHandler {
	 private final String secret;
	    private final UserService userService;

	    public TokenHandler(String secret, UserService userService) {
	        this.secret = secret;
	        this.userService = userService;
	    }

	    public User parseUserFromToken(String token) {
	        String username = Jwts.parser()
	                .setSigningKey(secret)
	                .parseClaimsJws(token)
	                .getBody()
	                .getSubject();
	        return userService.loadUserByUsername(username);
	    }

	    public String createTokenForUser(User user) {
	        return Jwts.builder().setHeaderParam("typ", "JWT")
	                .setSubject(user.getUsername())
	                .signWith(SignatureAlgorithm.HS512, secret)
	                .compact();
	    }
}
