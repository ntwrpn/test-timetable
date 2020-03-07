package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

    @Override
    public Subgroup save(Subgroup obj) {
        return SubgroupRepository.save(obj);
    }

    @Override
    public Subgroup update(Subgroup obj) {
        return SubgroupRepository.save(obj);
    }

    @Override
    public void delete(UUID id) {
        SubgroupRepository.deleteById(id);
    }

    @Autowired
    private SubgroupRepository SubgroupRepository;

    @Override
    public List<Subgroup> getAll() {
        return SubgroupRepository.findAll();
    }

    @Override
    public Optional<Subgroup> getById(UUID id) {
        return SubgroupRepository.findById(id);
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

