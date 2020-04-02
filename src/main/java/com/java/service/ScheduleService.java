package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Schedule;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public interface ScheduleService {

    Schedule save(Schedule Schedule, UUID id);

    Schedule update(Schedule Schedule);

    void delete(UUID id);

    List<Schedule> getAll();

    Optional<Schedule> getById(UUID userId);
    
    public JsonSchema getFields();

    List<Schedule> findByStudyPlan(UUID id);
}