package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Speciality;
import com.java.repository.SpecialityRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.SpecialityService;

@Service
public class SpecialityServiceImpl implements SpecialityService {

    @Override
    public Speciality save(Speciality obj) {
        return SpecialityRepository.save(obj);
    }

    @Override
    public Speciality update(Speciality obj) {
        return SpecialityRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        SpecialityRepository.delete(id);
    }

    @Autowired
    private SpecialityRepository SpecialityRepository;

    @Override
    public List<Speciality> getAll(){
        return SpecialityRepository.findAll();
    }

    @Override
    public Optional<Speciality> getById(UUID id) {
        return SpecialityRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Speciality.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

