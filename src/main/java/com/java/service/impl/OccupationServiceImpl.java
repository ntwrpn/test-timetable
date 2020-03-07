package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Occupation;
import com.java.repository.OccupationRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.OccupationService;

@Service
public class OccupationServiceImpl implements OccupationService {

    @Override
    public Occupation save(Occupation obj) {
        return OccupationRepository.save(obj);
    }

    @Override
    public Occupation update(Occupation obj) {
        return OccupationRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        OccupationRepository.delete(id);
    }

    @Autowired
    private OccupationRepository OccupationRepository;

    @Override
    public List<Occupation> getAll(){
        return OccupationRepository.findAll();
    }

    @Override
    public Optional<Occupation> getById(UUID id) {
        return OccupationRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Occupation.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

