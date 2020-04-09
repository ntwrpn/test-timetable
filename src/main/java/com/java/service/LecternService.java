package com.java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.java.domain.Lectern;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public interface LecternService {

    Lectern save(Lectern Lectern, UUID id) throws Exception;

    Lectern update(Lectern Lectern);

    void delete(UUID id);

    List<Lectern> getAll();

    Optional<Lectern> getById(UUID userId);
    
    public JsonSchema getFields();

	List<Lectern> findLecternsByDeaneryId(UUID id);

    List<Lectern> findByName(String name);

    List<Lectern> findByFullname(String fullname);
}