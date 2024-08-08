package com.tasks.testiteco.services;

import com.tasks.testiteco.models.Department;
import com.tasks.testiteco.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    public void saveDepartment(Department department) {
        departmentRepository.save(department);
    }

    public List<Department> getDepartmentsByDate(Date date) {
        return departmentRepository.findByDateOfCreationBeforeAndDateOfClosingAfterOrDateOfClosingIsNull(date);
    }
}


