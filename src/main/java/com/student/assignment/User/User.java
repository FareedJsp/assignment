package com.student.assignment.User;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Author_Name")
    private String name;
    @Enumerated(EnumType.STRING)  // Add this line to use enum type
    @Column(name = "Gender")
    private GenderType gender;
    @Column(name = "Email")
    private String email;
    @Column(name = "Description")
    private String description;
    @Column(name = "DateOfBirth")
    private LocalDate date_of_birth;

    public User() {
    }

    public User(String name, GenderType gender, String email, String description, LocalDate date_of_birth) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.description = description;
        this.date_of_birth = date_of_birth;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public int getAge() {
        LocalDate today = LocalDate.now();
        return Period.between(date_of_birth, today).getYears();
    }

    public enum GenderType {
        MALE,
        FEMALE
    }
}
