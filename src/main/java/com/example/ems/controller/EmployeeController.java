package com.example.ems.controller;

import com.example.ems.dto.EmployeeDto;
import com.example.ems.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Only ADMIN and HR can create employees
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createEmployee(@Valid @RequestBody EmployeeDto dto) {
        return employeeService.createEmployee(dto);
    }

    // Everyone can view employees
    @GetMapping("/{id}")
    public EmployeeDto getById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeDto> getAll() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/department/{departmentId}")
    public List<EmployeeDto> getByDepartment(@PathVariable Long departmentId) {
        return employeeService.getEmployeesByDepartment(departmentId);
    }

    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    @PutMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable Long id,
                                      @Valid @RequestBody EmployeeDto dto) {
        return employeeService.updateEmployee(id, dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
