package com.example.bookstore.user;

import com.example.bookstore.Exceptions.RegisterException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;

@Service
@AllArgsConstructor
public class UserService {
    final
    UserRepository userRepository;


    public void registerUser(User user) throws RegisterException {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) throw new RegisterException("Username already exists.");
        if (userRepository.findByEmail(user.getEmail()).isPresent()) throw new RegisterException("Email already in use.");

        userRepository.save(user);
    }

}
