package com.example.uberserviceauth.controllers;

import com.example.uberserviceauth.dto.PassengerDto;
import com.example.uberserviceauth.dto.PassengerSignUpRequestDto;
import com.example.uberserviceauth.service.AuthService;
import jakarta.annotation.Resource;
import jakarta.persistence.Entity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;
   // private  BCryptPasswordEncoder bCryptPasswordEncoder;
    public AuthController(AuthService authService) {
        this.authService = authService;
     //   this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

     @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerDto> signUp(@RequestBody PassengerSignUpRequestDto passengerSignUpRequestDto)
    {
        PassengerDto response = authService.signupPassenger(passengerSignUpRequestDto);
        return new ResponseEntity<>(response,HttpStatus.CREATED);

    }

    @GetMapping("/signin/passenger")
    public ResponseEntity<?> signIn(){
        return new ResponseEntity<>(10,HttpStatus.CREATED);
    }


}
