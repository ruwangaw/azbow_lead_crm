package crb.gawarammana.crm.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTProviderService {
	private SecretKey key = Keys.hmacShaKeyFor(JWTConstant.JWT_SECRET.getBytes());

	public String generateToken(Authentication auth) {
		String jwt = Jwts.builder()
					.setIssuedAt(new Date())
					.setExpiration(new Date(new Date().getTime()+86400000))
					.claim("email", auth.getName())
					.signWith(key)
					.compact();
				return jwt;
	}
	
	public String getEmailFromJwtToken(String jwt) {
		jwt = jwt.substring(7);
		Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		String email = String.valueOf(claims.get("email"));
		return email;
	}
}
