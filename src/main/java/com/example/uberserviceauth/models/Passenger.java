package com.example.uberserviceauth.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Passenger extends  BaseModel{
    @Column(nullable = false)
    private  String name;

    @OneToMany(mappedBy = "passenger")
    private List<Booking> bookings = new ArrayList<>();
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;


}