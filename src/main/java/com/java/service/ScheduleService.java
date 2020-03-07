package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Schedule;
import org.json.simple.JSONObject;

public interface ScheduleService {

    Schedule save(Schedule Schedule);

    Schedule update(Schedule Schedule);

    void delete(UUID id);

    List<Schedule> getAll();

    Optional<Schedule> getById(UUID userId);
    
    public JSONObject getFields();
}