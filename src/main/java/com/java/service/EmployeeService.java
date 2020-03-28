package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Employee;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public interface EmployeeService {

    Employee save(Employee employee);

    Employee update(Employee employee);

    void delete(UUID id);

    List<Employee> getAll();

    Optional<Employee> getById(UUID userId);
    
    public JsonSchema getFields();

    List<Employee> findByDeanery(UUID uuid);
}