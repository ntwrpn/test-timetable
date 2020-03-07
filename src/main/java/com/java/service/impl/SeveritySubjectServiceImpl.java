package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.SeveritySubject;
import com.java.repository.SeveritySubjectRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.SeveritySubjectService;

@Service
public class SeveritySubjectServiceImpl implements SeveritySubjectService {

    @Override
    public SeveritySubject save(SeveritySubject obj) {
        return SeveritySubjectRepository.save(obj);
    }

    @Override
    public SeveritySubject update(SeveritySubject obj) {
        return SeveritySubjectRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        SeveritySubjectRepository.delete(id);
    }

    @Autowired
    private SeveritySubjectRepository SeveritySubjectRepository;

    @Override
    public List<SeveritySubject> getAll(){
        return SeveritySubjectRepository.findAll();
    }

    @Override
    public Optional<SeveritySubject> getById(UUID id) {
        return SeveritySubjectRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : SeveritySubject.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

