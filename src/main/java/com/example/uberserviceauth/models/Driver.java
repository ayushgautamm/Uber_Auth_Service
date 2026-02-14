package com.example.uberserviceauth.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Driver extends BaseModel {

    @Column(unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String licenseNumber;

    // 1 : n , Driver : Booking

    @OneToMany(mappedBy = "driver",fetch =  FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Booking> bookings;
}