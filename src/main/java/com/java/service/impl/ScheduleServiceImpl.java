package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.*;
import java.util.*;

import com.java.domain.*;
import com.java.repository.OccupationRepository;
import com.java.repository.ScheduleRepository;

import java.lang.reflect.Field;

import org.hibernate.validator.constraints.URL;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.ScheduleService;
import com.java.repository.StudyPlanRepository;


@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;
	
	@Autowired
    private StudyPlanRepository studyPlanRepository;

    @Autowired
    private OccupationRepository occupationRepository;

    @Override
    public Schedule save(Schedule obj, UUID id) {
		if(id != null){
			obj.setStudyPlan(studyPlanRepository.findById(id).get());
		}
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Schedule>> violations = validator.validate(obj);
        if(violations.size()!=0){
            throw  new ConstraintViolationException(violations);
        }
        return scheduleRepository.save(obj);
    }

    @Override
    public Schedule update(Schedule obj) {
		Optional<Schedule> schedule = scheduleRepository.findById(obj.getId());
        if(schedule.isPresent()){
            obj.setStudyPlan(schedule.get().getStudyPlan());
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Schedule>> violations = validator.validate(obj);
            if(violations.size()!=0){
                throw  new ConstraintViolationException(violations);
            }
            return scheduleRepository.save(obj);
        }
        return null;
    }

    
    @Override
    public void delete(UUID id) {
        scheduleRepository.deleteById(id);
    }


    @Override
    public List<Schedule> getAll() {
        return  sortOccupationCounters(scheduleRepository.findAll());
    }

    @Override
    public Optional<Schedule> getById(UUID id) {
        List<Schedule> schedules = new ArrayList<>();
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        schedules.add(schedule.get());
        schedule = Optional.of(sortOccupationCounters(schedules).get(0));
        return schedule ;
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(Schedule.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }

    @Override
    public List<Schedule> findByStudyPlan(UUID id) {
       return sortOccupationCounters(scheduleRepository.findByStudyPlanId(id));

    }

    private List<Schedule> sortOccupationCounters(List<Schedule> schedules){
        List<Occupation> occupations = occupationRepository.findAll();
        for(Schedule schedule: schedules){
            List<OccupationCounter> occupationCounters = new ArrayList<>();
            for(Occupation occupation: occupations){
                OccupationCounter occupationCounter = findOccupationCounerByOccupation(occupation, schedule.getCountOccupation());
                if(occupationCounter != null){
                    occupationCounters.add(occupationCounter);
                }
            }
            schedule.setCountOccupation(occupationCounters);
            for(Course course: schedule.getCourses()){
                List<OccupationCounterCourse> occupationCounterCourses = new ArrayList<>();
                for(Occupation occupation: occupations){
                    OccupationCounterCourse occupationCounterCourse = findOccupationCounterCourseByOccupation(occupation, course.getCountOccupation());
                    if(occupationCounterCourse != null){
                        occupationCounterCourses.add(occupationCounterCourse);
                    }
                }
                course.setCountOccupation(occupationCounterCourses);
            }
        }
        return schedules;
    }

    private OccupationCounter findOccupationCounerByOccupation(Occupation occupation, List<OccupationCounter> list){
        for(OccupationCounter occupationCounter: list){
            if(occupationCounter.getOccupation().getId().equals(occupation.getId())){
                return occupationCounter;
            }
        }
        return null;
    }

    private OccupationCounterCourse findOccupationCounterCourseByOccupation(Occupation occupation, List<OccupationCounterCourse> list){
        for(OccupationCounterCourse occupationCounter: list){
            if(occupationCounter.getOccupation().getId().equals(occupation.getId())){
                return occupationCounter;
            }
        }
        return null;
    }
}

