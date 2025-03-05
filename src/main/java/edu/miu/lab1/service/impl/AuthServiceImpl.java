package edu.miu.lab1.service.impl;

import edu.miu.lab1.entity.dto.LoginRequest;
import edu.miu.lab1.entity.dto.RefreshTokenRequest;
import edu.miu.lab1.entity.dto.LoginResponse;
import edu.miu.lab1.service.AuthService;
import edu.miu.lab1.helper.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {


    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;


    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication result = null;
        try {
            result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(result.getName());

        final String accessToken = jwtUtil.generateToken(userDetails);
        final String refreshToken = jwtUtil.generateRefreshToken(loginRequest.getEmail());
        return new LoginResponse(accessToken, refreshToken);
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        if (jwtUtil.validateToken(refreshTokenRequest.getRefreshToken()) && jwtUtil.isRefreshToken(refreshTokenRequest.getRefreshToken())) { //Added check for refresh token type
            if (jwtUtil.isTokenExpired(refreshTokenRequest.getAccessToken())) {
                final String accessToken = jwtUtil.doGenerateToken(jwtUtil.getSubject(refreshTokenRequest.getRefreshToken()));
                return new LoginResponse(accessToken, refreshTokenRequest.getRefreshToken());
            } else {
                log.info("Access token is not expired.");
                return new LoginResponse(refreshTokenRequest.getAccessToken(), refreshTokenRequest.getRefreshToken()); //Return the same tokens if access token is still valid.
            }
        } else {
            log.warn("Invalid refresh token.");
            return new LoginResponse(); // Or throw an exception
        }
    }
}
