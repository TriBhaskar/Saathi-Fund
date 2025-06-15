package com.anterka.saathifund.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
// @RequestMapping(ApiPaths.API_PREFIX + ApiPaths.SAATHI_FUND_USER)
public class SaathiFundUserController {

    // private final SaathiFundUserService saathiFundUserService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser() {
        // Logic for user registration
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/profile")
    public ResponseEntity<String> getUserProfile() {
        // Logic to retrieve user profile
        return ResponseEntity.ok("User profile data");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser() {
        // Logic for user login
        return ResponseEntity.ok("User logged in successfully");
    }



    // Additional methods for user management can be added here

}
