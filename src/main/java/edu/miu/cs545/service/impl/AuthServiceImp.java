package edu.miu.cs545.service.impl;

import edu.miu.cs545.domain.authentication.LoginRequest;
import edu.miu.cs545.domain.authentication.LoginResponse;
import edu.miu.cs545.domain.authentication.RefreshTokenRequest;
import edu.miu.cs545.service.AuthService;
import edu.miu.cs545.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class AuthServiceImp implements AuthService {
    @Autowired
    private MyUserDetailService myUserDetailService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            var result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())
            );
        }catch (BadCredentialsException e){
            log.info("Bad Credential");
        }

        UserDetails myUserDetail = myUserDetailService.loadUserByUsername(loginRequest.getUsername());
        final String jwt = jwtUtil.generateToken(myUserDetail);
        final String refreshToken = jwtUtil.generateRefreshToken(loginRequest.getUsername());
        var loginResponse = new LoginResponse(jwt, refreshToken);
        log.info(jwt);
        return loginResponse;
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        boolean isRefreshTokenValid =  jwtUtil.validateToken(refreshTokenRequest.getRefreshToken());
        if (isRefreshTokenValid){
            var isAccessTokenExpired = jwtUtil.isTokenExpired(refreshTokenRequest.getAccessToken());
            if (isAccessTokenExpired){
                final String accessToken = jwtUtil.doGenerateToken(jwtUtil.getSubject(refreshTokenRequest.getRefreshToken()));
                //I also regenerate the refresh token when ever the access token expire this helps to prevent if the refresh token is stolen
                //every time when the access token expired without the user interference the refresh token help us to get new access token
                final String refreshToken = jwtUtil.generateRefreshToken(jwtUtil.getSubject(refreshTokenRequest.getRefreshToken()));
                var loginResponse = new LoginResponse(accessToken, refreshToken);
                return loginResponse;
            }

        }
        return new LoginResponse(refreshTokenRequest.getAccessToken(),refreshTokenRequest.getRefreshToken());
    }
}
