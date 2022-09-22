package com.devoteam.VehicleApplication.config;

import com.devoteam.VehicleApplication.service.VehicleUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final VehicleUserDetailsService vehicleUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers("/vehicles/admin/**").hasRole("ADMIN")
                .antMatchers("/vehicles/**").hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        log.info("Testing encoder password {}", passwordEncoder.encode("root"));

        auth.inMemoryAuthentication()
                .withUser("USER")
                .password(passwordEncoder.encode("root"))
                .roles("USER")
                .and()
                .withUser("ADMIN")
                .password(passwordEncoder.encode("root"))
                .roles("USER", "ADMIN");

        auth.userDetailsService(vehicleUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}
