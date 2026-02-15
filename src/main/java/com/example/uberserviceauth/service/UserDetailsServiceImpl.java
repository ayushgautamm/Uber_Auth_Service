package com.example.uberserviceauth.service;

import com.example.uberserviceauth.helpers.AuthPassengerDetails;
import com.example.uberserviceauth.models.Passenger;
import com.example.uberserviceauth.repository.PassengerRespository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This Class is responsible for the loading of the user in the form of UserDetails object for auth
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private PassengerRespository passengerRespository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Passenger> passenger = passengerRespository.findPassengerByEmail(email);

        if(passenger.isPresent()) {
            return new AuthPassengerDetails(passenger.get());
        }
        throw new UsernameNotFoundException("Could not find user with email " + email);
    }
}
