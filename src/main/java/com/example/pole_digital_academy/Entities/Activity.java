package com.example.pole_digital_academy.Entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Activity {
    public static final String KEY_ID="id";
    public static final String KEY_TITLE="title";
    public static final String KEY_DESCRIPTION="description";
    public static final String KEY_START_DATE="start_date";
    public static final String KEY_END_DATE="end_date";
    public static final String KEY_ACTIVITY_TYPE="end_date";
    public static final String KEY_ACTIVITY_STATUS="end_date";
    public static final String KEY_RESPONSIBLE="end_date";
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String title ;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.ORDINAL)
    private ActivityTypeEnum activityType=ActivityTypeEnum.COURSE;

    @Enumerated(EnumType.ORDINAL)
    private ActivityStatusEnum status=ActivityStatusEnum.ACTIVE;

    @OneToOne
    private Responsible responsible;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Exercice> exercices;
    public void setId(int id) {
        this.id = id;
    }

    public ActivityTypeEnum getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityTypeEnum activityType) {
        this.activityType = activityType;
    }

    public Responsible getResponsible() {
        return responsible;
    }

    public void setResponsible(Responsible responsible) {
        this.responsible = responsible;
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

    public ActivityStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ActivityStatusEnum status) {
        this.status = status;
    }

    public List<Exercice> getExercices() {
        return exercices;
    }

    public void setExercices(List<Exercice> exercices) {
        this.exercices = exercices;
    }
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

}
