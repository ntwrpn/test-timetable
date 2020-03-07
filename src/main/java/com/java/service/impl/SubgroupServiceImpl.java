package com.java.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Subgroup;
import com.java.repository.SubgroupRepository;

import java.lang.reflect.Field;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.SubgroupService;

@Service
public class SubgroupServiceImpl implements SubgroupService {

    @Autowired
    private SubgroupRepository subgroupRepository;

    @Override
    public Subgroup save(Subgroup obj) {
        return subgroupRepository.save(obj);
    }

    @Override
    public Subgroup update(Subgroup obj) {
        return subgroupRepository.save(obj);
    }

    @Override
    public void delete(UUID id) {
        subgroupRepository.deleteById(id);
    }

    @Override
    public List<Subgroup> getAll() {
        return subgroupRepository.findAll();
    }

    @Override
    public Optional<Subgroup> getById(UUID id) {
        return subgroupRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Subgroup.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

