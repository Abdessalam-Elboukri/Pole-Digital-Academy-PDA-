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

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public ParticipationTypeEnum getParticipationType() {
        return participationType;
    }

    public void setParticipationType(ParticipationTypeEnum participationType) {
        this.participationType = participationType;
    }

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
