package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.java.domain.Lectern;
import com.java.domain.Subject;
import com.java.repository.LecternRepository;
import com.java.repository.DeaneryRepository;

import java.lang.reflect.Field;

import com.java.repository.SubjectRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import java.io.IOException;
import com.java.service.LecternService;
import javax.validation.ConstraintViolationException;

@Service
public class LecternServiceImpl implements LecternService {

    @Autowired
    private LecternRepository lecternRepository;
	
	@Autowired
    private DeaneryRepository deaneryRepository;

    @Autowired
    private SubjectRepository subjectRepository;
	
    @Override
    public Lectern save(Lectern obj, UUID id) throws Exception {
        if(id !=null){
		    obj.setDeanery(deaneryRepository.findById(id).get());
        }
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Lectern>> violations = validator.validate(obj);
        if(violations.size()!=0){
            throw  new ConstraintViolationException(violations);
        }
        return lecternRepository.save(obj);
    }

    @Override
    public Lectern update(Lectern obj) {
		//obj.setDeanery(deaneryRepository.findById(obj.getDeanery().getId()).get());
		Optional<Lectern> lectern = lecternRepository.findById(obj.getId());
        if(lectern.isPresent()){
            if(!lectern.get().getName().equals(obj.getName())){
                updateDepartmentOfSubjects(lectern.get().getName(), obj.getName());
            }
            lectern.get().setId(obj.getId());
            lectern.get().setFullname(obj.getFullname());
            lectern.get().setName(obj.getName());
            lectern.get().setDescription(obj.getDescription());
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Lectern>> violations = validator.validate(lectern.get());
            if(violations.size()!=0){
                throw  new ConstraintViolationException(violations);
            }
            return lecternRepository.save(lectern.get());
        }
        return null;
    }

    
    @Override
    public void delete(UUID id) {
        deleteTemplate(lecternRepository.findById(id).get().getName());
        lecternRepository.deleteById(id);
    }

    private void deleteTemplate(String name) {
        for(Subject subject: subjectRepository.findAllByDepartmentAndTemplate(name,true)){
            subjectRepository.delete(subject);
        }
    }

    private void updateDepartmentOfSubjects(String nameOld, String nameNew){
        for(Subject subject: subjectRepository.findAllByDepartment(nameOld)){
            subject.setDepartment(nameNew);
            subjectRepository.save(subject);
        }
    }

    @Override
    public List<Lectern> getAll() {
        return lecternRepository.findAll();
    }

    @Override
    public Optional<Lectern> getById(UUID id) {
        return lecternRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try{
            mapper.acceptJsonFormatVisitor(Lectern.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx){
            return null;
        } 
    }
	
	@Override
	public List<Lectern> findLecternsByDeaneryId(UUID id){

        return lecternRepository.findByDeaneryId(id);
	}

    @Override
    public List<Lectern> findByName(String name) {
        return lecternRepository.findByName(name);
    }

    @Override
    public List<Lectern> findByFullname(String fullname) {
        return lecternRepository.findByFullname(fullname);
    }

    @Override
    public Optional<Lectern> findByGroupsId(UUID uuid) {
        return lecternRepository.findBySpecialitiesGroupsId(uuid);
    }
}

