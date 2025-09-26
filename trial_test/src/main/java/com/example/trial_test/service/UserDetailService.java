package com.example.trial_test.service;


import com.example.trial_test.entity.Users;
import com.example.trial_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUserId(username);
        if (user != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserId())
                    .password(user.getPassword())
                    .roles(user.getRoles().stream()
                            .map(Enum::name) // Use the enum names exactly as stored
                            .toArray(String[]::new))
                    .build();
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
