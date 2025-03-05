package edu.miu.lab1.service;

import edu.miu.lab1.entity.dto.LoginRequest;
import edu.miu.lab1.entity.dto.RefreshTokenRequest;
import edu.miu.lab1.entity.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
