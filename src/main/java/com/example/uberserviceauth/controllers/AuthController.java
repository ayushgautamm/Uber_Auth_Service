package com.example.uberserviceauth.controllers;

import com.example.uberserviceauth.dto.AuthRequestDto;
import com.example.uberserviceauth.dto.PassengerDto;
import com.example.uberserviceauth.dto.PassengerSignUpRequestDto;
import com.example.uberserviceauth.service.AuthService;
import com.example.uberserviceauth.service.JwtService;
import jakarta.annotation.Resource;
import jakarta.persistence.Entity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Value("${cookie_exiry}")
    private  int CookieExpiry;
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private JwtService jwtService;
    public AuthController(AuthService authService, AuthenticationManager authenticationManager , JwtService jwtService) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerDto> signUp(@RequestBody PassengerSignUpRequestDto dto) {
        PassengerDto response = authService.signupPassenger(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/signin/passenger")
    public ResponseEntity<?> signIn(@RequestBody AuthRequestDto dto,
                                    HttpServletResponse response) {

        System.out.println("Request Received: " + dto.getEmail() + " " + dto.getPassword());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );

        if (authentication.isAuthenticated()) {

            String jwtToken = jwtService.createToken(dto.getEmail());

            ResponseCookie cookie = ResponseCookie.from("JwtToken", jwtToken)
                    .httpOnly(true)
                    .secure(false) // true in production with https
                    .path("/")
                    .maxAge(CookieExpiry)
                    .build();

            response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

            return ResponseEntity.ok(jwtToken);

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}

