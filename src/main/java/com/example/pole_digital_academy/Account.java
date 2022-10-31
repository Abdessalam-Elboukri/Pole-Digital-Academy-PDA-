package com.example.pole_digital_academy;


import jakarta.persistence.*;
@Entity
@Table(name = "accounts")
public class Account {

    float balance;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    public Account() {
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
