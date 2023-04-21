package com.mrex.mrexapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "firstname", nullable = false)
    private String firstName;
    @Column(name = "lastname", nullable = false)
    private String lastName;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "password", nullable = false, length = 20)
    private String password;
    @Column(unique = true, name = "username", nullable = false, columnDefinition = "VARCHAR(20)")
    private String username;
    @Column(name = "phone_number")
    private String phoneNumber;
}
