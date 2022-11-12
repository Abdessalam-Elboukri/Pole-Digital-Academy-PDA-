package com.example.pole_digital_academy.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String title ;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    @Enumerated(EnumType.ORDINAL)
    private ActivityTypeEnum activityType=null;
    @Enumerated(EnumType.ORDINAL)
    private ActivityStatusEnum status=null;
    @ManyToOne
    @JoinColumn(name = "responsible_id",referencedColumnName = "id")
    private Responsible responsible;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "activity")
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
}
