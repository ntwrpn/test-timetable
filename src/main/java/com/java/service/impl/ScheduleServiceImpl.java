package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Schedule;
import com.java.repository.ScheduleRepository;

import java.lang.reflect.Field;
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

    @Override
    public Schedule save(Schedule obj, UUID id) {
		if(id != null){
			obj.setStudyPlan(studyPlanRepository.findById(id).get());
		}
        return scheduleRepository.save(obj);
    }

    @Override
    public Schedule update(Schedule obj) {
		Optional<Schedule> schedule = scheduleRepository.findById(obj.getId());
        if(schedule.isPresent()){
            obj.setStudyPlan(schedule.get().getStudyPlan());
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
        return scheduleRepository.findAll();
    }

    @Override
    public Optional<Schedule> getById(UUID id) {
        return scheduleRepository.findById(id);
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
        return scheduleRepository.findByStudyPlanId(id);
    }
}

