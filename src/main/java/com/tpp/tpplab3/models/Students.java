package com.tpp.tpplab3.models;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    private String name;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Groups group;

    @Column(name = "enrollment_year")
    private Integer enrollmentYear;

    // Геттери та сеттери
    public Integer getStudentId() { return studentId; }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Groups getGroup() { return group; }
    public void setGroup(Groups group) { this.group = group; }

    public Integer getEnrollmentYear() { return enrollmentYear; }
    public void setEnrollmentYear(Integer enrollmentYear) { this.enrollmentYear = enrollmentYear; }
}
