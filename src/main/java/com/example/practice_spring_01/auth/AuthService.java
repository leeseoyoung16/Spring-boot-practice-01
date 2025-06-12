package com.example.practice_spring_01.auth;

import com.example.practice_spring_01.user.UserRepository;
import lombok.RequiredArgsConstructor;
import com.example.practice_spring_01.user.User;
import com.example.practice_spring_01.auth.RegisterRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public User registerUser(RegisterRequest request) {
        String username = request.getUsername();
        String rawPassword = request.getPassword();
        String email = request.getEmail();

        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Username " + username + " already exists.");
        }

        String encodedPassword = passwordEncoder.encode(rawPassword);

        User newUser = new User(username, encodedPassword, email);

        return userRepository.save(newUser);
    }

    @Transactional
    public String loginUser(LoginRequest request)
    {
        String username = request.getUsername();
        String rawPassword = request.getPassword();

        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, rawPassword));
            return "login success";
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password.");
        }
    }
}
