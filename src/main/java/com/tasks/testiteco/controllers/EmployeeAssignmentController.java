package com.tasks.testiteco.controllers;

import com.tasks.testiteco.models.Department;
import com.tasks.testiteco.models.Employee;
import com.tasks.testiteco.models.EmployeeAssignment;
import com.tasks.testiteco.services.DepartmentService;
import com.tasks.testiteco.services.EmployeeAssignmentService;
import com.tasks.testiteco.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/assignments")
@RequiredArgsConstructor
@Slf4j
public class EmployeeAssignmentController {
    private final EmployeeAssignmentService employeeAssignmentService;
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    @Operation(summary = "Get all assigments", description = "use this when you already added some departments and employees")
    @GetMapping
    public String getAllAssignments(Model model) {
        List<EmployeeAssignment> assignments = employeeAssignmentService.getAllAssignments();
        model.addAttribute("assignments", assignments);
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "assignments";
    }

    @Operation(summary = "Get assignment by date", description = "You can choose the date and click button, just easy")
    @GetMapping("/byPeriod")
    public String getAssignmentsByPeriod(@RequestParam("departmentId") Long departmentId,
                                         @RequestParam("date") String dateString,
                                         Model model) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            model.addAttribute("error", "Invalid date format. Please use yyyy-MM-dd.");
            log.error("Invalid date format. Please use yyyy-MM-dd.");
            return "error";
        }

        List<EmployeeAssignment> assignments = employeeAssignmentService.getAssignmentsByDepartmentAndPeriod(departmentId, date);
        model.addAttribute("assignments", assignments);

        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "assignments";
    }

    @Operation(summary = "Change assignment", description = "Don't forget about id in url, and choose new department for employer")
    @GetMapping("/change/{id}")
    public String showChangeAssignmentsForm(@PathVariable("id") Long employeeId, Model model) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departments);
        return "change_assignments";
    }

    @PostMapping("/change/{id}")
    @Transactional
    public String changeAssignment(@PathVariable("id") Long employeeId,
                                   @RequestParam("departmentId") String departmentId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        Department newDepartment = null;

        if (!departmentId.equals("none")) {
            Optional<Department> optionalDepartment = departmentService.getDepartmentById(Long.parseLong(departmentId));
            if (optionalDepartment.isPresent()) {
                newDepartment = optionalDepartment.get();
            } else {
                return "redirect:/error";
            }
        }

        EmployeeAssignment currentAssignment = employeeAssignmentService.getCurrentAssignment(employeeId);

        if (currentAssignment != null) {
            currentAssignment.setDismissalDate(new Date());
            employeeAssignmentService.updateAssignment(currentAssignment);
        }

        if (newDepartment != null) {
            EmployeeAssignment newAssignment = new EmployeeAssignment();
            newAssignment.setEmployee(employee);
            newAssignment.setDepartment(newDepartment);
            newAssignment.setEmploymentDate(new Date());
            employeeAssignmentService.createAssignment(newAssignment);
        }

        return "redirect:/assignments";
    }

    @PostMapping("/save")
    @Operation(summary = "Save your updated assignment")
    public String saveAssignment(EmployeeAssignment assignment) {
        employeeAssignmentService.saveAssignment(assignment);
        return "redirect:/assignments";
    }
}
