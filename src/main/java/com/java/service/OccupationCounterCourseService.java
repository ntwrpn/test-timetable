package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.OccupationCounterCourse;
import org.json.simple.JSONObject;

public interface OccupationCounterCourseService {

    OccupationCounterCourse save(OccupationCounterCourse OccupationCounterCourse);

    OccupationCounterCourse update(OccupationCounterCourse OccupationCounterCourse);

    void delete(UUID id);

    List<OccupationCounterCourse> getAll();

    Optional<OccupationCounterCourse> getById(UUID userId);
    
    public JSONObject getFields();
}