package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Employee;
import com.java.repository.EmployeeRepository;
import com.java.repository.DeaneryRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DeaneryRepository deaneryRepository;

    @Override
    public Employee save(Employee obj, UUID id) {
        if (id != null) {
            obj.setDeanery(deaneryRepository.findById(id).get());
        }
        return employeeRepository.save(obj);
    }

    @Override
    public Employee update(Employee obj) {
        Optional<Employee> employee = employeeRepository.findById(obj.getId());
        if (employee.isPresent()) {
            employee.get().setId(obj.getId());
            employee.get().setSurname(obj.getSurname());
            employee.get().setName(obj.getName());
            employee.get().setRank(obj.getRank());
            employee.get().setPatronymic(obj.getPatronymic());
            if (obj.getDeanery() != null) {
                employee.get().setDeanery(obj.getDeanery());
            }
            return employeeRepository.save(employee.get());
        }
        return null;
        //obj.setDeanery(deaneryRepository.findById(obj.getDeanery().getId()).get());
    }

    @Override
    public void delete(UUID id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getById(UUID id) {
        return employeeRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try {
            mapper.acceptJsonFormatVisitor(Employee.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx) {
            return null;
        }
    }

    @Override
    public List<Employee> findByDeanery(UUID uuid) {
        return employeeRepository.findByDeaneryId(uuid);
    }
}
