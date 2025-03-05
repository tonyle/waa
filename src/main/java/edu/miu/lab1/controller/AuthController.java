package edu.miu.lab1.controller;

import edu.miu.lab1.entity.dto.LoginRequest;
import edu.miu.lab1.entity.dto.RefreshTokenRequest;
import edu.miu.lab1.entity.dto.LoginResponse;
import edu.miu.lab1.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authenticate")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        var loginResponse = authService.login(loginRequest);
        return new ResponseEntity<LoginResponse>(
                loginResponse, HttpStatus.OK);
    }

    @PostMapping("/refreshToken")
    public LoginResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

}
