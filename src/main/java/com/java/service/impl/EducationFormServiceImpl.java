package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.EducationForm;
import com.java.repository.EducationFormRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.EducationFormService;

@Service
public class EducationFormServiceImpl implements EducationFormService {

    @Override
    public EducationForm save(EducationForm obj) {
        return EducationFormRepository.save(obj);
    }

    @Override
    public EducationForm update(EducationForm obj) {
        return EducationFormRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        EducationFormRepository.delete(id);
    }

    @Autowired
    private EducationFormRepository EducationFormRepository;

    @Override
    public List<EducationForm> getAll(){
        return EducationFormRepository.findAll();
    }

    @Override
    public Optional<EducationForm> getById(UUID id) {
        return EducationFormRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : EducationForm.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

