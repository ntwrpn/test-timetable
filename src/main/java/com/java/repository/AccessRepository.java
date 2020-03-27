package com.java.repository;

import com.java.domain.*;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccessRepository extends JpaRepository<Access,UUID> {

    Optional<Access> findById(UUID Ids);
    
    Optional<Access> findByPath(String path);
    
    @Modifying
    @Transactional
    @Query( value = "select * from access as t1 INNER JOIN user_roles as T3 ON T3.role=?1 INNER JOIN roles_access as T2 ON T2.role_id=T3.id where t1.id=t2.access_id;", nativeQuery = true)
    List<Access> getByUserRoles(String path);

    void deleteById(UUID uuid);
    
}