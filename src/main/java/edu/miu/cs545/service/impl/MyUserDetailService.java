package edu.miu.cs545.service.impl;

import edu.miu.cs545.domain.User;
import edu.miu.cs545.domain.authentication.MyUserDetail;
import edu.miu.cs545.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@Slf4j
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(username + "here");
       Optional<User> user = userRepository.findByUsername(username);
       user.orElseThrow(() -> new UsernameNotFoundException("Not Found" + username));
        return modelMapper.map(user,MyUserDetail.class);
    }
}
