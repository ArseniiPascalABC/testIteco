package com.tasks.testiteco.controllers;

import com.tasks.testiteco.models.Department;
import com.tasks.testiteco.models.Employee;
import com.tasks.testiteco.models.EmployeeAssignment;
import com.tasks.testiteco.services.DepartmentService;
import com.tasks.testiteco.services.EmployeeAssignmentService;
import com.tasks.testiteco.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    private final DepartmentService departmentService;
    private final EmployeeAssignmentService employeeAssignmentService;

    @GetMapping
    @Operation(summary = "Get all employers")
    public String getAllEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "employees";
    }

    @GetMapping("/new")
    @Operation(summary = "Save new employer")
    public String showNewEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "new_employee";
    }

    @PostMapping("/save")
    @Transactional
    @Operation(summary = "Page with employer settings", description = ", description = Choose department and employer creditnails, click button, it's done")
    public String saveEmployee(Employee employee, @RequestParam("departmentId") Long departmentId) {
        Department department = departmentService.getDepartmentById(departmentId).orElse(null);
        if (department != null) {
            EmployeeAssignment assignment = new EmployeeAssignment();
            assignment.setEmployee(employee);
            assignment.setDepartment(department);
            assignment.setEmploymentDate(new Date());
            employeeService.saveEmployee(employee);
            employeeAssignmentService.saveAssignment(assignment);
        } else {
            return "redirect:/employees/new";
        }
        return "redirect:/employees";
    }
}
