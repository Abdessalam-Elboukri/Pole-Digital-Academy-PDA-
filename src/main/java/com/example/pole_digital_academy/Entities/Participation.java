package com.example.pole_digital_academy.Entities;

import jakarta.persistence.*;

@Entity
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    @OneToOne
    private Activity activity;
    @OneToOne
    Participant participant;
    @Enumerated
    private ParticipationTypeEnum participationType;
    public Participation() {
    }
    public enum ParticipationTypeEnum{
        SIGNED_IN("inscris"),
        CANCELED("Annulé"),
        PRESENT("Présent"),
        ABSENT("Absent");
        private String name;

        ParticipationTypeEnum(String name) {
            this.name = name;
        }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
