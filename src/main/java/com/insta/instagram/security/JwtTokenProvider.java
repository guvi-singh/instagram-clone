package com.insta.instagram.security;

import javax.crypto.SecretKey;

import org.apache.catalina.security.SecurityClassLoad;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import com.insta.instagram.config.SecurityContext;

import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

	public JwtTokenClaims getClaimsFromToken(String token)
	{

		SecretKey key = Keys.hmacShaKeyFor(SecurityContext.JWT_KEY.getBytes());
		//Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJwt(token).getBody();
		Claims claims = Jwts.parser()
			    .setSigningKey(key)
			    .parseClaimsJws(token)
			    .getBody();
		String userName = String.valueOf(claims.get("username"));
		JwtTokenClaims jwtTokenClaims=new JwtTokenClaims();
		jwtTokenClaims.setUsername(userName);
		return jwtTokenClaims;
	
	}	
}
