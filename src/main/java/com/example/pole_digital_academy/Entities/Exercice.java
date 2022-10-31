package com.example.pole_digital_academy.Entities;

public class Exercice {
    private int id;
    private int year;
    private int status=ExerciceStatusEnum.CANCELED.ordinal();

    enum ExerciceStatusEnum{
        IN_PROGRESS,
        DONE,
        CANCELED
    }

}
