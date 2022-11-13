package edu.miu.cs545.service;

import edu.miu.cs545.domain.authentication.LoginRequest;
import edu.miu.cs545.domain.authentication.LoginResponse;
import edu.miu.cs545.domain.authentication.RefreshTokenRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthService {

    LoginResponse login(LoginRequest loginRequest);

    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
