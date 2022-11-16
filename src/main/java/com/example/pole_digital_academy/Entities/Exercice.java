package com.example.pole_digital_academy.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exercices")
public class Exercice {
    public static final String KEY_ID="id";
    public static final String KEY_TITLE="title";
    public static final String KEY_YEAR="year";
    public static final String KEY_STATUS="status";
    public static final String KEY_START_DATE="start_date";
    public static final String KEY_END_DATE="end_date";
    public static final String KEY_ACTIVITY_ID="activity_id";


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String title;
    private int year;
    @Enumerated(EnumType.ORDINAL)
    private ExerciceStatusEnum status=ExerciceStatusEnum.NOT_SET;
    LocalDate startDate;
    LocalDate endDate;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "activity_id",referencedColumnName = "id")
    private Activity activity;
    public enum ExerciceStatusEnum{
        IN_PROGRESS("En cours"),
        DONE("Faite"),
        CANCELED("annulé"),
        NOT_SET("inconnue");
        private String name;
        ExerciceStatusEnum(String name){
            this.name=name;
        }
        @Override
        public String toString() {
            return name;
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

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
