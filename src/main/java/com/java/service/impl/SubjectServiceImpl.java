package com.java.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Lectern;
import com.java.domain.Subject;
import com.java.repository.LecternRepository;
import com.java.repository.SubjectRepository;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;

import java.io.IOException;

import com.java.service.SubjectService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private LecternRepository lecternRepository;

    @Override
    public Subject save(Subject obj) {
        return subjectRepository.save(obj);
    }

    @Override
    @Transactional
    public Subject update(Subject obj) {
        Optional<Subject> subject = subjectRepository.findById(obj.getId());
        if (subject.isPresent()) {
            obj.setStudyPlan(subject.get().getStudyPlan());
            if (obj.getSemesters() == null) {
                obj.setSemesters(subject.get().getSemesters());
            }
            if (obj.getPereodicSeverities() == null) {
                obj.setSemesters(subject.get().getSemesters());
            }
            if (obj.getSeverities() == null) {
                obj.setSemesters(subject.get().getSemesters());
            }
            return subjectRepository.save(obj);
        }
        return null;
    }


    @Override
    public void delete(UUID id) {
        subjectRepository.deleteById(id);
    }


    @Override
    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    @Override
    public List<Subject> getSubjects(UUID lecternId) {
        if(lecternId != null){
            Optional<Lectern> lectern = lecternRepository.findById(lecternId);
            return lectern.isPresent() ? subjectRepository.findAllByDepartmentAndTemplate(lectern.get().getName(), true) : subjectRepository.findAll();
        } else {
            return subjectRepository.findAll();
        }
    }

    @Override
    public Optional<Subject> getById(UUID id) {
        return subjectRepository.findById(id);
    }

    @Override
    public JsonSchema getFields() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        try {
            mapper.acceptJsonFormatVisitor(Subject.class, visitor);
            JsonSchema schema = visitor.finalSchema();
            return schema;
        } catch (IOException exx) {
            return null;
        }
    }
}

