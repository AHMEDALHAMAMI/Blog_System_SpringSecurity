package org.example.blogsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.blogsystem.Api.ApiResponse;
import org.example.blogsystem.Model.User;
import org.example.blogsystem.Service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(
            @Valid @RequestBody User user) {

        authService.register(user);

        return ResponseEntity.status(200)
                .body(new ApiResponse("User registered successfully"));
    }
}