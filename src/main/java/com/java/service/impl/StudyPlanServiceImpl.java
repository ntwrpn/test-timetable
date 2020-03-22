package com.java.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.StudyPlan;
import com.java.repository.StudyPlanRepository;

import java.lang.reflect.Field;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.StudyPlanService;

@Service
public class StudyPlanServiceImpl implements StudyPlanService {

    @Autowired
    private StudyPlanRepository studyPlanRepository;

    @Override
    public StudyPlan save(StudyPlan obj) {
        return studyPlanRepository.save(obj);
    }

    @Override
    public StudyPlan update(StudyPlan obj) {
        return studyPlanRepository.save(obj);
    }

    @Override
    public void delete(UUID id) {
        studyPlanRepository.deleteById(id);
    }

    @Override
    public List<StudyPlan> getAll() {
        return studyPlanRepository.findAll();
    }

    @Override
    public Optional<StudyPlan> getById(UUID id) {
        return studyPlanRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : StudyPlan.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
	
	@Override
	public List<StudyPlan> findStudyplansByLecternId(UUID id){
		return studyPlanRepository.findStudyplansByLecternId(id);
	}
}

