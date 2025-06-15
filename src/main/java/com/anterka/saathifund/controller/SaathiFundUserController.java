package com.anterka.saathifund.controller;


import com.anterka.saathifund.constants.ApiPaths;
import com.anterka.saathifund.constants.ResponseStatus;
import com.anterka.saathifund.dto.request.login.LoginRequest;
import com.anterka.saathifund.dto.request.register.RegisterRequest;
import com.anterka.saathifund.dto.response.LoginResponse;
import com.anterka.saathifund.dto.response.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.API_PREFIX)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SaathiFundUserController {

    private static final Logger log = LoggerFactory.getLogger(SaathiFundUserController.class);

    // private final SaathiFundUserService saathiFundUserService;

    @PostMapping(ApiPaths.REGISTER_USER)
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody RegisterRequest registerRequest) {
        // Logic for user registration
        return ResponseEntity.ok(RegisterResponse.builder()
                .status(ResponseStatus.SUCCESS)
                .message("User registered successfully")
                .username(registerRequest.getUserName())
                .otpValiditySeconds(300) // Example value
                .timestamp(LocalDateTime.now())
                .build());
    }

    @GetMapping("/profile")
    public ResponseEntity<String> getUserProfile() {
        // Logic to retrieve user profile
        return ResponseEntity.ok("User profile data");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        // Logic for user login
        return ResponseEntity.ok(LoginResponse.builder()
                .status(ResponseStatus.SUCCESS)
                .token("exampleToken123") // Example token
                .userId("userId123") // Example user ID
                .email("test@email").build());
    }

}
