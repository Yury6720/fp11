package com.kuzminski.configuration.security;

import com.kuzminski.configuration.security.config.TokenConfig;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

  private final TokenConfig tokenConfig;

  public String generateToken(Authentication authentication) {

    UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + tokenConfig.getJwtExpirationInMs());

    return Jwts.builder()
        .setSubject(Long.toString(userPrincipal.getId()))
        .setIssuedAt(new Date())
        .setExpiration(expiryDate)
        .signWith(SignatureAlgorithm.HS512, tokenConfig.getJwtSecret())
        .compact();
  }

  public Long getUserIdFromJWT(String token) {
    Claims claims =
        Jwts.parser().setSigningKey(tokenConfig.getJwtSecret()).parseClaimsJws(token).getBody();

    return Long.parseLong(claims.getSubject());
  }

  public Long getActualUserId (HttpServletRequest request){

    return getUserIdFromJWT(request.getHeader("auth-token"));

  }

  public boolean validateToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(tokenConfig.getJwtSecret()).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException ex) {
      log.error("Invalid JWT signature");
    } catch (MalformedJwtException ex) {
      log.error("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      log.error("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      log.error("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      log.error("JWT claims string is empty.");
    }
    return false;
  }
}
