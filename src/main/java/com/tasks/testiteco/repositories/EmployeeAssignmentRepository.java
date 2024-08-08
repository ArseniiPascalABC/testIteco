package com.tasks.testiteco.repositories;

import com.tasks.testiteco.models.EmployeeAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeAssignmentRepository extends JpaRepository<EmployeeAssignment, Long> {
    //Методы перегружу для более легкой читаемости в рамках тестового задания
    //Две реализации как минимум есть
    //1) Либо дважды передать пару дат
    List<EmployeeAssignment> findByDepartmentIdAndEmploymentDateBetweenOrDismissalDateBetween(Long departmentId, Date employmentDate, Date dissmissalDate, Date employmentDate1, Date dismissalDate1);
    //2) Либо писать запрос в ручную
    @Query("SELECT employeeAssignment FROM EmployeeAssignment employeeAssignment WHERE employeeAssignment.department.id = :departmentId AND (:date BETWEEN employeeAssignment.employmentDate AND COALESCE(employeeAssignment.dismissalDate, :date))")
    List<EmployeeAssignment> findByDepartmentIdAndEmploymentDateBetweenOrDismissalDateBetween(@Param("departmentId") Long departmentId, @Param("date") Date date);

    Optional<EmployeeAssignment> findByEmployeeIdAndDismissalDateIsNull(Long employeeId);
}
