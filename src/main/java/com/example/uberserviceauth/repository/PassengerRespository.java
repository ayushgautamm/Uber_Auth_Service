package com.example.uberserviceauth.repository;

import com.example.uberserviceauth.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRespository extends JpaRepository<Passenger, Long> {
    Optional<Passenger> findPassengerByEmail(String email);
}
