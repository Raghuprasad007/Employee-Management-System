package com.example.ems.service.impl;

import com.example.ems.dto.DepartmentDto;
import com.example.ems.entity.Department;
import com.example.ems.exception.ResourceAlreadyExistsException;
import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.repository.DepartmentRepository;
import com.example.ems.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    private DepartmentDto mapToDto(Department department) {
        DepartmentDto dto = new DepartmentDto();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setDescription(department.getDescription());
        return dto;
    }

    private Department mapToEntity(DepartmentDto dto) {
        Department department = new Department();
        department.setId(dto.getId());
        department.setName(dto.getName());
        department.setDescription(dto.getDescription());
        return department;
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto dto) {
        if (departmentRepository.existsByNameIgnoreCase(dto.getName())) {
            throw new ResourceAlreadyExistsException("Department with name '" + dto.getName() + "' already exists");
        }
        Department department = mapToEntity(dto);
        Department saved = departmentRepository.save(department);
        return mapToDto(saved);
    }

    @Override
    public DepartmentDto updateDepartment(Long id, DepartmentDto dto) {
        Department existing = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());

        Department updated = departmentRepository.save(existing);
        return mapToDto(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentDto getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
        return mapToDto(department);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
        departmentRepository.delete(department);
    }
}
