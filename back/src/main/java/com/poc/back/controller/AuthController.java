package com.poc.back.controller;

import com.poc.back.dto.UserDto;
import com.poc.back.model.User;
import com.poc.back.service.UserService;
import com.poc.back.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User loggedInUser = userService.login(user.getEmail(), user.getPassword());
        if (loggedInUser == null) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        final String jwtToken = jwtUtil.generateToken(userService.loadUserByUsername(user.getEmail()));

        UserDto userDto = new UserDto(loggedInUser.getId(), loggedInUser.getEmail(), null);

        AuthResponse response = new AuthResponse(jwtToken, userDto);

        return ResponseEntity.ok(response);
    }
}