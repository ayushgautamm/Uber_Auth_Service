package com.example.uberserviceauth.helpers;

import com.example.uberserviceauth.models.Passenger;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
//why we need this clssa?
// Because Spring Security works on UserDetails polymorphic type for auth
@Getter
@Setter
public class AuthPassengerDetails extends Passenger implements UserDetails {

    private String username;
    private String password;

    public AuthPassengerDetails(Passenger passenger) {
        this.username =  passenger.getEmail();
        this.password = passenger.getPassword();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override

    public String getUsername() {
        return this.username;
    }

   public  String getPassword() {
        return this.password;
   }
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
