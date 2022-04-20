package com.vincent.demo.controller;

import com.vincent.demo.Auth.AuthRequest;
import com.vincent.demo.Auth.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
/*
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private JWTService jwtService;

    @PostMapping
    public ResponseEntity<Map<String,String>> issueToken(@RequestBody AuthRequesst request){
        String token = jwtService.genToken(request);
        Map<String,String> response = Collections.singletonMap("Token",token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/parse")
    public ResponseEntity<Map<String,Object>> parseToken(Map<String,String> request){

        String token = request.get("token");

        Map<String,Object> response = jwtService.parseToken(token);

        return ResponseEntity.ok(response);
    }

}
*/
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private JWTService jwtService;

    @PostMapping
    public ResponseEntity<Map<String, String>> generateToken( @RequestBody AuthRequest request) {
        String token = jwtService.genToken(request);
        Map<String, String> response = Collections.singletonMap("token", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/parse")
    public ResponseEntity<Map<String, Object>> parseToken(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        Map<String, Object> response = jwtService.parseToken(token);

        return ResponseEntity.ok(response);
    }
}