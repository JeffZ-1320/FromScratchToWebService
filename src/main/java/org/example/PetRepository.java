package org.example;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PetRepository extends JpaRepository<PetsEntity, Integer> {

}
