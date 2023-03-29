package de.allianz.springboot.config;

import de.allianz.springboot.entity.Role;
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
                .authorities(Role.MEMBER.getGrantedAuthorities())
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("12345"))
                //.password("{noop}admin")
                .authorities(Role.ADMIN.getGrantedAuthorities())
                .build();

        UserDetails analyst = User.builder()
                .username("analyst")
                .password(passwordEncoder.encode("12345"))
                //.password("{noop}analyst")
                .authorities(Role.ANALYST.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(userDetails, admin, analyst);
    }
}
