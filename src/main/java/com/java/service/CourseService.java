package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Course;
import org.json.simple.JSONObject;

public interface CourseService {

    Course save(Course Course);

    Course update(Course Course);

    void delete(UUID id);

    List<Course> getAll();

    Optional<Course> getById(UUID userId);
    
    public JSONObject getFields();
}