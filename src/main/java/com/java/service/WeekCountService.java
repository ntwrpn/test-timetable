package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.WeekCount;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public interface WeekCountService {

    WeekCount save(WeekCount WeekCount);

    WeekCount update(WeekCount WeekCount);

    void delete(UUID id);

    List<WeekCount> getAll();

    Optional<WeekCount> getById(UUID userId);
    
    public JsonSchema getFields();
}