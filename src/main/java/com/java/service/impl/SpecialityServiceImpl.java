package com.java.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Speciality;
import com.java.repository.LecternRepository;
import com.java.repository.SpecialityRepository;

import java.lang.reflect.Field;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.SpecialityService;

@Service
public class SpecialityServiceImpl implements SpecialityService {

    @Autowired
    private SpecialityRepository specialityRepository;

    @Autowired
    private LecternRepository lecternRepository;

    @Override
    public Speciality save(Speciality obj) {
        return specialityRepository.save(obj);
    }

    @Override
    public Speciality save(Speciality obj, UUID lecternId) {
        obj.setLectern(lecternRepository.findById(lecternId).get());
        return specialityRepository.save(obj);
    }

    @Override
    public Speciality update(Speciality obj) {
        Optional<Speciality> speciality = specialityRepository.findById(obj.getId());
        if(speciality.isPresent()){
            speciality.get().setId(obj.getId());
            speciality.get().setDescription(obj.getDescription());
            speciality.get().setName(obj.getName());
            return specialityRepository.save(speciality.get());
        }
        return null;
    }

    @Override
    public void delete(UUID id) {
        specialityRepository.deleteById(id);
    }

    @Override
    public List<Speciality> getAll() {
        return specialityRepository.findAll();
    }

    @Override
    public Optional<Speciality> getById(UUID id) {
        return specialityRepository.findById(id);
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

