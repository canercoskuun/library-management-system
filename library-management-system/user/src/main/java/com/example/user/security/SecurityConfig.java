package com.example.user.security;

import com.example.user.enums.UserType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

/*
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails admin= User.withUsername("admin")
                .password(encoder().encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails user= User.withUsername("user")
                .password(encoder().encode("user"))
                .roles("USER")
                .build();
        UserDetails supervisor= User.withUsername("supervisor")
                .password(encoder().encode("supervisor"))
                .roles("LIBRARYSUPERVISOR")
                .build();
        return new InMemoryUserDetailsManager(admin,user,supervisor);
    }

*/


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/users/create-user").permitAll()) // herkes kullanıcı kaydı olabilir
                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/users/create-supervisor").hasRole(UserType.ADMIN.name())) // sadece admin supervisor kaydedebilir
                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/users/all"
                        ,"api/users/get-user-by-email").hasAnyRole(UserType.ADMIN.name(),UserType.LIBRARYSUPERVISOR.name())) // admin ve supervisor herkesi görebilir
                .authorizeHttpRequests(auth-> auth.anyRequest().authenticated())
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults());

        return security.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }




}
