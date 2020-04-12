package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Timetable;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public interface TimetableService {

    Timetable save(Timetable Timetable);

    Timetable update(Timetable Timetable);

    void delete(UUID id);

    List<Timetable> getAll();

    Optional<Timetable> getById(UUID userId);
    
    public JsonSchema getFields();
}