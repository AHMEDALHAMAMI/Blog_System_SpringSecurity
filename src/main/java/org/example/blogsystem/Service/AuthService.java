package org.example.blogsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.blogsystem.Exception.ApiException;
import org.example.blogsystem.Model.User;
import org.example.blogsystem.Repository.AuthRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(User user) {
        if (authRepository.existsByUsername(user.getUsername())) {
            throw new ApiException("Username already exists");
        }

        user.setId(null);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        authRepository.save(user);
    }
}