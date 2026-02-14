package com.example.uberserviceauth.dto;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component

public class PassengerSignUpRequestDto {
    private String email;
    private String password;
    private String phoneNumber;
    private String name;
}
