package com.example.pole_digital_academy.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "participants")
public class Participant extends User{
    public static final String KEY_DOMAINE="domaine";

    private String domaine;

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }
}
