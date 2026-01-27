package com.egineeringdigest.journalApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Profile("dev")
public class SpringSecurity{

      @Bean
      public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
          http
                  .csrf(csrf -> csrf.disable())
                  .authorizeHttpRequests( auth -> auth
                  .requestMatchers("/journal/**").authenticated()
                          .requestMatchers("/user/**").authenticated()
                          .requestMatchers("/admin/**").hasRole("ADMIN")
                          .anyRequest().permitAll()
               )

                  .httpBasic();

          return http.build();
      }
      @Bean
      public PasswordEncoder passwordEncoder(){
          return new BCryptPasswordEncoder();
      }

      @Bean
      public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
              return configuration.getAuthenticationManager();
    }
}
