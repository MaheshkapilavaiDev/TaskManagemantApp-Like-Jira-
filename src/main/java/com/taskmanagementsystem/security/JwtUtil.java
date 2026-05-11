package com.taskmanagementsystem.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	// SECRET KEY
	private final String SECRET = "mysupersecretkeymysupersecretkeymysupersecretkeyadminmysupersecretkeyadminmysupersecretkeyadmin";

	// 1 DAY
	private final long EXPIRATION = 1000 * 60 * 60 * 24;

	private Key getSigningKey() {

		return Keys.hmacShaKeyFor(SECRET.getBytes());
	}

	// GENERATE TOKEN
	public String generateToken(String email, String role) {

		return Jwts.builder()

				.setSubject(email)

				.claim("role", role)

				.setIssuedAt(new Date())

				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))

				.signWith(getSigningKey(), SignatureAlgorithm.HS256)

				.compact();
	}

	// EXTRACT CLAIMS
	public Claims extractClaims(String token) {

		return Jwts.parserBuilder()

				.setSigningKey(getSigningKey())

				.build()

				.parseClaimsJws(token)

				.getBody();
	}

	// GET EMAIL
	public String getEmail(String token) {

		return extractClaims(token).getSubject();
	}

	// GET ROLE
	public String getRole(String token) {

		return extractClaims(token).get("role", String.class);
	}

	// VALIDATE TOKEN
	public boolean isValid(String token) {

		return extractClaims(token).getExpiration().after(new Date());
	}
}
