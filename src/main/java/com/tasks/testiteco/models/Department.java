package com.tasks.testiteco.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date_of_creation", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfCreation;

    @Column(name = "date_of_closing")
    @Temporal(TemporalType.DATE)
    private Date dateOfClosing;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Department parentDepartment;

    @OneToMany(mappedBy = "parentDepartment")
    private Set<Department> subDepartments;

    public Department(String name, Date dateOfCreation, Date dateOfClosing, Department parentDepartment) {
        this.name = name;
        this.dateOfCreation = dateOfCreation;
        this.dateOfClosing = dateOfClosing;
        this.parentDepartment = parentDepartment;
    }

}
