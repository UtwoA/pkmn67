package ru.mirea.uglovaa.pkmn.models;

import lombok.*;
import ru.mirea.uglovaa.pkmn.entities.StudentEntity;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student implements Serializable  {
    public static final long serialVersionUID = 1L;
    private String firstName;
    private String surName;
    private String familyName;
    private String group;

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}