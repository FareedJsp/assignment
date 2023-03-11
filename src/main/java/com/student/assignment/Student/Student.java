package com.student.assignment.Student;

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
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Student_Name")
    private String name;
    @Enumerated(EnumType.STRING)  // Add this line to use enum type
    @Column(name = "Gender")
    private GenderType gender;
    @Column(name = "Department")
    private String department;
    @Column(name = "Email")
    private String email;
    @Column(name = "CGPA")
    private float cgpa;
    @Column(name = "DateOfBirth")
    private LocalDate date_of_birth;

    public Student() {
        // Default constructor
    }
    
    public Student(String name, GenderType gender, String department, String email, float cgpa, LocalDate date_of_birth) {
        this.name = name;
        this.gender = gender;
        this.department = department;
        this.email = email;
        this.cgpa = cgpa;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getCgpa() {
        return cgpa;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
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

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", gender=" + gender + ", department=" + department + ", email="
                + email + ", cgpa=" + cgpa + ", date_of_birth=" + date_of_birth + "]";
    }

}


