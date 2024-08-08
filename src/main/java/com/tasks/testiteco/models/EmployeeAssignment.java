package com.tasks.testiteco.models;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "employee_assignments")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(name = "employment_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date employmentDate;

    @Column(name = "dismissal_date")
    @Temporal(TemporalType.DATE)
    private Date dismissalDate;

    public EmployeeAssignment(Employee employee, Department department, Date employmentDate, Date dismissalDate) {
        this.employee = employee;
        this.department = department;
        this.employmentDate = employmentDate;
        this.dismissalDate = dismissalDate;
    }

}
