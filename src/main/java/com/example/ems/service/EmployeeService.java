package com.example.ems.service;

import com.example.ems.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto dto);

    EmployeeDto updateEmployee(Long id, EmployeeDto dto);

    EmployeeDto getEmployeeById(Long id);

    List<EmployeeDto> getAllEmployees();

    List<EmployeeDto> getEmployeesByDepartment(Long departmentId);

    void deleteEmployee(Long id);
}
