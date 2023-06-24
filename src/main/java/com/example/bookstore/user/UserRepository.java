package com.example.bookstore.user;

import com.example.bookstore.security.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    List<User> findAllByRole(Role role);

    @Modifying
    @Transactional
    @Query("update User u set u.role = :role where u.username = :username")
    void setRoleByUsername(@Param("username") String username, @Param("role") Role role);
}
