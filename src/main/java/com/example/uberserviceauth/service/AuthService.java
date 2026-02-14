package com.example.uberserviceauth.service;

import com.example.uberserviceauth.dto.PassengerDto;
import com.example.uberserviceauth.dto.PassengerSignUpRequestDto;
import com.example.uberserviceauth.models.Passenger;
import com.example.uberserviceauth.repository.PassengerRespository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private  final PassengerRespository passengerRespository;
    private  final BCryptPasswordEncoder bCryptPasswordEncoder;
    public AuthService(PassengerRespository passengerRespository , BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.passengerRespository = passengerRespository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public PassengerDto signupPassenger(PassengerSignUpRequestDto passengerSignUpRequestDto){
        Passenger passenger  = Passenger.builder()
                .email(passengerSignUpRequestDto.getEmail())
                .password(bCryptPasswordEncoder.encode(passengerSignUpRequestDto.getPassword()))
                .name(passengerSignUpRequestDto.getName())
                .phoneNumber(passengerSignUpRequestDto.getPhoneNumber())
                .build();

        Passenger newPassenger = passengerRespository.save(passenger);
        return PassengerDto.from(newPassenger);
    }
}
