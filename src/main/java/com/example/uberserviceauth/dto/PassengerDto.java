package com.example.uberserviceauth.dto;


import com.example.uberserviceauth.models.Passenger;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class PassengerDto {
   private String id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private Date createdAt;

    public static PassengerDto from(Passenger p){
     PassengerDto result = PassengerDto.builder()
             .id(p.getId().toString())
             .name(p.getName())
             .email(p.getEmail())
             .password(p.getPassword())
             .phoneNumber(p.getPhoneNumber())
             .name(p.getName())
             .createdAt(p.getCreatedAt())
             .build();
     return result;
    }
}
