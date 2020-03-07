package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.StudyPlan;
import org.json.simple.JSONObject;

public interface StudyPlanService {

    StudyPlan save(StudyPlan StudyPlan);

    StudyPlan update(StudyPlan StudyPlan);

    void delete(UUID id);

    List<StudyPlan> getAll();

    Optional<StudyPlan> getById(UUID userId);
    
    public JSONObject getFields();
}