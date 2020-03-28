package com.java.repository;

import com.java.domain.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<UserToken,UUID> {
    Optional<UserToken> findByToken(String token);
    void deleteByToken(String token);
}
