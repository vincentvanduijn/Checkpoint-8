package com.devoteam.VehicleApplication.service;

import com.devoteam.VehicleApplication.repository.VehicleUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Log4j2
@Repository
@RequiredArgsConstructor
public class VehicleUserDetailsService implements UserDetailsService {

    private final VehicleUserRepository vehicleUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(vehicleUserRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
