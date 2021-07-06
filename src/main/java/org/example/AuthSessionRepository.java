package org.example;


import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface AuthSessionRepository extends JpaRepository<AuthSessionEntity, String> {

//    @Modifying
//    @Query
    long deleteByEmail(String email);
}

