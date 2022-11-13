package edu.miu.cs545.controller;

import edu.miu.cs545.domain.authentication.LoginRequest;
import edu.miu.cs545.domain.authentication.LoginResponse;
import edu.miu.cs545.domain.authentication.RefreshTokenRequest;
import edu.miu.cs545.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authenticate")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        var loginResponse = authService.login(loginRequest);
        return loginResponse;
    }

    @PostMapping("/refreshToken")
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        return authService.refreshToken(refreshTokenRequest);
    }
}
