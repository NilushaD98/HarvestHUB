package com.HarvestHUB.service.IMPL;

import com.HarvestHUB.collection.User;
import com.HarvestHUB.exeption.UserNotFoundException;
import com.HarvestHUB.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> byEmailEquals = userRepository.findUserByEmailEquals(email);
        if(!byEmailEquals.isPresent()){
            throw new UserNotFoundException();
        }else {
            return byEmailEquals.get();
        }
    }
}
