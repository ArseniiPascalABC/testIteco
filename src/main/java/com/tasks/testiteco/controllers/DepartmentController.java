package com.tasks.testiteco.controllers;

import com.tasks.testiteco.models.Department;
import com.tasks.testiteco.services.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/departments")
@RequiredArgsConstructor
@Slf4j
public class DepartmentController {
    private final DepartmentService departmentService;

    @Operation(summary = "Get all departments")
    @GetMapping
    public String getAllDepartments(Model model) {
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "departments";
    }

    @Operation(summary = "Add new department")
    @GetMapping("/new")
    public String showNewEmployeeForm(Model model) {
        model.addAttribute("department", new Department());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "new_department";
    }

    @Operation(summary = "Get existing departments by date", description = "Your request must be like http://localhost:8080/departments/byDate?date=2024-08-07")
    @GetMapping("/byDate")
    public String getDepartmentsByDate(@RequestParam("date") String dateString, Model model) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            model.addAttribute("error", "Invalid date format. Please use yyyy-MM-dd.");
            log.error("Invalid date format. Please use yyyy-MM-dd.");
            return "error";
        }

        List<Department> departments = departmentService.getDepartmentsByDate(date);
        model.addAttribute("departments", departments);
        return "departments";
    }

    @Operation(summary = "Save new department with or without parent department", description = "Just select parent, but don't forget - you can skip this step")
    @PostMapping("/save")
    public String saveDepartment(Department department, @RequestParam(value = "parentDepartmentId", required = false) Long parentDepartmentId) {
        department.setDateOfCreation(Date.from(Instant.now()));
        if (parentDepartmentId != null) {
            department.setParentDepartment(departmentService.getDepartmentById(parentDepartmentId).orElse(null));
        }
        departmentService.saveDepartment(department);
        return "redirect:/departments";
    }
}
