package com.example.pole_digital_academy.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "exercices")
public class Exercice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private int year;
    @Enumerated(EnumType.ORDINAL)
    private ExerciceStatusEnum status=ExerciceStatusEnum.NOT_SET;
    LocalDate startDate;
    LocalDate endDate;


   // @JoinColumn(name = "activity_id",referencedColumnName = "id",nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    private Activity activity;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    //TODO:: add activity
    public enum ExerciceStatusEnum{
        IN_PROGRESS("En cours"),
        DONE("Faite"),
        CANCELED("annulé"),
        NOT_SET("inconnue");
        private String name;
        ExerciceStatusEnum(String name){
            this.name=name;
        }

        public String getName() {
            return name;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ExerciceStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ExerciceStatusEnum status) {
        this.status = status;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
