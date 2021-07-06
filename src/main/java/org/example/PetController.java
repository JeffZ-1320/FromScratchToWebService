package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/secure/pet")
public class PetController {
    @Autowired
    private PetRepository petRepository;


    @GetMapping("/allPets")
    public List<PetEntity> getAllPets(){
        List<PetEntity> allPets = petRepository.findAll();
        return allPets;

    }

    @GetMapping("/getPetById/{id}")
    public PetEntity getPetById(@PathVariable int id){
        // haven't defined an exception
        return petRepository.findById(id).orElse(null);
    }

    @PostMapping("/newPet")
    public PetEntity addPet(@Valid @RequestBody PetEntity pet){
//        ZoneId zoneUTC = ZoneId.of("UTC");
//        ZonedDateTime petBrithdate = pet.getBirthdate();
//        ZonedDateTime UTCTime = nowMontréal.withZoneSameInstant( ZoneOffset.UTC );
        return petRepository.save(pet);
    }

    @PutMapping("/updatePet/{id}")
    public PetEntity updatePetRecord(@PathVariable int id, @NotNull @Valid @RequestBody PetEntity newPet){
//        PetsEntity petEntity = petRepository.findById(id).orElse(null);
//        petEntity.setUserId(newPet.getUserId());
//        petEntity.setPetName(newPet.getPetName());
//        System.out.println(newPet.getBirthdate());
//        petEntity.setBirthdate(newPet.getBirthdate());
//        petEntity.setColor(newPet.getColor());
//        petEntity.setBreedType(newPet.getBreedType());
//        petEntity.setSex(newPet.getSex());
        return petRepository.save(newPet);
    }

    @DeleteMapping("/deletePet/{id}")
    public int deletePet(@PathVariable int id){
        petRepository.deleteById(id);
        return 1;
    }
}
