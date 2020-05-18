package com.java.service.impl;


import java.util.*;

import com.java.domain.Semester;
import com.java.domain.StudyPlan;
import com.java.domain.Subject;
import com.java.domain.WeekCount;
import com.java.repository.SpecialityRepository;
import com.java.repository.StudyPlanRepository;


import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;

import java.io.IOException;
import com.java.service.StudyPlanService;

import javax.validation.*;

@Service
public class StudyPlanServiceImpl implements StudyPlanService {

    @Autowired
    private StudyPlanRepository studyPlanRepository;

    @Autowired
    private SpecialityRepository specialityRepository;

    @Override
    public StudyPlan save(StudyPlan obj) {
        return saveOrUpdateStudyPlan(obj);
    }

    @Override
    public StudyPlan update(StudyPlan obj) {
        return saveOrUpdateStudyPlan(obj);
    }

    private StudyPlan saveOrUpdateStudyPlan(StudyPlan obj){
        obj.setSpeciality(specialityRepository.findById(obj.getSpeciality().getId()).get());
        obj = validateStudyPlan(obj);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<StudyPlan>> violations = validator.validate(obj);
        if (violations.size() != 0) {
            throw new ConstraintViolationException(violations);
        }
        return studyPlanRepository.save(obj);
    }

    private StudyPlan validateStudyPlan(StudyPlan studyPlan){
        if(studyPlan.getWeeks().size() < studyPlan.getCountOfSem()){
            for(int i =studyPlan.getWeeks().size(); i < studyPlan.getCountOfSem(); i++ ){
                WeekCount weekCount = new WeekCount();
                weekCount.setPosition(i+1);
                weekCount.setCount(15);
                weekCount.setStudyPlan(studyPlan);
                studyPlan.getWeeks().add(weekCount);
            }
        } else if (studyPlan.getWeeks().size() > studyPlan.getCountOfSem()){
            List<WeekCount> list = new ArrayList<>();
            for(int i = 0; i < studyPlan.getCountOfSem(); i++ ){
                list.add(studyPlan.getWeeks().get(i));
            }
            studyPlan.setWeeks(list);
        }
        for(Subject subject: studyPlan.getSubjects()){
            if(subject.getSemesters().size() < studyPlan.getCountOfSem()){
                for(int i =subject.getSemesters().size(); i < studyPlan.getCountOfSem(); i++ ){
                    Semester semester = new Semester();
                    semester.setNumber(i+1);
                    semester.setHoursPerWeek(0);
                    semester.setCreditUnits(0);
                    semester.setSubject(subject);
                    subject.getSemesters().add(semester);
                }
            } else if (subject.getSemesters().size() > studyPlan.getCountOfSem()) {
                List<Semester> list = new ArrayList<>();
                for(int i = 0; i < studyPlan.getCountOfSem(); i++ ){
                    list.add(subject.getSemesters().get(i));
                }
                subject.setSemesters(list);
            }
        }
        return studyPlan;
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
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try {
            mapper.acceptJsonFormatVisitor(StudyPlan.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx) {
            return null;
        }
    }

    @Override
    public List<StudyPlan> findStudyplansByLecternId(UUID id) {
        return studyPlanRepository.findStudyplansByLecternId(id);
    }

    @Override
    public List<StudyPlan> findStudyplansBySpecialityId(UUID id) {
        return studyPlanRepository.findStudyplansBySpecialityId(id);
    }
}

