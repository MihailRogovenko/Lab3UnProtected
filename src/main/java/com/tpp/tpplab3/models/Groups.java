package com.tpp.tpplab3.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "groups")
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupId;

    @NotBlank
    private String name;

    @NotBlank
    private String department;

    @OneToOne
    @JoinColumn(name = "curator_id", nullable = false)
    private Curators curator;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Students> students;

    // Гетери та сетери
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Curators getCurator() {
        return curator;
    }

    public void setCurator(Curators curator) {
        this.curator = curator;
    }

    public List<Students> getStudents() {
        return students;
    }

    public void setStudents(List<Students> students) {
        this.students = students;
    }
}
