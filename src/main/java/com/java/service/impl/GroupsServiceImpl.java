package com.java.service.impl;

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

    @Autowired
    private GroupsRepository groupsRepository;

    @Override
    public Groups save(Groups obj) {
        return groupsRepository.save(obj);
    }

    @Override
    public Groups update(Groups obj) {
        return groupsRepository.save(obj);
    }

    @Override
    public void delete(UUID id) {
        groupsRepository.deleteById(id);
    }

    @Override
    public List<Groups> getAll() {
        return groupsRepository.findAll();
    }

    @Override
    public Optional<Groups> getById(UUID id) {
        return groupsRepository.findById(id);
    }

    @Override
    public JSONObject getFields() {
        JSONObject obj = new JSONObject();
        for (Field field : Groups.class.getDeclaredFields()) {
            if (field.getName()!="flow" && field.getName()!="subgroup"){
                obj.put(field.getName(), field.getType().getSimpleName().toLowerCase());
            }
        }
        return obj;
    }
}

