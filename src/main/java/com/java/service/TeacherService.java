package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Teacher;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public interface TeacherService {

    Teacher save(Teacher teacher);

    Teacher save(Teacher teacher, UUID lecternId);

    Teacher update(Teacher teacher);

    void delete(UUID id);

    List<Teacher> getAll();

    Optional<Teacher> getById(UUID userId);
    
    public JsonSchema getFields();

    List<Teacher> findByLectern(UUID uuid);
}