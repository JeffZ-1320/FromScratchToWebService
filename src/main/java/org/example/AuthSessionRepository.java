package org.example;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AuthSessionRepository extends JpaRepository<AuthSessionEntity, String> {
    long deleteByEmail(String email);
}

