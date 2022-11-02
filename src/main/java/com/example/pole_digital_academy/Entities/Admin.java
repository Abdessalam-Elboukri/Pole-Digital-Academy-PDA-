package com.example.pole_digital_academy.Entities;

import com.example.pole_digital_academy.utils.PasswordHasher;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends User{
    @Column(nullable = false)
    private String passwordHash;


    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPassword(String password) {
        this.passwordHash = PasswordHasher.hash(password);
    }
}
