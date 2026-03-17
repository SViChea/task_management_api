package site.taskmanagement.taskmanagementapi.service.auth.impl;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import site.taskmanagement.taskmanagementapi.model.User;
import site.taskmanagement.taskmanagementapi.service.auth.JWTService;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JWTServiceimpl implements JWTService {

    @Value("${app.jwt.secret}")
    private String secretKey;

    @Value("${app.jwt.expiration}")
    private Integer expiresIn;

    @Override
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roleNames = user.getRoles().stream()
                .map(role -> "ROLE_" + role.getRoleName())
                .toList();

        return Jwts.builder()
                .claim("user_id", user.getId())
                .subject(user.getUsername())
                .claim("roles", roleNames)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiresIn))
                .signWith(getSigningKey())
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())   // validates signature automatically
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

//    Function to extract Claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractAllClaims(token));
    }

//    Extract Username
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

//    Extract Expiration Time
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

//    Extract Roles
    public List<String> extractRoles(String token) {
        return extractClaim(token, claims -> claims.get("roles", List.class));
    }

//    Check token expiration
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String username = extractUsername(token);

            return username.equals(userDetails.getUsername())
                    && !isTokenExpired(token);

        } catch (ExpiredJwtException e) {
            throw new JwtException("Token has expired");
        } catch (UnsupportedJwtException e) {
            throw new JwtException("Token format is unsupported");
        } catch (MalformedJwtException e) {
            throw new JwtException("Token is malformed");
        } catch (SignatureException e) {
            throw new JwtException("Invalid token signature");
        } catch (IllegalArgumentException e) {
            throw new JwtException("Token is empty or null");
        }
    }

    public List<GrantedAuthority> extractAuthorities(String token) {
        List<String> roles = extractRoles(token);
        if (roles == null) return List.of();

        return roles.stream()
                .map(SimpleGrantedAuthority::new)   // e.g. "ROLE_ADMIN"
                .collect(Collectors.toList());
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
