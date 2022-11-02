package com.example.pole_digital_academy.Entities;


import jakarta.persistence.*;

@Entity
@Table(name = "responsible")
public class Responsible extends User{
    private String domaine;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "res_type", unique = false, referencedColumnName = "id")
    private ResponsibleType res_type;

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public ResponsibleType getRes_type() {
        return res_type;
    }

    public void setRes_type(ResponsibleType res_type) {
        this.res_type = res_type;
    }
}