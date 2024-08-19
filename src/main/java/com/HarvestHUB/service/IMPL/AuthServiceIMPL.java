package com.HarvestHUB.service.IMPL;

import com.HarvestHUB.collection.Token;
import com.HarvestHUB.collection.User;
import com.HarvestHUB.dto.request.AuthenticationDTO;
import com.HarvestHUB.dto.response.AuthenticationSuccessDTO;
import com.HarvestHUB.exeption.BadCredentialException;
import com.HarvestHUB.exeption.UserNotFoundException;
import com.HarvestHUB.repo.TokenRepository;
import com.HarvestHUB.repo.UserRepository;
import com.HarvestHUB.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceIMPL implements AuthService {


    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtServiceIMPL jwtService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public AuthenticationSuccessDTO authenticate(AuthenticationDTO authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword())
            );
        }catch (Exception e){
            log.error(e.getMessage());
            throw new BadCredentialException();
        }
        Optional<User> user = userRepository.findUserByEmailEquals(authenticationRequest.getEmail());
        if(user.isPresent()){
            revokeAllUserToken(user.get());
            String access_token = jwtService.generateToken(user.get());
            Token token = new Token(access_token,user.get().getEmail());
            tokenRepository.save(token);
            return new AuthenticationSuccessDTO(
                    user.get().getId(),
                    user.get().getRole(),
                    access_token,
                    jwtService.generateRefreshToken(user.get())
            );
        }else {
            throw new UserNotFoundException();
        }
    }

//    @Override
//    public AuthResponseDTO passwordChange(ChangePasswordDTO changePasswordDTO) {
//        Optional<User> byEmailEquals = userRepository.findByEmailEquals(changePasswordDTO.getEmail());
//        if(byEmailEquals.isPresent()){
//            User user = byEmailEquals.get();
//            user.setPassword(passwordEncoder.encode(changePasswordDTO.getPassword()));
//            userRepository.save(user);
//            return authenticate(new DefaultAuthenticationDTO(user.getEmail(), changePasswordDTO.getPassword()));
//        }else{
//            throw new UserNotFoundException();
//        }
//    }
//
    private void revokeAllUserToken(User user){
        List<Token> tokens = tokenRepository.findTokensByUserEmailEquals(user.getEmail());
        if(tokens.isEmpty()){
            return;
        }
        tokens.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);

        });
        tokenRepository.saveAll(tokens);
    }
}
