package com.vincent.demo.Auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Calendar;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JWTService {

    @Autowired
    private AuthenticationManager authenticationManager;

    private final String KEY = "kevin0987654321kevin0987654321kevin0987654321";

    public String genToken(AuthRequest authRequest){
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getUserName());
        authentication = authenticationManager.authenticate(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Key secretkey = Keys.hmacShaKeyFor(KEY.getBytes());

        Calendar exp = Calendar.getInstance();
        exp.add(Calendar.MINUTE,10);

        Claims claims = Jwts.claims();
        claims.setIssuer("kevin");
        claims.setExpiration(exp.getTime());
        claims.put("userName",userDetails.getUsername());


        return  Jwts.builder()
                .setClaims(claims)
                .signWith(secretkey)
                .compact();
    }

    public Map<String,Object> parseToken(String token){

        Key secretKey = Keys.hmacShaKeyFor(KEY.getBytes());

        JwtParser parser = Jwts.parserBuilder()
                            .setSigningKey(secretKey)
                            .build();

        Claims claims = parser.parseClaimsJws(token).getBody();

        return  claims.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }

}
