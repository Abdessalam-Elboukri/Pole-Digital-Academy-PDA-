package com.example.pole_digital_academy.Entities;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {

    public static final String KEY_ID="id";
    public static final String KEY_FIRST_NAME="firstname";
    public static final String KEY_LAST_NAME="lastname";
    public static final String KEY_EMAIL="email";
    public static final String KEY_PHONE="phone";
    public static final String KEY_ROLE="role";
    public static final String KEY_USER_STATUS="user_status";


    @Id
    @GeneratedValue (strategy = GenerationType.TABLE)
    private int id;

    @Column(name = "first_name")
    private String  firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private UserStatusEnum userStatus;

    public enum Role{
        ADMIN,
        RESPONSIBLE,
        PARTICIPANT
    }

    public enum UserStatusEnum {
        ACTIVE,
        DISABLED
    }


    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserStatusEnum getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatusEnum userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                ", userStatus=" + userStatus +
                '}';
    }
}

