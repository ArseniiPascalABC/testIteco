package com.tasks.testiteco.services;

import com.tasks.testiteco.models.EmployeeAssignment;
import com.tasks.testiteco.repositories.EmployeeAssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeAssignmentService {
    private final EmployeeAssignmentRepository employeeAssignmentRepository;

    public EmployeeAssignment getCurrentAssignment(Long employeeId) {
        Optional<EmployeeAssignment> optionalAssignment = employeeAssignmentRepository.findByEmployeeIdAndDismissalDateIsNull(employeeId);
        return optionalAssignment.orElse(null);
    }

    public void updateAssignment(EmployeeAssignment assignment) {
        employeeAssignmentRepository.save(assignment);
    }

    public void createAssignment(EmployeeAssignment assignment) {
        employeeAssignmentRepository.save(assignment);
    }

    public List<EmployeeAssignment> getAllAssignments() {
        return employeeAssignmentRepository.findAll();
    }

    public List<EmployeeAssignment> getAssignmentsByDepartmentAndPeriod(Long departmentId, Date assigmentDate) {
        return employeeAssignmentRepository.findByDepartmentIdAndEmploymentDateBetweenOrDismissalDateBetween(departmentId, assigmentDate);
    }

    public void saveAssignment(EmployeeAssignment assignment) {
        employeeAssignmentRepository.save(assignment);
    }

}
