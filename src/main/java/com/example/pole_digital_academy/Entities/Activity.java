package com.example.pole_digital_academy.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "activities")
@Inheritance(strategy = InheritanceType.JOINED)
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title ;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.ORDINAL)
    private ActivityTypeEnum activityType=ActivityTypeEnum.COURSE;
    @Enumerated(EnumType.ORDINAL)
    private ActivityStatusEnum status=ActivityStatusEnum.ACTIVE;
    //TODO:: responsible
    //TODO:: fix oneToMany relationship
    @OneToMany(mappedBy = "activity",cascade = CascadeType.ALL)
    private List<Exercice> exercices;

    public int getId() {
        return id;
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

}
