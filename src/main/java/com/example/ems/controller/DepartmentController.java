package com.example.ems.controller;

import com.example.ems.dto.DepartmentDto;
import com.example.ems.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // Only ADMIN and HR can create departments
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentDto createDepartment(@Valid @RequestBody DepartmentDto dto) {
        return departmentService.createDepartment(dto);
    }

    // Everyone can view departments
    @GetMapping("/{id}")
    public DepartmentDto getById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @GetMapping
    public List<DepartmentDto> getAll() {
        return departmentService.getAllDepartments();
    }

    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    @PutMapping("/{id}")
    public DepartmentDto update(@PathVariable Long id,
                                @Valid @RequestBody DepartmentDto dto) {
        return departmentService.updateDepartment(id, dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }
}
