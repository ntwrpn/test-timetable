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
public interface UsersRepository extends JpaRepository<Users,UUID> {

    Optional<Users> findById(UUID uuid);

    void deleteById(UUID uuid);

    Optional<Users> findByUsername(String login);
    
    List<Users> findByEnabledTrue();
    
    List<Users> findByEnabledFalse();
    
    @Modifying
    @Transactional
    @Query( value = "select * from users where deanery=?1", nativeQuery = true)
    List<Users> findByDeanery(UUID id);

    @Modifying
    @Transactional
    @Query( value = "select * from users where lectern=?1", nativeQuery = true)
    List<Users> findByLectern(UUID id);

    @Modifying
    @Transactional
    @Query( value = "select * from users as t1 join deanery as t2 on t2.name=?1 where t1.deanery=t2.id;", nativeQuery = true)
    List<Users> getByDeanery(String deanery);

    @Modifying
    @Transactional
    @Query( value = "select * from users as t1 join lectern as t2 on t2.name=?1 where t1.lectern=t2.id;", nativeQuery = true)
    List<Users> getByLectern(String lectern);


    @Modifying
    @Transactional
    @Query( value = "UPDATE Users SET enabled=false WHERE id = ?1", nativeQuery = true)
    void blockUser(UUID id);

    @Modifying
    @Transactional
    @Query( value = "UPDATE Users SET enabled=true WHERE id = ?1", nativeQuery = true)
    void unBlockUser(UUID id);
}