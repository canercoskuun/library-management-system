package com.example.user.service;

import com.example.user.dto.UserDto;
import com.example.user.enums.UserType;
import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User addUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setUserType(UserType.USER);
        userRepository.save(user);
        log.info("User with email {} has been successfully created.", user.getEmail());
        return user;
    }
    public User addSupervisor(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setUserType(UserType.LIBRARYSUPERVISOR);
        userRepository.save(user);
        log.info("Supervisor with email {} has been successfully created.", user.getEmail());
        return user;
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) {
        User user= userRepository.findByEmail(email);
        if(user==null){
            log.warn("User not found with email : {}", email);
            throw new RuntimeException("User not found");
        }
        return user;
    }

    public User updateUser(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());
        if(user==null){
            log.warn("User not found with email :  {}", userDto.getEmail());
            throw new RuntimeException("User not found ");
        }
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        log.info("User with email {} has been successfully updated.", user.getEmail());
        return  user;
    }

    public void deleteUser(String email) {
    User user = userRepository.findByEmail(email);
    if(user==null){
        log.warn("User not found with email {}", email);
        throw new RuntimeException("User not found");
    }
    userRepository.delete(user);
    log.info("User with email {} has been successfully deleted.", email);
    }
}

