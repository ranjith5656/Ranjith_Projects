package com.employee.security.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * JwtService is responsible for handling JSON Web Tokens (JWT) operations such as token extraction,
 * expiration validation, token validation, token generation, and token signing.
 */
@Component
public class JwtService {

    // Secret key for signing the JWT
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    // It provides methods to generate, extract, and validate JWTs.
    /**
     * Extracts the username from the JWT token.
     *
     * @param token The JWT token
     * @return The extracted username from the token
     */
    
  //claim is a encoded as a JSON object that is used as the payload of a JSON Web Signature (JWS) structure
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts the expiration date from the JWT token.
     *
     * @param token The JWT token
     * @return The expiration date extracted from the token
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts a specific claim from the JWT token using a claims resolver function.
     *
     * @param token          The JWT token
     * @param claimsResolver The claims resolver function
     * @param <T>            The type of the claim
     * @return The extracted claim using the provided resolver function
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extracts all claims from the JWT token.
     *
     * @param token The JWT token
     * @return All claims extracted from the token
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Checks if a JWT token is expired.
     *
     * @param token The JWT token
     * @return True if the token is expired, false otherwise
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Validates a JWT token against a user's details.
     *
     * @param token       The JWT token
     * @param userDetails The user details
     * @return True if the token is valid for the given user, false otherwise
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Generates a JWT token for the given username.
     *
     * @param userName The username for which the token is generated
     * @return The generated JWT token
     */
    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    /**
     * Creates a JWT token with the specified claims and username.
     *
     * @param claims   The claims to include in the token
     * @param userName The username to associate with the token
     * @return The created JWT token
     */
    
    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    /**
     * Gets the signing key from the encoded secret.
     *
     * @return The signing key used for JWT signing
     */
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
