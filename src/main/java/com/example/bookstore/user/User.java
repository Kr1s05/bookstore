package com.example.bookstore.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false)
    private String username;

    @Column(length = 45,nullable = false)
    private String password;

    @Column(nullable = false)
    @Email(message = "Enter a valid email!")
    private String email;
}
