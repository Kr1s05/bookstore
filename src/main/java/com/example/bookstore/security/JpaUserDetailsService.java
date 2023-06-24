package com.example.bookstore.security;

import com.example.bookstore.user.User;
import com.example.bookstore.user.UserRepository;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
public class JpaUserDetailsService implements UserDetailsService {
    final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) user = userRepository.findByEmail(username);
        return user.map(SecurityUser::new).orElseThrow(() -> new UsernameNotFoundException("Username or email not found"));
    }
}
