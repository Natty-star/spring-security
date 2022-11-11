package edu.miu.cs545.service.impl;

import edu.miu.cs545.domain.authentication.LoginRequest;
import edu.miu.cs545.domain.authentication.LoginResponse;
import edu.miu.cs545.domain.authentication.MyUserDetail;
import edu.miu.cs545.service.AuthService;
import edu.miu.cs545.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
    ModelMapper modelMapper;
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
        String jwt = jwtUtil.generateToken(myUserDetail);
        var loginResponse = new LoginResponse(jwt);
        log.info(jwt);
        return loginResponse;

    }
}
