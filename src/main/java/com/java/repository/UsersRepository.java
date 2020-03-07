package com.java.repository;

import com.java.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users,UUID> {

    Optional<Users> findById(UUID Ids);

    void delete(UUID uuid);

    Optional<Users> findByName(String login);

    

    @Modifying
    @Transactional
    @Query( value = "UPDATE Users SET enable=false WHERE id = ?1", nativeQuery = true)
    void blockUser(UUID id);

    @Modifying
    @Transactional
    @Query( value = "UPDATE Users SET enable=true WHERE id = ?1", nativeQuery = true)
    void unBlockUser(UUID id);
}