package com.staj.demo.security;

import com.staj.demo.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.HashSet;

@Component
public class UserDetailsServiceImpl  implements UserDetailsService {


    private final WebClient webClient;

    public UserDetailsServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = webClient.get().uri("http://localhost:8089/api/users/get-user-by-email?email="+username)
                .headers(httpHeaders -> httpHeaders.setBasicAuth("admin", "admin"))
                .retrieve()
                .bodyToMono(User.class)
                .block();
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        HashSet<GrantedAuthority> setAuthorities = new HashSet<>();
        setAuthorities.add(new SimpleGrantedAuthority(user.getUserType().name()));
        return new UserDetailsImpl(user, new ArrayList<>(setAuthorities));
    }
}
