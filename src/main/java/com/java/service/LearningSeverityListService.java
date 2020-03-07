package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.LearningSeverityList;
import org.json.simple.JSONObject;

public interface LearningSeverityListService {

    LearningSeverityList save(LearningSeverityList LearningSeverityList);

    LearningSeverityList update(LearningSeverityList LearningSeverityList);

    void delete(UUID id);

    List<LearningSeverityList> getAll();

    Optional<LearningSeverityList> getById(UUID userId);
    
    public JSONObject getFields();
}