package com.bookStore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.bookStore.enums.Role.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        // Customer permissions
                        .requestMatchers(HttpMethod.GET, "/api/profile/**").hasRole(CUSTOMER.name())
                        .requestMatchers(HttpMethod.PUT, "/api/profile/**").hasRole(CUSTOMER.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/profile/**").hasRole(CUSTOMER.name())
                        .requestMatchers(HttpMethod.POST, "/api/profile/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/bookings/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/bookings/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/bookings/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/bookings/**").authenticated()// Register new users

                        // Manager permissions
                        .requestMatchers(HttpMethod.GET, "/api/products/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/products/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/products/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/products/**").authenticated()

                        .requestMatchers(HttpMethod.GET, "/api/bookings/**").hasRole(MANAGER.name())
                        .requestMatchers(HttpMethod.POST, "/api/bookings/**").hasRole(MANAGER.name())
                        .requestMatchers(HttpMethod.PUT, "/api/bookings/**").hasRole(MANAGER.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/bookings/**").hasRole(MANAGER.name())

                        .requestMatchers(HttpMethod.GET, "/api/users/**").authenticated()/*hasRole(MANAGER.name())*/
                        .requestMatchers(HttpMethod.POST, "/api/users/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/users/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/users/**").authenticated()

                        // Admin permissions
                        .requestMatchers(HttpMethod.GET, "/api/users/**").authenticated()/*hasRole(ADMIN.name())*/
                        .requestMatchers(HttpMethod.POST, "/api/users/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/users/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/users/**").authenticated()

                        .anyRequest().authenticated()
                ).formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {

        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .roles(ADMIN.name()).build();
        UserDetails manager = User.builder()
                .username("manager")
                .password(encoder.encode("manager"))
                .roles(MANAGER.name()).build();
        UserDetails customer = User.builder()
                .username("customer")
                .password(encoder.encode("customer"))
                .roles(CUSTOMER.name()).build();
        return new InMemoryUserDetailsManager(admin, manager, customer);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
