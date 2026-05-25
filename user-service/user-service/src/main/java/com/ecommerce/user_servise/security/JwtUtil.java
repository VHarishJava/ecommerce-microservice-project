package com.ecommerce.user_servise.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;
	
	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(secret.getBytes());
	}
	
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+86400000))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	
	 public String extractUsername(String token) {
	        return Jwts.parserBuilder()
	                .setSigningKey(getSigningKey())
	                .build()
	                .parseClaimsJws(token)
	                .getBody()
	                .getSubject();
	    }

	    // Validate token
	    public boolean validateToken(String token) {
	        try {
	            Jwts.parserBuilder()
	                .setSigningKey(getSigningKey())
	                .build()
	                .parseClaimsJws(token);
	            return true;
	        } catch (JwtException e) {
	            return false;
	        }
	    }
	
}
