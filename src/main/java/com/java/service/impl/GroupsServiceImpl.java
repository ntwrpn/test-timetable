package com.java.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Groups;
import com.java.repository.GroupsRepository;

import java.lang.reflect.Field;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.service.GroupsService;

@Service
public class GroupsServiceImpl implements GroupsService {

    @Override
    public Groups save(Groups obj) {
        return GroupsRepository.save(obj);
    }

    @Override
    public Groups update(Groups obj) {
        return GroupsRepository.save(obj);
    }

    
    @Override
    public void delete(UUID id) {
        GroupsRepository.deleteById(id);
    }

    @Autowired
    private GroupsRepository GroupsRepository;

    @Override
    public List<Groups> getAll(){
        return GroupsRepository.findAll();
    }

    @Override
    public Optional<Groups> getById(UUID id) {
        return GroupsRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Groups.class.getDeclaredFields()) {
            obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
        }
        return obj;
    }
}

