package de.allianz.springboot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@RequiredArgsConstructor
public class UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    @Bean
    public InMemoryUserDetailsManager user(){
        UserDetails userDetails = User.builder()
                .username("user")
                .password(passwordEncoder.encode("1234"))
                .roles("USER_ROLE")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("12345"))
                //.password("{noop}admin")
                .roles("USER_ROLE", "ADMIN_ROLE")
                .build();
        return new InMemoryUserDetailsManager(userDetails, admin);
    }
}
