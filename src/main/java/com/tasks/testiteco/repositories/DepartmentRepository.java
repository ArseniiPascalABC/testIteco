package com.tasks.testiteco.repositories;

import com.tasks.testiteco.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    //Методы перегружу для более легкой читаемости в рамках тестового задания
    //Две реализации как минимум есть
    //1) Либо дважды передать одну и ту же дату
    List<Department> findByDateOfCreationBeforeAndDateOfClosingAfterOrDateOfClosingIsNull(Date date, Date date2);
    //2) Либо писать запрос в ручную
    @Query("SELECT department FROM Department department WHERE department.dateOfCreation <= :date AND (department.dateOfClosing IS NULL OR department.dateOfClosing >= :date)")
    List<Department> findByDateOfCreationBeforeAndDateOfClosingAfterOrDateOfClosingIsNull(Date date);
}
