package com.example.pole_digital_academy.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Activity {
    public static final String KEY_ID="id";
    public static final String KEY_TITLE="title";
    public static final String KEY_DESCRIPTION="description";
    public static final String KEY_START_DATE="start_date";
    public static final String KEY_END_DATE="end_date";
    public static final String KEY_ACTIVITY_TYPE="activity_type";
    public static final String KEY_ACTIVITY_STATUS="activity_status";
    public static final String KEY_RESPONSIBLE_ID ="responsible_id";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String title ;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    @OneToMany(mappedBy = "activity",cascade = {CascadeType.REMOVE})
    private List<Participation> participation =new ArrayList<>();
    @Enumerated(EnumType.ORDINAL)
    private ActivityTypeEnum activityType=null;
    @Enumerated(EnumType.ORDINAL)
    private ActivityStatusEnum status=null;
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "responsible_id",referencedColumnName = "id")
    private Responsible responsible;
    @OneToMany(cascade = CascadeType.MERGE,mappedBy = "activity",fetch = FetchType.EAGER)
    private List<Exercice> exercices=new ArrayList<>();

    public enum ActivityTypeEnum{
        COURSE("Formation"),TALK("Talk"),EVENT("Evénement");
        private String name;
        private  ActivityTypeEnum(String name){
            this.name=name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }
    public enum ActivityStatusEnum{
        ACTIVE("Activé"),INACTIVE("Désactivé");
        private String name;
        ActivityStatusEnum(String name){
            this.name =name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<Participation> getParticipation() {
        return participation;
    }

    public void setParticipation(List<Participation> participation) {
        this.participation = participation;
    }

    public ActivityTypeEnum getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityTypeEnum activityType) {
        this.activityType = activityType;
    }

    public ActivityStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ActivityStatusEnum status) {
        this.status = status;
    }

    public Responsible getResponsible() {
        return responsible;
    }

    public void setResponsible(Responsible responsible) {
        this.responsible = responsible;
    }

    public List<Exercice> getExercices() {
        return exercices;
    }

    public void setExercices(List<Exercice> exercices) {
        this.exercices = exercices;
    }
}
