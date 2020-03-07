package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.StudyPlanStatus;
import org.json.simple.JSONObject;

public interface StudyPlanStatusService {

    StudyPlanStatus save(StudyPlanStatus StudyPlanStatus);

    StudyPlanStatus update(StudyPlanStatus StudyPlanStatus);

    void delete(UUID id);

    List<StudyPlanStatus> getAll();

    Optional<StudyPlanStatus> getById(UUID userId);
    
    public JSONObject getFields();
}