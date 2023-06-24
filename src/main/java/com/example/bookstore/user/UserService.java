package com.example.bookstore.user;

import com.example.bookstore.exceptions.RegisterException;
import com.example.bookstore.security.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    final
    UserRepository userRepository;

    final
    PasswordEncoder encoder;

    public void registerUser(User user) throws RegisterException {
        if (userRepository.findByUsername(user.getUsername()).isPresent())
            throw new RegisterException("Username already exists.");
        if (userRepository.findByEmail(user.getEmail()).isPresent())
            throw new RegisterException("Email already in use.");
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setActive(true);
        userRepository.save(user);
    }

    public List<User> getAdmins(){
        return userRepository.findAllByRole(Role.ADMIN);
    }

    public void setAdmin(String username) {
        userRepository.setRoleByUsername(username, Role.ADMIN);
    }
}
